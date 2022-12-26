package com.bookstore.common.infrastructure.kafka.notification;

import com.bookstore.common.utils.notification.NotificationDestination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class KafkaNotificationEmailRequest extends KafkaNotificationRequest {
    @Override
    public NotificationDestination getDestination() {
        return NotificationDestination.EMAIL;
    }

    private String title;
    private String userId;
}
