package com.bookstore.notificationservice.application.es.notification.command.api.commands;

import com.bookstore.common.infrastructure.axon.BaseCommand;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReadNotificationCommand extends BaseCommand<String> {
    private String notificationId;
    private String userId;
    private String sourceId;
    private String sourceType;
    private int read;
    private int trash;
    private String content;

    public ReadNotificationCommand(String id,
                                   String notificationId,
                                   String userId,
                                   String sourceId,
                                   String sourceType,
                                   int trash,
                                   String content) {
        super(id);
        this.notificationId = notificationId;
        this.userId = userId;
        this.sourceId = sourceId;
        this.sourceType = sourceType;
        this.read = 1;
        this.trash = trash;
        this.content = content;
    }
}
