package com.rm9.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.rm9.entity.Order;
import com.rm9.entity.Product;

@Repository
public class OnlineSaleRepositoryImpl implements OnlineSaleRepository {

	private final Logger log = LoggerFactory.getLogger(OnlineSaleRepositoryImpl.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Product> getProduct() {
		List<Product> resultList = new ArrayList<>();
		try {
			resultList = mongoTemplate.findAll( Product.class);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
		return resultList;
	}

	@Override
	public void saveOrder(Order order) {
		try {
			mongoTemplate.save(order);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
		}
	}
	
	

}
