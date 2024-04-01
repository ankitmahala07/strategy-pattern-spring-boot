package com.ankitmahala07.strategypattern.strategies;

import com.ankitmahala07.strategypattern.interfaces.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component("credit-card")
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        //Actual implementation of handling a credit card payment
        //e.g. Audit transaction or generate notification
        System.out.println("Paying Rs." + amount + " via Credit Card.");
    }
}
