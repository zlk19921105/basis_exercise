package com.example.boot.util;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 系统文件管理控制器
 * @author zhoulk
 *
 */
@RestController
@RequestMapping("/sysFile")
public class SysFileController {

	/**
	 * 上传系统文件信息
	 * @param request
	 * @param response
	 * @param file 上传文件
	 * @return
	 */
	@RequestMapping("/addSysFile")
	public String addSysFile(HttpServletRequest request,HttpServletResponse  response ,@RequestParam(value = "file", required = false) MultipartFile file,String name){
		response.setHeader("Access-Control-Allow-Origin",  request.getHeader("Origin"));
		response.addHeader("Access-Control-Allow-Credentials", "true");
		String formart = ""; //文件类型
		long maxFileSize = 10*1048576L; //(10M)，文件大小限制
				String filePath =request.getSession().getServletContext().getRealPath("")+"\\upload";//文件存放地址
		try{
			HashMap<String,String> map= FileUploadUtils.uploadFiles(file, filePath);

		}catch(Exception ex){
			ex.printStackTrace();
		}
		return  null;
	}


}
