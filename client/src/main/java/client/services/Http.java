package client.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {

    // POST - create
    // PUT - update

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();


    public static <T> T post(String url, Object body, Class<T> responseType) throws Exception {
        String requestBody = mapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        int status = response.statusCode();

        if (status < 200 || status >= 300) {
            // ВАЖНО: вот тут ты как раз увидишь HTML-страницу ошибки
            throw new RuntimeException("POST " + url + " failed with code " + status + "\n" + response.body());
        }

        if (responseType == Void.class) {
            return null;
        }

        return mapper.readValue(response.body(), responseType);
    }



    public static <T> T get(String url, Class<T> responseType) throws Exception {

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        int code = conn.getResponseCode();
        InputStream is = code == 200 ? conn.getInputStream() : conn.getErrorStream();

        return mapper.readValue(is, responseType);
    }

    public static <T> T put(String url, Object body, Class<T> responseType) throws Exception {

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod("PUT");
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

    public static <T> T getList(String url, com.fasterxml.jackson.core.type.TypeReference<T> typeRef) throws Exception {

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        int code = conn.getResponseCode();
        InputStream is = code == 200 ? conn.getInputStream() : conn.getErrorStream();

        if (code != 200) {
            String body = new String(is.readAllBytes());
            throw new RuntimeException("GET " + url + " failed with code " + code + "\n" + body);
        }

        return mapper.readValue(is, typeRef);
    }


    public static void delete(String url) throws Exception {

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod("DELETE");
        conn.setDoOutput(false);

        int code = conn.getResponseCode();

        if (code != 200 && code != 204) {
            InputStream err = conn.getErrorStream();
            if (err != null) {
                throw new RuntimeException(new String(err.readAllBytes()));
            } else {
                throw new RuntimeException("DELETE failed with code " + code);
            }
        }
    }

    public static void postVoid(String url, Object body) throws Exception {
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");

        if (body != null) {
            byte[] json = mapper.writeValueAsBytes(body);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json);
            }
        }

        int code = conn.getResponseCode();

        if (code != 200 && code != 204) {
            InputStream err = conn.getErrorStream();
            if (err != null) {
                throw new RuntimeException(new String(err.readAllBytes()));
            } else {
                throw new RuntimeException("POST failed with code " + code);
            }
        }
    }

    // ChatGPT
    public static <T> T uploadFile(String url, Long groupId, Long uploadedBy, String title, File file) throws Exception {

        String boundary = "----Boundary" + System.currentTimeMillis();

        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        try (OutputStream os = conn.getOutputStream()) {

            String part1 = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"groupId\"\r\n\r\n"
                    + groupId + "\r\n";
            os.write(part1.getBytes());

            String part2 = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"uploadedBy\"\r\n\r\n"
                    + uploadedBy + "\r\n";
            os.write(part2.getBytes());

            String part3 = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"title\"\r\n\r\n"
                    + title + "\r\n";
            os.write(part3.getBytes());

            String header = "--" + boundary + "\r\n"
                    + "Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\n"
                    + "Content-Type: application/octet-stream\r\n\r\n";
            os.write(header.getBytes());

            try (InputStream fis = new java.io.FileInputStream(file)) {
                fis.transferTo(os);
            }

            os.write("\r\n".getBytes());
            os.write(("--" + boundary + "--\r\n").getBytes());
        }

        int code = conn.getResponseCode();

        InputStream is = code == 200 ? conn.getInputStream() : conn.getErrorStream();

        if (code != 200) {
            String body = "";
            if (is != null) {
                body = new String(is.readAllBytes());
            }
            throw new RuntimeException("UPLOAD " + url + " failed with code " + code + "\n" + body);
        }

        if (is == null) {
            throw new RuntimeException("UPLOAD " + url + " returned empty body");
        }

        return mapper.readValue(is, (Class<T>) client.models.Resource.class);
    }



}
