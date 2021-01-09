package com.lxz.services;

import java.io.IOException;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-09 10:27
 **/
public interface TenderService extends BaseServices{
    public boolean modify(String iniCode,String iniPeople,String state) throws IOException;
}
