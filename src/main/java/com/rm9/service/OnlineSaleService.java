package com.rm9.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.rm9.controller.Controller;
import com.rm9.entity.Order;
import com.rm9.entity.Product;
import com.rm9.model.ConfirmReq;
import com.rm9.model.ConfirmtResp;
import com.rm9.model.GetProductResp;
import com.rm9.model.PrintReq;
import com.rm9.repository.OnlineSaleRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class OnlineSaleService {
	
	@Autowired
	private OnlineSaleRepository onlineSaleRepository;
	
	private final Logger log = LoggerFactory.getLogger(OnlineSaleService.class);
	
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
		order.setRequestId(req.getRequestId());
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

	public byte[] print(PrintReq req) throws JRException, IOException {
//		File file = ResourceUtils.getFile("classpath:reportTemplates/Blank_A4.jrxml");
		
		 String userDirectory = FileSystems.getDefault()
			        .getPath("")
			        .toAbsolutePath()
			        .toString();
		 
		 log.info("--------------------");
		 log.info(userDirectory);
		 log.info("--------------------");
		
		 
		 File file = ResourceUtils.getFile("classpath:application.properties");
		 String abpath = file.getAbsolutePath();
		 log.info("--------------------");
		 log.info(abpath);
		 log.info("--------------------");
		 
//		File file = new File("/app/file:/app/resources/"+"Blank_A4.jrxml");
//		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
////		File file = new File(classloader.getResource("Blank_A4.jrxml").getFile());
//		String abpath = file.getAbsolutePath();
//		JasperReport jasperReport = JasperCompileManager.compileReport(abpath);
//		List<PrintReq> reqList = new ArrayList<>();
//		reqList.add(req);
//		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(reqList);
//		Map<String,Object> parameters = new HashMap<>();
//		parameters.put("CreatedBy", "GHB");
//		parameters.put("startDate", "123123");
//		parameters.put("endDate", "123123");
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,datasource);
//		String fileLocation = new File("src\\main\\resources").getAbsolutePath() + "\\" + req.getRequestId();
//		JasperExportManager.exportReportToPdfFile(jasperPrint,fileLocation);
//		byte[] bytes = Files.readAllBytes(Paths.get(fileLocation));
		return null;
	}
	
}
