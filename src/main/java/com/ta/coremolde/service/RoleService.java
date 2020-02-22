package com.ta.coremolde.service;

import com.ta.coremolde.model.entity.Role;

public interface RoleService {

    Role getRole(String name);
    String addRole(String name);

}
