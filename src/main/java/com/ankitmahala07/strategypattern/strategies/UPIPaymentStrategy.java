package com.ankitmahala07.strategypattern.strategies;

import com.ankitmahala07.strategypattern.interfaces.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component("upi")
public class UPIPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        //Actual implementation of handling a UPI payment
        //e.g. Audit transaction or generate notification
        System.out.println("Paying Rs." + amount + " via UPI.");
    }
}
