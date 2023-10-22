package com.banka.billpayment.controllers;

import com.banka.billpayment.config.RabbitMQProducer;
import com.banka.billpayment.entity.BalanceReq;
import com.banka.billpayment.entity.User;
import com.banka.billpayment.entity.Wallet;
import com.banka.billpayment.services.UserService;
import com.banka.billpayment.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final WalletService walletService;

    private final RabbitMQProducer rabbitMQProducer;

    public UserController(UserService userService, WalletService walletService, RabbitMQProducer rabbitMQProducer) {
        this.userService = userService;
        this.walletService = walletService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PostMapping("/addBalance")
    public Wallet addBalance(@RequestParam("userId") Long userId,
                             @RequestParam("walletId") Long walletId,
                             @RequestBody BalanceReq addBalance) {
        rabbitMQProducer.sendMessage("Yukleme talebiniz alindi");
        return walletService.addBalance(userId, walletId, addBalance);
    }

    @PostMapping("/addWallet")
    public Wallet addWallet(@RequestBody Wallet wallet) {
        return walletService.addWallet(wallet);
    }

    @GetMapping("/getWallet/{walletId}")
    public Optional<Wallet> getWallet(@PathVariable("walletId") Long walletId) {
        return walletService.getWallet(walletId);
    }
}
