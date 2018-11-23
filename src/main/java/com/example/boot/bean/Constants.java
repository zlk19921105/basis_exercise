package com.example.boot.bean;
/**
 * 常量类
 * @author zhoulk
 *
 */
public final class Constants {
	//禁止实例化
	private Constants(){};

	//请求报文
	public static final String REQ_MESSAGE="请求报文";
	//应答报文
	public static final String RES_MESSAGE="应答报文";
	//xml
	public static final String XML="xml";
	//json
	public static final String JSON="json";
	/*//1网状网xml
	public static final String MESH_XML="1";
	//2网状网json
	public static final String MESH_JSON="2";
	//3能开json
	public static final String OPEN_JSON="3";*/

	//F定长，V可变长
	//num数值
	public static final String NUM="num";
	//long数值(json才有)
	public static final String LONG="long";
	//string字符串
	public static final String STRING="string";
	//对象
	public static final String JSON_OBJECT="JSONObject";
	//数组
	public static final String JSON_ARRAY="JSONArray";
	//数组或者对象
	public static final String JSON_OBJECT_OR_ARRAY="object_or_array";
	//必选
	public static final String REQUIRED="必选";
	//可选
	public static final String OPTIONAL="可选";
	/**json的根*/
	public static final String ROOT="ROOT";
	/**xml的根*/
	public static final String INTER_BOSS="InterBOSS";

	/*1. 取值不为空 2.正整数或0 3.从表中读取【select】 4.时间格式【select】 5.不包括某些值 6.取值长度
    7.保留几位小数 8.大于等于数值 9.等于请求参数 10.区间值 11.多个值 12.模糊匹配  【5-12 input】*/
	//无规则，例：2|notRule|null;标识为2
	public static final String RULE_NOT_RULE ="not_rule";
	//固定规则，例：1|fixed_value|101;标识为1
	public static final String RULE_FIXED_VALUE="fixed_value";
	//以下1-12为规则。例：0|rule_int|3;标识为0
	//1. 取值不为空
	public static final String RULE_IS_NULL="rule_not_null";
	//2.正整数或0
	public static final String RULE_INT="rule_int";
	//3.从表中读取【select】
	public static final String RULE_TABLE="rule_table";
	//4.时间格式【select】
	public static final String RULE_TIME="rule_time";
	//5不包括某些值
	public static final String RULE_NOT_INCLUDED="rule_not_included";
	//6.取值长度 
	public static final String RULE_LENGTH="rule_length";
	//7.保留几位小数 
	public static final String RULE_DECIMAL="rule_decimal";
	//8.大于等于数值
	public static final String RULE_GT_EQ="rule_gt_eq";
	//9.等于请求参数 
	public static final String RULE_EQ="rule_eq";
	//10.区间值 
	public static final String RULE_INTERVAL="rule_Interval";
	//11.多个值
	public static final String RULE_MULTIPLE_VAL="rule_multiple_val";
	//12.模糊匹配  【5-12 input】
	public static final String RULE_LIKE="rule_like";
}
