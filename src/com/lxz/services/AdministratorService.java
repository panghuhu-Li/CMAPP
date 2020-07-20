package com.lxz.services;

import java.io.IOException;
import java.util.List;

public interface AdministratorService {

    //添加管理员
    boolean add(Object object) throws IOException;

    //删除管理员
    boolean delete(String accountNumber) throws IOException;

    //修改管理员信息
    boolean modify(String accountNumber,String name, String linkWay) throws IOException;

    //通过姓名查找管理员
    Object search(String name) throws IOException;
    
    //判断登录的账号密码是否正确,返回登录类型
    String whoRegister(String accountNumber, String password) throws IOException;
    
    //从文件获取所有管理者信息
    List<Object> getAdministrator() throws IOException;

}
