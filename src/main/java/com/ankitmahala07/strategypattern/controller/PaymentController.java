package com.ankitmahala07.strategypattern.controller;

import com.ankitmahala07.strategypattern.context.PaymentContext;
import com.ankitmahala07.strategypattern.interfaces.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentContext paymentContext;
    private final Map<String, PaymentStrategy> paymentStrategies;

    @Autowired
    public PaymentController(PaymentContext paymentContext,
                             Map<String, PaymentStrategy> paymentStrategies){
        this.paymentContext = paymentContext;
        this.paymentStrategies = paymentStrategies;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payAmount(@RequestParam double amount, @RequestParam String paymentMethod){
        PaymentStrategy selectedStrategy = paymentStrategies.get(paymentMethod);
        if(selectedStrategy == null){
            System.out.println("Invalid payment method, "+paymentMethod);
            return ResponseEntity.badRequest().body("Invalid payment method");
        }
        paymentContext.setPaymentStrategy(selectedStrategy);
        paymentContext.processPayment(amount);
        return ResponseEntity.ok("Payment successful.");
    }
}
