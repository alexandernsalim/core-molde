package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.service.RajaOngkirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/molde/api/v1/rajaongkir")
public class RajaOngkirController {

    @Autowired
    private RajaOngkirService rajaOngkirService;

    @GetMapping("/province")
    public String getProvinces() {
        return rajaOngkirService.getProvinces();
    }

    @GetMapping("/cities")
    public String getCities(@RequestParam(value = "province", required = false) Integer province) {
        return rajaOngkirService.getCities(province);
    }

}
