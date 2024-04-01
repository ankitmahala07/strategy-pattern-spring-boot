package com.ankitmahala07.strategypattern.context;

import com.ankitmahala07.strategypattern.interfaces.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentContext {

    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void processPayment(double amount){
        if(paymentStrategy == null){
            throw new IllegalStateException("Payment strategy is not set.");
        }
        paymentStrategy.pay(amount);
    }

}
