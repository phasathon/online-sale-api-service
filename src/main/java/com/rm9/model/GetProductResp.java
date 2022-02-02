package com.rm9.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GetProductResp extends TemplateJsonResponse implements Serializable{

	@JsonIgnore
	private static final long serialVersionUID = 1L;

	@JsonProperty("checkCustomerStatusRespBean")
	private transient GetProductRespBean getProductRespBean;

	@Data
	public class GetProductRespBean {
		private List<Product> productList;
	}
	
	@Data
	public class Product{
		private String productName;
		private Double productPrice;
	}

}
