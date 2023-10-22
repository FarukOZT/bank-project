package com.banka.billpayment.services;

import com.banka.billpayment.entity.Bill;
import com.banka.billpayment.repositories.BillRepository;
import com.banka.billpayment.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public BillService(BillRepository billRepository, UserRepository userRepository, UserService userService) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public Bill addBill(Bill bill) {
        Bill editBill = new Bill();
        editBill.setPrice(bill.getPrice());
        editBill.setIsPaid(bill.getIsPaid());
        if (userRepository.findById(bill.getUser().getId()).isPresent()) {
            editBill.setUser(userRepository.getById(bill.getUser().getId()));
        }
        billRepository.save(editBill);
        return editBill;
    }

    public Optional<Bill> findBill(Long billId) {
        return billRepository.findById(billId);
    }

    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    public List<Bill> getBillOwnUser(Long userId) {
        return billRepository.findBillOwnUser(userId);
    }

    public void deleteBill(Long billId) {
        billRepository.deleteById(billId);
    }

    public List<Bill> findUnpaid(Long userId) {
        List<Bill> find = getBillOwnUser(userId);
        return find.stream().filter((bill -> bill.getIsPaid().equals(false))).collect(Collectors.toList());
    }

    public List<Bill> unpaid(Long userId) {

        List<Bill> unpaid = findUnpaid(userId);

        if (unpaid.isEmpty()) {
            throw new RuntimeException("hepsi odendi");
        } else {
            return unpaid;
        }
    }

    public List<Bill> findAllFalse(Long userId) {
        return billRepository.findAllFalseByUserId(userId);
    }

    public void unpaidBills(Long userId) {

        List<Bill> unpaidBills = findAllFalse(userId);
        if (unpaidBills.isEmpty()) {
            throw new RuntimeException("odenmemis fatura yok");
        } else {
            unpaidBills.forEach(bill -> {
                bill.setIsPaid(true);
                billRepository.save(bill);
            });
        }
    }
}
