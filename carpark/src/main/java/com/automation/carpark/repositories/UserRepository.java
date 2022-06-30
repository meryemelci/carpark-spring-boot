package com.automation.carpark.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.automation.carpark.model.User;

@Repository
public interface UserRepository extends MongoRepository<User,String>{
	
	User findByEmail(String email);

}
