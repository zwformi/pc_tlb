package com.yunrer.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.common.ProcessResult;
import com.yunrer.entity.ModuleForArticle;
import com.yunrer.service.ModuleForArticleService;

@Controller
@RequestMapping("/module")
public class ModuleForArticleControl {

	@Resource
	private ModuleForArticleService moduleforarticleService;
	/**
	 * 添加模块
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult addModule(HttpServletRequest request) {
		return moduleforarticleService.addModule(request);
	}
	
	/**
	 * 删除模块
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult deleteModule(HttpServletRequest request) {
		return moduleforarticleService.deleteModule(request);
	}
	
	/**
	 * 更新模块
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ProcessResult modifyModule(HttpServletRequest request) {
		return moduleforarticleService.modifyModule(request);
	}
	
	/**
	 * 查询模块
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public ModuleForArticle queryModule(HttpServletRequest request) {
		return moduleforarticleService.queryModule(request);
	}
	
	/**
	 * 查询模块总数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryCount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryModuleCount(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("count", moduleforarticleService.queryModuleCount(request));
		return map;
	}
	
	/**
	 * 分页查询模块列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/queryList", method = RequestMethod.POST)
	@ResponseBody
	public List<ModuleForArticle> queryModuleList(HttpServletRequest request) {
		List<ModuleForArticle> list = moduleforarticleService.queryModuleList(request);
		return list;
	}	
}
