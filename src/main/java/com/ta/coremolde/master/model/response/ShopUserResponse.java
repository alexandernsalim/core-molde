package com.ta.coremolde.master.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopUserResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNo;
}
