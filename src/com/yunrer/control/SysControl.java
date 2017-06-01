package com.yunrer.control;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yunrer.common.FileUtils;
import com.yunrer.service.UserCompanyService;

/**
 * 系统功能
 * @author wangpeng
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/sys")
@SuppressWarnings("unchecked")
public class SysControl extends UploadControl {

	@Resource
	private UserCompanyService userCompanyService;
	/**
	 * 普通图片上传
	 */
	@RequestMapping("/uploadPic")
	@ResponseBody
	public Map uploadPic(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/images/", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 一键需求文件上传
	 */
	@RequestMapping("/uploadPic_demand")
	@ResponseBody
	public Map uploadPic_demand(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/file/demand/", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 用户图像上传
	 */
	@RequestMapping("/uploadPic_head")
	@ResponseBody
	public Map uploadPic_head(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/images/user_head/", request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 公司资质图片上传
	 */
	@RequestMapping("/uploadPic_company")
	@ResponseBody
	public Map uploadPic_company(@RequestParam("filedata") MultipartFile file,
			HttpServletRequest request, HttpSession session) {
		Map map = null;
		try {
			map = super.upload(file, "/upload/images/company_img/", request);
			if(map.get("status").toString().equals("1")){//上传图片成功
				request.setAttribute("img_url", map.get("path").toString());
				//将图片保存到数据库
				int id = userCompanyService.addCompanyImg(session, request);
				if(id<=0){//如果保存数据库不成功，则删除已上传的图片
					FileUtils.deleteFile(map.get("ylj").toString());
					map.put("status", "0");
				}else{
					map.put("id", id);
				}
				map.remove("ylj");//将实际路径移除，以免带到前台，泄露计算机存储位置
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}