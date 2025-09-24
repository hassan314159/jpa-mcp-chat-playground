package me.ai.playground.mcp_chat.orders.adapter.controller;

import jakarta.validation.constraints.NotBlank;
import me.ai.playground.mcp_chat.orders.domain.Order;
import me.ai.playground.mcp_chat.orders.domain.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository repo;

    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    public record CreateOrderRequest(
            @NotBlank String productName,
            @Min(1) int quantity
    ) {
    }

    public record UpdateOrderRequest(
            String productName,
            Integer quantity
    ) {
    }

    public record OrderView(Long id, String productName, int quantity) {
        static OrderView from(Order o) {
            return new OrderView(o.getId(), o.getProductName(), o.getQuantity());
        }
    }

    @GetMapping
    public List<OrderView> list() {
        return repo.findTop10ByOrderByIdAsc().stream().map(OrderView::from).toList();
    }

    @GetMapping("/{id}")
    public OrderView get(@PathVariable Long id) {
        Order o = repo.findById(id).orElseThrow(() -> new NotFound("Order %d not found".formatted(id)));
        return OrderView.from(o);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    private static class NotFound extends RuntimeException {
        public NotFound(String msg) { super(msg); }
    }
}