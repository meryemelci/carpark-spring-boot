package com.automation.carpark.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.automation.carpark.model.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String>{
	
	 Role findByRole(String role);

}
