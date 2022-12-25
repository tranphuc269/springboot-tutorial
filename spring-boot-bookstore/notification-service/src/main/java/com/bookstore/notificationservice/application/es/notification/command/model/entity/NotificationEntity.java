package com.bookstore.notificationservice.application.es.notification.command.model.entity;

import com.bookstore.common.infrastructure.repo.model.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class NotificationEntity extends BaseModel {
    @Id
    @GeneratedValue(generator = "notification-uuid")
    @GenericGenerator(name = "notification-uuid", strategy = "uuid")
    @Column(name = "notification_id", updatable = false, nullable = false)
    private String notification_id;

    @Column(name = "user_id", updatable = false, nullable = false)
    private String userId;

    @Column(name = "source_id", updatable = false, nullable = false)
    private String sourceId;
    @Column(name = "source_type", updatable = false, nullable = false)
    private String sourceType; // Nếu source type = ORDER chẳng hạn -> source id sẽ là order id;
    @Column(name = "type", updatable = false, nullable = false)
    private String type;
    @Column(name = "read", updatable = false, nullable = false)
    private int read;
    @Column(name = "trash", updatable = false, nullable = false)
    private int trash;
    @Column(name = "content", updatable = false, nullable = false)
    private String content;
}
