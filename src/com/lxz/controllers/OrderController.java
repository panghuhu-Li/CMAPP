package com.lxz.controllers;

import com.lxz.Factory.MyServiceFactory;
import com.lxz.services.AdministratorService;
import com.lxz.services.OrderService;
import com.lxz.servicesimpl.OrderServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-07 20:16
 **/
public class OrderController {
    // 用工厂模式减少直接new对象
    private final OrderService orderService = (OrderServiceImpl) MyServiceFactory
            .createService("Order");

    public boolean add(Object object) throws IOException {
        return orderService.add(object);
    }

    public List<Object> getList() throws IOException {
        return orderService.getList();
    }

    public Object search(String name) throws IOException {
        return orderService.search(name);
    }

    public boolean delete(String orderNumber) throws IOException {
        return orderService.delete(orderNumber);
    }

    public boolean modify(String iniCode, String iniName, String iniNumber, String iniDate, String iniDeadline, String iniPeople, String iniLinlWay, String iniPlace) throws IOException {
        return orderService.modify(iniCode,iniName,iniNumber,iniDate,iniDeadline,iniPeople,iniLinlWay,iniPlace);
    }
}
