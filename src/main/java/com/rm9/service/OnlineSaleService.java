package com.rm9.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rm9.entity.Order;
import com.rm9.entity.Product;
import com.rm9.model.ConfirmReq;
import com.rm9.model.ConfirmtResp;
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
	
	public ConfirmtResp confirm(ConfirmReq req) {
		Order order = new Order();
		order.setName(req.getName());
		order.setAddress(req.getAddress());
		order.setConfirmDate(req.getConfirmDate());
		order.setDiscount(req.getDiscount());
		order.setGrandTotal(req.getGrandTotal());
		order.setLineId(req.getLineId());
		order.setName(req.getName());
		order.setPhone(req.getPhone());
		Order.Product orderProduct = null;
		List<Order.Product> orderProductList = new ArrayList<Order.Product>();
		for(ConfirmReq.Product productDetail:req.getProductList()) {
			orderProduct = new Order.Product();
			orderProduct.setAmount(productDetail.getAmount());
			orderProduct.setNumber(productDetail.getNumber());
			orderProduct.setPrice(productDetail.getPrice());
			orderProduct.setProductName(productDetail.getProductName());
			orderProductList.add(orderProduct);
		}
		
		order.setProductList(orderProductList);
		onlineSaleRepository.saveOrder(order);
		
		ConfirmtResp confirmtResp = new ConfirmtResp();
		confirmtResp.setApiStatus(1);
		confirmtResp.setApiMessage("success");
		return confirmtResp;
	}
	
}
