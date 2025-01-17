package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.constant.ResponseConstant;
import com.ta.coremolde.master.model.constant.StatusConstant;
import com.ta.coremolde.master.model.entity.Account;
import com.ta.coremolde.master.model.entity.Category;
import com.ta.coremolde.master.model.entity.Customization;
import com.ta.coremolde.master.model.entity.Request;
import com.ta.coremolde.master.model.exception.MoldeException;
import com.ta.coremolde.master.model.request.RequestRequest;
import com.ta.coremolde.master.model.response.ErrorResponse;
import com.ta.coremolde.master.model.response.RequestResponse;
import com.ta.coremolde.master.repository.RequestRepository;
import com.ta.coremolde.master.service.*;
import com.ta.coremolde.util.ResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ShopService shopService;

    private Logger LOG = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Override
    public List<RequestResponse> getAllRequest(Integer id) {
        return ResponseMapper.mapAsList(requestRepository.findAllByStatusEquals(id), RequestResponse.class);
    }

    @Override
    public List<RequestResponse> getAllActiveRequest() {
        return ResponseMapper.mapAsList(requestRepository.findAllByStatusIn(0, 1), RequestResponse.class);
    }

    @Override
    public String changeRequestStatus(Integer id, StatusConstant condition) {
        Request request = requestRepository.findRequestById(id);

        if (request.getStatus() == 0) {
            switch (condition) {
                case ACCEPT:
                    request.setStatus(StatusConstant.ACCEPT.getValue());
                    shopService.createShop(request.getShopName(), request.getAccount(), request.getCustomization());
                    requestRepository.save(request);
                    break;
                case REJECT:
                    request.setStatus(StatusConstant.REJECT.getValue());
                    requestRepository.save(request);
                    break;
            }

            return ResponseConstant.UPDATE_REQUEST_SUCCESS;
        }

        return ResponseConstant.UPDATE_REQUEST_FAILED;
    }

    @Override
    public String createRequest(String email, RequestRequest requestRequest) {
        if (requestRepository.existsByShopName(requestRequest.getShopName())) {
            throw new MoldeException(
                    ErrorResponse.SHOP_NAME_EXISTS.getCode(),
                    ErrorResponse.SHOP_NAME_EXISTS.getMessage()
            );
        }

        if (requestRepository.existsByAppName(requestRequest.getAppName())) {
            throw new MoldeException(
                    ErrorResponse.APP_NAME_EXISTS.getCode(),
                    ErrorResponse.APP_NAME_EXISTS.getMessage()
            );
        }

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
                    .shopName(requestRequest.getShopName())
                    .appName(requestRequest.getAppName())
                    .customization(customization)
                    .account(account)
                    .build()
            );

            return ResponseConstant.CREATE_REQUEST_SUCCESS;
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage());

            return ResponseConstant.CREATE_REQUEST_FAILED;
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());

            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

    @Override
    public String updateRequest(String email, Integer id, RequestRequest requestRequest) {
        if (requestRepository.existsByShopName(requestRequest.getShopName())) {
            throw new MoldeException(
                    ErrorResponse.SHOP_NAME_EXISTS.getCode(),
                    ErrorResponse.SHOP_NAME_EXISTS.getMessage()
            );
        }

        if (requestRepository.existsByAppName(requestRequest.getAppName())) {
            throw new MoldeException(
                    ErrorResponse.APP_NAME_EXISTS.getCode(),
                    ErrorResponse.APP_NAME_EXISTS.getMessage()
            );
        }

        String appName = requestRequest.getAppName();
        String shopName = requestRequest.getShopName();
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
            request.setShopName(shopName);
            request.setAppName(appName);
            request.setCustomization(updatedCustomization);
            requestRepository.save(request);

            return ResponseConstant.UPDATE_REQUEST_SUCCESS;
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage());

            return ResponseConstant.UPDATE_REQUEST_FAILED;
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());

            throw new MoldeException(
                    ErrorResponse.RESOURCE_NOT_FOUND.getCode(),
                    ErrorResponse.RESOURCE_NOT_FOUND.getMessage()
            );
        }
    }

    @Override
    public RequestResponse completeRequest(Integer id, String downloadUrl) {
        Request request = requestRepository.findRequestById(id);
        request.setStatus(StatusConstant.COMPLETE.getValue());
        request.setDownloadUrl(downloadUrl);

        return ResponseMapper.map(requestRepository.save(request), RequestResponse.class);
    }

    @Override
    public String cancelRequest(String email, Integer id) {
        try {
            Account account = accountService.getAccount(email);
            Request request = requestRepository.findRequestById(id);
            Integer ownerId = request.getAccount().getId();
            checkResourceAccess(ownerId, account.getId());
            requestRepository.delete(request);

            return ResponseConstant.DELETE_REQUEST_SUCCESS;
        } catch (IllegalArgumentException e) {
            LOG.error(e.getMessage());

            return ResponseConstant.DELETE_REQUEST_FAILED;
        }
    }

    @Override
    public RequestResponse getActiveRequest(String email) {
        Account account = accountService.getAccount(email);

        return ResponseMapper.map(requestRepository.findRequestByAccount_IdAndStatus(account.getId(), 0), RequestResponse.class);
    }

    @Override
    public RequestResponse getShopRequest(String email) {
        return ResponseMapper.map(requestRepository.findRequestByAccount_Email(email), RequestResponse.class);
    }

    private void checkResourceAccess(Integer ownerId, Integer accountId) {
        if (!ownerId.equals(accountId)) {
            throw new MoldeException(
                    ErrorResponse.UNAUTHORIZED_RESOURCE_ACCESS.getCode(),
                    ErrorResponse.UNAUTHORIZED_RESOURCE_ACCESS.getMessage());
        }
    }

}
