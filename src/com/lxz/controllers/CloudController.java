package com.lxz.controllers;

import com.lxz.Factory.MyServiceFactory;
import com.lxz.services.CloudInterface;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 云工厂数据接收和返回结果接收
 * @author: 李星泽
 * @create: 2020-07-16 20:25
 **/
public class CloudController {

    // 用工厂模式减少直接new对象

    private CloudInterface cloudInterface = (CloudInterface) MyServiceFactory.createService("Cloud");

    /**
     * @param object:云工厂创建的新对象
     * @return boolean:返回是否成功添加
     * @description 添加云工厂
     **/
    public boolean add(Object object) throws IOException {
        return cloudInterface.add(object);
    }

    /**
     * @return List<Object>:返回所有工厂对象
     * @description 返回所有的工厂对象
     **/
    public List<Object> getFactory() throws IOException {
        return cloudInterface.getList();
    }

    /**
     * @param factoryName:云工厂名称
     * @return Object:返回查找的云工厂对象
     * @description 通过工厂名称查找工厂并返回工厂对象
     **/
    public Object searchFactory(String factoryName) throws IOException {
        return cloudInterface.search(factoryName);
    }

    /**
     * @param accountNumber:云工厂负责人的登录账号
     * @return boolean:返回是否修改成功
     * @description 修改工厂工作状态
     **/
    public boolean modifyStatus(String accountNumber) throws IOException {
        return cloudInterface.modifyStatus(accountNumber);
    }
}
