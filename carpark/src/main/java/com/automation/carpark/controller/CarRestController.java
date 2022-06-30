package com.automation.carpark.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.automation.carpark.model.Car;
import com.automation.carpark.repositories.CarRepository;
import com.automation.carpark.service.CarService;

@RequestMapping("/rest")
@RestController
public class CarRestController {

	@Autowired
	private CarService carService;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@GetMapping("/cars")
	@ResponseBody
	public List<Car> newCar() {

		List<Car> car1 = carService.getAllCar();
		return car1;
	}

	@GetMapping("/list")
	public Page<Car> listCars(Pageable pageable) {

		Pageable page = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
		Page<Car> cars = carRepository.findAll(page);

		return cars;

	}

//	@GetMapping("/getcar/{page}/{size}")
//	public List<Car> getPaginatedCountries(@PathVariable int pageNo, @PathVariable int pageSize) {
//
//		return carService.findPaginated(pageNo, pageSize);
//	}

	
	
	
//	@GetMapping("/list")
//	public ResponseEntity<List<Car>> slice(Pageable pageable){
//		
//		@SuppressWarnings("unchecked")
//		List<Car> cars=(List<Car>) carRepository.findAll(pageable);
//		return ResponseEntity.ok(cars);
//	}

	@GetMapping("/dateCar")
	public List<Car> datedCar(@RequestParam String startDate, String endDate) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date startCreatedDate = df.parse(startDate);

		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		Date endCreatedDate = df2.parse(endDate);

		Query query = new Query();
		query.addCriteria(
				Criteria.where("payment").is(true).and("createdDate").gte(startCreatedDate).lt(endCreatedDate));
		List<Car> cars = mongoTemplate.find(query, Car.class);
		return cars;

	}

	@GetMapping("/paidCar")
	public List<Car> paidCar() {

		Query query = new Query();
		query.addCriteria(Criteria.where("payment").is(true));
		List<Car> cars = mongoTemplate.find(query, Car.class);
		return cars;

	}

	@GetMapping("/getstatusTrue")
	public List<Car> findByStatus() {

		Query query = new Query();
		query.addCriteria(Criteria.where("status").is(true));

		List<Car> cars = mongoTemplate.find(query, Car.class);

		return cars;

	}

	@GetMapping("/car/{id}")
	public Car Car(@PathVariable("id") String id) {
		Car car = carService.getCarById(id).get();
		return car;
	}

	@PostMapping("/addcar")
	public ResponseEntity<Car> addCar(@RequestBody Car car) {
		return new ResponseEntity<Car>(carRepository.save(car), HttpStatus.CREATED);
	}

	@PutMapping("/updateCarStatus/{id}")
	public Car updateCarStatus(@PathVariable("id") String id) {
		Car car = carService.getCarById(id).get();
		if (car.isStatus()) {
			car.setStatus(false);
		} else {
			car.setStatus(true);
		}
		return carService.updateCar(car);
	}

	@PutMapping("/updateCarPayment/{id}")
	public Car updateCarPayment(@PathVariable("id") String id) {
		Car car = carService.getCarById(id).get();
		if (car.isPayment()) {
			car.setPayment(false);
		} else {
			car.setPayment(true);
		}
		return carService.updateCar(car);
	}

	@PutMapping("/updateCar/{id}")
	public void updateCar(@PathVariable("id") String id, @RequestBody Car car) {

		Car updateCar = carService.getCarById(id).orElse(car);
		// System.out.println("model"+updateCar.getModel());
		updateCar.setModel(car.getModel());
		updateCar.setPlate(car.getPlate());
		carService.updateCar(updateCar);

	}

	@GetMapping("/updateCar/{id}")
	public Car getCar(@PathVariable String id) {
		return carService.getCarById(id).get();
	}

	@DeleteMapping("/delete/{id}")
	public String deleteCar(@PathVariable("id") String id) {

		carRepository.deleteById(id);
		return "Arac kaydı silindi";

	}

//	@PutMapping("/updateCar")
//	public Car updateCar(@RequestBody Car car) {		
//		 return carService.updateCar(car);
//	}
//

//	
//	@GetMapping("/updateCar")
//	public String getUpdate() {
//		
//		return "updateCar";
//	}

	/*
	 * @GetMapping("/dateCar") public Page<Car>
	 * datedCar(@RequestParam("createdDate") String createdDate, Pageable pageable)
	 * {
	 * 
	 * DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); String
	 * formattedDate=df.format(createdDate); Pageable page
	 * =PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
	 * Sort.by(Sort.Direction.DESC, "createdDate")); Page<Car> cars =
	 * carRepository.findByCreatedDate(formattedDate, page);
	 * 
	 * return cars;
	 * 
	 * }
	 */

	/*
	 * @GetMapping("/paidCar") public List<Car> paidCar() {
	 * 
	 * List<Car> car1 = carService.getAllCar(); List<Car> car2 =new ArrayList<>();
	 * for (Car car : car1) {
	 * 
	 * if (car.isPayment()) { car2.add(car); }
	 * 
	 * }return car2;
	 * 
	 * }
	 */

}
