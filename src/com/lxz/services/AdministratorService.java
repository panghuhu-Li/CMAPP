package com.lxz.services;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 用户数据接口
 * @author: 李星泽
 * @create: 2020-07-16 06:23
 **/
public interface AdministratorService extends BaseServices {

    /**
     * @param object:管理员对象
     * @return boolean:判断是否添加成功
     * @description 添加管理员注册
     **/
    boolean add(Object object) throws IOException;

    /**
     * @param accountNumber:管理员登录账号
     * @return String:返回是否成功删除
     * @description 根据管理员账户删除
     **/
    boolean delete(String accountNumber) throws IOException;

    /**
     * @param accountNumber:管理员登录账号
     * @param name:管理员姓名
     * @param linkWay:管理员联系方式
     * @return boolean:返回是否修改成功
     * @description 根据管理员登录账号修改管理员姓名和联系方式
     **/
    boolean modify(String accountNumber, String name, String linkWay) throws IOException;

    /**
     * @param name:管理员姓名
     * @return Object:返回该管理员对象
     * @description 根据管理员姓名进行查找
     **/
    Object search(String name) throws IOException;

    /**
     * @param accountNumber:管理员登录账号
     * @param password:管理员登录密码
     * @return String:返回管理员登录类型
     * @description 登录人员权限判断
     **/
    String whoRegister(String accountNumber, String password) throws IOException;

    /**
     * @return List<Object>:返回所有管理员信息
     * @description 获得所有管理员信息
     **/
    List<Object> getList() throws IOException;
}
