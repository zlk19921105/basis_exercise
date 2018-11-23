package com.example.boot.zc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Administrator zhoulk
 * date: 2018/10/19 0019
 */
public class zc {
    public static void main(String[] args) {
        /*String str = "select * from data_manager where data_port like \"$_ fd0fdaf\" and config_name='$数据库连接名称'";
        String regEx = "['\"]\\$[\\u4e00-\\u9fa5a-zA-Z0-9 _-]+['\"]";
        // String regEx = "([\'\"])(.*?)\\1";
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);

        while(matcher.find()) {
            System.out.println(matcher.group().substring(1,matcher.group().length()-1));
            System.out.println("Start 0:"+matcher.start(0)+" End 0:"+matcher.end(0));//总匹配的索引
        }*/
        //1,2,3,4
        Integer pId = 1;
        ArrayList<Integer> cIds = new ArrayList<>();
     //   ArrayList<Integer> cIds = new ArrayList<>(); 1-0,2-1,3-1,4-3
        cIds.add(0);
        cIds.add(1);
        cIds.add(1);
        cIds.add(3);

    }
}
