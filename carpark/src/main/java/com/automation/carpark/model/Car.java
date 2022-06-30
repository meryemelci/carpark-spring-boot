package com.automation.carpark.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cars")
public class Car {

	@Id
	private String id;

	private String model;
	private String plate;
	private boolean payment;
	private boolean status;

	@JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm")
	@CreatedDate
	private Date createdDate=new Date(System.currentTimeMillis());

}
