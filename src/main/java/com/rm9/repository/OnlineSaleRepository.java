package com.rm9.repository;

import java.util.List;

import com.rm9.entity.Order;
import com.rm9.entity.Product;

public interface OnlineSaleRepository {
	
	List<Product> getProduct();
	
	void saveOrder(Order order);
}
