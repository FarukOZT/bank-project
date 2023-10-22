package com.banka.billpayment.services;

import com.banka.billpayment.entity.Bill;
import com.banka.billpayment.entity.Payment;
import com.banka.billpayment.repositories.BillRepository;
import com.banka.billpayment.repositories.PaymentRepository;
import com.banka.billpayment.repositories.WalletRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BillService billService;
    private final BillRepository billRepository;
    private final UserService userService;
    private final WalletRepository walletRepository;
    private final WalletService walletService;

    public PaymentService(PaymentRepository paymentRepository, BillService billService, BillRepository billRepository, UserService userService, WalletRepository walletRepository, WalletService walletService) {
        this.paymentRepository = paymentRepository;
        this.billService = billService;
        this.billRepository = billRepository;
        this.userService = userService;
        this.walletRepository = walletRepository;
        this.walletService = walletService;
    }

    public Payment makePayment(Long userId, Long billId, Long walletId) {
        Payment payment = new Payment();

        double amount = billService.findBill(billId).get().getPrice();
        payment.setTotalPrice(amount);
        double payPrice = walletService.getWallet(walletId).get().getBalance();

        if (userService.findUser(userId).isPresent()) {
            if (payPrice > amount) {
                walletService.getWallet(walletId).get().setBalance(payPrice - amount);
                billService.findBill(billId).get().setIsPaid(true);
                payment.setUser(userService.findUser(userId).get());
                paymentRepository.save(payment);
            }
        }
        return payment;
    }

    /*public Payment makePayment2(Long userId) {
        List<Bill> billList = billService.findAllFalse(userId);
        double totalAmount = billList.stream().mapToDouble(Bill::getPrice).sum();

        Payment payment = new Payment();
        payment.setTotalPrice(totalAmount);
        if (userService.findUser(userId).isPresent()) {
            payment.setUser(userService.findUser(userId).get());
        } else {
            return null;
        }
        billService.unpaidBills(userId);
        return paymentRepository.save(payment);
    }*/
}