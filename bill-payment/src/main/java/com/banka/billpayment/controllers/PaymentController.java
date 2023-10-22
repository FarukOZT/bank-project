package com.banka.billpayment.controllers;

import com.banka.billpayment.entity.Payment;
import com.banka.billpayment.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("paybill")
    public Payment makeAllPayment3(@RequestParam("userId") Long userId,
                                   @RequestParam("billId") Long billId,
                                   @RequestParam("walletId") Long walletId) {
        return paymentService.makePayment(userId, billId, walletId);
    }

}
