package com.bank.bankingapp.service.impl;

import com.bank.bankingapp.dto.AccountDTO;
import com.bank.bankingapp.entity.Account;
import com.bank.bankingapp.exception.AccountNotFoundException;
import com.bank.bankingapp.exception.InsufficientBalanceException;
import com.bank.bankingapp.repository.AccountRepository;
import com.bank.bankingapp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bank.bankingapp.mapper.AccountMapper.MAPPER;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = accountRepository.save(MAPPER.accountDTOToAccount(accountDTO));
        return MAPPER.accountToAccountDTO(account);
    }

    @Override
    public AccountDTO getAccountById(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        return MAPPER.accountToAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(Long accountId, Double amount) {
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));
        existingAccount.setBalance(amount + existingAccount.getBalance());
        Account account = accountRepository.save(existingAccount);
        return MAPPER.accountToAccountDTO(account);
    }

    @Override
    public AccountDTO withdraw(Long accountId, Double amount) {
        Account existingAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found"));

        if (amount > existingAccount.getBalance()) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        Double remainingBalance = existingAccount.getBalance() - amount;
        existingAccount.setBalance(remainingBalance);
        Account savedAccount = accountRepository.save(existingAccount);
        return MAPPER.accountToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> accountsList = accountRepository.findAll();
        return accountsList.stream().map(MAPPER::accountToAccountDTO).toList();
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account Not Found"));
        accountRepository.deleteById(accountId);
    }


}
