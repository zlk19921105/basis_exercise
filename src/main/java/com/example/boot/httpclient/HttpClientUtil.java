package com.example.boot.httpclient;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**http连接工具类
 * Administrator zhoulk
 * date: 2018/10/1 0001
 */
public class HttpClientUtil {
    private  Logger logger = Logger.getLogger(getClass());

    /**
     * 获取http返回数据    包含  xml格式、json格式
     * @param urlStr 地址
     * @param statement 参数,json或者xml
     * @param requestMethod 请求方式
     * @param  temporize 延迟时间
     * @return http 返回结果
     * @throws Exception 异常
     */
    public static HashMap<String,String> request(String urlStr,String statement,String requestMethod,Integer temporize) throws MalformedURLException,IOException,Exception {
        HashMap<String,String> map = new HashMap<>(2);
        HashMap<String,String> headerMap =  new HashMap<>();
        String header = "";
        String body = "";
        if(!statement.isEmpty()){
            if(isJson(statement)){
                JSONObject json = JSONObject.fromObject(statement);
                Iterator iterator = json.keys();
                while(iterator.hasNext()){
                    String key = (String)iterator.next();
                    String value = json.getString(key);
                    if("header".equals(key)){
                        header = value;
                    }
                    if("body".equals(key)){
                        body = value;
                    }
                }
                //判断是xml或json
                if(isXML(header) && isXML(body)){
                    map = doHttpPostMultipart(header,body,urlStr,temporize);
                }else if(isJson(header) && isJson(body)){
                    JSONObject json1 = JSONObject.fromObject(header);
                    Iterator iterator1 = json1.keys();
                    while(iterator1.hasNext()){
                        String key1 = (String)iterator1.next();
                        String value1 = json1.getString(key1);
                        headerMap.put(key1, value1);
                    }
                    StringBuffer buffer;
                    OutputStreamWriter out;
                    URL url;

                    try {
                        url = new URL(urlStr);
                        // http协议传输
                        HttpURLConnection http = (HttpURLConnection) url.openConnection();
                        http.setDoOutput(true);
                        http.setDoInput(true);
                        http.setUseCaches(false);
                        http.setInstanceFollowRedirects(false);
                        http.setConnectTimeout(temporize);
                        // 设置请求方式（GET/POST）
                        http.setRequestMethod(requestMethod);
                        http.setRequestProperty("Content-Type","application/json;charset=UTF-8");
                        if(!headerMap.isEmpty()){
                            for(Map.Entry<String, String> entry:headerMap.entrySet()){
                                http.setRequestProperty(entry.getKey(),entry.getValue());
                            }
                        }
                        http.connect();
                        //传参
                        out = new OutputStreamWriter(http.getOutputStream(), "utf-8");
                        out.append(body);
                        out.flush();
                        out.close();

                        Integer responseCode = http.getResponseCode();
                        if(responseCode==302){//重定向，获取url
                            String location =  http.getHeaderField("Location");
                            if(location != null && !location.isEmpty()) {
                                urlStr = location;
                            }
                            url = new URL(urlStr);
                            http = (HttpURLConnection) url.openConnection();
                        }

                        responseCode = http.getResponseCode();
                        if(responseCode!=200){
                            throw new Exception("请求失败，错误状态码："+http.getResponseCode()+";");
                        }

                        BufferedReader reader = new BufferedReader(new InputStreamReader(http.getInputStream(),"UTF-8"));
                        String line;
                        buffer = new StringBuffer();
                        while ((line = reader.readLine()) != null) {
                            buffer.append(line);
                        }
                        reader.close();
                        http.disconnect();
                        System.out.println(buffer.toString());

                        map.put("responseCode",Integer.valueOf(http.getResponseCode()).toString());
                        map.put("body", buffer.toString());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    throw new Exception("报文模板 header或  body 不满足 xml格式 或 json格式："+ statement +";");
                }
            }
        }
        return map;
    }

    /**
     * 判断是json字符串
     * @param content json字符串
     * @return true or false
     */
    private static boolean isJson(String content){
        try {
            JSONObject.fromObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否是xml结构
     */
    private static boolean isXML(String rtnMsg){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            builder.parse( new InputSource( new StringReader( rtnMsg )));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 发送Http Multipart请求
     * XML格式
     * @param headXml header
     * @param bodyXml body
     * @param url url
     * @param timeout 延时
     * @return 处理后结果
     * @throws Exception 异常
     */
    private static HashMap<String,String> doHttpPostMultipart(String headXml, String bodyXml,String url, int timeout) throws Exception {
        HashMap<String,String> map = new HashMap<>(2);
        String responseCode ;
        String responseBody;
        try {
            HttpClient httpclient = new HttpClient();
            httpclient.getParams().setParameter("http.protocol.version",HttpVersion.HTTP_1_1);
            httpclient.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            httpclient.getParams().setParameter("http.protocol.content-type","multipart/form-data");
            httpclient.getParams().setParameter("http.socket.timeout",timeout * 1000);
            PostMethod httppost = new PostMethod(url);

            StringPart[] parts = new StringPart[2];
            parts[0] = new StringPart("xmlhead", headXml, "UTF-8");
            parts[1] = new StringPart("xmlbody", bodyXml, "UTF-8");

            MultipartRequestEntity requestEntity = new MultipartRequestEntity(parts, httpclient.getParams());
            httppost.setRequestEntity(requestEntity);
            try {
                httpclient.executeMethod(httppost);
                if (httppost.getStatusCode() == HttpStatus.SC_OK) {
                    responseCode = String.valueOf(httppost.getStatusCode());
                    responseBody = httppost.getResponseBodyAsString();
                } else {
                    throw new Exception("Unexpected failure: " + httppost.getStatusLine().toString());
                }
            } finally {
                httppost.releaseConnection();
            }
        } catch (Exception e) {
            String sMsg = "Http协议post方法发送字符流时候出现异常：" + e.getMessage();
            throw new Exception(sMsg, e);
        }
        map.put("responseCode", responseCode);
        map.put("body", responseBody);
        return map;
    }


}
