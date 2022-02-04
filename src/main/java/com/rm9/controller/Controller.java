package com.rm9.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rm9.model.ConfirmReq;
import com.rm9.model.ConfirmtResp;
import com.rm9.model.GetProductResp;
import com.rm9.model.PrintReq;
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
	
	@PostMapping("/confirm")
	public ConfirmtResp confirm(@RequestBody ConfirmReq req) throws IOException {
		log.info("[ENTER][confirm]");
		ConfirmtResp confirmtResp = onlineSaleService.confirm(req);
		log.info("[EXIT][confirm] with data: "+ confirmtResp.toString());
		return confirmtResp;
	}
	
	@PostMapping(value = "print", produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	public ResponseEntity<byte[]> print(@RequestBody PrintReq req) throws IOException {

		byte[] content = null;
		try {
			content = onlineSaleService.print(req);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return ResponseEntity.ok().contentLength(content.length)
				.header(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + req.getRequestId()).body(content);
	}


}
