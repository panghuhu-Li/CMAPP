package com.lxz.controllers;

import com.lxz.Factory.MyServiceFactory;
import com.lxz.services.ProductService;
import com.lxz.servicesimpl.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 对产品操作数据和结果的接受
 * @author: 李星泽
 * @create: 2020-07-18 15:40
 **/
public class ProductController {

    // 用工厂模式减少直接new对象
    private ProductService productService = (ProductServiceImpl) MyServiceFactory.createService("Product");

    /**
     * @param object: 添加的产品对象
     * @return boolean:返回产品对象是否添加成功
     * @description 添加产品
     **/
    public boolean add(Object object) throws IOException {
        return productService.add(object);
    }

    /**
     * @return List<Object>:返回所有的产品信息
     * @description 得到所有的产品信息
     **/
    public List<Object> getProductInfo() throws IOException {
        return productService.getList();
    }

    /**
     * @param produceNumber: 产品编号
     * @return boolean:返回产品是否删除成功
     * @description 根据产品编号删除产品
     **/
    public boolean delete(String produceNumber) throws IOException {
        return productService.delete(produceNumber);
    }

    /**
     * @param productName: 产品名称
     * @return Object:返回要查找的产品对象
     * @description 根据产品名称进行检索
     **/
    public Object searchProductInfo(String productName) throws IOException {
        return productService.search(productName);
    }

    /**
     * @param productNumber:产品编号
     * @param productName:       产品名称
     * @param productType:产品类型
     * @param productSpec:产品规格
     * @param producyDesc:产品描述
     * @return boolean:返回产品信息是否修改成功
     * @description 修改产品信息
     **/
    public boolean modify(String productNumber, String productName, String productType, String productSpec,
                          String producyDesc) throws IOException {
        return productService.modify(productNumber, productName, productType, productSpec, producyDesc);
    }

    /**
     * @param object: 要添加的产品类型对象
     * @return boolean: 返回产品类型是否添加成功
     * @description 添加产品类型
     **/
    public boolean addType(Object object) throws IOException {
        return productService.addType(object);
    }

    /**
     * @return List<Object>: 返回所有的产品类型信息
     * @description 得到所有的产品类型信息
     **/
    public List<Object> getProductType() throws IOException {
        return productService.getProductType();
    }

    /**
     * @param productName: 产品类型名称
     * @return Object: 返回要查找的产品类型对象
     * @description 检索产品类型
     **/
    public Object searchProductType(String productName) throws IOException {
        return productService.searchProductType(productName);
    }

    /**
     * @param original:         原产品类型
     * @param typeName:修改后的产品类型
     * @return Object: 返回要查找的产品类型对象
     * @description 修改产品类型
     **/
    public boolean modifyType(String original, String typeName) throws IOException {
        return productService.modifyType(original, typeName);
    }

    /**
     * @param productTypeNumber: 产品类型
     * @return boolean: 返回是否删除成功
     * @description 删除产品类型
     **/
    public boolean deleteType(String productTypeNumber) throws IOException {
        return productService.deleteType(productTypeNumber);
    }
}
