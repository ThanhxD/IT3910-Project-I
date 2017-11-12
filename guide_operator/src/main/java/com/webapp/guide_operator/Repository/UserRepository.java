package com.webapp.guide_operator.Repository;

import com.webapp.guide_operator.Entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
