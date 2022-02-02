package com.rm9.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Document(collection = "order")
@Data
public class Order {
	@Field("name")
	private String name;
	@Field("confirm_date")
	private String confirmDate;
	@Field("address")
	private String address;
	@Field("phone")
	private String phone;
	@Field("line_id")
	private String lineId;
	@Field("product_list")
	private List<Product> productList;
	@Field("discount")
	private Double discount;
	@Field("grand_total")
	private Double grandTotal;
	
	@Data
	public static class Product{
		@Field("product_mame")
		private String productName;
		@Field("price")
		private Double price;
		@Field("number")
		private Integer number;
		@Field("amount")
		private Double amount;
	}
}
