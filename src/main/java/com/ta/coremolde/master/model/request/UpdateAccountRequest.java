package com.ta.coremolde.master.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNo;
}
