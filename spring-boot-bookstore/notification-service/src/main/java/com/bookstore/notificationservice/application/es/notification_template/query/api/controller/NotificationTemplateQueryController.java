//package com.bookstore.notificationservice.application.es.notification_template.query.api.controller;
//
//import com.bookstore.common.application.response.dto.BaseResponse;
//import com.bookstore.notificationservice.application.es.notification_template.query.api.queries.GetAllTemplateQuery;
//import com.bookstore.notificationservice.application.es.notification_template.query.api.queries.GetNotificationTemplateBySourceTypeQuery;
//import com.bookstore.notificationservice.application.es.notification_template.query.model.dto.NotificationTemplateResponse;
//import org.axonframework.messaging.responsetypes.ResponseTypes;
//import org.axonframework.queryhandling.QueryGateway;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController("/api/notification-template-query")
//public class NotificationTemplateQueryController {
//
//    @Autowired
//    private QueryGateway queryGateway;
//
//    @PostMapping("/")
//    public BaseResponse<Object> findAllTemplate() {
//        System.out.println("find all");
//        GetAllTemplateQuery query = new GetAllTemplateQuery();
//        return BaseResponse.ofSucceeded(
//                queryGateway.query(
//                                query,
//                                ResponseTypes.multipleInstancesOf(NotificationTemplateResponse.class))
//                        .join()
//        );
//    }
//
//    @PostMapping("/{sourceType}")
//    public BaseResponse<Object> findTemplateBySourceType(@PathVariable String sourceType) {
//        GetNotificationTemplateBySourceTypeQuery query = GetNotificationTemplateBySourceTypeQuery
//                .builder()
//                .sourceType(sourceType)
//                .build();
//        return BaseResponse.ofSucceeded(
//                queryGateway.query(
//                                query,
//                                ResponseTypes.multipleInstancesOf(NotificationTemplateResponse.class))
//                        .join()
//        );
//    }
//
//}
