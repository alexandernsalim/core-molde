package com.ta.coremolde.master.service;

import com.ta.coremolde.master.model.entity.Role;

public interface RoleService {

    Role getRole(String name);
    String addRole(String name);

}
