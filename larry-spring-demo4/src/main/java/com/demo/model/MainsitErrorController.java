//package com.demo.model;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.web.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//public class MainsitErrorController implements ErrorController {
//	
//	private final String ERROR_PATH = "/error";
//
//	private static Logger LOG = LoggerFactory.getLogger(MainsitErrorController.class);
//	
//	@RequestMapping(value=ERROR_PATH)
//	public String handlerError(){
//		LOG.error("ERROR!!!!");
//		return "page/static/404.html";
//	}
//	
//	@Override
//	public String getErrorPath() {
//		// TODO Auto-generated method stub
//		return ERROR_PATH;
//	}
//
//}
