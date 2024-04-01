package com.ankitmahala07.strategypattern.strategies;

import com.ankitmahala07.strategypattern.interfaces.PaymentStrategy;
import org.springframework.stereotype.Component;

@Component("apple-wallet")
public class AppleWalletPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        //Actual implementation of handling a UPI payment
        //e.g. Audit transaction or generate notification
        System.out.println("Paying Rs." + amount + " via Apple Wallet.");
    }
}
