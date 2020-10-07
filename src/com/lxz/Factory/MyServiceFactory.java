package com.lxz.Factory;

import com.lxz.services.BaseServices;
import com.lxz.servicesimpl.AdministratorServiceimpl;
import com.lxz.servicesimpl.CloudInterfaceImpl;
import com.lxz.servicesimpl.EquipmentServiceImpl;
import com.lxz.servicesimpl.ProductServiceImpl;

/**
 * @program: CMAPP
 * @description 工厂模式创建对象，避免多次new对象，使代码利于维护
 * @author: 李星泽
 * @create: 2020-07-16 15:09
 **/
public class MyServiceFactory {

    /**
     * @param message:创建相应对象需要接受信息
     * @description 产品类型实体类
     **/
    public static BaseServices createService(String message) {

        BaseServices baseService = null;

        // 管理者
        if ("Administrator".equals(message))
            baseService = new AdministratorServiceimpl();

        // 云工厂
        if ("Cloud".equals(message))
            baseService = new CloudInterfaceImpl();

        // 设备信息
        if ("Equipment".equals(message))
            baseService = new EquipmentServiceImpl();

        //产品信息
        if ("Product".equals(message))
            baseService = new ProductServiceImpl();

        return baseService;
    }
}
