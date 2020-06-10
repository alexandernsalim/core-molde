package com.ta.coremolde.shop.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscussionDetailResponse {
    private Integer id;
    private String question;
    private String questionOwner;
    private List<DiscussionResponseServiceResponse> responses;
}
