package com.example.boot.json_xml;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import net.sf.json.util.JSONTokener;
import org.json.XML;

import java.util.Iterator;
import java.util.Map;

/**json数据循环比对
 * Administrator zhoulk
 * date: 2018/10/2 0002
 */
public class JsonCompare {

    public static Boolean jsonCompare() throws Exception {
        String json1= "{\"code\":1,\"data\":{\"list\":{\"linkShow\":[{\"name\":\"查询结果_zlk\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]},{\"name\":\"查询结果_zlk11\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]},{\"name\":\"查询结果_zlk22\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]}],\"parametervalueIn\":\"$主键编号:105,$账号:admin,$文件名:js.rar\"}},\"msg\":\"success\"}";
       // String json1="{\"linkShow\":[{\"name\":\"查询结果_zlk\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]},{\"name\":\"查询结果_zlk11\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]},{\"name\":\"查询结果_zlk22\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]}]}";

        String json2= "{\"code\":6,\"data\":{\"list\":{\"linkShow\":[{\"name\":\"查询结果_zlk11\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]},{\"name\":\"查询结果_zlk11\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]},{\"name\":\"查询结果_zlk22\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\",\"SS\":[{\"nn\":\"11\",\"zz\":22},{\"nn\":\"11\",\"zz\":22}]}],\"parametervalueIn\":\"$主键编号:105,$账号:admin,$文件名:js.rar\"}},\"msg\":\"success\"}";

        if(isJson( json1.replaceAll( "\\\\ ", " "))){
            getValCompare( json1.replaceAll( "\\\\ ", " "),json2.replaceAll( "\\\\ ", " "));
            // getValCompare1(json1);
        }
        return null;
    }

    /**
     * xml字符串转json
     * @param xml xml字符串
     * @return json
     * @throws Exception
     */
    public static String xml2jsonString(String xml) throws Exception {
       org.json.JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj.toString();/*
        XMLSerializer serializer = new XMLSerializer();
        return serializer.read(xml).toString();*/
    }


    /**  取值比对
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
            throw new Exception("不是json，不能对比");
        }

        JSONObject beJson = JSONObject.fromObject(beCompareContent);
        Iterator beIterator = beJson.keys();

        JSONObject compareJson = JSONObject.fromObject(compareContent);
        Iterator compareIterator = compareJson.keys();

        while(compareIterator.hasNext()){
            //拿到key
            String  compareKey = (String) compareIterator.next();
            //该方法只能拿到最外层的值
            String compareValue = compareJson.getString(compareKey);
            // json2 key查找json1
            while(beIterator.hasNext()) {
                //拿到key
                String beKey = (String) beIterator.next();
                //该方法只能拿到最外层的值
                String beValue = beJson.getString(beKey);
                if(!compareKey.equals(beKey)){
                    //key不存在
                    flag = false;
                    throw new Exception("键："+compareKey+"不存在");
                }else{
                    //key存在
                    beValue = beJson.getString(compareKey);
                    Object compareJson1 = new JSONTokener(compareValue).nextValue();
                    Object beJson1 = new JSONTokener(beValue).nextValue();
                    if((compareJson1 instanceof JSONObject)&&(beJson1 instanceof JSONObject)){//key 对应的value是JSONObject
                        getValCompare(beValue,compareValue);
                    }else if((compareJson1 instanceof JSONArray)&&(beJson1 instanceof JSONArray)){//key 对应的value是JSONArray
                         //通过key拿到对应的key-valie,并转化为数组
                         /*  JSONArray compareJsonArray = JSON.parseArray(compareValue);
                            JSONArray beJsonArray = JSON.parseArray(beValue);*/
                        JSONObject comparevalue1=JSONObject.fromObject(compareValue);
                        JSONObject beValue1=JSONObject.fromObject(beValue);
                        JSONArray compareJsonArray =comparevalue1.getJSONArray(compareKey);
                        JSONArray beJsonArray =beValue1.getJSONArray(beKey);

                        if(compareJsonArray.size()>beJsonArray.size()){//预期个数多余实际数
                            flag = false;
                            throw new Exception("键"+compareKey+"：预期个数多余实际数");
                        }

                        for (int i = 0; i <compareJsonArray.size() ; i++) {
                            for(int j = 0; j <beJsonArray.size() ; j++){
                                Object compareObject = compareJsonArray.get(i);
                                Object beObject = beJsonArray.get(j);
                                getValCompare(beObject.toString(),compareObject.toString());
                            }
                        }
                    } else{//该层到取值,比对value是否相同
                        if(!compareValue.equals(beValue)){//值不相等
                            flag = false;
                            throw new Exception(beKey+"预期值："+compareValue+",实际值："+beValue);
                        }
                    }
                }
                if(flag){//找到对应值，终止内层循环
                    break;
                }
            }
        }
        return  flag;
    }

    /**
     * 取值比对
     * @param beCompareContent 被比对json
     * @param compareContent 比对json
     * @return 存在多层时比对？
     */
    public static boolean getValCompare1(String beCompareContent,String compareContent) throws Exception {
        boolean  flag = true;
        //判断是不是json数据
        if(!isJson( beCompareContent.replaceAll( "\\\\ ", " "))||!isJson( compareContent.replaceAll( "\\\\ ", " "))){//不是json
            flag = false;//不是json，不能对比
            throw new Exception("不是json，不能对比");
        }

        JSONObject beJson = JSONObject.fromObject(beCompareContent);
        Iterator beIterator = beJson.keys();

        JSONObject compareJson = JSONObject.fromObject(compareContent);
        Iterator compareIterator = compareJson.keys();

        while(compareIterator.hasNext()){
            //拿到key
            String  compareKey = (String) compareIterator.next();
            String compareValue = compareJson.getString(compareKey);//该方法只能拿到最外层的值
            // json2 key查找json1
            while(beIterator.hasNext()) {
                //拿到key
                String beKey = (String) beIterator.next();
                String beValue = beJson.getString(beKey);//该方法只能拿到最外层的值
                if(!compareKey.equals(beKey)){//key不存在
                    flag = false;
                    throw new Exception("键："+compareKey+"不存在");
                }else{//key存在
                    beValue = beJson.getString(compareKey);//该方法只能拿到最外层的值
                    //通过key拿到对应的key-valie,并转化为数组
                    if((compareValue.startsWith("{")||compareValue.startsWith("[{"))&&((beValue.startsWith("{")||beValue.startsWith("[{")))) {//还没到最后取值,该层比对key否相同,格式相同
                        if (compareValue.startsWith("[")&&beValue.startsWith("[")) {//该层有循环i++,JSONArray
                          /*  JSONArray compareJsonArray = JSON.parseArray(compareValue);
                            JSONArray beJsonArray = JSON.parseArray(beValue);*/

                            JSONObject compareJson1=JSONObject.fromObject(compareValue);
                            JSONObject beValue1=JSONObject.fromObject(beValue);
                            JSONArray compareJsonArray =compareJson1.getJSONArray(compareKey);
                            JSONArray beJsonArray =beValue1.getJSONArray(beKey);

                            if(compareJsonArray.size()>beJsonArray.size()){//预期个数多余实际数
                                flag = false;
                                throw new Exception("键"+compareKey+"：预期个数多余实际数");
                            }

                            for (int i = 0; i <compareJsonArray.size() ; i++) {
                                for(int j = 0; j <beJsonArray.size() ; j++){
                                    Object compareObject = compareJsonArray.get(i);
                                    Object beObject = beJsonArray.get(j);
                                    getValCompare(beObject.toString(),compareObject.toString());
                                }
                            }
                        }else{
                            getValCompare(beValue,compareValue);
                        }

                    }else{//该层到取值,比对value是否相同
                        if(!compareValue.equals(beValue)){//值不相等
                            flag = false;
                            throw new Exception(beKey+"预期值："+compareValue+",实际值："+beValue);
                        }
                    }
                }

          /*  Object object = jsonObject2.get(key);
             if(object instanceof JSONObject){//key 对应的value是JSONObject
                getValCompare1(object);
            }else if(object instanceof JSONArray){//key 对应的value是JSONArray
                JSONArray objArray = (JSONArray)object;
                String linkShow = jsonObject2.getString(key);
                JSONArray jsonArray1 = JSON.parseArray(linkShow);
                for (int i = 0; i <jsonArray1.size() ; i++) {
                    Object o = jsonArray1.get(i);
                    int ss= 0;
                    getValCompare1(o);
                }
            }else{//对应的是值，非JSONObjec或者JSONArray
                System.out.println(key+":--"+object);
            }*/
              if(flag){//找到对应值，终止内层循环
                  break;
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

    /**
     * 判断是否是xml结构
     */
    public static boolean isXML(String value) {
        try {
            DocumentHelper.parseText(value);
        } catch (DocumentException e) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String json1= "{\"code\":0,\"data\":{\"list\":{\"linkShow\":[{\"name\":\"查询结果_zlk\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\"},{\"name\":\"查询结果_zlk11\",\"value\":\"select * from sys_file where res_id='$主键编号' and file_name='$文件名'\"}],\"parametervalueIn\":\"$主键编号:105,$账号:admin,$文件名:js.rar\"}},\"msg\":\"success\"}";
        String json2 = "{\"list\":{\"tree\":[{\"field_name\":\"InterBOSS\",\"field_type\":\"\",\"is_select\":\"0\",\"constraints\":\"\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":0,\"id\":0,\"field_length\":\"\",\"select_list\":\"\",\"rule\":\"\",\"parent_name\":\"\",\"parent_id\":-1,\"value\":\"\"},{\"field_name\":\"Version\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":812,\"field_length\":\"—\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":},{\"field_name\":\"TestFlag\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":813,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"sdsff\"},{\"field_name\":\"BIPType\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":814,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"\"},{\"field_name\":\"BIPCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":816,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"ActivityCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":817,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"ActionCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":818,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"TestFlag1\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":815,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"包含\"}]}}";
        JSONObject json=JSONObject.fromObject(json1);
        Map<String, Object> map =json;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
          //  System.out.println(entry.getKey()+"="+entry.getValue());
        }

        try {
            jsonCompare();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><user_info><password></password><username>张三</username></user_info>";

        String xml1 =  "<MapSet>"
                + "<MapGroup id='Sheboygan'>" + "<Map>"
                + "<Type>MapGuideddddddd</Type>"
                + "<SingleTile>true</SingleTile>"
                + "<Extension>"
                + "<ResourceId>ddd</ResourceId>"
                + "</Extension>"
                + "</Map>"
                + "<Map>"
                + "<Type>ccc</Type>"
                + "<SingleTile>ggg</SingleTile>"
                + "<Extension>"
                + "<ResourceId>aaa</ResourceId>"
                + "</Extension>"
                + "</Map>"
                + "<Extension />"
                + "</MapGroup>"
                + "<ddd>"
                + "33333333"
                + "</ddd>"
                + "<ddd>"
                + "444"
                + "</ddd>"
                + "</MapSet>";

        try {
          //  boolean xml2 = isXML(xml1);
           // String jsonString = xml2jsonString(xml1);
           // System.out.println(xml2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
