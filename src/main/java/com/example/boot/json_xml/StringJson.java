package com.example.boot.json_xml;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.boot.bean.CompareExcetion;
import com.example.boot.bean.Constants;
import com.example.boot.bean.ProtocolTemp;
import com.example.boot.bean.ResMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Administrator zhoulk
 * date: 2018/11/7 0007
 */
public class StringJson {
/*    public static void main(String[] args) {
        String jsonStr = "{\"list\":{\"tree\":[{\"field_desc\":\"\",\"field_name\":\"InterBOSS\",\"field_type\":\"\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":0,\"edit_user\":\"\",\"id\":0,\"field_length\":\"\",\"select_list\":\"\",\"rule\":\"\",\"value_desc\":\"\",\"value\":\"\",\"map\":null,\"create_time\":null,\"parent_name\":\"\",\"create_user\":\"\",\"parent_id\":-1},{\"field_desc\":\"定购关系记录\",\"field_name\":\"SvcCont\",\"field_type\":\"—\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":914,\"field_length\":\"－\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"InterBOSS\",\"create_user\":\"超级管理员\",\"parent_id\":0},{\"field_desc\":\"业务受理请求\",\"field_name\":\"SyncInfo\",\"field_type\":\"—\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":915,\"field_length\":\"—\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"SvcCont\",\"create_user\":\"超级管理员\",\"parent_id\":914},{\"field_desc\":\"商品规格信息\",\"field_name\":\"POInfo\",\"field_type\":\"—\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"+\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":916,\"field_length\":\"－\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"SyncInfo\",\"create_user\":\"超级管理员\",\"parent_id\":915},{\"field_desc\":\"商品规格操作\",\"field_name\":\"Action\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":917,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"1-增加基于“POSpecNumber”节点的全量新增下发，无删除和变更\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品规格编码\",\"field_name\":\"POSpecNumber\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":918,\"field_length\":\"V9\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品规格名称\",\"field_name\":\"POSpecName\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":919,\"field_length\":\"V40\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品规格状态\",\"field_name\":\"Status\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":920,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"A-正常商用S-内部测试T-测试待审R-试商用      \",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品生效时间\",\"field_name\":\"StartDate\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":921,\"field_length\":\"F14\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"YYYYMMDDHH24MMSS\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品失效时间\",\"field_name\":\"EndDate\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":922,\"field_length\":\"F14\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"YYYYMMDDHH24MMSS\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品规格描述\",\"field_name\":\"Description\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":923,\"field_length\":\"V500\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品开放省\",\"field_name\":\"EnableCompanys\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":924,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品规格资费\",\"field_name\":\"POSpecRatePolicys\",\"field_type\":\"—\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"？\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":925,\"field_length\":\"－\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"产品规格信息\",\"field_name\":\"Products\",\"field_type\":\"—\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":926,\"field_length\":\"－\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"YYYYMMDDHHMISS\",\"field_name\":\"POTimeStamp\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":927,\"field_length\":\"F14\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"商品规格发布时间，用于一致性校验\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POInfo\",\"create_user\":\"超级管理员\",\"parent_id\":916},{\"field_desc\":\"商品开放省，编码参见统一编码，可有多个\",\"field_name\":\"EnableCompanyID\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"+\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":928,\"field_length\":\"F3\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"EnableCompanys\",\"create_user\":\"超级管理员\",\"parent_id\":924},{\"field_desc\":\"一条商品资费策略信息\",\"field_name\":\"POSpecRatePolicy\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"+\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":929,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POSpecRatePolicys\",\"create_user\":\"超级管理员\",\"parent_id\":925},{\"field_desc\":\"资费策略编码\",\"field_name\":\"POSpecRatePolicyID\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":930,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POSpecRatePolicy\",\"create_user\":\"超级管理员\",\"parent_id\":929},{\"field_desc\":\"资费策略名称\",\"field_name\":\"Name\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":931,\"field_length\":\"V40\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POSpecRatePolicy\",\"create_user\":\"超级管理员\",\"parent_id\":929},{\"field_desc\":\"资费策略描述\",\"field_name\":\"Description\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"?\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":932,\"field_length\":\"V500\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POSpecRatePolicy\",\"create_user\":\"超级管理员\",\"parent_id\":929},{\"field_desc\":\"期望生效时间\",\"field_name\":\"StartDate\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":933,\"field_length\":\"F14\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"YYYYMMDDHH24MMSS\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POSpecRatePolicy\",\"create_user\":\"超级管理员\",\"parent_id\":929},{\"field_desc\":\"期望生效时间\",\"field_name\":\"EndDate\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":934,\"field_length\":\"F14\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"YYYYMMDDHH24MMSS\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POSpecRatePolicy\",\"create_user\":\"超级管理员\",\"parent_id\":929},{\"field_desc\":\"商品级资费计划\",\"field_name\":\"RatePlans\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":935,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POSpecRatePolicy\",\"create_user\":\"超级管理员\",\"parent_id\":929},{\"field_desc\":\"一条资费\",\"field_name\":\"RatePlan\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"+\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":936,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"RatePlans\",\"create_user\":\"超级管理员\",\"parent_id\":935},{\"field_desc\":\"资费计划标识\",\"field_name\":\"RatePlanID\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":937,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"RatePlan\",\"create_user\":\"超级管理员\",\"parent_id\":936},{\"field_desc\":\"资费描述\",\"field_name\":\"Description\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"?\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":938,\"field_length\":\"V300\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"RatePlan\",\"create_user\":\"超级管理员\",\"parent_id\":936},{\"field_desc\":\"ICB参数值\",\"field_name\":\"POICB\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"*\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":939,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"RatePlan\",\"create_user\":\"超级管理员\",\"parent_id\":936},{\"field_desc\":\"资费模式\",\"field_name\":\"ICBPatterNumber\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":940,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POICB\",\"create_user\":\"超级管理员\",\"parent_id\":939},{\"field_desc\":\"资费模式描述\",\"field_name\":\"Description\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":941,\"field_length\":\"V500\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POICB\",\"create_user\":\"超级管理员\",\"parent_id\":939},{\"field_desc\":\"参数（可能有多个）\",\"field_name\":\"Parameter\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"*\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":942,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"POICB\",\"create_user\":\"超级管理员\",\"parent_id\":939},{\"field_desc\":\"参数编码\",\"field_name\":\"ParameterNumber\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":943,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"Parameter\",\"create_user\":\"超级管理员\",\"parent_id\":942},{\"field_desc\":\"参数名\",\"field_name\":\"ParameterName\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":944,\"field_length\":\"V200\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"Parameter\",\"create_user\":\"超级管理员\",\"parent_id\":942},{\"field_desc\":\"一条产品规格信息\",\"field_name\":\"ProductInfo\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"+\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":945,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"Products\",\"create_user\":\"超级管理员\",\"parent_id\":926},{\"field_desc\":\"产品规格基本信息\",\"field_name\":\"ProductSpec\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":946,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductInfo\",\"create_user\":\"超级管理员\",\"parent_id\":945},{\"field_desc\":\"产品级资费\",\"field_name\":\"ProductRatePlans\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"？\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":947,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductInfo\",\"create_user\":\"超级管理员\",\"parent_id\":945},{\"field_desc\":\"产品属性\",\"field_name\":\"ProductSpecCharacters\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":948,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductInfo\",\"create_user\":\"超级管理员\",\"parent_id\":945},{\"field_desc\":\"关联产品\",\"field_name\":\"ProductSpecRelation\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"？\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":949,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductInfo\",\"create_user\":\"超级管理员\",\"parent_id\":945},{\"field_desc\":\"产品规格编号\",\"field_name\":\"ProductSpecNumber\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":950,\"field_length\":\"V7\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpec\",\"create_user\":\"超级管理员\",\"parent_id\":946},{\"field_desc\":\"产品规格名称\",\"field_name\":\"ProductSpecName\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":951,\"field_length\":\"V40\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpec\",\"create_user\":\"超级管理员\",\"parent_id\":946},{\"field_desc\":\"产品规格状态\",\"field_name\":\"Status\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":952,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"A-正常商用S-内部测试T-测试待审R-试商用       \",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpec\",\"create_user\":\"超级管理员\",\"parent_id\":946},{\"field_desc\":\"产品描述\",\"field_name\":\"Description\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"？\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":953,\"field_length\":\"V500\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpec\",\"create_user\":\"超级管理员\",\"parent_id\":946},{\"field_desc\":\"一条资费\",\"field_name\":\"ProductRatePlan\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"+\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":954,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductRatePlans\",\"create_user\":\"超级管理员\",\"parent_id\":947},{\"field_desc\":\"资费计划标识\",\"field_name\":\"RatePlanID\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":955,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductRatePlan\",\"create_user\":\"超级管理员\",\"parent_id\":954},{\"field_desc\":\"资费描述\",\"field_name\":\"Description\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"?\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":956,\"field_length\":\"V300\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductRatePlan\",\"create_user\":\"超级管理员\",\"parent_id\":954},{\"field_desc\":\"ICB参数值\",\"field_name\":\"ProductOrderICB\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"*\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":957,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductRatePlan\",\"create_user\":\"超级管理员\",\"parent_id\":954},{\"field_desc\":\"资费模式\",\"field_name\":\"ICBPATTERNNUMBER\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":958,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductOrderICB\",\"create_user\":\"超级管理员\",\"parent_id\":957},{\"field_desc\":\"资费模式描述\",\"field_name\":\"Description\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":959,\"field_length\":\"V500\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductOrderICB\",\"create_user\":\"超级管理员\",\"parent_id\":957},{\"field_desc\":\"参数（可能有多个）\",\"field_name\":\"Parameter\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"*\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":960,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductOrderICB\",\"create_user\":\"超级管理员\",\"parent_id\":957},{\"field_desc\":\"参数编码\",\"field_name\":\"ParameterNumber\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":961,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"Parameter\",\"create_user\":\"超级管理员\",\"parent_id\":960},{\"field_desc\":\"参数名\",\"field_name\":\"ParameterName\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":962,\"field_length\":\"V200\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"Parameter\",\"create_user\":\"超级管理员\",\"parent_id\":960},{\"field_desc\":\"一个属性\",\"field_name\":\"ProductSpecCharacter\",\"field_type\":\"-\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"+\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":963,\"field_length\":\"-\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpecCharacters\",\"create_user\":\"超级管理员\",\"parent_id\":948},{\"field_desc\":\"产品属性代码\",\"field_name\":\"ProductSpecCharacterNumber\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":964,\"field_length\":\"V11\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpecCharacter\",\"create_user\":\"超级管理员\",\"parent_id\":963},{\"field_desc\":\"属性名称\",\"field_name\":\"Name\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":965,\"field_length\":\"V40\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpecCharacter\",\"create_user\":\"超级管理员\",\"parent_id\":963},{\"field_desc\":\"属性枚举值取值，可能有多个\",\"field_name\":\"ValueSource\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"*\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":966,\"field_length\":\"V40\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpecCharacter\",\"create_user\":\"超级管理员\",\"parent_id\":963},{\"field_desc\":\"产品规格标识\",\"field_name\":\"ProductSpecID_B\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":967,\"field_length\":\"V20\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpecRelation\",\"create_user\":\"超级管理员\",\"parent_id\":949},{\"field_desc\":\"依赖关系\",\"field_name\":\"RelationType\",\"field_type\":\"String\",\"edit_time\":null,\"is_select\":\"\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":146,\"edit_user\":\"\",\"id\":968,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"value_desc\":\"1-依赖2-互斥3-组成\",\"value\":\"\",\"map\":{\"isChoose\":\"notRule\",\"notRule\":\"null\",\"isConfig\":\"2\"},\"create_time\":{\"time\":1541645872000,\"minutes\":57,\"seconds\":52,\"hours\":10,\"month\":10,\"year\":118,\"timezoneOffset\":-480,\"day\":4,\"date\":8},\"parent_name\":\"ProductSpecRelation\",\"create_user\":\"超级管理员\",\"parent_id\":949}],\"port_id\":146}}";
        JSONObject json=JSONObject.fromObject(jsonStr);
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
                ResMessage ResMessage=(ResMessage)JSONObject.toBean(jsonObject, ResMessage.class);
                //  System.out.println( ResMessage.toString());
               *//* Iterator iterator1 = json1.keys();
                while (iterator1.hasNext()) {
                    //拿到树list对应的值
                    String key1 = (String) iterator1.next();
                    String value1 = json1.getString(key1);
                    if("Root".equals(key1)){
                        continue;
                    }
                 }*//*
                if("Root".equals(ResMessage.getField_name())&&"0".equals(ResMessage.getId())){
                    //节点不需要
                    continue;
                }
                if("InterBOSS".equals(ResMessage.getField_name())&&ResMessage.getId().equals(0)){
                    //节点需要
                    pId = -1;
                }
                list.add(ResMessage);
            }

            List<ResMessage> ResMessages = formatTree(list);
            //  JSONObject jsonObject = new JSONObject();
         //   JSONObject json1 = getChild(list, pId, jsonObject);
           System.out.println("args = [" + args + "]");
        }
    }*/


    public static List<ResMessage> formatTree(List<ResMessage> list ){
        ResMessage root=new ResMessage();
        ResMessage node=new ResMessage();
        List<ResMessage> treelist=new ArrayList<>(); //拼凑好的Json数据
        List<ResMessage> parentNodes=new ArrayList<>(); // 存放所有父节点
        if(list!=null && list.size()>0){
            root=list.get(0); //第一个一定是根节点 0
            for(int i=1; i<list.size(); i++){
                node=list.get(i);
                if(node.getParent_id().equals(root.getId())){ //从跟节点开始遍历是不是子节点
                    parentNodes.add(node);
                    root.getChildren().add(node);
                }else{ //获取root子节点的孩子节点
                    getChildrenNodes(parentNodes, node);

                    parentNodes.add(node);
                }
            }
        }
        treelist.add(root);
        return treelist;

    }

    private static void getChildrenNodes(List<ResMessage> parentNodes , ResMessage node){
        for(int i=parentNodes.size()-1; i>=0; i--){
            ResMessage pnode=parentNodes.get(i);

            if(pnode.getId().equals(node.getParent_id())){
                pnode.getChildren().add(node);
                return;
            }


        }
    }

    /**
     * 入库
     * @param list 报文list
     * @param pId 父id,初始为0
     * @return
     */
    private static JSONObject getChild(ArrayList<ResMessage> list,Integer pId, JSONObject jsonObject){
        StringBuffer sbf = new StringBuffer();
      //  HashMap<String, ResMessage> childMap = new HashMap<>();
        ArrayList<ResMessage>  childList = new ArrayList<>();
        int i = 0;
        for(ResMessage message:list){
            i++;
            //序号不包含.，一级，json 对应pId==-1;xml对应pId=-1开始
            if(pId.equals(message.getParent_id())) {
                sbf.append("," + message.getId());
                //孩子节点
                childList.add(message);
               // childMap.put(message.getField_name(),message);
                System.out.println(message.getId() + ":pId=" + pId);
            }
        }

        if(sbf!=null&&sbf.length()>0&&i==list.size()){
            String sub = sbf.toString().substring(1);
            String[] split1 = sub.split(",");
            for(String str:split1){
                for(ResMessage message:list){
                    //找到当前节点对应的父节点
                    if(message.getId().equals(pId)){
                        message.setChildren(childList);
                        jsonObject.put(message.getField_name(),message);
                    }
                }
                getChild(list,Integer.valueOf(str),jsonObject);
            }
        }
        return jsonObject;
    }


    /** 取值比对
     * @param preContent 被比对json(预期结果)
     * @param resContent 比对json (响应结果)
     * @param TreeMap 被比对json(预期结果树,map结果)
     * @return 存在多层时比对？
     */
    private static boolean getValCompare11(String resContent,String preContent,String is_preResult,HashMap<String,Object> TreeMap) throws CompareExcetion,Exception {
        boolean  flag = true;

          if(!isJson(resContent)||!isJson( preContent)){
            //不是json
            flag = false;
            throw new Exception("不是json，不能对比");
        }

        //判断是不是xml数据
        if(isXML(resContent)){
          //  xml2jsonString(compareContent);
        }

       //响应
        JSONObject resJson = JSONObject.fromObject(resContent);
        Iterator resIterator = resJson.keys();

        JSONObject preJson = JSONObject.fromObject(preContent);
        Iterator preIterator = preJson.keys();
        //String,num,Long,对象，数组，对象或者数组
        while(resIterator.hasNext()){
            //拿到key
            String  resKey = (String) resIterator.next();
            //该方法只能拿到最外层的值
            String resValue = resJson.getString(resKey);
            // json2 key查找json1
            while(preIterator.hasNext()) {
                //拿到key
                String preKey = (String) preIterator.next();
                //该方法只能拿到最外层的值
                String preValue = preJson.getString(preKey);
                //预期节点id，用于查询规则
                String id;
                //key不相等
                if(!preKey.equals(resKey)){
                    //key不存在
                    if(preKey.equals("id")){
                        continue;
                    }
                    //key不相等，不比较。
                    if(!preJson.containsKey(resKey)){
                        //resKey键在preJson中不存在，结束内存循环
                        throw new CompareExcetion("响应结果中key："+resKey+"在预期结果中不存在。");
                    }
                }else{
                    //key存在相同
                    if(!StringUtils.isEmpty(resValue)&&resValue!=null&&!StringUtils.isEmpty(preValue)&&preValue!=null){
                        Object resJson1 = new JSONTokener(resValue).nextValue();
                        Object preJson1 = new JSONTokener(preValue).nextValue();
                        if((resJson1 instanceof JSONObject)&&(preJson1 instanceof JSONObject)){
                            //响应截取json对应value类型；0-JSONObject，1-JSONArray，2-String,3对应叶子值
                            //jsonTypeCompare(preKey,preTree,resJson1,preJson1);
                            if("1".equals(is_preResult)) {
                                //勾选，需要比较规则
                                JSONObject preValueJson = JSONObject.fromObject(preValue);
                                id = preValueJson.getString("id");
                                if("0".equals(id)){
                                    //Root或者InterBoos不比较
                                }else{
                                    ResMessage preMess = getPreRule(id,preKey, TreeMap);
                                    JSONObject treeChildJson = JSONObject.fromObject(preMess.getChildren());
                                    //规则存在，进行规则比对
                                    if(preMess!=null){
                                        ruleCompare(resJson1,preMess,"0");
                                    }
                                }
                            }
                            getValCompare11(resValue,preValue,is_preResult,TreeMap);
                        }else if((resJson1 instanceof JSONArray)&&(preJson1 instanceof JSONArray)){
                            //通过key拿到对应的key-valie,并转化为数组
                            if("1".equals(is_preResult)) {
                                //勾选，需要比较规则
                                JSONObject preValueJson = JSONObject.fromObject(preValue);
                                id = preValueJson.getString("id");
                                if("0".equals(id)){
                                    //Root或者InterBoos不比较
                                }else{
                                    ResMessage preMess = getPreRule(id,preKey, TreeMap);
                                    JSONObject treeChildJson = JSONObject.fromObject(preMess.getChildren());
                                    //规则存在，进行规则比对
                                    if(preMess!=null){
                                        ruleCompare(resJson1,preMess,"0");
                                    }
                                }
                            }
                            JSONArray resJsonArray = JSON.parseArray(resValue);
                            JSONArray preJsonArray = JSON.parseArray(preValue);
                          /*   JSONObject resValue1=JSONObject.fromObject(resValue);
                            JSONObject preValue1=JSONObject.fromObject(preValue);
                            JSONArray resJsonArray =resValue1.getJSONArray(resKey);
                            JSONArray preJsonArray =preValue1.getJSONArray(preKey);*/

                            /*if(resJsonArray.size()>preJsonArray.size()){//预期个数多余实际数
                                flag = false;
                                throw new Exception("键"+resKey+"：预期个数多余实际数");
                            }*/

                            for (int i = 0; i <resJsonArray.size() ; i++) {
                                for(int j = 0; j <preJsonArray.size() ; j++){
                                    Object resObject = resJsonArray.get(i);
                                    Object preObject = preJsonArray.get(j);
                                    getValCompare11(resObject.toString(),preObject.toString(),is_preResult,TreeMap);
                                }
                            }

                        }else if((resJson1 instanceof String)&&(preJson1 instanceof String)){
                            if("1".equals(is_preResult)) {
                                //勾选，需要比较规则
                                JSONObject preValueJson = JSONObject.fromObject(preValue);
                                id = preValueJson.getString("id");
                                if("0".equals(id)){
                                    //Root或者InterBoos不比较
                                }else{
                                    ResMessage preMess = getPreRule(id,preKey, TreeMap);
                                    JSONObject treeChildJson = JSONObject.fromObject(preMess.getChildren());
                                    //规则存在，进行规则比对
                                    if(preMess!=null){
                                        ruleCompare(resJson1,preMess,"0");
                                    }
                                }
                            }
                        } else{

                            //该层到取值,比对value是否相同,叶子节点
                            if(preValue!=null&&!StringUtils.isEmpty(preValue)){
                                //预期节点有值，直接比较值是否相等。不需要比较规则
                                JSONObject preValueJson = JSONObject.fromObject(preValue);
                                if(!resValue.equals(preValue)){
                                    //值不相等
                                    flag = false;
                                    throw new CompareExcetion(preKey+"预期值："+resValue+",实际值："+preValue);
                                }
                            }


                            //该层到取值,比对value是否相同
                            JSONObject preJson11 = JSONObject.fromObject(preValue);
                            Iterator iterator = preJson11.keys();
                            while(iterator.hasNext()){
                                String key =(String) iterator.next();
                                if(preJson11.containsKey("value")){
                                    if("value".equals(key)){
                                        if(preJson11.getString(key)!=null&&!StringUtils.isEmpty(preJson11.getString(key))){
                                            //预期叶子,有值
                                            if(!resValue.equals(preJson11.getString(key))){
                                                //值不相等
                                                flag = false;
                                                throw new CompareExcetion(preKey+"预期值："+preJson11.getString(key)+"," +
                                                        "实际值："+resValue);
                                            }
                                        }else{
                                            //预期叶子无值，比规则
                                            System.out.println(preKey+"规则比较====");
                                            break;
                                        }
                                    }
                                }else{
                                    //预期非叶子，比规则
                                    System.out.println(preKey+"规则比较====");
                                    break;
                                }
                            }
                        }
                    }else{
                        //resValue==null||preValue==null
                    }

                    }
            }
            if(!flag){
                //返回结果与预期不相符合
                break;
            }
        }
        return  flag;
    }


    /**
     * 获取当前节点对应的预期规则等信息
     * @param id map中规则对应id
     * @param preKey 预期key（用于提示）
     * @param TreeMap 截取的对应预期结果
     * @return ResMessage 预期比对信息
     */
  private static ResMessage getPreRule(String id,String preKey,HashMap<String,Object> TreeMap) throws CompareExcetion {
      ResMessage resMessage=null;
      if(id!=null&&!StringUtils.isEmpty(id)){
            //预期
            if(TreeMap!=null&&TreeMap.containsKey(id)&&TreeMap.get(id)!=null){
                //规则存在
                resMessage =(ResMessage) TreeMap.get(id);
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
     * 获取当前节点对应的预期规则等信息
     * @param preKey 预期key
     * @param TreeMap 截取的对应预期结果
     * @return ResMessage 预期比对信息
     */
  /*  private static ResMessage  getPreRule(String preKey,HashMap<String,Object> TreeMap) throws CompareExcetion,Exception {
        ResMessage resMessage=null;
        JSONArray preTreeJsonArray = JSON.parseArray(TreeMap);
        if(preTreeJsonArray != null){
            for (int i = 0; i <preTreeJsonArray.size() ; i++) {
                Object object = preTreeJsonArray.get(i);
                JSONObject treeJson = JSONObject.fromObject(object.toString());
                if(treeJson.containsKey(preKey)){
                    //规则存在
                    //预期
                    JSONObject preJson = JSONObject.fromObject(TreeMap);
                    if(preJson.containsKey(preKey)){
                        ObjectMapper objectMapper=new ObjectMapper();
                        JSONObject jsonObject=JSONObject.fromObject(preJson.toString());
                        resMessage=(ResMessage)JSONObject.toBean(jsonObject, ResMessage.class);
                    }
                    // 比对：类型（可选，必选），长度，是否为空,约束（可选，必选）
                  *//*   preMessRule.getField_type();
                    preMessRule.getField_length();
                    preMessRule.getIs_empty();
                    preMessRule.getConstraints();
                    preMessRule.getRule();*//*
                    //孩子节点
                   *//* JSONObject treeChildJson = JSONObject.fromObject(preMessRule.getChildren());
                    preTree = treeChildJson.toString();*//*
                }else{
                    //接口应答报文模板有修改，未找到对应规则，请重新加载用例中的应答报文树
                    throw new CompareExcetion(";键："+preKey+"接口应答报文模板有修改，未找到对应规则，" +
                            "请重新加载用例中的应答报文树");
                }
            }
        }
        return resMessage;
    }*/

    /**
     * 规则比对
     * @param resJson1 应答报文节点
     * @param preMessRule 预期规则
     * @param isLeaf 1是叶子，0非叶子
     * @return true 成功，false 失败
     */
    public static Boolean ruleCompare(Object resJson1,ResMessage preMessRule,String isLeaf) throws CompareExcetion {
        boolean flag = true;
        //预期结果存在值
       /* if(preMessRule.getValue()!=null&&!StringUtils.isEmpty(preMessRule.getValue())){
            if(resJson1 instanceof JSONObject){
                throw  new CompareExcetion("预期中key:"+preMessRule.getField_name()+"存在值，应答报文返回结果是对象！");
            }
            if(resJson1 instanceof JSONArray){
                throw  new CompareExcetion("预期中key:"+preMessRule.getField_name()+"存在值，应答报文返回结果是数组！");
            }

            if(resJson1 instanceof String){
               // throw  new CompareExcetion("预期中key:"+preMessRule.getField_name()+"存在值，应答报文返回结果是数组！");
            }
            //值不相等
            if(resJson1!=null&&!preMessRule.getValue().equals(resJson1.toString())){
                throw  new CompareExcetion("预期中key:"+preMessRule.getField_name()+"的值为："+preMessRule.getValue()+"，" +
                        "应答报文返回结果为："+resJson1.toString());
            }
            //响应无返回值，此时需要规则反向比对响应,可能该节点不是必须节点（另一方法）
        }*/
            // 比对：类型（可选，必选），长度，是否为空,约束（可选，必选）
            String field_type = preMessRule.getField_type();
            String field_length = preMessRule.getField_length();
            String is_empty = preMessRule.getIs_empty();
            String constraints = preMessRule.getConstraints();
            String rule = preMessRule.getRule();
            //规则类型
            String ruleKey = null;
            //规则对象值
            String ruleValue = null;

            if(rule!=null&&!StringUtils.isEmpty(rule)&&"1".equals(isLeaf)){
                // rule规则有值,响应是叶子节点
                //rule:0|key|value
                String[] split = rule.split("\\|");
                //key
                ruleKey = split[1];
                //value
                if(split!=null&&split.length>=3){
                    ruleValue = split[2];
                }
                //rule对应规则
                getRuleItem(resJson1,ruleKey,ruleValue,preMessRule);
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
    public static Boolean getRuleItem(Object resJson1,String ruleKey,String ruleValue,ResMessage preMessRule) throws CompareExcetion {
        boolean flag = true;
        switch (ruleKey){
            //固定值
            case Constants.RULE_FIXED_VALUE :
                flag = fixedCompare(resJson1,ruleValue,preMessRule.getField_name());
                break;
            // 取值不为空
            case Constants.RULE_IS_NULL :
                flag = isNotCompare(resJson1,ruleValue,preMessRule.getField_name());
                break;
            //正整数或0
            case Constants.RULE_INT :
                flag = integerCompare(resJson1,ruleValue,preMessRule.getField_name());
                break;
            //表中获取
            case Constants.RULE_TABLE :
                break;
            //日期格式
            case Constants.RULE_TIME :
                break;
            //不包含某些值
            case Constants.RULE_NOT_INCLUDED :
                break;
            //值长度
            case Constants.RULE_LENGTH :
                break;
            //j保留几位小数
            case Constants.RULE_DECIMAL :
                break;
            //大于等于
            case Constants.RULE_GT_EQ :
                break;
            //等于
            case Constants.RULE_EQ :
                break;
            //区间值
            case Constants.RULE_INTERVAL :
                break;
            //多个值
            case Constants.RULE_MULTIPLE_VAL :
                break;
            //模糊匹配
            case Constants.RULE_LIKE :
                break;
            default:
                //无rule规则
                break;
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
    public static Boolean integerCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
        boolean flag = true;
        if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
            flag = true;
        }else{
            throw new CompareExcetion("响应key:"+preKey+"对应值不能为空！");
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
    public static Boolean isNotCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
        boolean flag = true;
        if(resJson1!=null&&!StringUtils.isEmpty(resJson1.toString())){
            flag = true;
        }else{
            throw new CompareExcetion("响应key:"+preKey+"对应值不能为空！");
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
    public static Boolean fixedCompare(Object resJson1,String ruleValue,String preKey) throws CompareExcetion{
        boolean flag = true;
        if(resJson1==null){
            throw new CompareExcetion("预期key:"+preKey+"固定值为："+ruleValue+"。响应key:"+preKey+"返回值为空！");
        }
        if(resJson1!=null&& StringUtils.isEmpty(resJson1.toString())){
            throw new CompareExcetion("预期key:"+preKey+"固定值为："+ruleValue+"。响应key:"+preKey+"返回值为空！");
        }
        if(ruleValue.equals(resJson1.toString())){
            flag = true;
        }else{
            throw new CompareExcetion("预期key:"+preKey+"固定值为："+ruleValue+"。响应key:"+preKey+"返回值为:"+resJson1);
        }
        return flag;
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

    /** 取值比对
     * @param message_tree 被比对json(预期结果)
     * @param compareContent 比对json (响应结果)
     * @return 存在多层时比对？
     */
    private  boolean getValCompare1(String compareContent,String is_preResult,String message_tree) throws Exception {
        boolean  flag = true;

        //判断是不是json数据
        if(!isJson(message_tree)||!isJson( compareContent)){
            //不是json
            flag = false;
            throw new Exception("不是json，不能对比");
        }

        JSONObject beJson = JSONObject.fromObject(message_tree);
        Iterator beIterator = beJson.keys();

        JSONObject compareJson = JSONObject.fromObject(compareContent);
        Iterator compareIterator = compareJson.keys();

        while(compareIterator.hasNext()){
            //拿到key
            String  compareKey = (String) compareIterator.next();
            //该方法只能拿到最外层的值
            String compareValue = compareJson.getString(compareKey);

            while (beIterator.hasNext()){
                //拿到key
                String beKey = (String) beIterator.next();
                //该方法只能拿到最外层的值
                String beValue = beJson.getString(beKey);

               // Object compareJson1 = new JSONTokener(compareValue).nextValue();
              // Object beJson1 = new JSONTokener(beValue).nextValue();
            }

            // json2 key查找json1
            /* while(beIterator.hasNext()) {
                //拿到key
                String beKey = (String) beIterator.next();
                //该方法只能拿到最外层的值
                String beValue = beJson.getString(beKey);
                if(!compareKey.equals(beKey)){
                    //key不相等，不比较。
                	if(!beJson.containsKey(compareKey)){
                		//compareKey键在beJson中不存在，结束内存循环
                		break;
                	}else{
                		//键在当前json中有存在，但是还没取到
                		continue;
                	}
                    //throw new Exception("键："+compareKey+"不存在");
                }else{
                    //key存在
                    beValue = beJson.getString(compareKey);

                   //非叶子节点，判断规则12项中选中值是否满足
            		if("1".equals(is_preResult)){
            			//勾线，需要比较规则12条
            		}

                    Object compareJson1 = new JSONTokener(compareValue).nextValue();
                    Object beJson1 = new JSONTokener(beValue).nextValue();
                    if((compareJson1 instanceof JSONObject)&&(beJson1 instanceof JSONObject)){//key 对应的value是JSONObject
                    	getValCompare1(beValue,compareValue,is_preResult,message_tree);
                    }else if((compareJson1 instanceof JSONArray)&&(beJson1 instanceof JSONArray)){//key 对应的value是JSONArray
                         //通过key拿到对应的key-valie,并转化为数组
                          JSONArray compareJsonArray = JSON.parseArray(compareValue);
                            JSONArray beJsonArray = JSON.parseArray(beValue);
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
                                getValCompare1(beObject.toString(),compareObject.toString(),is_preResult,message_tree);
                            }
                        }
                    } else{
                    	//该层到取值,比对value是否相同,叶子节点
                    	if(beValue!=null&&!StringUtils.isEmpty(beValue)){
                    		//预期节点有值，直接比较值是否相等。不需要比较规则12条
                    		if(!compareValue.equals(beValue)){
                            	//值不相等
                                flag = false;
                                throw new Exception(beKey+"预期值："+compareValue+",实际值："+beValue);
                            }
                    	}else{
                    		//预期节点无值
                    		if("1".equals(is_preResult)){
                    			//勾线，需要比较规则12条
                    		}
                    	}
                    }
                }
                if(flag){//找到对应值，终止内层循环，或者key不存在
                    break;
                }
            }*/
        }
        return  flag;
    }


    public static void main(String[] args) {
        String tree = "{\"list\":{\"tree\":[{\"field_name\":\"InterBOSS\",\"field_type\":\"\",\"is_select\":\"0\",\"constraints\":\"\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":0,\"id\":0,\"field_length\":\"\",\"select_list\":\"\",\"rule\":\"\",\"parent_name\":\"\",\"parent_id\":-1,\"value\":\"\"},{\"field_name\":\"Version\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":812,\"field_length\":\"—\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"\"},{\"field_name\":\"TestFlag\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":813,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"sdsff\"},{\"field_name\":\"BIPType\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":814,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"\"},{\"field_name\":\"BIPCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":816,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"ActivityCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":817,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"ActionCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":818,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"TestFlag1\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":815,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"包含\"}]}}";

        String res = "{\"InterBOSS\":{\"Version\":1,\"TestFlag\":\"sdsff\",\"BIPType\":{\"BIPCode\":\"\",\"ActivityCode\":\"设置\",\"ActionCode\":\"55\"},\"TestFlag1\":\"包含\"}}";
        String pre = "{\"InterBOSS\":{\"Version\":1,\"TestFlag\":\"sdsff\",\"BIPType\":{\"BIPCode\":\"\",\"ActivityCode\":\"设置\",\"ActionCode\":\"55\"},\"TestFlag1\":\"包含\"}}";
        System.out.println("args = [" + args + "]");
        //处理json或者xml格式树（根节点处理）
        List<ResMessage> tree1 = getMessageTree(tree);
        //将树list层级化
        List<ResMessage> formatTree = formatTree(tree1);

        JSONObject jsonTree = new JSONObject();
        jsonTree.put("list", formatTree);

        //获取树，port_id对应的整颗树，map("节点id","节点信息")
       HashMap<String,Object> TreeMap = new HashMap<>();


        try {
            getValCompare11(res, pre,"1", TreeMap);
            System.out.println("args = [" + args + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
