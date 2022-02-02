package com.rm9.model;

import lombok.Data;
import java.util.List;

@Data
public class ConfirmReq {
	
	private String name;
	private String confirmDate;
	private String address;
	private String phone;
	private String lineId;
	private List<Product> productList;
	private Double discount;
	private Double grandTotal;
	
	@Data
	public static class Product{
		private String productName;
		private Double price;
		private Integer number;
		private Double amount;
	}
	
}
