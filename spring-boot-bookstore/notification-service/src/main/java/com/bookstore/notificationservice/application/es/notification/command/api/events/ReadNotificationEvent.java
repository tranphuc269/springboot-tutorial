package com.bookstore.notificationservice.application.es.notification.command.api.events;

import com.bookstore.common.infrastructure.axon.BaseEvent;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReadNotificationEvent extends BaseEvent<String> {
    private String notificationId;
    private String userId;
    private String sourceId;
    private String sourceType;
    private int read;
    private int trash;
    private String content;

    public ReadNotificationEvent(String id,
                                 String notificationId,
                                 String userId,
                                 String sourceId,
                                 String sourceType,
                                 String content) {
        super(id);
        this.notificationId = notificationId;
        this.userId = userId;
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.read = 1;
        this.trash = 0;
        this.content = content;
    }
}
