package com.polarbookshop.orderservice.web;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.polarbookshop.orderservice.domain.Order;
import com.polarbookshop.orderservice.domain.OrderService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("orders")
public class OrderController {
  private Logger logger = LoggerFactory.getLogger(OrderController.class);
  private final OrderService orderService;
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }
 
  @GetMapping
  public Flux<Order> getAllOrders() {
    return orderService.getAllOrders();
  }
 
  @PostMapping
  public Mono<Order> submitOrder(
    @RequestBody @Valid OrderRequest orderRequest
  ) {
    logger.info("submitting order");
    return orderService.submitOrder(
     orderRequest.isbn(), orderRequest.quantity()
    );
  }
}
