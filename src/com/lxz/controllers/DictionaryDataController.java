package com.lxz.controllers;

import com.lxz.Factory.MyServiceFactory;
import com.lxz.services.DictionaryDataService;
import com.lxz.servicesimpl.DictionaryDataServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-05 21:03
 **/
public class DictionaryDataController {
    private final DictionaryDataService dictionaryDataService = (DictionaryDataServiceImpl) MyServiceFactory.createService("DictionaryData");

    public List<Object> getDictionaryData() throws IOException {
        return dictionaryDataService.getList();
    }

    public boolean add(Object object) throws IOException {
        return dictionaryDataService.add(object);
    }

    public boolean delete(String dictionaryNumber) throws IOException {
        return dictionaryDataService.delete(dictionaryNumber);
    }
}
