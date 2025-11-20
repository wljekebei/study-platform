package poch.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class NotificationController {


        @MessageMapping("/test")

        @SendTo("/topic/test")
        public String testMessage(String msg) {
            return "Server received: " + msg;
        }
    }


