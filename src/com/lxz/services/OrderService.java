package com.lxz.services;

import java.io.IOException;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-07 20:17
 **/
public interface OrderService extends BaseServices {
    public boolean delete(String orderNumber) throws IOException;

    public boolean modify(String iniCode, String iniName, String iniNumber, String iniDate, String iniDeadline, String iniPeople, String iniLinlWay, String iniPlace) throws IOException;
}
