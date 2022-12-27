package com.bookstore.notificationservice.application.es.notification_user.command.model;

import com.bookstore.notificationservice.application.es.notification_user.command.model.entity.NotificationUserCommandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationUserCommandRepository
        extends JpaRepository<NotificationUserCommandEntity, String> {
}
