package com.ta.coremolde.master.model.response;

import com.ta.coremolde.master.model.entity.Bank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountResponse {
    private Integer id;
    private String owner;
    private String no;
    private Bank bank;
}
