package com.lxz.controllers;

import com.lxz.Factory.MyServiceFactory;
import com.lxz.services.AdministratorService;
import com.lxz.servicesimpl.AdministratorServiceimpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 用户数据和返回结果接受层
 * @author: 李星泽
 * @create: 2020-07-16 06:13
 **/
public class Adminidtratorcontrollers {

    // 用工厂模式减少直接new对象
    private AdministratorService administratorService = (AdministratorServiceimpl) MyServiceFactory
            .createService("Administrator");

    /**
     * @param object:管理员对象
     * @return boolean:判断是否添加成功
     * @description 添加管理员注册
     **/
    public boolean addAdministrator(Object object) throws IOException {
        return administratorService.add(object);
    }

    /**
     * @param accountNumber:管理员登录账号
     * @param password:管理员登录密码
     * @return String:返回管理员登录类型
     * @description 登录人员权限判断
     **/
    public String whoRegister(String accountNumber, String password) throws IOException {
        return administratorService.whoRegister(accountNumber, password);
    }

    /**
     * @return List<Object>:返回所有管理员信息
     * @description 获得所有管理员信息
     **/
    public List<Object> getAdministrator() throws IOException {
        return administratorService.getList();
    }

    /**
     * @param accountNumber:管理员登录账号
     * @return String:返回是否成功删除
     * @description 根据管理员账户删除
     **/
    public boolean delete(String accountNumber) throws IOException {
        return administratorService.delete(accountNumber);
    }

    /**
     * @param name:管理员姓名
     * @return Object:返回该管理员对象
     * @description 根据管理员姓名进行查找
     **/
    public Object searchAdministrator(String name) throws IOException {
        return administratorService.search(name);
    }

    /**
     * @param accountNumber:管理员登录账号
     * @param name:管理员姓名
     * @param linkWay:管理员联系方式
     * @return boolean:返回是否修改成功
     * @description 根据管理员登录账号修改管理员姓名和联系方式
     **/
    public boolean modifyAdministrator(String accountNumber, String name, String linkWay) throws IOException {
        return administratorService.modify(accountNumber, name, linkWay);
    }
}
