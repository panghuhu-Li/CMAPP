package com.lxz.controllers;

import com.lxz.entity.Administrator;
import com.lxz.services.AdministratorService;
import com.lxz.servicesimpl.AdministratorServiceimpl;

import java.io.IOException;
import java.util.List;

import javax.xml.crypto.Data;

public class Adminidtratorcontrollers {

    private AdministratorService administratorService = new AdministratorServiceimpl();

    // 添加管理员注册
    public boolean addAdministrator(Object object) throws IOException {
        return administratorService.add(object);
    }

    // 登录人员权限判断
    public String whoRegister(String accountNumber, String password) throws IOException {
        return administratorService.whoRegister(accountNumber, password);
    }

    public List<Object> getAdministrator() throws IOException {
        return administratorService.getAdministrator();
    }

    public boolean delete(String accountNumber) throws IOException {
        return administratorService.delete(accountNumber);
    }

    public Object searchAdministrator(String name) throws IOException {
        return administratorService.search(name);
    }
    
    public boolean modifyAdministrator(String accountNumber,String name, String linkWay) throws IOException {
        return administratorService.modify(accountNumber, name, linkWay);
    }
}
