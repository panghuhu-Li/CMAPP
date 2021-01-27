package com.lxz.services;

import com.lxz.entity.Tender;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-09 10:27
 **/
public interface TenderService extends BaseServices {

    /**
     *
     * @param iniCode initialized code number
     * @param iniPeople initialized consignee
     * @param state tender state
     * @return boolean
     * @throws IOException throw IOException
     * @description tender modify
     */
    public boolean modify(String iniCode, String iniPeople, String state) throws IOException;

    /**
     *
     * @return List<Tender>
     * @throws IOException throw IOException
     * @description sort the tender according the orderNumber
     */
    public List<Tender> sortList() throws IOException;
}
