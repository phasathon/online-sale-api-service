package com.rm9.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm9.entity.Product;
import com.rm9.model.GetProductResp;
import com.rm9.repository.OnlineSaleRepository;


@Service
public class OnlineSaleService {
	
	@Autowired
	private OnlineSaleRepository onlineSaleRepository;
	
	public GetProductResp getProduct() {
		
		List<Product> productList = onlineSaleRepository.getProduct();
		
		GetProductResp getProductResp = new GetProductResp();
		GetProductResp.GetProductRespBean getProductRespBean = getProductResp.new GetProductRespBean();
		List<GetProductResp.Product> productListResp = new ArrayList<>();
		GetProductResp.Product product = null;
		for(Product productDetail : productList) {
			product = getProductResp.new Product();
			product.setProductName(productDetail.getProductName());
			product.setProductPrice(productDetail.getPrice());
			productListResp.add(product);
		}
		
		getProductRespBean.setProductList(productListResp);
		getProductResp.setGetProductRespBean(getProductRespBean);
		getProductResp.setApiStatus(1);
		getProductResp.setApiMessage("success");
		
		return getProductResp;
		
	}
	
}
