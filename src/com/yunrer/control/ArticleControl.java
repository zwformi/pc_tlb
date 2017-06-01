package com.yunrer.control;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunrer.entity.Article;
import com.yunrer.entity.UserInfo;
import com.yunrer.service.ArticleService;

/**
 * 用户管理
 * @author wangpeng
 */
@Controller
@SuppressWarnings("unchecked")
public class ArticleControl {
	
	@Resource
	private ArticleService articleService;
	
	/**
	 * 查询单页信息
	 */
	@RequestMapping(value = "/article/{str}",method = RequestMethod.GET)
	public String toArticle(HttpServletRequest request, @PathVariable String str) {
		return articleService.toArticle(request,str);
	}
	
	/**
	 * 根据模块获取单页列表
	 * @param request
	 * @param str
	 * @return
	 */
	@RequestMapping(value = "/moduleforarticle/{typeid}",method = RequestMethod.POST)
	@ResponseBody
	public List<Article> moduleforArticle(HttpServletRequest request, @PathVariable Integer typeid) {
		return articleService.getArticleByType(request,typeid);
	}
	/**
	 * 查询公司大事记列表
	 */
	@RequestMapping(value = "/article/gsdsj",method = RequestMethod.GET)
	public String toGsdsjlb(HttpServletRequest request) {
		return articleService.togsdsjList(request);
	}
	@RequestMapping(value = "/article/gsdsjlb1",method = RequestMethod.GET)
	public String toGsdsjlb1(HttpServletRequest request) {
		return articleService.togsdsjList1(request);
	}
	
	/**
	 * 查询公司大事记
	 */
	@RequestMapping(value = "/article/gsdsjxq",method = RequestMethod.GET)
	public String toGsdsjxq(HttpServletRequest request) {
		return articleService.togsdsjDetail(request);
	}
	@RequestMapping(value = "/article/gsdsjxq1",method = RequestMethod.GET)
	public String toGsdsjxq1(HttpServletRequest request) {
		return articleService.togsdsjDetail1(request);
	}
	
}