package com.ankitmahala07.strategypattern.controller;

import com.ankitmahala07.strategypattern.interfaces.PaymentStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PaymentStrategy upiPaymentStrategy;

    @Test
    public void testPayAmount_UPI() throws Exception {

        double amount = 100.0;
        String paymentMethod = "upi";

        MockHttpServletRequestBuilder request = post("/payment/pay")
                .param("amount", String.valueOf(amount))
                .param("paymentMethod", paymentMethod)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Payment successful."));

    }

    @Test
    public void testPayAmount_CreditCard() throws Exception {

        double amount = 122.0;
        String paymentMethod = "credit-card";

        MockHttpServletRequestBuilder request = post("/payment/pay")
                .param("amount", String.valueOf(amount))
                .param("paymentMethod", paymentMethod)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Payment successful."));

    }

    @Test
    public void testPayAmount_AppleWallet() throws Exception {

        double amount = 184.20;
        String paymentMethod = "apple-wallet";

        MockHttpServletRequestBuilder request = post("/payment/pay")
                .param("amount", String.valueOf(amount))
                .param("paymentMethod", paymentMethod)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Payment successful."));

    }

    @Test
    public void testPayAmount_Exception() throws Exception {

        double amount = 2000.0;
        String paymentMethod = "paytm";

        MockHttpServletRequestBuilder request = post("/payment/pay")
                .param("amount", String.valueOf(amount))
                .param("paymentMethod", paymentMethod)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid payment method"));

    }

}
