package com.yunrer.control;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.yunrer.common.FileUtils;

@SuppressWarnings("unchecked")
public class UploadControl {
	private String allowSuffix = "jpg,png,gif,jpeg";// 允许文件格式
	private long allowSize = 4L;// 允许文件大小
	private String fileName;
	private String[] fileNames;

	public String getAllowSuffix() {
		return allowSuffix;
	}

	public void setAllowSuffix(String allowSuffix) {
		this.allowSuffix = allowSuffix;
	}

	public long getAllowSize() {
		return allowSize * 1024 * 1024;
	}

	public void setAllowSize(long allowSize) {
		this.allowSize = allowSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public void setFileNames(String[] fileNames) {
		this.fileNames = fileNames;
	}

	/**
	 * 功能：重新命名文件
	 */
	private String getFileNameNew() {
		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return fmt.format(new Date());
	}

	/**
	 * 功能：多文件上传
	 * 后续使用
	 */
	public void uploads(MultipartFile[] files, String destDir,
			HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		try {
			fileNames = new String[files.length];
			int index = 0;
			for (MultipartFile file : files) {
				String suffix = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf(".") + 1);
				int length = getAllowSuffix().indexOf(suffix);
				if (length == -1) {
					throw new Exception("不允许上传" + suffix + "类型的文件！");
				}
				if (file.getSize() > getAllowSize()) {
					throw new Exception("文件超过限制的大小！");
				}
				String realPath = request.getSession().getServletContext()
						.getRealPath("/");
				File destFile = new File(realPath + destDir);
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
				String fileNameNew = getFileNameNew() + "." + suffix;//
				File f = new File(destFile.getAbsoluteFile() + "\\"
						+ fileNameNew);
				file.transferTo(f);
				f.createNewFile();
				fileNames[index++] = basePath + destDir + fileNameNew;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 功能：文件上传
	 */
	public Map upload(MultipartFile file, String destDir,
			HttpServletRequest request) throws Exception {
		Map map = new HashMap();
		
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		try {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1);
			int length = getAllowSuffix().indexOf(suffix);
			if (length == -1) {
				map.put("status", 0);
				map.put("msg", "不允许上传" + suffix + "类型的文件！");
			}
			
			if (file.getSize() > getAllowSize()) {
				map.put("status", 0);
				map.put("msg", "文件超过限制的大小！");
			}

			String realPath = request.getSession().getServletContext()
					.getRealPath("/");
			File destFile = new File(realPath + destDir);
			if (!destFile.exists()) {
				destFile.mkdirs();
			}
			String fileNameNew = getFileNameNew() + "." + suffix;
			File f = new File(destFile.getAbsoluteFile() + "/" + fileNameNew);
			file.transferTo(f);
			f.createNewFile();
			fileName = basePath + destDir + fileNameNew;
			
			map.put("status", 1);
			map.put("msg", "上传文件成功！");
			map.put("name", fileNameNew);
			map.put("path", destDir + fileNameNew);
			map.put("size", file.getSize());
			map.put("ext", suffix);
			map.put("ylj", realPath + destDir + fileNameNew);
			//删除原文件
			if(request.getParameter("DelFilePath") != null){
				String DelFilePath = request.getParameter("DelFilePath");
				FileUtils.deleteFile(realPath + DelFilePath);
			}
		} catch (Exception e) {
			map.put("status", 0);
			map.put("msg", "上传过程中发生意外错误！");
			e.printStackTrace();
		}
		return map;
	}
}