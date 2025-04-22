package comsep23.textileindustry.controller;

import comsep23.textileindustry.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/pending")
    public List<String> getPendingDeliveries() {
        return deliveryService.getPendingDeliveries();
    }

    @GetMapping("/delivered")
    public List<String> getDeliveredOrders() {
        return deliveryService.getDeliveredOrders();
    }

    @PostMapping("/deliver")
    public String deliverOrder(@RequestParam String keyword) {
        return deliveryService.deliverOrder(keyword);
    }

    @GetMapping("/count-delivered")
    public int countDelivered() {
        return deliveryService.countDelivered();
    }

    @GetMapping("/count-pending")
    public int countPending() {
        return deliveryService.countPending();
    }

    @GetMapping("/earnings")
    public double getEarnings() {
        return deliveryService.calculateEarnings();
    }

}
