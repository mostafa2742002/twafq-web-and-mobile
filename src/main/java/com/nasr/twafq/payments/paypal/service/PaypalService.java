package com.nasr.twafq.payments.paypal.service;

import com.paypal.sdk.PaypalServerSdkClient;
import com.paypal.sdk.controllers.OrdersController;
import com.paypal.sdk.exceptions.ApiException;
import com.paypal.sdk.http.response.ApiResponse;
import com.paypal.sdk.models.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PaypalService {

    private final PaypalServerSdkClient client;

    public PaypalService(PaypalServerSdkClient client) {
        this.client = client;
    }

    public Order createOrder(CreateOrderInput createOrderInput) throws IOException, ApiException {
        OrdersController ordersController = client.getOrdersController();
        ApiResponse<Order> apiResponse = ordersController.createOrder(createOrderInput);
        return apiResponse.getResult();
    }

    public Order captureOrder(String orderID) throws IOException, ApiException {
        CaptureOrderInput captureInput = new CaptureOrderInput.Builder(orderID, null).build();
        OrdersController ordersController = client.getOrdersController();
        ApiResponse<Order> apiResponse = ordersController.captureOrder(captureInput);
        return apiResponse.getResult();
    }
}