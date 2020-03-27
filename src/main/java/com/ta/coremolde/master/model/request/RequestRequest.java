package com.ta.coremolde.master.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestRequest {
    private String appName;
    private String appLogo;
    private String appBackground;
    private String appFontColor;
    private String prodLayout;
    private Integer category;
}
