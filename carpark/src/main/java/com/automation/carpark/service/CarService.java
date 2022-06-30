package com.automation.carpark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automation.carpark.model.Car;
import com.automation.carpark.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	public List<Car> getAllCar() {

		return carRepository.findAll();

	}

	public Optional<Car> getCarById(String id) {

		return  carRepository.findById(id);
	}

	public Car getCarByPlate(String plate) {
		Optional<Car> optional = carRepository.findById(plate);
		Car car = null;
		if (optional.isPresent()) {
			car = optional.get();
		} else {
			throw new RuntimeException(" Todo not found for id :: " + plate);
		}
		return car;
	}

	public void Save(Car car) {

		carRepository.save(car);
	}

	public Car updateCar(Car car) {	
		
		return carRepository.save(car);
	}

	public void deleteCar(String id) {

		carRepository.deleteById(id);
	}


//	public List<Car> findPaginatedCar(int pageNo, int pageSize) {
//
//        Pageable paging = PageRequest.of(pageNo, pageSize);
//        Page<Car> pagedResult = carRepository.findAll(paging);
//
//        return (List<Car>) pagedResult;
//    }
	
//	@Transactional(readOnly = true, propagation =Propagation.SUPPORTS )
//	public List<UserViewDTO> slice(Pageable pageable) {
//		return userRepository.findAll(pageable).stream().map(UserViewDTO::of).collect(Collectors.toList());
//	}


}
