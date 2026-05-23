package com.bank.bankingapp.service;

import com.bank.bankingapp.dto.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(Long accountId);

    AccountDTO deposit(Long accountId, Double amount);

    AccountDTO withdraw(Long accountId, Double amount);

    List<AccountDTO> getAllAccounts();

    void deleteAccount(Long accountId);
}
