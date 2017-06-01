package com.yunrer.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.service.StringMapService;

@Controller("/stringmap")
public class StringmapControl {

	@Resource
	private StringMapService stringmapService;
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody List<?>  getStringmap(HttpServletRequest request){
		
	return	stringmapService.getStringmap(request);
		
	}
}
