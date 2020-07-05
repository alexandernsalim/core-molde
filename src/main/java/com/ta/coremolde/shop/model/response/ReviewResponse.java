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
public class ReviewResponse {
    private Integer id;
    private String title;
    private String description;
    private String rating;
    private Product product;
}
