package com.example.boot.json_xml;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.alibaba.druid.util.StringUtils;
import com.example.boot.bean.CompareExcetion;
import com.example.boot.bean.Constants;
import com.example.boot.bean.ProReqMessage;
import com.example.boot.bean.ResMessage;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

/**
 * 报文比对方法
 * @author Administrator
 *
 */
public class MessageCompare {

	/**
     * 格式层级树
     * @param list 树list
     * @return 格式化后的树
     */
    public static List<ResMessage> formatTree(List<ResMessage> list ){
        ResMessage root=new ResMessage();
        ResMessage node=new ResMessage();
        //拼凑好的Json数据
        List<ResMessage> treelist=new ArrayList<>(); 
        // 存放所有父节点
        List<ResMessage> parentNodes=new ArrayList<>(); 
        if(list!=null && list.size()>0){
        	 //第一个一定是根节点 0
            root=list.get(0);
            for(int i=1; i<list.size(); i++){
                node=list.get(i);
                //从跟节点开始遍历是不是子节点
                if(node.getParent_id().equals(root.getId())){ 
                    parentNodes.add(node);
                    root.getChildren().add(node);
                }else{ 
                	//获取root子节点的孩子节点
                   getChildrenNodes(parentNodes, node);
                   parentNodes.add(node);
                }
            }
        }
        treelist.add(root);
        return treelist;
    }

    /**
     * @param parentNodes 父节点
     * @param node
     */
    private static void getChildrenNodes(List<ResMessage> parentNodes , ResMessage node){
        for(int i=parentNodes.size()-1; i>=0; i--){
            ResMessage pnode=parentNodes.get(i);
            if(pnode.getId().equals(node.getParent_id())){
                pnode.getChildren().add(node);
                return;
            }
        }
    }
    
    /**应答报文树初步加工
     * @param messageTree 应答报文树
     */
    @SuppressWarnings("unused")
	public static List<ResMessage> getMessageTree(String messageTree){
    	JSONObject json=JSONObject.fromObject(messageTree);
        Iterator iterator = json.keys();
        ArrayList<ResMessage> list = new ArrayList<>();
        Integer pId = 0;
        // {"list":{"tree":[{\
        while (iterator.hasNext()){
            String key =(String) iterator.next();
            String value =json.getString(key);
            //获取tree
            JSONObject jsonTree=JSONObject.fromObject(value);
            String treeValue =jsonTree.getString("tree");
            JSONArray jsonArray = JSON.parseArray(treeValue);
            for(int i= 0; i <jsonArray.size() ; i++){
                //具体的树节点list
                JSONObject json1=JSONObject.fromObject(jsonArray.get(i));
                ObjectMapper objectMapper=new ObjectMapper();
                JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(i).toString());
                ResMessage resMessage=(ResMessage)JSONObject.toBean(jsonObject, ResMessage.class);
                if("Root".equals(resMessage.getField_name())&&"0".equals(resMessage.getId())){
                    //节点不需要
                    continue;
                }
                list.add(resMessage);
            }
        }
        return list;
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
    public static boolean isXML(String rtnMsg){
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
     * xml字符串转json
     * @param xml xml字符串
     * @return json
     * @throws Exception
     */
    public static  String xml2jsonString(String xml) throws Exception {
        org.json.JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj.toString();
       /* XMLSerializer serializer = new XMLSerializer();
        return serializer.read(xml).toString();*/
    }
    
	/**
	 * 获取当前节点对应的预期规则等信息
	 * @param id map中规则对应id
	 * @param preKey 预期key（用于提示）
	 * @param TreeMap 截取的对应预期结果
	 * @return ResMessage 预期比对信息
	 */
  public static ProReqMessage getPreRule(String id, String preKey, HashMap<String,ProReqMessage> TreeMap) throws CompareExcetion {
	  ProReqMessage resMessage=null;
      if(id!=null&&!StringUtils.isEmpty(id)){
            //预期
            if(TreeMap!=null&&TreeMap.containsKey(id)&&TreeMap.get(id)!=null){
                //规则存在
                resMessage = TreeMap.get(id);
            }else{
                //接口应答报文模板有修改，未找到对应规则，请重新加载用例中的应答报文树
                throw new CompareExcetion(";键："+preKey+"接口应答报文模板有修改，未找到对应规则，" +
                        "请重新加载用例中的应答报文树");
            }
            return  resMessage;
        }else{
          //接口应答报文模板有修改，未找到对应规则，请重新加载用例中的应答报文树
          throw new CompareExcetion(";键："+preKey+"没有找到节点id,预期结果拼接有错误!");
        }
    }
  
  /**
   * 规则比对（此时预期有值的已经校验完毕）
  * @param kv 键加resJson1（未拆开前）
   * @param resJson1 应答报文节点
   * @param preMessRule 预期规则
   * @param isLeaf 1是叶子，0非叶子
   * @return true 成功，false 失败
   */
  public static Boolean ruleCompare(Object kv,Object resJson1,ProReqMessage preMessRule,String isLeaf) throws CompareExcetion {
      boolean flag = true;
      // 比对：类型=>array数组,num(V6)数值,long(V50)数值(json才有),string字符串,
      //类型=>JSONObject对象,数组JSONArray,数组或者对象object_or_array，
      //是否为空（可选，必选）;长度(针对叶子);约束（可选，必选）
      String field_type = preMessRule.getField_type();
      //F定长，V可变长
      String field_length = preMessRule.getField_length();
      String is_empty = preMessRule.getIs_empty();
      String constraints = preMessRule.getConstraints();
      
      //is_empty 与 is_empty(不会同时存在)
      if(constraints!=null&&!StringUtils.isEmpty(constraints)){
    	  is_empty = constraints;
      }
      
      
      
      if("1".equals(isLeaf)){
    	  //预期叶子
    	  if(resJson1 instanceof JSONObject){
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    		 //可选，必选（针对字段，不针对值），反向比对时需要，看预期是否全部返回
    		 // requiredCompare();
    	  }else if(resJson1 instanceof JSONArray){
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    	  }else if(resJson1 instanceof String){
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    		  fieldLengthCompare(field_length,resJson1,preMessRule.getField_name());
    	  }else{
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    		  fieldLengthCompare(field_length,resJson1,preMessRule.getField_name());
    	  }
      }else{
    	  //预期非叶子,field_length不比对
    	  if(resJson1 instanceof JSONObject){
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    	  }else if(resJson1 instanceof JSONArray){
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    	  }else if(resJson1 instanceof String){
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    		  fieldLengthCompare(field_length,resJson1,preMessRule.getField_name());
    	  }else{
    		  fieldTypeCompare(field_type,resJson1,preMessRule.getField_name(),kv);
    		  fieldLengthCompare(field_length,resJson1,preMessRule.getField_name());
    	  }
      }
      
      String rule = preMessRule.getRule();
      //规则类型
      String ruleKey = null;
      //规则对象值
      String ruleValue = null;

      if(rule!=null&&!StringUtils.isEmpty(rule)&&"1".equals(isLeaf)){
          //rule规则有值,且规则是叶子节点
          //rule:0|key|value
          String[] split = rule.split("\\|");
          //key
          ruleKey = split[1];
          //value
          if(split!=null&&split.length>=3){
              ruleValue = split[2];
          }
          
          if(resJson1 instanceof JSONObject){
        	  JSONObject fromObject = JSONObject.fromObject(resJson1);
        	  Iterator<?> keys = fromObject.keys();
        	  while(keys.hasNext()){
        		  Object key = keys.next();
        		  throw new CompareExcetion("响应"+preMessRule.getField_name()+"在预期结果中不存在！"); 
        	  }
          }
          
          if(resJson1 instanceof JSONArray){
        	//判断{"ss":["dd","gg"],"hh":[{"dd":"ff"}],"ff":[],"hh":[{}],"ff":[["dd","gg"],["dd","gg"]]} （未考虑到）
        	 JSONArray parseArray = JSON.parseArray(resJson1.toString());
        	 if(parseArray!=null&&parseArray.size()>0){
        		for(int i=0;i< parseArray.size();i++){
        			Object object = parseArray.get(0);
        			if(object instanceof String){
        				 throw new CompareExcetion("响应"+preMessRule.getField_name()+"为数组！预期中不为数组！"); 
        				 //getRuleItem(resJson1,ruleKey,ruleValue,preMessRule);
        				// break;
        			}else{
        				 throw new CompareExcetion("响应"+preMessRule.getField_name()+"为数组！预期中不为数组！");
        				// break;
        			}
            	 }
        	 }
          }
          
          //rule对应规则
          getRuleItem(resJson1,ruleKey,ruleValue,preMessRule);
      }
      return flag;
  }
  
  
  /**
   * 长度比较（F：定长，V：变长。数字：长度）
   * @param fieldLength 长度（如F10）
   * @param resJson1 值
   * @param preKey 预期键
   * @return
 * @throws CompareExcetion 
   */
  private static Boolean fieldLengthCompare(String fieldLength,Object resJson1,String preKey) throws CompareExcetion{
	  boolean flag =true;
	  if(fieldLength!=null&&!StringUtils.isEmpty(fieldLength)){
		  if(fieldLength.startsWith("F")){
			 Integer length = Integer.valueOf(fieldLength.substring(1));
			 if(resJson1.toString().length()!=length){
				 throw new CompareExcetion("响应"+preKey+"值为："+resJson1+"，长度不等于："+fieldLength);
			 }
		  }else if(fieldLength.startsWith("V")){
			 Integer length = Integer.valueOf(fieldLength.substring(1));
			 if(resJson1.toString().length()>length){
				 throw new CompareExcetion("响应"+preKey+"值为："+resJson1+"，长度不能大于："+fieldLength);
			 }
		  }else if(fieldLength.contains("-")){
			  
		  }else{
			  throw new CompareExcetion("预期"+preKey+"配置错误，需要F或者V开头！");
		  }
	  }
	  return true;
	  
  }
  
  /**
   * 类型判断
   * @param fieldType 类型
   * @param resJson1 响应值
   * @param preKey 预期key
   * @param kv (响应值)
   * @return
   * @throws CompareExcetion
   */
  private static Boolean fieldTypeCompare(String fieldType,Object resJson1,String preKey,Object kv) throws CompareExcetion{
	  boolean flag = true;
	  switch (fieldType) {
			case "Num":
				isNum(resJson1,preKey,kv);
				break;
			case "Long":
				isLong(resJson1,preKey,kv);
				break;
			case "String":
				isString(resJson1,preKey,kv);
				break;
			case "Object":
				isJSONObject(resJson1,preKey);
				break;
			case "Array":
				isJSONArray(resJson1,preKey);
				break;
			case "ObjectOrArray":
				isArrayOrbject(resJson1,preKey);
				break;
			default:
				break;
	  }
	  return null;
	  
  }
  
  
  /**
   * 响应是对象（字符串）
   * @param resJson1
   * @param preKey
   * @return
   * @throws CompareExcetion 
   */
  private static Boolean isString(Object resJson1,String preKey,Object kv) throws CompareExcetion{
	  boolean flag = true;
	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
		  String str = kv.toString().substring(1,kv.toString().length()-1);
		  String[] split = str.split(",");
		  for(String s:split){
			  if(s.contains(":")){
				  String[] split2 = s.split(":");
				  if(preKey.equals(split2[0].substring(1, split2[0].length()-1))){
					  if(split2[1]!=null&&!StringUtils.isEmpty(split2[1])&&split2[1].startsWith("\"")){
						  
					  }else if(split2[1].equals("")){
						  
					  }else{
						  throw new CompareExcetion("响应"+preKey+"对应值不是字符串,字符串必选有引号！");
					  }
				  }
			  }
		  }
		  
		  if(!(resJson1 instanceof String)){
			  throw new CompareExcetion("响应"+preKey+"对应值为"+resJson1.toString()+"不是字符串！");
		  }
	  }
	return flag;
	  
  }
  
  /**
   * 响应是对象（JSONObject）
   * @param resJson1
   * @param preKey
   * @return
   * @throws CompareExcetion 
   */
  private static Boolean isJSONObject(Object resJson1,String preKey) throws CompareExcetion{
	  boolean flag = true;
	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
		  if(!(resJson1 instanceof JSONObject)){
			  throw new CompareExcetion("响应"+preKey+"对应值为"+resJson1.toString()+"不是对象！");
		  }
	  }
	return flag;
	  
  }
  
  /**
   * 响应是数组（JSONArray）
   * @param resJson1
   * @param preKey
   * @return
   * @throws CompareExcetion 
   */
  public static Boolean isJSONArray(Object resJson1,String preKey) throws CompareExcetion{
	  boolean flag = true;
	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
		  if(!(resJson1 instanceof JSONArray)){
			  throw new CompareExcetion("响应"+preKey+"对应值为"+resJson1.toString()+"不是数组！");
		  }
	  }
	return flag;
	  
  }
  
  /**
   * 响应是数组或者对象（JSONArray or JSONObject）
   * @param resJson1
   * @param preKey
   * @return
   * @throws CompareExcetion 
   */
  private static Boolean isArrayOrbject(Object resJson1,String preKey) throws CompareExcetion{
	  boolean flag = true;
	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
		  if(!(resJson1 instanceof JSONArray||resJson1 instanceof JSONObject)){
			  throw new CompareExcetion("响应"+preKey+"对应值为"+resJson1.toString()+"。既不是数组，也不是对象！");
		  }
	  }
	  return flag;
	  
  }
  
  /**
   * 响应num数值(V6,可变长度，6)
   * @param resJson1
   * @param preKey
   * @return
   * @throws CompareExcetion 
   */
  private static Boolean isNum(Object resJson1,String preKey,Object kv) throws CompareExcetion{
	  boolean flag = true;
	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
		  Pattern pattern1 = Pattern.compile("^\\d+$|-\\d+$"); // 就是判断是否为整数
		  Pattern pattern2 = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");//判断是否为小数
		  if(resJson1.toString().length()>6||!(pattern1.matcher(resJson1.toString()).matches()||pattern2.matcher(resJson1.toString()).matches())){
			  throw new CompareExcetion("响应"+preKey+"对应值为"+resJson1.toString()+",长度大于6,且必须为数字！");
		  }
		  
		  String str = kv.toString().substring(1,kv.toString().length()-1);
		  String[] split = str.split(",");
		  for(String s:split){
			  if(s.contains(":")){
				  String[] split2 = s.split(":");
				  if(preKey.equals(split2[0].substring(1, split2[0].length()-1))){
					  if(split2[1]!=null&&!StringUtils.isEmpty(split2[1])&&!split2[1].startsWith("\"")&&!split2[1].endsWith("\"")){
						  
					  }else{
						  throw new CompareExcetion("响应"+preKey+"对应值不是数字,数字不能有引号！");
					  }
				  }
			  }
		  }
	  }else{
		  throw new CompareExcetion("响应"+preKey+"对应值不能为空,长度必须小于等于6,且必须为数字！");
	  }
	return flag;
  }
  
  /**
   * 响应Long数值(V50,可变长度，50)
   * @param resJson1
   * @param preKey
   * @return
   * @throws CompareExcetion 
   */
  private static Boolean isLong(Object resJson1,String preKey,Object kv) throws CompareExcetion{
	  boolean flag = true;
	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
		  Pattern pattern1 = Pattern.compile("^\\d+$|-\\d+$"); // 就是判断是否为整数
		  Pattern pattern2 = Pattern.compile("\\d+\\.\\d+$|-\\d+\\.\\d+$");//判断是否为小数
		  if(resJson1.toString().length()>50||!(pattern1.matcher(resJson1.toString()).matches()||pattern2.matcher(resJson1.toString()).matches())){
			  throw new CompareExcetion("响应"+preKey+"对应值为"+resJson1.toString()+"，长度大于50，且必须为数字类型！");
		  }
		  String str = kv.toString().substring(1,kv.toString().length()-1);
		  String[] split = str.split(",");
		  for(String s:split){
			  if(s.contains(":")){
				  String[] split2 = s.split(":");
				  if(preKey.equals(split2[0].substring(1, split2[0].length()-1))){
					  if(split2[1]!=null&&!StringUtils.isEmpty(split2[1])&&!split2[1].startsWith("\"")&&!split2[1].endsWith("\"")){
						  
					  }else{
						  throw new CompareExcetion("响应"+preKey+"对应值不是数字,数字不能有引号！");
					  }
				  }
			  }
		  }
	  }else{
		  throw new CompareExcetion("响应"+preKey+"对应值不能为空,长度必须小于等于50,且必须为数字！");
	  }
	return flag;
  }
  
  /**
   * rule规则12项比较，外加固定值一项
   * @param resJson1 响应value
   * @param ruleKey rule key
   * @param ruleValue 规则值
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean getRuleItem(Object resJson1,String ruleKey,String ruleValue,ProReqMessage preMessRule) throws CompareExcetion {
      boolean flag = true;
      switch (ruleKey){
          //固定值
          case Constants.RULE_FIXED_VALUE :
              fixedCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //取值不为空
          case Constants.RULE_IS_NULL :
              isNotCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //正整数或0
          case Constants.RULE_INT :
              integerCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //表中获取
          case Constants.RULE_TABLE :
        	  tableCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //日期格式
          case Constants.RULE_TIME :
        	  timeCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //不包含某些值
          case Constants.RULE_NOT_INCLUDED :
        	  notIncludedCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //值长度
          case Constants.RULE_LENGTH :
        	  lengthCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //j保留几位小数
          case Constants.RULE_DECIMAL :
        	  decimalCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //大于等于
          case Constants.RULE_GT_EQ :
        	  gtEqCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //等于
          case Constants.RULE_EQ :
        	  eqCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //区间值
          case Constants.RULE_INTERVAL :
        	  intervalCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //多个值
          case Constants.RULE_MULTIPLE_VAL :
        	  multipleCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          //模糊匹配
          case Constants.RULE_LIKE :
        	  likeCompare(resJson1,ruleValue,preMessRule.getField_name());
              break;
          default:
              //无rule规则
              break;
      }
      return flag;
  }
  
  /**
   * 模糊查询
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean likeCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue)){
		  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
			 if(!resJson1.toString().contains(ruleValue)){
				 throw new CompareExcetion("响应"+preKey+"值为"+resJson1.toString()+"，必须包含："+ruleValue); 
			 }
		  }else{
			  throw new CompareExcetion("响应"+preKey+"值为空，必须包含："+ruleValue); 
		  }
      }
      return flag;
  }
  
  /**
   * 多个值(响应必须为预期中的一个)
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean multipleCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue)){
		  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
			  boolean fl = false;
			  if(ruleValue.contains(",")){
				  String[] split = ruleValue.split(",");
	    		  for(String str:split){
	    			  if(resJson1.toString().equals((str))){
	    				  fl = true;  
	    			  }
	    		  }
			  }else{
				  if(resJson1.toString().equals(ruleValue)){
	    				  fl = true;  
				  }
			  }
			  if(!fl){
				  throw new CompareExcetion("响应"+preKey+"值为"+resJson1.toString()+"，必须为一下其中一个："+ruleValue); 
			  }
		  }else{
			  throw new CompareExcetion("响应"+preKey+"值为空，必须为一下其中一个："+ruleValue); 
		  }
    	  
      }
      return flag;
  }
  
  /**
   * 区间值
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean intervalCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue)){
    	  //存在区间值
    	  if(ruleValue.contains("\\-")){
    		  String[] split = ruleValue.split("\\-");
    		  String start = split[0];
    		  String end = split[0];
    		  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    			  if(resJson1.toString().compareTo(start)<0||resJson1.toString().compareTo(end)>0){
    				  throw new CompareExcetion("响应"+preKey+"值为"+resJson1.toString()+"不满足区间范围，预期区间范围："+ruleValue); 
    			  }
    		  }else{
    			  throw new CompareExcetion("响应"+preKey+"值为空不满足区间范围，预期区间范围："+ruleValue); 
    		  }
    	  }else{
    		  throw new CompareExcetion("预期"+preKey+"区间值为："+ruleValue+",配置区间值必须用-隔开（例：3-8）"); 
    	  }
      }
      return flag;
  }
  
  
  /**
   * 等于
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean eqCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    	  if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue.toString())){
    		  if(resJson1.toString().compareTo(ruleValue)!=0){
    			  throw new CompareExcetion("响应"+preKey+"对应值:"+resJson1.toString()+",预期要求等于:"+ruleValue); 
    		  }
    	  }
      }else{
    	  if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue.toString())){
    		  throw new CompareExcetion("响应"+preKey+"对应值为空,预期要求等于:"+ruleValue); 
    	  }
      }
      return flag;
  }
  
  /**
   * 大于等于
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean gtEqCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    	  if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue.toString())){
    		  if(resJson1.toString().compareTo(ruleValue)<0){
    			  throw new CompareExcetion("响应"+preKey+"对应值:"+resJson1.toString()+",预期要求大于等于:"+ruleValue); 
    		  }
    	  }
      }else{
    	  if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue.toString())){
    		  throw new CompareExcetion("响应"+preKey+"对应值为空,预期要求大于等于:"+ruleValue); 
    	  }
      }
      return flag;
  }
  
  /**
   * 保留几位小数
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean decimalCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      Pattern pattern = Pattern.compile("[0-9]*");
      if(!pattern.matcher(ruleValue).matches()){
    	  throw new CompareExcetion("预期"+preKey+"对应值配置错误，保留小数位必须为非负整数！");
      }else{
    	  int length = Integer.valueOf(ruleValue);
    	  int length1 = 0;
    	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    		  if(resJson1.toString().contains(".")){
    			  int lastIndexOf = resJson1.toString().lastIndexOf(".");
    			  length1 = resJson1.toString().substring(lastIndexOf+1).length();
    		  }
    		  if(length!=length1){
    			  throw new CompareExcetion("响应"+preKey+"对应值保留了"+length1+"位小数，预期要求保留"+ruleValue+"位小数。"); 
    		  }
          }else{
        	  if(length!=0){
        		  throw new CompareExcetion("响应"+preKey+"对应值为空，预期要求保留"+ruleValue+"位小数。"); 
        	  }
          }
      }
      return flag;
  }
  
  /**
   * 值长度
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean lengthCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      Pattern pattern = Pattern.compile("[0-9]*");
      if(!pattern.matcher(ruleValue).matches()){
    	  throw new CompareExcetion("预期"+preKey+"对应值配置错误，值长度必须为非负整数！");
      }else{
    	  int length = Integer.valueOf(ruleValue);
    	  if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    	    	if(resJson1.toString().length()!=length){
    	    		throw new CompareExcetion("响应"+preKey+"对应值长度为:"+resJson1.toString().length()+"，预期要求返回长度为："+length); 
    	    	}
          }else{
        	  //resJson1为空
        	  if(length!=0){
     	    		throw new CompareExcetion("响应"+preKey+"对应值长度为:"+0+"，预期要求返回长度为："+length); 
        	  }
          }
      }
      return flag;
  }
  
  /**
   * 不包含某些值
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean notIncludedCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    	 StringBuffer sbf = new StringBuffer();
    	 if(ruleValue!=null&&!StringUtils.isEmpty(ruleValue)){
    		 if(ruleValue.contains(",")){
    			 String[] split = ruleValue.split(",");
    			 for(String str:split){
    				 if(resJson1.toString().contains(str)){
    					 sbf.append(","+str);
    				 }
    			 }
    		 }else{
    			 if(resJson1.toString().contains(ruleValue)){
					 sbf.append(","+ruleValue);
				 }
    		 }
    		 if(sbf!=null&&sbf.length()>0){
				 throw new CompareExcetion("响应"+preKey+"对应值中不能包含数据："+sbf.toString().substring(1));
			 }
    	 }
      }
      return flag;
  }
  
  /**
   * 日期格式验证
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean timeCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    	try { SimpleDateFormat sdf = new SimpleDateFormat(ruleValue);
			sdf.parse(resJson1.toString());
		} catch (ParseException e) {
			throw new CompareExcetion("响应"+preKey+"对应值日期格式不满足，正确格式为："+ruleValue);
		}
    	  
      }else{
    	  throw new CompareExcetion("响应"+preKey+"对应值为日期格式数据，不能为空。");
      }
      return flag;
  }
  
  /**
   * 表中取值
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean tableCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
    	  ArrayList<String> list = new ArrayList<>();
    	  if(resJson1.toString().contains(",")){
    		  String[] split = resJson1.toString().split(",");
    		  for(String str:split){
    			  list.add(str);
    		  }
    	  }else{
    		  list.add(resJson1.toString());
    	  }
    	  switch (ruleValue) {
  		case "data_sys":
  			List<HashMap<String, String>> queryDataSys =null; //testExecutionMapper.queryDataSys();
  			if(queryDataSys!=null&&queryDataSys.size()>0){
  				for(String list1:list){
  					boolean fl = false;
  					for(HashMap<String, String> map:queryDataSys){
  	  					if(list1.equals(map.get("sys_name"))){
  	  					   fl= true;
  	  					}
  	  				}
  					if(!fl){
  						throw new CompareExcetion("响应"+preKey+"对应值："+list1+"，在表data_sys中不存在");
  					}
  				}
  			}
  			break;
  		case "task_usecase":
  			
  			break;
  		default:
  			break;
  	}
      }
      return flag;
  }
  
  
  /**
   * 正整数或0
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean integerCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      Pattern pattern = Pattern.compile("[0-9]*");
      if(!pattern.matcher(resJson1.toString()).matches()){
    	  throw new CompareExcetion("响应"+preKey+"对应值："+resJson1.toString()+"，不是非负整数");
      }
      return flag;
  }

  /**
   * 不为空
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean isNotCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
          flag = true;
      }else{
          throw new CompareExcetion("响应"+preKey+"对应值不能为空！");
      }
      return flag;
  }

  /**
   *固定值一项
   * @param resJson1 响应value
   * @param ruleValue 规则值
   * @param   preKey 预期key
   * @return 比较结果 true 成功，false 失败
   */
  private static Boolean fixedCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
      boolean flag = true;
      if(resJson1==null){
          throw new CompareExcetion("预期"+preKey+"固定值为："+ruleValue+"。响应"+preKey+"返回值为空！");
      }
      if(resJson1!=null&& StringUtils.isEmpty(resJson1.toString())){
          throw new CompareExcetion("预期"+preKey+"固定值为："+ruleValue+"。响应"+preKey+"返回值为空！");
      }
      if(ruleValue.equals(resJson1.toString())){
          flag = true;
      }else{
          throw new CompareExcetion("预期"+preKey+"固定值为："+ruleValue+"。响应"+preKey+"返回值为:"+resJson1);
      }
      return flag;
  }

	public static String preResultFormat(String preResult,JSONObject resultJson,String pName){
		JSONObject preJson = JSONObject.fromObject(preResult);
		Iterator preIterator = preJson.keys();
		String prekey = null;
		while(preIterator.hasNext()){
			prekey = (String)preIterator.next();
			Object preValue = preJson.get(prekey);
		//	String ss = ss(preValue, prekey, preJson);
			JSONObject valueObject = JSONObject.fromObject(preValue);
			if(preValue instanceof JSONObject){
				if(valueObject.containsKey("id")&&valueObject.containsKey("value")){
					String val = removeId(preValue.toString());
					preJson.put(prekey,val);
				}
				if(!valueObject.containsKey("value")&&valueObject.containsKey("id")){
					String val = removeId(preValue.toString());
					preJson.put(prekey,val);
					preResultFormat(preJson.get(prekey).toString(),preJson,prekey);
				}
			}else if(preValue instanceof JSONArray){

			}else if(preValue instanceof String){
			}else{
			}
			resultJson = new JSONObject();
			//resultJson.put(pName,preJson);
			//preJson.put(prekey,ss);
		}
		System.out.println("=========================");
		System.out.println(preJson+"==="+pName);

	/*	while (iterator.hasNext()){
			String key = (String)iterator.next();
			Object value = preJson.get(key);
			if("id".equals(key)||"value".equals(key)){
				continue;
			}
			JSONObject valueJson = JSONObject.fromObject(value);
			Iterator valueIterator = valueJson.keys();
			if(value instanceof JSONObject){
				if(!valueJson.containsKey("id")&&!valueJson.containsKey("value")){
					continue;
				}
				if(valueJson.containsKey("id")){
					valueJson.remove("id");
					preJson.put(key,valueJson);
				}
				if(valueJson.containsKey("value")){
					preJson.put(key,valueJson.get("value"));
					continue;
				}
				preResultFormat(valueJson.toString(),result);
			}else if(value instanceof JSONArray){
				JSONArray valueJsonArray = JSON.parseArray(value.toString());
				if(valueJsonArray!=null&&valueJsonArray.size()>0){
					for(int i =0;i<valueJsonArray.size();i++){
						Object o = valueJsonArray.get(i);
						preResultFormat(o.toString(),result);
					}
				}
			}else if(value instanceof String){
			}else{
			}
				//preResultFormat(preJson.toString());
			}*/
			//pre_result = preJson.toString();
		return  null;
	}

	public static String ss(Object preValue, String prekey, JSONObject preJson){
		JSONObject valueObject = JSONObject.fromObject(preValue);
		if(preValue instanceof JSONObject){
			if(valueObject.containsKey("id")&&valueObject.containsKey("value")){
				String val = removeId(preValue.toString());
				preJson.put(prekey,val);
			}
			if(!valueObject.containsKey("value")&&valueObject.containsKey("id")){
				String val = removeId(preValue.toString());
				preJson.put(prekey,val);
				preResultFormat(preJson.get(prekey).toString(),preJson,prekey);
			}
		}else if(preValue instanceof JSONArray){

		}else if(preValue instanceof String){
		}else{
		}
		return preJson.toString();
	}

	public static String removeId(String value){
		String val = null;
		JSONObject jsonObject = JSONObject.fromObject(value);
		if(jsonObject.containsKey("value")){
			val = jsonObject.get("value").toString();
		}else{
			jsonObject.remove("id");
			val = jsonObject.toString();
		}
		return val;
	}

	public static void main(String[] args) {
		String pre = "{\"InterBOSS\":{\"id\":0,\"Version\":{\"id\":1257,\"value\":\"\"},\"TestFlag\":{\"id\":1258,\"BIPType\":{\"id\":1259,\"BIPCode1\":{\"id\":1260,\"value\":\"\"},\"ActivityCode1\":{\"id\":1261,\"value\":[23,56]}}},\"BIPType\":{\"id\":1259,\"BIPCode\":{\"id\":1260,\"value\":\"\"},\"ActivityCode\":{\"id\":1261,\"value\":[23,56]}}}}";
		JSONObject preJson = JSONObject.fromObject(pre);
		Iterator iterator = preJson.keys();

		//preResultFormat(pre,new JSONObject(),null);
	}
}
