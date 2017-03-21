package com.demo.model;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainsitErrorController implements ErrorController {
	
	private final String ERROR_PATH = "/error";

	private static Logger LOG = LoggerFactory.getLogger(MainsitErrorController.class);
	
	@Autowired
	HttpServletResponse res = null;
	
	/**
	 * 映射错误页
	 * @return
	 */
	@RequestMapping(value=ERROR_PATH)
	public void handlerError() throws Exception{
		LOG.error("ERROR!!!!");
//		return "page/static/404.html";
		res.sendRedirect("page/static/404.html");
	}
	
	/**
	 * 错误页路径
	 */
	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
