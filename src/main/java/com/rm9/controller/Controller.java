package com.rm9.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rm9.model.GetProductResp;
import com.rm9.service.OnlineSaleService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class Controller {
	
	private final Logger log = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	private OnlineSaleService onlineSaleService;
	
	@PostMapping("/product")
	public GetProductResp getProduct() throws IOException {
		log.info("[ENTER][getProduct]");
		GetProductResp getProductResp = onlineSaleService.getProduct();
		log.info("[EXIT][getProduct] with data: "+ getProductResp.toString());
		return getProductResp;
	}

}
