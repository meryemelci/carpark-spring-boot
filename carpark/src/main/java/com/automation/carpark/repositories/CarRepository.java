package com.automation.carpark.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.automation.carpark.model.Car;

@Repository
public interface CarRepository extends MongoRepository<Car,String>{

	List<Car> findByPlate(String plate);

	//Page<Car> findByCreatedDate(String formattedDate, Pageable page);

	List<Car> findByCreatedDate(Date createdDate);	
	
//	Page<Car> findPaginated(int pageNo, int pageSize);
	
}
