package com.example.boot.script;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**脚本信息获取
 * Administrator zhoulk
 * date: 2018/10/11 0011
 */
public class RunScript {

    /**
     * 运行脚本
     * @param shell 脚本字符串
     */
    public static  void execShell(String shell) {
       // String[] cmd = {"/bin/sh", "-c", "ps -elf | grep -v grep | grep EuropeStaticsEntrance | wc -l"};
        StringBuffer result = new StringBuffer();
        try {
            Process exec = Runtime.getRuntime().exec(shell);
           /* BufferedInputStream in = new BufferedInputStream(exec.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));*/
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(exec.getInputStream(), "gbk"));
            String lineStr;
            while ((lineStr = bufferedReader.readLine()) != null) {
                result.append(lineStr);
            }
            bufferedReader.close();
            bufferedReader.close();
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
         String shell = "#!/bin/bash for i in `seq 1 100` do j=$[$j+$i] done echo $j";
        String shell1 = "rasdial.exe 宽带连接 13900000000 111111";
        execShell(shell1);
    }
}
