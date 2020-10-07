package com.lxz.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: CMAPP
 * @description 文件信息处理
 * @author: 李星泽
 * @create: 2020-07-16 12:03
 **/
public class FileUtils {

    /**
     * @description 储存用户注册信息 choose为true： 追加模式       false： 覆盖模式
     * @param filename:文件名
     * @param userJson:Json字符串
     * @param choose:追加模式
     * @throws IOException:
     */
    public void saveData(String filename,String userJson,boolean choose) throws IOException {
        String filepath = "Data/" + filename;
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(filepath,choose));
        bufferedWriter.write(userJson);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }

    /**
     * @description :从文件中读取管理员信息
     * @param filename:文件名
     * @return String:从文件中读取的Json字符转
     * @throws IOException:
     */
    public String readFile(String filename) throws IOException {
        String jsonString="";
        String filepath = "Data/" + filename;
        BufferedReader bufferedReader=new BufferedReader(new FileReader(filepath));
        String fileline=bufferedReader.readLine();
        jsonString=fileline;
        while(fileline!=null) {
            fileline=bufferedReader.readLine();
            if(fileline!=null){
                jsonString=fileline+","+jsonString;
            }
        }
        bufferedReader.close();
        return "["+jsonString+"]";
    }
}
