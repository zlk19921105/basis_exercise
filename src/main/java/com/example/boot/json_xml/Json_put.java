package com.example.boot.json_xml;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;

/**json修改
 * Administrator zhoulk
 * date: 2018/11/13 0013
 */
public class Json_put {
    public static void main(String[] args) {
        String data = "{\"data1\":[{\"sql_id\":121,\"sql_name\":\"fdsagadsgs\",\"pre_result\":\"fdsafa\",\"is_preResult\":1}],\"data2\":[{\"sql_id\":110,\"sql_name\":\"sql1\",\"pre_result\":\"fdsfdsdfsdss\",\"is_preResult\":1}],\"data3\":[{\"type\":0,\"sql_id\":119,\"sql_name\":\"dsffsadf\",\"use_code\":\"fdsdsaff\",\"pre_result\":\"fdasfsa\",\"text_temlateid\":\"\",\"message_tree\":\"\",\"tree_value\":{\"port_id\":\"114\",\"root\":{\"field_name\":\"InterBOSS/Root\",\"is_select\":\"0\",\"id\":0,\"select_list\":\"\",\"parent_id\":-1,\"value\":\"\"},\"leaf\":[{\"id\":111,\"value\":\"adds\"}]}},{\"type\":0,\"sql_id\":121,\"sql_name\":\"fdsagadsgs\",\"use_code\":\"fdsdsaff\",\"pre_result\":\"fdasfsa\",\"text_temlateid\":\"\",\"message_tree\":\"\",\"tree_value\":{\"port_id\":\"116\",\"root\":{\"field_name\":\"InterBOSS/Root\",\"is_select\":\"0\",\"id\":0,\"select_list\":\"\",\"parent_id\":-1,\"value\":\"\"},\"leaf\":[{\"id\":211,\"value\":\"124ffs\"},{\"id\":213,\"value\":\"35trr\"}]}}]}";

        String s = replaceMessTree(data);
        System.out.print(s);
    }

    public static String replaceMessTree(String data){
        JSONObject json = JSONObject.fromObject(data);
        if(json.containsKey("data3")){
            String data3 = json.getString("data3");
            net.sf.json.JSONArray compareJsonArray =json.getJSONArray("data3");
          //  JSONArray compareJsonArray = JSON.parseArray(data3);
            if(compareJsonArray!=null){
                for (int i = 0; i <compareJsonArray.size() ; i++) {
                    Object object = compareJsonArray.get(i);
                    JSONObject jsonTree = JSONObject.fromObject(object);
                    if(jsonTree.containsKey("tree_value")){
                        String tree_value = jsonTree.getString("tree_value");
                        //  "tree_value":{"port_id":"116","root":{"field_name":"InterBOSS/Root","is_select":"0","id":0,"select_list":"","parent_id":-1,"value":""},"leaf":[{"id":211,"value":"124ffs"},{"id":213,"value":"35trr"}]}
                        JSONObject jsonTreeValue = JSONObject.fromObject(tree_value);
                        if(jsonTree.containsKey("message_tree")){
                           //port_id 查询树
                           Integer port_id = Integer.valueOf(jsonTreeValue.getString("port_id"));
                           String tree= "{\"list\":{\"tree\":[{\"id\":0,\"field_name\":\"InterBOSS\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":-1},{\"id\":914,\"field_name\":\"SvcCont\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":0},{\"id\":915,\"field_name\":\"SyncInfo\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":914},{\"id\":916,\"field_name\":\"POInfo\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":915},{\"id\":917,\"field_name\":\"Action\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":918,\"field_name\":\"POSpecNumber\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":919,\"field_name\":\"POSpecName\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":920,\"field_name\":\"Status\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":921,\"field_name\":\"StartDate\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":922,\"field_name\":\"EndDate\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":923,\"field_name\":\"Description\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":924,\"field_name\":\"EnableCompanys\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":925,\"field_name\":\"POSpecRatePolicys\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":926,\"field_name\":\"Products\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":927,\"field_name\":\"POTimeStamp\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":916},{\"id\":928,\"field_name\":\"EnableCompanyID\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":924},{\"id\":929,\"field_name\":\"POSpecRatePolicy\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":925},{\"id\":930,\"field_name\":\"POSpecRatePolicyID\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":929},{\"id\":931,\"field_name\":\"Name\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":929},{\"id\":932,\"field_name\":\"Description\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":929},{\"id\":933,\"field_name\":\"StartDate\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":929},{\"id\":934,\"field_name\":\"EndDate\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":929},{\"id\":935,\"field_name\":\"RatePlans\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":929},{\"id\":936,\"field_name\":\"RatePlan\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":935},{\"id\":937,\"field_name\":\"RatePlanID\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":936},{\"id\":938,\"field_name\":\"Description\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":936},{\"id\":939,\"field_name\":\"POICB\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":936},{\"id\":940,\"field_name\":\"ICBPatterNumber\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":939},{\"id\":941,\"field_name\":\"Description\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":939},{\"id\":942,\"field_name\":\"Parameter\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":939},{\"id\":943,\"field_name\":\"ParameterNumber\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":942},{\"id\":944,\"field_name\":\"ParameterName\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":942},{\"id\":945,\"field_name\":\"ProductInfo\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":926},{\"id\":946,\"field_name\":\"ProductSpec\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":945},{\"id\":947,\"field_name\":\"ProductRatePlans\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":945},{\"id\":948,\"field_name\":\"ProductSpecCharacters\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":945},{\"id\":949,\"field_name\":\"ProductSpecRelation\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":945},{\"id\":950,\"field_name\":\"ProductSpecNumber\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":946},{\"id\":951,\"field_name\":\"ProductSpecName\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":946},{\"id\":952,\"field_name\":\"Status\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":946},{\"id\":953,\"field_name\":\"Description\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":946},{\"id\":954,\"field_name\":\"ProductRatePlan\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":947},{\"id\":955,\"field_name\":\"RatePlanID\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":954},{\"id\":956,\"field_name\":\"Description\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":954},{\"id\":957,\"field_name\":\"ProductOrderICB\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":954},{\"id\":958,\"field_name\":\"ICBPATTERNNUMBER\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":957},{\"id\":959,\"field_name\":\"Description\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":957},{\"id\":960,\"field_name\":\"Parameter\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":957},{\"id\":961,\"field_name\":\"ParameterNumber\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":960},{\"id\":962,\"field_name\":\"ParameterName\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":960},{\"id\":963,\"field_name\":\"ProductSpecCharacter\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":948},{\"id\":964,\"field_name\":\"ProductSpecCharacterNumber\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":963},{\"id\":965,\"field_name\":\"Name\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":963},{\"id\":966,\"field_name\":\"ValueSource\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":963},{\"id\":967,\"field_name\":\"ProductSpecID_B\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":949},{\"id\":968,\"field_name\":\"RelationType\",\"value\":\"\",\"parent_name\":\"\",\"parent_id\":949}],\"port_id\":22}}";
                            //加root/InterBOSS
                           Object root = jsonTreeValue.getString("root");
                           jsonTree.put("message_tree",tree);
                       }
                        compareJsonArray.set(i,jsonTree);
                    }
                }
            }
            json.put("data3",compareJsonArray);
            return  json.toString() ;
        }else{
            return data;
        }
    }
}
