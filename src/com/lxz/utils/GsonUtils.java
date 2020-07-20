package com.lxz.utils;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-16 12:02
 **/
public class GsonUtils {
    //转换为Json字符串
    public String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
    
    //返回一个对象集合
    public List<Object> toObjectList(String js, Class<?> c) {
        JsonParser parser = new JsonParser();
        //得到一个装有属性的Json数组，有几个对象就有几个数组
        JsonArray jsonArray = parser.parse(js).getAsJsonArray();
        Gson gson = new Gson();
        List<Object> objects = new ArrayList<>();
        for (JsonElement jsonElement : jsonArray) {
            Object object = gson.fromJson(jsonElement, c);
            objects.add(object);
        }
        return objects;
    }
    
    //将字符串转换为对象
    public static Object toObj(String js, Class<?> c) {
        JsonParser parser = new JsonParser();
        //得到一个装有属性的Json数组
        JsonArray jsonArray = parser.parse(js).getAsJsonArray();
        Gson gson = new Gson();
        Object obj = null;
        //将属性放在obj中
        for (JsonElement jsonElement : jsonArray) {
            obj = gson.fromJson(jsonElement, c);
        }
        return obj;
    }

}
