package com.example.boot.json_xml;

import com.alibaba.druid.util.StringUtils;
import com.example.boot.bean.CompareExcetion;
//import net.sf.json.JSONArray;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Administrator zhoulk
 * date: 2018/11/13 0013
 */
public class Test {
    public static void main(String[] args) {
       // System.out.println(isNumeric("11.0"));
        Object json = new Object();
        json = "[23,33]";
        String json1 = "{\"s1\":1,\"s2\":\"2\"}";
        HashMap<String,Object> map  = null;
        if(map!=null&&!map.isEmpty()){
            System.out.println("notEmpty");
        }else{
            System.out.println("isEmpty");
        }
       // JSONObject jsonObject = JSONObject.fromObject(json1);
       /* System.out.println("s1" + jsonObject.get("s1") );
        System.out.println("s2" + jsonObject.get("s2") );*/
        /*try {
            isJSONArray(json,"preKey");
        } catch (CompareExcetion compareExcetion) {
            System.out.println(compareExcetion.getMessage());
        }*/

      /* Object preJson1 = new JSONTokener(json).nextValue();
        if(preJson1 instanceof JSONObject){
            System.out.println("JSONObject");
        }else if(preJson1 instanceof JSONArray){
            System.out.println("JSONArray");
        }else if(preJson1 instanceof String){
            System.out.println("String");
        }else{
            System.out.println("value");
        }*/

    }

    public static boolean isNumeric(String string){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(string).matches();
    }

    public static Boolean isJSONArray(Object resJson1,String preKey) throws CompareExcetion {
        boolean flag = true;
        if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
            Object object = new JSONTokener(resJson1.toString()).nextValue();
            if(!(object instanceof JSONArray)){
                throw new CompareExcetion("响应"+preKey+"对应值为"+resJson1.toString()+"不是数组！");
            }
        }else{
            throw new CompareExcetion("响应"+preKey+"对应值不是数组！");
        }
        return flag;

    }


}
