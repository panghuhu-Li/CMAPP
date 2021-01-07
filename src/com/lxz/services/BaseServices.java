package com.lxz.services;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 主接口
 * @author: 李星泽
 * @create: 2020-07-16 06:23
 **/
public interface BaseServices {

    boolean add(Object object) throws IOException;

    List<Object> getList() throws IOException;

    //查找功能
    Object search(String name) throws IOException;
}
