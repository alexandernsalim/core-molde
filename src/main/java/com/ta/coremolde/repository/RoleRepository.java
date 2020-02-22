package com.ta.coremolde.repository;

import com.ta.coremolde.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByName(String name);

}
