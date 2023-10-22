package com.banka.billpayment.controllers;

import com.banka.billpayment.entity.Bill;
import com.banka.billpayment.services.BillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping("/add")
    public Bill addBill(@RequestBody Bill bill) {
        return billService.addBill(bill);
    }

    @GetMapping("/getAllFalse")
    public ResponseEntity<List<Bill>> getAllBillsPaymentFalse(@RequestParam("id") Long id) {
        return ResponseEntity.ok(billService.findAllFalse(id));
    }

    @DeleteMapping("/delete/{billId}")
    public ResponseEntity deleteBill(@PathVariable Long billId) {
        billService.deleteBill(billId);
        return ResponseEntity.ok("Silindi : " + billId);
    }

    @GetMapping("/unpaid")
    public List<Bill> getUnpaid(@RequestParam("id") Long id) {
        return billService.unpaid(id);
    }

    @GetMapping("/getAll")
    public List<Bill> getAllBill() {
        return billService.getAllBill();
    }

    @GetMapping("/getBillOwnUser")
    public List<Bill> getBillOwnUser(@RequestParam("id") Long id) {
        return billService.getBillOwnUser(id);
    }
}
