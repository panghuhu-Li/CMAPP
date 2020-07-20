package com.lxz.controllers;

import com.lxz.services.CloudInterface;
import com.lxz.servicesimpl.CloudInterfaceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-16 20:25
 **/
public class CloudController {

    private CloudInterface cloudInterface=new CloudInterfaceImpl();

    //添加工厂
    public boolean add(Object object) throws IOException {
        return cloudInterface.add(object);
    }

    //返回所有的工厂对象
    public List<Object> getFactory() throws IOException{
        return cloudInterface.getFactory();
    }

    //通过工厂名称查找工厂并返回工厂对象
    public Object searchFactory(String factoryName) throws IOException {
        return cloudInterface.searchFactory(factoryName);
    }

    //修改工厂工作状态
    public boolean modifyStatus(String accountNumber) throws IOException {
        return cloudInterface.modifyStatus(accountNumber);
    }
}
