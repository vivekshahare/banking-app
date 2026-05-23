package com.bank.bankingapp.controller;

import com.bank.bankingapp.dto.AccountDTO;
import com.bank.bankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable("id") Long accountId) {
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<AccountDTO> deposit(@PathVariable("id") Long accountId,
                                              @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.deposit(accountId, amount));
    }

    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDTO> withdraw(@PathVariable("id") Long accountId,
                                               @RequestBody Map<String, Double> request) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.withdraw(accountId, amount));
    }

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteAccount(@PathVariable("id") Long accountId){
        accountService.deleteAccount(accountId);
        return ResponseEntity.ok("Account Deleted Successfully");
    }
}
