package com.ta.coremolde.master.service.impl;

import com.ta.coremolde.master.model.constant.ResponseConstant;
import com.ta.coremolde.master.model.entity.Role;
import com.ta.coremolde.master.repository.RoleRepository;
import com.ta.coremolde.master.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public Role getRole(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Override
    public String addRole(String name) {
        try {
            roleRepository.save(Role.builder()
                    .name(name)
                    .build());

            return ResponseConstant.ADD_ROLE_SUCCESS;
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());

            return ResponseConstant.ADD_ROLE_FAILED;
        }
    }

}
