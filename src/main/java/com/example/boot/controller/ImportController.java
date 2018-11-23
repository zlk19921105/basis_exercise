package com.example.boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.boot.bean.ResMessage;
import com.example.boot.bean.ResMessage;
import com.example.boot.exception.FileFormatException;
import com.example.boot.util.ImportUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * excel导入
 * Administrator zhoulk
 * date: 2018/10/28 0028
 */
@RestController
@RequestMapping("/import")
public class ImportController {
    //xml类型
    public static final String XML="xml";
    //json类型
    public static final String JSON="json";

    @RequestMapping("/importExcel")
    public String importExcel(HttpServletRequest request, HttpServletResponse response , @RequestParam(value = "file", required = false) MultipartFile file){
          //  response.setHeader("Access-Control-Allow-Origin",  request.getHeader("Origin"));
          //  response.addHeader("Access-Control-Allow-Credentials", "true");
        //文件上传路径
       // String filePath = file.getOriginalFilename();
        ArrayList<Object> list =  new ArrayList<>();
        try {
            List<Object[]> excelResult = ImportUtil.readExcel(file);
            //遍历得到行
            excelResult.forEach(row->{
                if(!(list.contains(row[1]))){
                    list.add(row[1]);
                }else{
                    System.out.print("存在重复协议模板名称:"+row[1]);
                    //return "存在重复协议模板名称:"+row[1];
                }

                //协议类型必须为xml,或者json
                if(!(XML.equals(row[2])||JSON.equals(row[2]))){
                    //return "存在协议模板类型不满足xml或者json格式:";
                }
                //遍历得到列值
               /* for (int i = 0; i <row.length ; i++) {
                    if(!(list.contains(row[1]))){
                        list.add(row[1]);
                    }else{
                        //return "存在重复协议模板名称:"+row[1];
                    }

                    //协议类型必须为xml,或者json
                    if(!(XML.equals(row[2])||JSON.equals(row[2]))){
                        //return "存在协议模板类型不满足xml或者json格式:";
                    }
                }*/
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (FileFormatException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping("/test2")
    public String test2(HttpServletRequest request, HttpServletResponse response,@RequestParam(value ="data") String data){
        String ss = data;
        return null;
    }

    @RequestMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse response){

        String jsonStr ="{\"list\":{\"tree\":[{\"field_name\":\"InterBOSS\",\"field_type\":\"\",\"is_select\":\"0\",\"constraints\":\"\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":0,\"id\":0,\"field_length\":\"\",\"select_list\":\"\",\"rule\":\"\",\"parent_name\":\"\",\"parent_id\":-1,\"value\":\"\"},{\"field_name\":\"Version\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":812,\"field_length\":\"—\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"\"},{\"field_name\":\"TestFlag\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":813,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"sdsff\"},{\"field_name\":\"BIPType\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":814,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"\"},{\"field_name\":\"BIPCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":816,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"ActivityCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":817,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"ActionCode\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":818,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"BIPType\",\"parent_id\":814,\"value\":\"\"},{\"field_name\":\"TestFlag1\",\"field_type\":\"String\",\"is_select\":\"0\",\"constraints\":\"1\",\"selectList\":[],\"order_id\":\"\",\"is_empty\":\"\",\"temp_id\":138,\"id\":815,\"field_length\":\"F1\",\"select_list\":\"\",\"rule\":\"2|notRule|null\",\"parent_name\":\"InterBOSS\",\"parent_id\":0,\"value\":\"包含\"}]}}";
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
            JSONArray jsonArray = com.alibaba.fastjson.JSON.parseArray(treeValue);
            for(int i= 0; i <jsonArray.size() ; i++){
                //具体的树节点list
                JSONObject json1=JSONObject.fromObject(jsonArray.get(i));
                ObjectMapper objectMapper=new ObjectMapper();
                JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(i).toString());
                ResMessage ResMessage=(ResMessage)JSONObject.toBean(jsonObject, ResMessage.class);
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
            JSONObject json1=new JSONObject();
            json1.put("list",ResMessages);
            return json1.toString();
        }
        return null;
    }

    /**
     * 格式层级树
     * @param list 树list
     * @return 格式化后的树
     */
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

    /**
     *
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

    /**
     * 入库
     * @param list 报文list
     * @param pId 父id,初始为0
     * @return
     */
    private  JSONObject getChild(ArrayList<ResMessage> list,Integer pId, JSONObject jsonObject){
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

}
