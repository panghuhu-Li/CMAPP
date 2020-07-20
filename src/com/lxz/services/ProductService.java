package com.lxz.services;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    //添加产品
    boolean add(Object object) throws IOException;

    //获得所有产品进行显示
    List<Object> getProductInfo() throws IOException;

    //删除对应编号的产品信息
    boolean delete(String produceNumber) throws IOException;

    //根据产品名称进行检索
    Object searchProductInfo(String productName) throws IOException;

    //对产品信息进行修改
    boolean modify(String productNumber,String productName,String productType,String productSpec,String producyDesc) throws IOException;
}
