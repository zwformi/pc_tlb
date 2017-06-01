package com.yunrer.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.yunrer.common.ProcessResult;
import com.yunrer.dao.ArticleDao;
import com.yunrer.dao.ModuleForArticleDao;
import com.yunrer.entity.ModuleForArticle;

@Service("ModuleForArticleService")
public class ModuleForArticleService {
	@Resource
	private ModuleForArticleDao moduleforarticleDao;
	@Resource
	private ArticleDao articleDao;
	/**
	 * 添加模块
	 * @param request
	 * @return
	 */
	public ProcessResult addModule(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			//module_name,module_url,belongto_tf,sortid
			ModuleForArticle module = new ModuleForArticle();
			module.setModule_name(request.getParameter("module_name"));
			module.setModule_url(request.getParameter("module_url"));
			module.setBelongto_tf(Integer.parseInt(request.getParameter("belongto_tf")));
			module.setSortid(Integer.parseInt(request.getParameter("sortid")));
			moduleforarticleDao.addModule(module);
			
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
	 * 删除模块
	 * @param request
	 * @return
	 */
	public ProcessResult deleteModule(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			String id = request.getParameter("id");
			Object[] ids = id.split(",");			
			moduleforarticleDao.deleteModule(ids);
			pr.setSuccess(true);
			pr.setMessage("删除成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
    
	/**
	 * 更新模块
	 * @param request
	 * @return
	 */
	public ProcessResult modifyModule(HttpServletRequest request) {
		ProcessResult pr = new ProcessResult();
		try{
			ModuleForArticle module = new ModuleForArticle();
			module.setId(Integer.parseInt(request.getParameter("id")));
			module.setModule_name(request.getParameter("module_name"));
			module.setModule_url(request.getParameter("module_url"));
			module.setBelongto_tf(Integer.parseInt(request.getParameter("belongto_tf")));
			module.setSortid(Integer.parseInt(request.getParameter("sortid")));
			moduleforarticleDao.modifyModule(module);
			
			pr.setSuccess(true);
			pr.setMessage("保存成功");
		} catch (Exception ex) {
			pr.setSuccess(false);
			pr.setMessage(ex.getMessage());
		}
		return pr;
	}
	
	/**
	 * 查询模块
	 * @param request
	 * @return
	 */
	public ModuleForArticle queryModule(HttpServletRequest request) {
		ModuleForArticle module = null;
		try{
			int id = Integer.parseInt(request.getParameter("id"));
			module = moduleforarticleDao.queryModule(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return module;
	}
	
	/**
	 * 查询模块总数
	 * @param request
	 * @return
	 */
	public int queryModuleCount(HttpServletRequest request) {
		int count = 0;
		try{
			String keyword = request.getParameter("keyword");
			count = moduleforarticleDao.queryModuleCount(keyword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return count;
	}
	/**
	 * 分页查询模块列表
	 * @param request
	 * @return
	 */
	public List<ModuleForArticle> queryModuleList(HttpServletRequest request) {
		List<ModuleForArticle> list = null;
		try{
			String keyword = request.getParameter("keyword");
			int pageIndex = 0;
			int pageSize = 10;
			//type:0分页查询，1全部查询
			int type = Integer.parseInt(request.getParameter("type"));
			if(type==0){
				pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
				pageSize = Integer.parseInt(request.getParameter("pageSize"));
			}
			list = moduleforarticleDao.querModuleList(type,keyword, pageIndex, pageSize);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}
