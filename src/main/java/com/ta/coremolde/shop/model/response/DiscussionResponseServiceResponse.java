package com.ta.coremolde.shop.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionResponseServiceResponse {
    private int id;
    private String message;
    private String shopReplyUsername;
    private String shopUserReplyUsername;
}
