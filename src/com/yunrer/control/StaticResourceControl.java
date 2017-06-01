package com.yunrer.control;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yunrer.common.ProcessResult;
import com.yunrer.entity.StaticResource;
import com.yunrer.service.StaticResourceService;

@Controller
@RequestMapping("/staticresource")
public class StaticResourceControl extends UploadControl{

	@Resource
	private StaticResourceService staticresourceService;
	/**
	 * 添加静态资源
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addStaticResource(HttpServletRequest request) throws Exception {
		String path="";
		//请求转化为多部件的请求		
	    MultipartHttpServletRequest mulReq= (MultipartHttpServletRequest) request; 
		if(!"".equals(request.getParameter("path"))){
			//解析为多部件文件
		    MultipartFile mfile = mulReq.getFile("uploadpath");
			Map rs=  super.upload(mfile, "/upload/images/", request);
			path=(String) rs.get("path");
		}

		return staticresourceService.addStaticResource(request,path);
	}
	
	/**
	 * 删除静态资源
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteStaticResource(HttpServletRequest request) {
		return staticresourceService.deleteStaticResource(request);
	}
	
	/**
	 * 更新静态资源
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyStaticResource(HttpServletRequest request) throws Exception {
		String path="";
		//请求转化为多部件的请求		
	    MultipartHttpServletRequest mulReq= (MultipartHttpServletRequest) request; 
		if(request.getParameter("path")!=null){
			//解析为多部件文件
		    MultipartFile mfile = mulReq.getFile("uploadpath");
			Map rs=  super.upload(mfile, "/upload/images/", request);
			path=(String) rs.get("path");
		}
		return staticresourceService.modifyStaticResource(request,path);
	}
	
	/**
	 * 查询静态资源
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public StaticResource queryStaticResource(HttpServletRequest request) {
		return staticresourceService.queryStaticResource(request);
	}
	
	/**
	 * 查询静态资源总数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryStaticResourceCount(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", staticresourceService.queryStaticResourceCount(request));
		return map;
	}
	
	/**
	 * 分页查询静态资源列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<StaticResource> queryStaticResourceList(HttpServletRequest request) {
		List<StaticResource> list = staticresourceService.queryModuleList(request);
		return list;
	}
	
	@RequestMapping(value = "/queryListbykeys", method = RequestMethod.POST)
	@ResponseBody
	public List<StaticResource> queryStaticResourceListbykeys(HttpServletRequest request) {
		List<StaticResource> list = staticresourceService.queryStaticResourceBykeys(request);
		return list;
	}
	
}
