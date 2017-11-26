package com.webapp.guide_operator.Repository;

import org.springframework.data.repository.CrudRepository;

import com.webapp.guide_operator.Entities.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
//	Page<User> findAll(Pageable pageable);
	
    User findByUsername(String username);
}
