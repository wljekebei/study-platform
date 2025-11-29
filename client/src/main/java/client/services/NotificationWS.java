package client.services;

import client.dto.Notification;
import javafx.application.Platform;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class NotificationWS {

    private static WebSocketStompClient stompClient; // connect
    private static StompSession session;

    private static final Map<Long, StompSession.Subscription> subscriptions = new ConcurrentHashMap<>(); // bc multithread

    private static final Set<Long> subscribedGroups = ConcurrentHashMap.newKeySet();

    private static Consumer<Notification> globalHandler;

    private static final String URL = "ws://localhost:8080/ws-native";

    public static synchronized void init(Consumer<Notification> handler) {
        globalHandler = handler;

        if (stompClient == null) {
            stompClient = new WebSocketStompClient(new StandardWebSocketClient());
            stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        }

        if (session == null || !session.isConnected()) {
            stompClient.connect(URL, new StompSessionHandlerAdapter() {
                @Override
                public void afterConnected(StompSession sess, StompHeaders connectedHeaders) {
                    session = sess;
                    resubscribeAll();
                }

                @Override
                public void handleTransportError(StompSession session, Throwable exception) {
                    exception.printStackTrace();
                }
            });
        } else {
            resubscribeAll();
        }
    }

    public static synchronized void subscribeGroups(Collection<Long> groupIds) {
        subscribedGroups.clear();
        subscribedGroups.addAll(groupIds);
        resubscribeAll();
    }

    public static synchronized void subscribeGroup(Long groupId) {
        subscribedGroups.add(groupId);
        resubscribeAll();
    }

    public static synchronized void unsubscribeGroup(Long groupId) {
        subscribedGroups.remove(groupId);

        StompSession.Subscription sub = subscriptions.remove(groupId);
        if (sub != null) {
            sub.unsubscribe();
        }
    }

    // main method to keep subscribed to all needed groups
    private static void resubscribeAll() {
        if (session == null || !session.isConnected() || globalHandler == null) {
            return;
        }

        for (Long groupId : new HashSet<>(subscriptions.keySet())) {
            if (!subscribedGroups.contains(groupId)) {
                StompSession.Subscription sub = subscriptions.remove(groupId);
                if (sub != null) {
                    sub.unsubscribe();
                }
            }
        }

        for (Long groupId : subscribedGroups) {
            if (!subscriptions.containsKey(groupId)) {
                subscribeGroupInternal(groupId, globalHandler);
            }
        }
    }

    // chatgpt
    private static void subscribeGroupInternal(Long groupId, Consumer<Notification> handler) {
        if (session == null || !session.isConnected()) return;

        StompSession.Subscription sub = session.subscribe("/topic/group/" + groupId, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Notification.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                Notification n = (Notification) payload;
                Platform.runLater(() -> handler.accept(n));
            }
        });

        subscriptions.put(groupId, sub);
    }
}
