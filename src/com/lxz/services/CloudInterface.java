package com.lxz.services;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 数据处理接口
 * @author: 李星泽
 * @create: 2020-07-16 20:26
 **/
public interface CloudInterface extends BaseServices {
    /**
     * @param object:云工厂创建的新对象
     * @return boolean:返回是否成功添加
     * @description 添加云工厂
     **/
    boolean add(Object object) throws IOException;

    /**
     * @return List<Object>:返回所有工厂对象
     * @description 返回所有的工厂对象
     **/
    List<Object> getList() throws IOException;

    /**
     * @param factoryName:云工厂名称
     * @return Object:返回查找的云工厂对象
     * @description 通过工厂名称查找工厂并返回工厂对象
     **/
    Object search(String factoryName) throws IOException;

    /**
     * @param accountNumber:云工厂负责人的登录账号
     * @return boolean:返回是否修改成功
     * @description 修改工厂工作状态
     **/
    boolean modifyStatus(String accountNumber) throws IOException;
}
