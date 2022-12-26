package com.bookstore.orderservice.application.es.order.command.write_model.entity;

import com.bookstore.common.infrastructure.repo.model.BaseModel;
import com.bookstore.orderservice.utils.OrderStatus;
import com.bookstore.orderservice.utils.PaymentMethod;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "orders")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCommandEntity extends BaseModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "order_id", updatable = false, nullable = false)
    private String orderId;

    @Column(name = "order_checking_id")
    private String orderCheckingId;


    @Column(name = "order_status")
    private String orderStatus;


    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "payment_status")
    private String paymentStatus;

}
