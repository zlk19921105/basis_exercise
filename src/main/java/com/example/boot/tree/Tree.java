package com.example.boot.tree;

import java.util.ArrayList;

/**
 * @author  zhoulk
 * date: 2018/10/29 0029
 */
public class Tree {
    public static void main(String[] args) {
       /* 序号	父元素名称	元素名称	约束	类型	长度	描述	取值说明
        1	InterBOSS	SvcCont	1	String	—	请求内容	XML格式的字符串
        1.1	SvcCont	POInfo	1	—	—	业务受理请求
        1.1.1	POInfo	OprTime	1	String	F14	操作时间
        1.1.2	POInfo	POSpecNumber	1	String	V9	商品规格编码
        1.1.3	POInfo	RspCode	1	String	F2	返回码	见返回码
        1.1.4	POInfo	RspDesc	?	String	V500	错误描述	RspCode=99时，应在RspDesc字段中填写相应的错误描述
        1.2	SvcCont	POInfo1	1	—	—	业务受理请求
        1.2.1	POInfo1	OprTime1	1	String	F14	操作时间
        1.2.2	POInfo1	POSpecNumber1	1	String	V9	商品规格编码
        1.2.3	POInfo1	RspCode1	1	String	F2	返回码	见返回码
        1.2.4	POInfo1	RspDesc1	?	String	V500	错误描述	RspCode=99时，应在RspDesc字段中填写相应的错误描述
        1.2.4.1	SvcCont41	POInfo41	1	—	—	业务受理请求
        1.3	SvcCont	POInfo2	1	—	—	业务受理请求
        2	InterBOSS	TestFlag	1	String	F1	测试标记	发起方填写
        0：非测试交易
        1：测试交易
        3	InterBOSS	TestFlag	1	String	F1	测试标记	发起方填写
        0：非测试交易
        1：测试交易*/
        String ss= "1.12.3.46";
        String[] split = ss.split("1.12.3.");
        String[] split1 = ss.split("1.12.3.46");
        System.out.println("args = [" + args + "]");
   /*     ArrayList<Object[]> list = new ArrayList();
       Object[]  arr = new Object[8];
       arr[0]="1";
       arr[1]="InterBOSS";
       arr[2]="SvcCont1";
       arr[3]="1";
       arr[4]="String";
       arr[5]="—";
       arr[6]="请求内容";
       arr[7]="一级";
       list.add(arr);

        arr = new Object[8];
        arr[0]="1.1";
        arr[1]="SvcCont1";
        arr[2]="POInfo1.1";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="二级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="1.2";
        arr[1]="SvcCont1";
        arr[2]="POInfo1.2";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="二级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="1.1.1";
        arr[1]="POInfo1.1";
        arr[2]="POInfo1.1.1";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="三级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="2";
        arr[1]="InterBOSS";
        arr[2]="SvcCont2";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="3";
        arr[1]="InterBOSS";
        arr[2]="SvcCont1";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="3.1";
        arr[1]="SvcCont3";
        arr[2]="POInfo3.1";
        arr[3]=null;
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="二级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="3.1.1";
        arr[1]="SvcCont3";
        arr[2]="POInfo3.1.1";
        arr[3]=null;
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="二级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="4";
        arr[1]="InterBOSS";
        arr[2]="SvcCont4";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="5";
        arr[1]="InterBOSS";
        arr[2]="SvcCont5";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="6";
        arr[1]="InterBOSS";
        arr[2]="SvcCont6";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="7";
        arr[1]="InterBOSS";
        arr[2]="SvcCont7";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[8];
        arr[0]="8";
        arr[1]="InterBOSS";
        arr[2]="SvcCont8";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[9];
        arr[0]="9";
        arr[1]="InterBOSS";
        arr[2]="SvcCont9";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[10];
        arr[0]="10";
        arr[1]="InterBOSS";
        arr[2]="SvcCont10";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);

        arr = new Object[11];
        arr[0]="11";
        arr[1]="InterBOSS";
        arr[2]="SvcCont11";
        arr[3]="1";
        arr[4]="String";
        arr[5]="—";
        arr[6]="请求内容";
        arr[7]="一级";
        list.add(arr);



        for(Object[] row:list){
            for(int i =0;i<row.length;i++){
                System.out.print(row[i]+",");
            }
            System.out.println("args = [" + args + "]");
        }

        //两个方法，一个入库，一个判重
        boolean flag = false;
        try{
            //NumVerification(list,".");
          //  storage(list,0,".",2,"");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            System.out.println("flag = [" + flag + "]");
        }
        System.out.println("args = [" + args + "]");*/
      //  getCh(list,0,".",2);
    }

    public static String storage(ArrayList<Object[]> list,Integer pId,String split,Integer tempId,String type){
        StringBuffer sbf = new StringBuffer();
        ArrayList<Object>  repeatList = new  ArrayList<>();
        for(Object[] row:list){
            //序号不包含.，一级，InterBOSS 对应id==0
          if((!row[0].toString().contains(split))&&pId==0){
                //1,2,3入库,pId=0
               sbf.append(","+row[0]);
               System.out.println(row[0]+":pId=0");
               //保证row[2]不重复
          }

          //保证只截到直接子节点
          if(row[0].toString().startsWith(split)&&row[0].toString().length()==split.length()+1){
                  //非根节点入库；pId!=0
                  sbf.append(","+row[0]);
                 //查询时候，名称加pid+tempId+名称 返回id

                  System.out.println(row[0]+":pId!=0");
           }

        }

        System.out.println("------------------------------");
        if(sbf!=null&&sbf.length()>0){
            String sub = sbf.toString().substring(1);
            String[] split1 = sub.split(",");
            for(String str:split1){
                storage(list,1,str+".",tempId,type);
            }
        }
    return null;
    }

    private static boolean NumVerification(ArrayList<Object[]> list,String split) throws Exception{
        StringBuffer sbf = new StringBuffer();
        ArrayList<Object>  repeatList = new  ArrayList<>();
        ArrayList<Object>  orderList = new  ArrayList<>();
        boolean flag = true;
        for(Object[] row:list) {
            if (!orderList.contains(row[0])) {
                orderList.add(row[0]);
            } else {
                flag = false;
                throw new Exception("不能存在相同的序号，重复序号：" + row[0]);
            }
        }
        for(Object[] row:list){
            //序号不包含.，一级，InterBOSS 对应id==0
            if((!row[0].toString().contains(split))&&".".equals(split)){
                sbf.append(","+row[0]);
                if(!repeatList.contains(row[2])){
                    repeatList.add(row[2]);
                }else{
                    flag = false;
                  throw  new Exception("名称重复,在同一层中出现重复名称："+row[2]);
                }
                System.out.println(row[0]+":pId=0");
                //保证row[2]不重复
            }

            //保证只截到直接子节点
            if(row[0].toString().startsWith(split)&&row[0].toString().length()==split.length()+1){
                sbf.append(","+row[0]);
                //查询时候，名称加pid+tempId+名称 返回id
                if(!repeatList.contains(row[2])){
                    repeatList.add(row[2]);
                }else{
                    flag = false;
                    //同层名称重复
                    throw new Exception("在同一层中出现重复名称："+row[2]);
                }
                System.out.println(row[0]+":pId!=0");
            }
        }
        System.out.println("------------------------------");
        if(sbf!=null&&sbf.length()>0){
            String sub = sbf.toString().substring(1);
            String[] split1 = sub.split(",");
            for(String str:split1){
                NumVerification(list,str+".");
            }
        }
        return flag;
    }
}
