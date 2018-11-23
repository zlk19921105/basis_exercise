package com.example.boot.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件下载（文件路径包含名称）
 * @author zhoulk
 * @date 2018-10-27
 */

public class FileDownloadUtils {

    /**
     * 单文件下载
     * @param response 响应
     * @param downLoadPath 文件路径（包含名称）
     * @param fileName 文件名
     */
	public static void fileDownload(HttpServletResponse response,String downLoadPath,String fileName){
		
		try {
			InputStream inStream = new FileInputStream(downLoadPath);//输入流
			response.reset();
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			byte[] buffer = new byte[1024];
            //输入流
			BufferedInputStream bis  = new BufferedInputStream(inStream);
            // 输出流
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer);
				i = bis.read(buffer);
			}
			//关闭流
			try {
				bis.close();
				os.close();
				os.flush();
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 多文件下载
	 * @param request 请求
	 * @param res 响应
	 * @param paths 多文件路径（多个逗号隔开，路径包含文件名称）
	 * @throws IOException 异常
	 */
	public static void fileDownloads(HttpServletRequest request,HttpServletResponse res,String paths) throws IOException{
        //创建压缩文件需要的空的zip包  
		String zipBasePath =request.getSession().getServletContext().getRealPath("")+"../upload";
        //String zipBasePath=request.getSession().getServletContext().getRealPath("/upload/zip");  
        String zipName = "temp.zip";
        String zipFilePath = zipBasePath+File.separator+zipName;  

        //创建需要下载的文件路径的集合
        List<String> filePaths = new ArrayList<>();
        String[] paths1 = paths.split(",");
        for(String pa:paths1){
            filePaths.add(pa);
        }
        //创建页面返回方式为输出流，会自动弹出下载框
        OutputStream out = res.getOutputStream();
        //压缩文件
        File zip = new File(zipFilePath);  
        if (!zip.exists()){     
            zip.createNewFile();     
        }
        //创建zip文件输出流  
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zip));
        zipFile(zipBasePath,zipName, zipFilePath,filePaths,zos);
        zos.close();
        //设置下载的压缩文件名称
        res.setHeader("Content-disposition", "attachment;filename="+zipName);

        //将打包后的文件写到客户端，输出的方法同上，使用缓冲流输出  
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFilePath));  
        byte[] buff = new byte[bis.available()];  
        bis.read(buff);
        bis.close();
        //输出数据文件
        out.write(buff);
        //释放缓存
        out.flush();
        //关闭输出流
        out.close();
        
        File file = new File(zipFilePath);
        //删除压缩包
        if(file.exists()&&file.isFile()){
        	file.delete();
        }
	}
	
	/**
     * 压缩文件
     * @param zipBasePath 临时压缩文件基础路径
     * @param zipName 临时压缩文件名称
     * @param zipFilePath 临时压缩文件完整路径
     * @param filePaths 需要压缩的文件路径集合
     * @throws IOException
     */
    private static String zipFile(String zipBasePath, String zipName, String zipFilePath, List<String> filePaths,ZipOutputStream zos) throws IOException {

        //循环读取文件路径集合，获取每一个文件的路径  
        for(String filePath : filePaths){
            //根据文件路径创建文件
            File inputFile = new File(filePath);
            //判断文件是否存在
            if(inputFile.exists()) {
                //判断是否属于文件，还是文件夹
                if (inputFile.isFile()) {
                    //创建输入流读取文件  
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));  

                    //将文件写入zip内，即将文件进行打包  
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));  

                    //写入文件的方法，同上                  
                    int size;
                    //设置读取数据缓存大小
                    byte[] buffer = new byte[1024];
                    while ((size = bis.read(buffer)) > 0) {  
                        zos.write(buffer, 0, size);  
                    }  
                    //关闭输入输出流  
                    zos.closeEntry();  
                    bis.close(); 

                } else {  //如果是文件夹，则使用穷举的方法获取文件，写入zip  
                    try {  
                        File[] files = inputFile.listFiles();  
                        List<String> filePathsTem = new ArrayList<>();
                        filePathsTem.forEach(path->{
                            filePathsTem.add(path);
                        });
                       /* for (File fileTem:files) {
                            filePathsTem.add(fileTem.toString());
                        }  */
                        return zipFile(zipBasePath, zipName, zipFilePath, filePathsTem,zos);
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            }  
        }  
        return null;
    } 

}
