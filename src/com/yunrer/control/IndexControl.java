package com.yunrer.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunrer.entity.Ad;
import com.yunrer.service.AdService;

/**
 * 跳转到登陆页
 * @author wangpeng
 */
@Controller
@RequestMapping("/index")
public class IndexControl {

	@Resource
	private AdService adService;
	@RequestMapping(method = RequestMethod.GET)
	public String toIndex(HttpServletRequest request) {
		
		return "index";
	}
	
}