package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String name);

}
