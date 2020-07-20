package com.lxz.services;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-16 20:26
 **/
public interface CloudInterface {
    //添加工厂
    boolean add(Object object) throws IOException;
    //获得所有工厂对象
    List<Object> getFactory() throws IOException;
    //获得单个工厂对象
    Object searchFactory(String factoryName) throws IOException;
    //修改工厂工作状态
    boolean modifyStatus(String accountNumber) throws IOException;
}
