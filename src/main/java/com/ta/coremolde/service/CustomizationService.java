package com.ta.coremolde.service;

import com.ta.coremolde.model.entity.Customization;

public interface CustomizationService {

    String createCustomization(Customization customization);
    String updateCustomization(Integer id, Customization updatedCustomization);

}
