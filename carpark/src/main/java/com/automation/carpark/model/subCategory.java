package com.automation.carpark.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subcategory")
public class subCategory {
	
	@Id
	private  String id;
	private  String name;
	

}
