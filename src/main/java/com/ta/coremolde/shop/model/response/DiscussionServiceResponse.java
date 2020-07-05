package com.ta.coremolde.shop.model.response;

import com.ta.coremolde.shop.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionServiceResponse {
    private int id;
    private String detail;
    private String questionMaker;
    private DiscussionResponseServiceResponse lastReply;
    private Product product;
}
