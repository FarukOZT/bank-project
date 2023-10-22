package com.banka.billpayment.services;

import com.banka.billpayment.entity.BalanceReq;
import com.banka.billpayment.entity.Wallet;
import com.banka.billpayment.repositories.UserRepository;
import com.banka.billpayment.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private final UserService userService;
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    public WalletService(UserService userService, WalletRepository walletRepository, UserRepository userRepository) {
        this.userService = userService;
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    public Wallet addWallet(Wallet wallet) {
        Wallet newWallet = new Wallet();
        newWallet.setBalance((double) 0);
        if (userRepository.findById(wallet.getUser().getId()).isPresent()) {
            newWallet.setUser(userRepository.getById(wallet.getUser().getId()));
            walletRepository.save(newWallet);
        }
        return newWallet;
    }

    public List<Wallet> getAllWallet() {
        return walletRepository.findAll();
    }

    public Optional<Wallet> getWallet(Long walletId) {
        return walletRepository.findById(walletId);
    }

    public Wallet addBalance(Long userId, Long walletId, BalanceReq balanceReq) {
        Wallet wallet = getWallet(walletId).get();
        balanceReq.setAddBalance(balanceReq.getAddBalance());
        wallet.setBalance(Double.sum(wallet.getBalance(), balanceReq.getAddBalance()));
        walletRepository.save(wallet);
        return wallet;
    }
}
