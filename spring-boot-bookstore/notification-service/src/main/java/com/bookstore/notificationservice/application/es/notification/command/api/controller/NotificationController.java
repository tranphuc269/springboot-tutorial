package com.bookstore.notificationservice.application.es.notification.command.api.controller;

import com.bookstore.notificationservice.domain.service.ISendNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/notification")
@RestController
public class NotificationController {

    @Autowired
    private ISendNotificationService notificationService;

    @PostMapping("/email")
    public void sendEmail() {
        notificationService.sendGmail();
    }
}