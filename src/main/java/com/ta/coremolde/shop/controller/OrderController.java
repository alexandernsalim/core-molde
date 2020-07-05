package com.ta.coremolde.shop.controller;

import com.ta.coremolde.master.controller.GlobalController;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.ExportService;
import com.ta.coremolde.shop.model.entity.Order;
import com.ta.coremolde.shop.model.request.AirwayBillRequest;
import com.ta.coremolde.shop.model.response.OrderResponse;
import com.ta.coremolde.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/molde/api/v1/order")
public class OrderController extends GlobalController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ExportService exportService;

    @GetMapping("/export")
    public ResponseEntity<InputStreamResource> export() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=orders.xlsx");
        ByteArrayInputStream file = exportService.exportOrder();

        return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(file));
    }

    @GetMapping("/shop/get")
    public Response<List<Order>> getShopOrder() {
        return toResponse(orderService.getShopOrder());
    }

    @GetMapping("/user/get")
    public Response<List<OrderResponse>> getUserOrder(HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(orderService.getUserOrder(email));
    }

    @GetMapping("/{orderId}/get")
    public Response<OrderResponse> getOrderDetail(@PathVariable Integer orderId) {
        return toResponse(orderService.getOrderDetail(orderId));
    }

    @PostMapping("/{orderId}/accept")
    public Response<Order> acceptOrder(@PathVariable Integer orderId) {
        return toResponse(orderService.acceptOrder(orderId));
    }

    @PostMapping("/{orderId}/reject")
    public Response<Order> rejectOrder(@PathVariable Integer orderId) {
        return toResponse(orderService.rejectOrder(orderId));
    }

    @PostMapping("/{orderId}/cancel")
    public Response<Order> cancelOrder(@PathVariable Integer orderId) {
        return toResponse(orderService.cancelOrder(orderId));
    }

    @PostMapping("/{orderId}/upload-payment")
    public Response<String> uploadPaymentImage(@RequestParam Integer shopId, @PathVariable Integer orderId, @RequestParam MultipartFile paymentImage) {
        return toResponse(orderService.uploadPaymentImage(shopId, orderId, paymentImage));
    }

    @PostMapping("/{orderId}/airway-bill")
    public Response<Order> setAirwayBill(@PathVariable Integer orderId, @ModelAttribute AirwayBillRequest request) {
        return toResponse(orderService.setAirwayBill(orderId, request.getAirwayBill()));
    }

    @PostMapping("/{orderId}/complete")
    public Response<Order> completeOrder(@PathVariable Integer orderId) {
        return toResponse(orderService.completeOrder(orderId));
    }

}
