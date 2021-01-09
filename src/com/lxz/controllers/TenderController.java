package com.lxz.controllers;

import com.lxz.Factory.MyServiceFactory;
import com.lxz.services.AdministratorService;
import com.lxz.services.TenderService;
import com.lxz.servicesimpl.AdministratorServiceimpl;
import com.lxz.servicesimpl.TenderServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-09 10:27
 **/
public class TenderController {
    private final TenderService tenderService = (TenderServiceImpl) MyServiceFactory
            .createService("Tender");
    public List<Object> getList() throws IOException {
        return tenderService.getList();
    }

    public boolean add(Object object) throws IOException {
        return tenderService.add(object);
    }

    public Object search(String tenderNumber) throws IOException {
        return tenderService.search(tenderNumber);
    }

    public boolean modify(String iniCode,String iniPeople,String state) throws IOException{
        return tenderService.modify(iniCode,iniPeople,state);
    }
}
