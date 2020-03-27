package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.constant.PathConstant;
import com.ta.coremolde.master.model.response.Response;
import com.ta.coremolde.master.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConstant.ROLE_MAPPING)
public class RoleController extends GlobalController {

    @Autowired
    private RoleService roleService;

    @PostMapping(PathConstant.ADD_ROLE)
    public Response<String> addRole(@RequestParam String name) {
        return toResponse(roleService.addRole(name));
    }

}
