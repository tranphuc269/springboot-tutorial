package com.bookstore.orderservice.application.es.order.query.api.projection;


import com.bookstore.orderservice.application.es.order.query.api.queries.OrderHistoryRequestCheckingQuery;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderProjection {

    @Autowired
    private EventStore eventStore;

    @QueryHandler
    public List<Object> handle(OrderHistoryRequestCheckingQuery query) {
        return eventStore.readEvents(query.getOrderCheckingId())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}
