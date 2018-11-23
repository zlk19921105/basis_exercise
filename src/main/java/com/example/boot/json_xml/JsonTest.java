package com.example.boot.json_xml;

import com.alibaba.druid.util.StringUtils;
import com.example.boot.bean.CompareExcetion;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import java.util.Iterator;

/**
 * Administrator zhoulk
 * date: 2018/11/12 0012
 */
public class JsonTest {
    public static void main(String[] args) {

        //数组存在问题
        String json3 ="{\"InterBOSS\":{\"Version\":\"adsaf\",\"TestFlag\":\"0\",\"BIPType\":[{\"BIPCode1\":\"gf\",\"ActivityCode\":\"adsaf\",\"ActionCode\":\"\"},{\"BIPCode\":\"gf\",\"ActivityCode\":\"adsaf\",\"ActionCode\":\"\"}]}}";
        String json1 ="{\"InterBOSS\":{\"Version\":\"adsaf1eee\",\"TestFlag\":\"0\",\"BIPType\":{\"BIPCode\":\"gf\",\"ActivityCode\":\"adsaf\",\"ActionCode\":\"\"}}}";
        String json2 ="{\"InterBOSS\":{\"id\":1001,\"Version\":{\"id\":1002,\"value\":\"adsaf\"},\"TestFlag\":{\"id\":1003,\"value\":\"\"},\"BIPType1\":{\"id\":1004,\"BIPCode\":{\"id\":1005,\"value\":\"gf11\"},\"ActivityCode\":{\"id\":1007,\"value\":\"adsaf\"},\"ActionCode\":{\"id\":1008,\"value\":\"\"}}}}";
        String json4 = "{\"InterBOSS\":{\"id\":1001,\"Version\":{\"id\":1002,\"Version11\":{\"id\":1021,\"value\":\"weer\"}},\"TestFlag\":{\"id\":1003,\"value\":\"0\"},\"BIPType\":{\"id\":1004,\"BIPCode\":{\"id\":1005,\"value\":\"gf\"},\"ActivityCode\":{\"id\":1002,\"value\":\"adsaf\"},\"ActionCode\":{\"id\":1002,\"value\":\"\"}}}}";
        try {
            getValCompare12(json1.replaceAll( "\\\\ ", " "),json2.replaceAll( "\\\\ ", " "));
            System.out.println("args = [" + args + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**  取值比对（预期比响应，校验是否多值）
     * @param resContent 响应
     * @param preContent 预期
     * @return 存在多层时比对
     */
    public static boolean getValCompare12(String resContent,String preContent) throws Exception {
        boolean  flag = true;
        //判断是不是json数据
        if(!isJson( resContent)||!isJson( preContent)){
            //不是json
            flag = false;
            throw new CompareExcetion("不是json，不能对比");
        }

        JSONObject preJson = JSONObject.fromObject(preContent);
        Iterator preIterator = preJson.keys();

        JSONObject resJson = JSONObject.fromObject(resContent);
        String id;
        while(preIterator.hasNext()){
            //拿到key
            String  preKey = (String) preIterator.next();
            //该方法只能拿到最外层的值
            String preValue = preJson.getString(preKey);
            if(preKey.equals("id")){
                id = preValue;
                continue;
            }
            Iterator resIterator = resJson.keys();
            // json2 key查找json1
            while(resIterator.hasNext()) {
                //拿到key
                String resKey = (String) resIterator.next();
                if(!resKey.equals(preKey)){
                    if(!resJson.containsKey(preKey)){
                        //看是否必选
                        flag = false;
                        throw new Exception("预期结果key："+preKey+"在响应结果中必须存在！");
                    }
                }else{
                    //key存在
                    String resValue = resJson.getString(resKey);
                    if(!StringUtils.isEmpty(preValue)&&preValue!=null&&!StringUtils.isEmpty(resValue)&&resValue!=null){
                        Object compareJson1 = new JSONTokener(preValue).nextValue();
                        Object beJson1 = new JSONTokener(resValue).nextValue();
                        if((compareJson1 instanceof JSONObject)&&(beJson1 instanceof JSONObject)){
                            //key 对应的value是JSONObject
                            System.out.println(preKey+"规则比较00====JSONObject");
                            getValCompare12(resValue,preValue);
                        }else if((compareJson1 instanceof JSONArray)&&(beJson1 instanceof JSONArray)){
                            //key 对应的value是JSONArray
                            //通过key拿到对应的key-valie,并转化为数组
                           /*JSONArray compareJsonArray = JSON.parseArray(compareValue);
                            JSONArray beJsonArray = JSON.parseArray(beValue);*/
                            JSONObject prevalue1=JSONObject.fromObject(preValue);
                            JSONObject resValue1=JSONObject.fromObject(resValue);
                            JSONArray preJsonArray =prevalue1.getJSONArray(preKey);
                            JSONArray resJsonArray =resValue1.getJSONArray(resKey);

                            if(preJsonArray.size()>resJsonArray.size()){
                                //预期个数多余实际数
                                flag = false;
                                throw new CompareExcetion("键"+preKey+"：预期个数多余实际数");
                            }

                            for (int i = 0; i <preJsonArray.size() ; i++) {
                                for(int j = 0; j <resJsonArray.size() ; j++){
                                    Object preObject = preJsonArray.get(i);
                                    Object resObject = resJsonArray.get(j);
                                    System.out.println(preKey+"规则比较0011====JSONArray");
                                    getValCompare12(resObject.toString(),preObject.toString());
                                }
                            }
                        }else if((compareJson1 instanceof String)&&(beJson1 instanceof String)) {
                            System.out.println(preKey+"===================String");
                        }else{
                            //该层到取值,比对value是否相同
                            System.out.println(preKey+"===================value");
                        }
                    }else{
                        System.out.println(preKey+"规则比较+++++++++++++other");
                    }
                }
            }
        }
        return  flag;
    }

    /**  取值比对
     * @param beCompareContent 被比对json
     * @param beCompareContent 被比对json
     * @param compareContent 比对json
     * @return 存在多层时比对？
     */
    public static boolean getValCompare(String beCompareContent,String compareContent) throws Exception {
        boolean  flag = true;
        //判断是不是json数据
        if(!isJson( beCompareContent)||!isJson( compareContent)){
            //不是json
            flag = false;
            throw new CompareExcetion("不是json，不能对比");
        }

        JSONObject beJson = JSONObject.fromObject(beCompareContent);

        JSONObject compareJson = JSONObject.fromObject(compareContent);
        Iterator compareIterator = compareJson.keys();

        while(compareIterator.hasNext()){
           // {"InterBOSS":{"Version":"adsaf","TestFlag":"0","BIPType":
            // [{"BIPCode":"gf","ActivityCode":"adsaf","ActionCode":""},{"BIPCode":"gf","ActivityCode":""}]}}
            //拿到key
            String  compareKey = (String) compareIterator.next();
            //该方法只能拿到最外层的值
            String compareValue = compareJson.getString(compareKey);
            // json2 key查找json1
            Iterator beIterator = beJson.keys();
            while(beIterator.hasNext()) {
                //拿到key
                String beKey = (String) beIterator.next();
                String id;
                if(!compareKey.equals(beKey)){
                    if(beKey.equals("id")){
                        continue;
                    }
                    if(!beJson.containsKey(compareKey)){
                        flag = false;
                        throw new Exception("键："+compareKey+"不存在");
                    }
                }else{
                    //key存在
                    String beValue = beJson.getString(beKey);
                    if(!StringUtils.isEmpty(compareValue)&&compareValue!=null&&!StringUtils.isEmpty(beValue)&&beValue!=null){
                        Object compareJson1 = new JSONTokener(compareValue).nextValue();
                        Object beJson1 = new JSONTokener(beValue).nextValue();
                        if((compareJson1 instanceof JSONObject)&&(beJson1 instanceof JSONObject)){
                            //key 对应的value是JSONObject
                            JSONObject beValueJson = JSONObject.fromObject(beValue);
                            id = beValueJson.getString("id");
                            System.out.println(compareKey+"规则比较00====");
                            getValCompare(beValue,compareValue);
                        }else if((compareJson1 instanceof JSONArray)&&(beJson1 instanceof JSONArray)){
                            //key 对应的value是JSONArray
                            //通过key拿到对应的key-valie,并转化为数组
                           /*JSONArray compareJsonArray = JSON.parseArray(compareValue);
                            JSONArray beJsonArray = JSON.parseArray(beValue);*/
                            JSONObject comparevalue1=JSONObject.fromObject(compareValue);
                            JSONObject beValue1=JSONObject.fromObject(beValue);
                            JSONArray compareJsonArray =comparevalue1.getJSONArray(compareKey);
                            JSONArray beJsonArray =beValue1.getJSONArray(beKey);

                            if(compareJsonArray.size()>beJsonArray.size()){
                                //预期个数多余实际数
                                flag = false;
                                throw new CompareExcetion("键"+compareKey+"：预期个数多余实际数");
                            }

                            for (int i = 0; i <compareJsonArray.size() ; i++) {
                                for(int j = 0; j <beJsonArray.size() ; j++){
                                    Object compareObject = compareJsonArray.get(i);
                                    Object beObject = beJsonArray.get(j);
                                    System.out.println(compareKey+"规则比较0011====");
                                    getValCompare(beObject.toString(),compareObject.toString());
                                }
                            }
                        }else if((compareJson1 instanceof String)&&(beJson1 instanceof String)) {
                            System.out.println("===================String");
                        }else{
                            //该层到取值,比对value是否相同
                            JSONObject beJson11 = JSONObject.fromObject(beValue);
                            Iterator iterator = beJson11.keys();
                            while(iterator.hasNext()){
                                String key =(String) iterator.next();
                                if(beJson11.containsKey("value")){
                                    if("value".equals(key)){
                                        if(beJson11.getString(key)!=null&&!StringUtils.isEmpty(beJson11.getString(key))){
                                            //预期叶子,有值
                                            System.out.println(compareKey+"非规则比较11====");
                                            if(!compareValue.equals(beJson11.getString(key))){
                                                //值不相等
                                               // flag = false;
                                              // throw new CompareExcetion(beKey+"预期值："+beJson11.getString(key)+",实际值："+compareValue);
                                            }
                                        }else{
                                            //预期叶子无值，比规则
                                            System.out.println(compareKey+"规则比较11====");
                                            break;
                                        }
                                    }
                                }else{
                                    //预期非叶子，比规则
                                    System.out.println(beKey+"规则比较22====");
                                    break;
                                }
                            }
                        }
                    }else{
                        System.out.println(compareKey+"规则比较+++++++++++++====");
                    }
                }
            }
        }
        return  flag;
    }


    /**
     * 判断是json字符串
     * @param content json字符串
     * @return true or false
     */
    public static boolean isJson(String content){
        try {
            JSONObject.fromObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
