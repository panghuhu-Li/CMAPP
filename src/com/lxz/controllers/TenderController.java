package com.lxz.controllers;
import com.lxz.Factory.MyServiceFactory;
import com.lxz.entity.Tender;
import com.lxz.services.TenderService;
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

    //工厂模式创建对象
    private final TenderService tenderService = (TenderServiceImpl) MyServiceFactory
            .createService("Tender");

    /**
     *
     * @return List<Object>
     * @throws IOException 异常抛出
     */
    public List<Object> getList() throws IOException {
        return tenderService.getList();
    }

    /**
     *
     * @param object the object added
     * @return boolean
     * @throws IOException throw IOException
     */
    public boolean add(Object object) throws IOException {
        return tenderService.add(object);
    }

    /**
     *
     * @param tenderNumber tender unique identification
     * @return Object
     * @throws IOException throw IOException
     */
    public Object search(String tenderNumber) throws IOException {
        return tenderService.search(tenderNumber);
    }

    /**
     *
     * @param iniCode initialized code number
     * @param iniPeople initialized consignee
     * @param state tender state
     * @return boolean
     * @throws IOException throw IOException
     * @description tender modify
     */
    public boolean modify(String iniCode, String iniPeople, String state) throws IOException {
        return tenderService.modify(iniCode, iniPeople, state);
    }

    /**
     *
     * @return List<Tender>
     * @throws IOException throw IOException
     * @description sort the tender according the orderNumber
     */
    public List<Tender> sortList() throws IOException {
        return tenderService.sortList();
    }


}
