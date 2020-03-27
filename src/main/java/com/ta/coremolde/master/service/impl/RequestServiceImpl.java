package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.service.CustomizationService;
import com.ta.coremolde.master.model.constant.ResponseConstant;
import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Category;
import com.ta.coremolde.master.model.entity.Customization;
import com.ta.coremolde.master.model.entity.Request;
import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.request.RequestRequest;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.master.repository.RequestRepository;
import com.ta.coremolde.master.service.AccountService;
import com.ta.coremolde.master.service.CategoryService;
import com.ta.coremolde.master.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomizationService customizationService;

    private Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Override
    public String createRequest(String email, RequestRequest requestRequest) {
        Category category = categoryService.getCategoryById(requestRequest.getCategory());

        try {
            Account account = accountService.getAccount(email);
            Customization customization = Customization.builder()
                    .appLogo(requestRequest.getAppLogo())
                    .appBackground(requestRequest.getAppBackground())
                    .appFontColor(requestRequest.getAppFontColor())
                    .prodLayout(requestRequest.getProdLayout())
                    .category(category)
                    .build();
            customizationService.createCustomization(customization);
            requestRepository.save(Request.builder()
                    .appName(requestRequest.getAppName())
                    .customization(customization)
                    .account(account)
                    .build()
            );

            return ResponseConstant.CREATE_REQUEST_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.CREATE_REQUEST_FAILED;
        } catch (NullPointerException e) {
            logger.error(e.getMessage());

            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

    @Override
    public String updateRequest(String email, Integer id, RequestRequest requestRequest) {
        String appName = requestRequest.getAppName();
        Category category = categoryService.getCategoryById(requestRequest.getCategory());

        try {
            Account account = accountService.getAccount(email);
            Request request = requestRepository.findRequestById(id);
            Integer ownerId = request.getAccount().getId();
            checkResourceAccess(ownerId, account.getId());
            Integer customizationId = request.getCustomization().getId();
            Customization updatedCustomization = Customization.builder()
                    .id(customizationId)
                    .appLogo(requestRequest.getAppLogo())
                    .appBackground(requestRequest.getAppBackground())
                    .appFontColor(requestRequest.getAppFontColor())
                    .prodLayout(requestRequest.getProdLayout())
                    .category(category)
                    .build();
            customizationService.updateCustomization(customizationId, updatedCustomization);
            request.setAppName(appName);
            request.setCustomization(updatedCustomization);
            requestRepository.save(request);

            return ResponseConstant.UPDATE_REQUEST_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.UPDATE_REQUEST_FAILED;
        } catch (NullPointerException e) {
            logger.error(e.getMessage());

            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

    @Override
    public String cancelRequest(String email, Integer id) {
        //TODO Functionality test

        try {
            Account account = accountService.getAccount(email);
            Request request = requestRepository.findRequestById(id);
            Integer ownerId = request.getAccount().getId();
            checkResourceAccess(ownerId, account.getId());
            requestRepository.delete(request);

            return ResponseConstant.DELETE_REQUEST_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.DELETE_REQUEST_FAILED;
        }
    }

    private void checkResourceAccess(Integer ownerId, Integer accountId) {
        if (!ownerId.equals(accountId)) {
            throw new MoldeException(
                    ErrorResponse.UNAUTHORIZED_RESOURCE_ACCESS.getCode(),
                    ErrorResponse.UNAUTHORIZED_RESOURCE_ACCESS.getMessage());
        }
    }

}
