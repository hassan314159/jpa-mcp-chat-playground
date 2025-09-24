package me.ai.playground.mcp_chat.orders.adapter.mcp;

import me.ai.playground.mcp_chat.orders.domain.Order;
import me.ai.playground.mcp_chat.orders.domain.OrderRepository;
import me.ai.playground.mcp_chat.orders.domain.OrdersListResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class OrderTools {

    Logger logger = LoggerFactory.getLogger(OrderTools.class);
    private final OrderRepository repo;

    public OrderTools(OrderRepository repo) {
        this.repo = repo;
    }

//    @McpTool(
//            name = "orders.list",
//            description = "List the first 10 orders."
//    )
//    public List<Order> listOrders(Map<String,Object> args) {
//        List<Order> orders = repo.findTop10ByOrderByIdAsc();
//        logger.info(orders.toString());
//        return orders;
//    }

    @McpTool(
            name = "orders.list",
            description = "List the first 10 orders."
    )
    public OrdersListResult listOrders(Map<String,Object> args) {
        List<Order> orders = repo.findTop10ByOrderByIdAsc();
        logger.info(orders.toString());
        return new OrdersListResult(orders);
    }

    @McpTool(
            name = "orders.get_by_id",
            description = "Fetch an order by its ID."
    )
    public Order getOrderById(long id) {
        return repo.findById(id).orElse(null);
    }
}
