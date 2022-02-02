package com.rm9.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "product")
@Data
public class Product {
	
	@Field("product_name")
	private String productName;
	
	@Field("price")
	private Double price;
}
