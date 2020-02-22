package com.ta.coremolde.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private String email;
    private String fullName;
    private String lastName;
    private String phoneNo;
}
