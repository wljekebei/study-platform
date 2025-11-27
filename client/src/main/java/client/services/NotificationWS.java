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

    private static WebSocketStompClient stompClient;
    private static StompSession session;

    // подписки по groupId
    private static final Map<Long, StompSession.Subscription> subscriptions = new ConcurrentHashMap<>();

    // на какие группы мы вообще хотим быть подписаны (декларативно)
    private static final Set<Long> desiredGroups = ConcurrentHashMap.newKeySet();

    // один глобальный обработчик (показывает тосты)
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
                    // как только подключились – подписываемся на все желаемые группы
                    resubscribeAll();
                }

                @Override
                public void handleTransportError(StompSession session, Throwable exception) {
                    exception.printStackTrace();
                }
            });
        } else {
            // если уже подключены – просто пересобираем подписки
            resubscribeAll();
        }
    }

    // установить полный список групп (после логина / обновления списка)
    public static synchronized void subscribeGroups(Collection<Long> groupIds) {
        desiredGroups.clear();
        desiredGroups.addAll(groupIds);
        resubscribeAll();
    }

    // добавить одну группу (после успешного join)
    public static synchronized void subscribeGroup(Long groupId) {
        desiredGroups.add(groupId);
        resubscribeAll();
    }

    // отписаться от одной группы (leave/delete)
    public static synchronized void unsubscribeGroup(Long groupId) {
        desiredGroups.remove(groupId);

        StompSession.Subscription sub = subscriptions.remove(groupId);
        if (sub != null) {
            sub.unsubscribe();
        }
    }

    // полностью всё выключить (logout)
    public static synchronized void disconnectAll() {
        for (StompSession.Subscription sub : subscriptions.values()) {
            sub.unsubscribe();
        }
        subscriptions.clear();
        desiredGroups.clear();

        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        session = null;
    }

    // ====== внутренние помощники ======

    private static void resubscribeAll() {
        if (session == null || !session.isConnected() || globalHandler == null) {
            return;
        }

        // отписываемся от лишних групп
        for (Long groupId : new HashSet<>(subscriptions.keySet())) {
            if (!desiredGroups.contains(groupId)) {
                StompSession.Subscription sub = subscriptions.remove(groupId);
                if (sub != null) {
                    sub.unsubscribe();
                }
            }
        }

        // подписываемся на недостающие
        for (Long groupId : desiredGroups) {
            if (!subscriptions.containsKey(groupId)) {
                subscribeGroupInternal(groupId, globalHandler);
            }
        }
    }

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
