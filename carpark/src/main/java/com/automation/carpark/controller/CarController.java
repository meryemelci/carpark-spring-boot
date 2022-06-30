package com.automation.carpark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.automation.carpark.model.Car;
import com.automation.carpark.service.CarService;

@Controller
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping("/car")
	public String showCar(Model model) {
		model.addAttribute("listcars", carService.getAllCar());
		return "car";
	}

	@GetMapping("/addcar")
	public String newCar(Model model) {
		
		model.addAttribute("cars", new Car());
		return "addcar";
	}

	@PostMapping("/addcar")
	public String newCars(Model model) {

		Car car = new Car();
		model.addAttribute("cars", car);

		return "addcar";
	}

	public String saveCar(@ModelAttribute("car") Car car) {
		carService.Save(car);
		return "redirect:/car";
	}

	public String saveCars(@ModelAttribute("car") Car car) {
		carService.Save(car);
		return "redirect:/car";
	}
	
	
	@GetMapping("/updateCar")
	public String index(Model model) {
		model.addAttribute("cars", new Car());
		return "updateCar";
	}
	
	@GetMapping("/muhasebe")
	public String muhasebe(Model model) {
		model.addAttribute("cars", new Car());
		return "muhasebe";
	}
	
	
//	@GetMapping("/updateCar")
//	public String showFormForUpdate(@RequestParam(value= "id") String id, Model model) {
//
//		Car car = carService.getCarById(id).get();
//
//		model.addAttribute("cars", car);
//		return "updateCar";
//	}


	
//	@GetMapping("/updateCar/{id}")
//	public String updateCar(@PathVariable("id") String id,Model model) {
//		
//		Car car = carService.getCarById(id).get();
//		model.addAttribute("cars", car);
//
//		return "redirect:/updateCar";
//	}

	
		
//	@PostMapping("/updateCar")
//	public String updateCars(Model model) {
//
//		Car car = new Car();
//		model.addAttribute("cars", car);
//
//		return "updateCar";
//	}

}
