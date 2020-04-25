package com.ta.coremolde.master.model.response;

import com.ta.coremolde.master.model.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopUserProfileResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private List<Address> addresses;
}
