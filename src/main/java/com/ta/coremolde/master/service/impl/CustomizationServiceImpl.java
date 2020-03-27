package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.constant.ResponseConstant;
import com.ta.coremolde.master.model.entity.Category;
import com.ta.coremolde.master.model.entity.Customization;
import com.ta.coremolde.master.repository.CustomizationRepository;
import com.ta.coremolde.master.service.CustomizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomizationServiceImpl implements CustomizationService {

    @Autowired
    private CustomizationRepository customizationRepository;

    private Logger logger = LoggerFactory.getLogger(CustomizationServiceImpl.class);

    @Override
    public String createCustomization(Customization customization) {
        try {
            customizationRepository.save(customization);

            return ResponseConstant.CREATE_CUSTOMIZATION_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.CREATE_CUSTOMIZATION_FAILED;
        }
    }

    @Override
    public String updateCustomization(Integer id, Customization updatedCustomization) {
        String updateAppLogo = updatedCustomization.getAppLogo();
        String updateAppBackground = updatedCustomization.getAppBackground();
        String updateAppFontColor = updatedCustomization.getAppFontColor();
        String updatedProdLayout = updatedCustomization.getProdLayout();
        Category updatedCategory = updatedCustomization.getCategory();

        try {
            Customization customization = customizationRepository.findCustomizationById(id);
            customization.setAppLogo(updateAppLogo);
            customization.setAppBackground(updateAppBackground);
            customization.setAppFontColor(updateAppFontColor);
            customization.setProdLayout(updatedProdLayout);
            customization.setCategory(updatedCategory);

            customizationRepository.save(customization);

            return ResponseConstant.UPDATE_CUSTOMIZATION_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.UPDATE_CUSTOMIZATION_FAILED;
        }
    }

}
