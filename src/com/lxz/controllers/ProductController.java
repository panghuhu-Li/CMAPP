package com.lxz.controllers;

import com.lxz.services.ProductService;
import com.lxz.servicesimpl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-18 15:40
 **/
public class ProductController {
    private ProductService productService=new ProductServiceImpl();
    //添加
    public boolean add(Object object) throws IOException {
        return productService.add(object);
    }
    //得到所有的产品信息
    public List<Object> getProductInfo() throws IOException {
        return productService.getProductInfo();
    }
    //根据产品编号删除产品
    public boolean delete(String produceNumber) throws IOException {
        return productService.delete(produceNumber);
    }
    //根据产品名称进行检索
    public Object searchProductInfo(String productName) throws IOException {
        return productService.searchProductInfo(productName);
    }
    //修改产品信息
    public boolean modify(String productNumber,String productName,String productType,String productSpec,String producyDesc) throws IOException {
        return productService.modify(productNumber,productName,productType,productSpec,producyDesc);
    }
}
