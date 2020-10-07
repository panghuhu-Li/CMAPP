package com.lxz.services;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 产品数据处理层接口
 * @author: 李星泽
 * @create: 2020-07-18 15:42
 **/
public interface ProductService extends BaseServices {

    /**
     * @param object: 添加的产品对象
     * @return boolean:返回产品对象是否添加成功
     * @description 添加产品
     **/
    boolean add(Object object) throws IOException;

    /**
     * @return List<Object>:返回所有的产品信息
     * @description 得到所有的产品信息
     **/
    List<Object> getList() throws IOException;

    /**
     * @param produceNumber: 产品编号
     * @return boolean:返回产品是否删除成功
     * @description 根据产品编号删除产品
     **/
    boolean delete(String produceNumber) throws IOException;

    /**
     * @param productName: 产品名称
     * @return Object:返回要查找的产品对象
     * @description 根据产品名称进行检索
     **/
    Object search(String productName) throws IOException;

    /**
     * @param productNumber:产品编号
     * @param productName:       产品名称
     * @param productType:产品类型
     * @param productSpec:产品规格
     * @param producyDesc:产品描述
     * @return boolean:返回产品信息是否修改成功
     * @description 修改产品信息
     **/
    boolean modify(String productNumber, String productName, String productType, String productSpec, String producyDesc) throws IOException;

    /**
     * @param object: 要添加的产品类型对象
     * @return boolean: 返回产品类型是否添加成功
     * @description 添加产品类型
     **/
    boolean addType(Object object) throws IOException;

    /**
     * @return List<Object>: 返回所有的产品类型信息
     * @description 得到所有的产品类型信息
     **/
    List<Object> getProductType() throws IOException;

    /**
     * @param productName: 产品类型名称
     * @return Object: 返回要查找的产品类型对象
     * @description 检索产品类型
     **/
    Object searchProductType(String productName) throws IOException;

    /**
     * @param original:         原产品类型
     * @param typeName:修改后的产品类型
     * @return Object: 返回要查找的产品类型对象
     * @description 修改产品类型
     **/
    boolean modifyType(String original, String typeName) throws IOException;

    /**
     * @param produceNumber: 产品类型
     * @return boolean: 返回是否删除成功
     * @description 删除产品类型
     **/
    boolean deleteType(String produceNumber) throws IOException;
}
