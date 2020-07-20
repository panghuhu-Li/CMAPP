package com.lxz.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-16 12:03
 **/
public class FileUtils {

    //储存用户注册信息 choose为true： 追加模式       false： 覆盖模式
    public void saveData(String filename,String userJson,boolean choose) throws IOException {
        String filepath = "Data/" + filename;
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(filepath,choose));
        bufferedWriter.write(userJson);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
    
    //从文件中读取管理员信息
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
