package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.ArticleDao;
import com.yunrer.dao.ModuleForArticleDao;
import com.yunrer.dao.StaticResourceDao;
import com.yunrer.entity.ModuleForArticle;
import com.yunrer.entity.StaticResource;

@Service("StaticResourceService")
public class StaticResourceService {

	@Resource
	private StaticResourceDao staticresourceleDao;
	/**
	 * 添加静态资源
	 * @param request
	 * @return
	 */
	public ProcessResult addStaticResource(HttpServletRequest request,String path) {
		ProcessResult pr = new ProcessResult();
		try{
			//module_name,module_url,belongto_tf,sortid
			StaticResource s = new StaticResource();
			s.setSkey(request.getParameter("key"));
			s.setSvalue(request.getParameter("value"));
			s.setPath(path); //uploadpath
			staticresourceleDao.addStaticResource(s);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			ex.printStackTrace();
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	/**
	 * 删除静态资源
	 * @param request
	 * @return
	 */
	public ProcessResult deleteStaticResource(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");			
			staticresourceleDao.deleteStaticResource(ids);
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
    
	/**
	 * 更新静态资源
	 * @param request
	 * @return
	 */
	public ProcessResult modifyStaticResource(HttpServletRequest request,String path) {
		ProcessResult pr = new ProcessResult();
		try{
			StaticResource s = new StaticResource();
			s.setId(Integer.parseInt(request.getParameter("id")));
			s.setSkey(request.getParameter("key"));
			s.setSvalue(request.getParameter("value"));
			s.setPath(path);
			staticresourceleDao.modifyStaticResource(s);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询静态资源
	 * @param request
	 * @return
	 */
	public StaticResource queryStaticResource(HttpServletRequest request) {
		StaticResource s = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			s = staticresourceleDao.queryStaticResource(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
	
	/**
	 * 查询静态资源总数
	 * @param request
	 * @return
	 */
	public int queryStaticResourceCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = staticresourceleDao.queryStaticResourceCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	/**
	 * 分页查询静态资源列表
	 * @param request
	 * @return
	 */
	public List<StaticResource> queryModuleList(HttpServletRequest request) {
		List<StaticResource> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			list = staticresourceleDao.querStaticResourceList(keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据key获取静态资源
	 * @param request
	 * @return
	 */
	public List<StaticResource> queryStaticResourceBykeys(HttpServletRequest request) {
	
		String keys = request.getParameter("keys");
		String[] key = keys.split(",");			
		return staticresourceleDao.queryStaticResourceBykey(key);
	}

}
