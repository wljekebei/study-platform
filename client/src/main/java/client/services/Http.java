package client.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T post(String url, Object body, Class<T> responseType) throws Exception {

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        // JSON тела
        byte[] json = mapper.writeValueAsBytes(body);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json);
        }

        int code = conn.getResponseCode();
        InputStream is = code == 200 ? conn.getInputStream() : conn.getErrorStream();

        return mapper.readValue(is, responseType);
    }
}
