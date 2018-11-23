package com.example.boot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传工具类
 * @author zhoulk
 *
 */
public class FileUploadUtils {
    static final String ZIP="zip";
	static final String RAR="rar";
	/**
	 * 文件上传(格式，大小50)
	 * @param file 请求文件对象
	 * @param filePath 存放路径
	 * @return  fileName,fileCode对应的Map
	 */
	public static HashMap<String,String> uploadFiles(MultipartFile file, String filePath){
		//文件名称(数据库)
		String fileName;
		//文件存放名称(服务器)
		String fileCode ;
		try{
			if(!file.isEmpty()){
				fileName =file.getOriginalFilename();
				fileCode  = getFileCode()+fileName.substring(fileName.lastIndexOf("."));
				File upload = new File(filePath);
				//判断上传文件的保存目录是否存在
				if (!upload.exists() && !upload.isDirectory()) {
					System.out.println(filePath+"目录不存在，需要创建");
					//创建目录
					upload.mkdir();
				}
				InputStream in = file.getInputStream();
				//创建一个文件输出流
				FileOutputStream out = new FileOutputStream(filePath + File.separator + fileCode);
				byte[] buffer = new byte[1024];
				//判断输入流中的数据是否已经读完的标识
				int len;
				//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
				while((len=in.read(buffer))>0){
					//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
					out.write(buffer, 0, len);
				}
				out.flush();
				out.close();
				in.close();

				HashMap<String,String> map =  new HashMap<>(3);
				map.put("fileName", fileName);
				map.put("formart", fileName.substring(fileName.lastIndexOf(".")+1));
				map.put("fileCode", fileCode);
				map.put("fileUrl", filePath);
				return map;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return  null;
	}

	/**
	 * 随机数生成
	 * @return 随机数
	 */
	private static String getFileCode(){
		Random rand = new Random();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sf.format(new Date())+(Integer) (rand.nextInt(9999)+10000);
	}

	/**
	 * 解压zip格式压缩包
	 * 对应的是ant.jar
	 */
	@SuppressWarnings("unused")
	private static void unzip(String sourceZip,String destDir) throws Exception{
		try{
			Project p = new Project();
			Expand e = new Expand();
			e.setProject(p);
			e.setSrc(new File(sourceZip));
			e.setOverwrite(false);
			e.setDest(new File(destDir));
	       /*
	           ant下的zip工具默认压缩编码为UTF-8编码，
	           而winRAR软件压缩是用的windows默认的GBK或者GB2312编码
	           所以解压缩时要制定编码格式
	           */
			e.setEncoding("gbk");
			e.execute();
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * 解压rar格式压缩包。
	 * 对应的是java-unrar-0.3.jar，但是java-unrar-0.3.jar又会用到commons-logging-1.1.1.jar
	 */
	   /*private static void unrar(String sourceRar,String destDir) throws Exception{
	       Archive a = null;
	       FileOutputStream fos = null;
	       try{
	           a = new Archive(new File(sourceRar));
	           FileHeader fh = a.nextFileHeader();
	           while(fh!=null){
	               if(!fh.isDirectory()){
	                   //1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
	                   String compressFileName = fh.getFileNameString().trim();
	                   String destFileName = "";
	                   String destDirName = "";
	                   //非windows系统
	                   if(File.separator.equals("/")){
	                       destFileName = destDir + compressFileName.replaceAll("\\\\", "/");
	                       destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));
	                   //windows系统
	                   }else{
	                       destFileName = destDir + compressFileName.replaceAll("/", "\\\\");
	                       destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));
	                   }
	                   //2创建文件夹
	                   File dir = new File(destDirName);
	                   if(!dir.exists()||!dir.isDirectory()){
	                       dir.mkdirs();
	                   }
	                   //3解压缩文件
	                   fos = new FileOutputStream(new File(destFileName));
	                   a.extractFile(fh, fos);
	                   fos.close();
	                   fos = null;
	               }
	               fh = a.nextFileHeader();
	           }
	           a.close();
	           a = null;
	       }catch(Exception e){
	           throw e;
	       }finally{
	           if(fos!=null){
	               try{fos.close();fos=null;}catch(Exception e){e.printStackTrace();}
	           }
	           if(a!=null){
	               try{a.close();a=null;}catch(Exception e){e.printStackTrace();}
	           }
	       }
	   }    */

	/**
	 * 解压入口
	 * @param sourceFile 需要解压文件（路径+名称）
	 * @param destDir （解压地址）
	 * @throws Exception 异常
	 */
	public static void deCompress(String sourceFile,String destDir) throws Exception{
		//保证文件夹路径最后是"/"或者"\"
		char lastChar = destDir.charAt(destDir.length()-1);
		if(lastChar!='/'&&lastChar!='\\'){
			destDir += File.separator;
		}
		//根据类型，进行相应的解压缩
		String type =sourceFile.substring(sourceFile.lastIndexOf("."));
		if(ZIP.equals(type)){
			FileUploadUtils.unzip(sourceFile, destDir);
		}else if(RAR.equals(type)){
			throw new Exception("暂时不支持rar压缩包！");
			//FileUploadUtils.unrar(sourceFile, destDir);
		}else{
			throw new Exception("只支持zip和rar格式的压缩包！");
		}
	}

}
