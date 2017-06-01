package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.dao.ArticleDao;
import com.yunrer.entity.Article;

@Service("ArticleService")
public class ArticleService {
	
	@Resource
	private ArticleDao articleDao;
	
	/**
	 * 到达文章页,获得文章列表及文章详情
	 */
	public String toArticle(HttpServletRequest request,String str) {
		String page = "index";
		try{
			int typeid = -1 ;
			int id = -1;
			if(str.indexOf("-")>0){
				typeid = Integer.parseInt(str.split("-")[0]);
				id = Integer.parseInt(str.split("-")[1]);
			}else{
				typeid = Integer.parseInt(str);
			}
			List<Article> articleList = articleDao.queryArticleList(typeid);
			if(id==-1&&!articleList.isEmpty()&&articleList.size()>0){
				id = articleList.get(0).getId();
			}
			Article article = articleDao.queryArticle(id);
			
			request.setAttribute("articleList", articleList);
			request.setAttribute("article", article);
			page = "other";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 根据模块获取单页列表
	 * @param request
	 * @param typeid
	 * @return
	 */
	public List<Article> getArticleByType(HttpServletRequest request,int typeid) {	
		return  articleDao.queryArticleList(typeid);			
	}
	
	
	/**
	 * 到达免费服务页
	 */
	public String toArticleMffw(HttpServletRequest request) {
		String page = "mffw";
		try{
			Article article = articleDao.queryArticle(23);
			request.setAttribute("article", article);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 到达公司大事记列表页
	 */
	public String togsdsjList(HttpServletRequest request) {
		String page = "index";
		try{
			int typeid=Integer.parseInt(request.getParameter("typeid"));
			List<Article> articleList = articleDao.queryArticleList(typeid);
			List<Article> articleList_dsj = articleDao.queryArticleList(99);
			request.setAttribute("articleList", articleList);
			request.setAttribute("articleList_dsj", articleList_dsj);
			page = "gsdsjlb";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}
	public String togsdsjList1(HttpServletRequest request) {
		String page = "index";
		try{
			int typeid=2;
			List<Article> articleList = articleDao.queryArticleList(typeid);
			List<Article> articleList_dsj = articleDao.queryArticleList(99);
			request.setAttribute("articleList", articleList);
			request.setAttribute("articleList_dsj", articleList_dsj);
			page = "gsdsjlb1";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 到达公司大事记详情页
	 */
	public String togsdsjDetail(HttpServletRequest request) {
		String page = "index";
		try{
			int typeid=Integer.parseInt(request.getParameter("typeid"));
			List<Article> articleList = articleDao.queryArticleList(typeid);
			request.setAttribute("articleList", articleList);
			int id = Integer.parseInt(request.getParameter("ID"));
			Article article = articleDao.queryArticle(id);
			request.setAttribute("article", article);
			page = "gsdsjxq";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}
	public String togsdsjDetail1(HttpServletRequest request) {
		String page = "index";
		try{
			int typeid=Integer.parseInt(request.getParameter("typeid"));
			List<Article> articleList = articleDao.queryArticleList(typeid);
			request.setAttribute("articleList", articleList);
			int id = Integer.parseInt(request.getParameter("ID"));
			Article article = articleDao.queryArticle(id);
			request.setAttribute("article", article);
			page = "gsdsjxq1";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return page;
	}
	
}