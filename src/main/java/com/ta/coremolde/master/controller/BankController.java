package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.entity.Bank;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("molde/api/v1/bank")
public class BankController extends GlobalController {

    @Autowired
    private BankService bankService;

    @GetMapping("/get")
    public Response<List<Bank>> getAllBank() {
        return toResponse(bankService.getAllBank());
    }

    @GetMapping("{bankId}/get")
    public Response<Bank> getBank(@PathVariable Integer bankId) {
        return toResponse(bankService.getBank(bankId));
    }

    @PostMapping("/add")
    public Response<Bank> addNewBank(@RequestParam String name) {
        return toResponse(bankService.addNewBank(name));
    }

    @PutMapping("{bankId}/edit")
    public Response<Bank> editBank(@PathVariable Integer bankId, @RequestParam String name) {
        return toResponse(bankService.editBank(bankId, name));
    }

    @DeleteMapping("{bankId}/remove")
    public Response<String> removeBank(@PathVariable Integer bankId) {
        return toResponse(bankService.removeBank(bankId));
    }

}
