package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.entity.Bank;
import com.ta.coremolde.master.model.request.BankAccountRequest;
import com.ta.coremolde.master.model.response.BankAccountResponse;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("molde/api/v1/bankaccount")
public class BankAccountController extends GlobalController {

    @Autowired
    private BankAccountService bankAccountService;

    @GetMapping("/get")
    public Response<List<BankAccountResponse>> getShopBankAccount(@RequestParam(required = false) Integer shopId, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(bankAccountService.getShopBankAccount(email, shopId));
    }

    @GetMapping("{bankAccountId}/get")
    public Response<Bank> getBankAccount(@PathVariable Integer bankAccountId) {
        return toResponse(bankAccountService.getBankAccount(bankAccountId));
    }

    @PostMapping("{bankId}/add")
    public Response<Bank> addNewBank(@PathVariable Integer bankId, @ModelAttribute BankAccountRequest request, HttpServletRequest httpServletRequest) {
        String email = httpServletRequest.getUserPrincipal().getName();

        return toResponse(bankAccountService.addBankAccount(email, bankId, request));
    }

    @PutMapping("{bankAccountId}/edit")
    public Response<Bank> editBank(@PathVariable Integer bankAccountId, @ModelAttribute BankAccountRequest request) {
        return toResponse(bankAccountService.editBankAccount(bankAccountId, request));
    }

    @DeleteMapping("{bankAccountId}/remove")
    public Response<String> removeBank(@PathVariable Integer bankAccountId) {
        return toResponse(bankAccountService.removeBankAccount(bankAccountId));
    }

}
