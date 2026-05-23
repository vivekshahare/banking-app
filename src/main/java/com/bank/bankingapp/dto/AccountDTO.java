package com.bank.bankingapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Long id;
    private String accountHolderName;
    private Double balance;
}*/

public record AccountDTO(
        Long id,
        String accountHolderName,
        Double balance
) {
}
