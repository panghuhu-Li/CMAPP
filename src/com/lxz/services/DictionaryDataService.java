package com.lxz.services;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-05 21:09
 **/
public interface DictionaryDataService extends BaseServices{

    boolean add(Object object) throws IOException;

    List<Object> getList() throws IOException;

    //查找功能
    Object search(String name) throws IOException;

    public boolean delete(String dictionaryNumber) throws IOException;
}
