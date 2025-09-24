package me.ai.playground.mcp_chat.orders.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findTop10ByOrderByIdAsc();
}