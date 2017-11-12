package com.webapp.guide_operator.Repository;

import com.webapp.guide_operator.Entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
