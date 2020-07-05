package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Customization;

public interface CustomizationService {

    String createCustomization(Customization customization);
    String updateCustomization(Integer id, Customization updatedCustomization);

}
