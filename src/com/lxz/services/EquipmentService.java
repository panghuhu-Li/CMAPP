package com.lxz.services;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 设备操作数据和结果接受接口
 * @author: 李星泽
 * @create: 2020-07-19 10:10
 **/
public interface EquipmentService extends BaseServices {
    /**
     * @param object:云工厂创建的新对象
     * @return boolean:返回是否成功添加
     * @description 添加设备信息
     **/
    boolean add(Object object) throws IOException;

    /**
     * @return List<Object>:返回所有设备对象
     * @description 得到所有的设备信息
     **/
    List<Object> getList() throws IOException;

    /**
     * @param productNumber:通过设备编号删除设备
     * @return boolean:返回是否删除成功
     * @description 删除相应设备
     **/
    boolean delete(String productNumber) throws IOException;

    /**
     * @param name: 设备名称
     * @return Object:返回查找的设备对象
     * @description 通过设备名称查找设备信息
     **/
    Object search(String name) throws IOException;

    /**
     * @param name: 设备名称
     * @return boolean:返回远程控制是否成功
     * @description 远程开关机
     **/
    boolean remoteControll(String name) throws IOException;

    /**
     * @param equipmentNumber:设备编号
     * @param equipmentName:设备名称
     * @param equipmentType:设备类型
     * @param equipmentSpec:设备规格
     * @param equipmentDesc:设备描述
     * @param equipmentState:设备状态
     * @param isRented:设备是否被租用
     * @param affiliation:设备所属
     * @return boolean:返回信息是否修改成功
     * @description 通过设备编号修改信息
     **/
    boolean modify(String equipmentNumber, String equipmentName, String equipmentType, String equipmentSpec,
                   String equipmentDesc, String equipmentState, String isRented, String affiliation) throws IOException;

    /**
     * @param equipmentNumber: 设备编号
     * @param aff:设备所属
     * @return boolean:返回租用是否成功
     * @description 租用设备
     **/
    boolean rent(String equipmentNumber, String aff) throws IOException;

    /**
     * @param equipmentNumber: 设备编号
     * @param productName:设备名称
     * @param capa:设备配置的产品产能
     * @return boolean:返回产能是否配置成功
     * @description 产能配置
     **/
    boolean setCapacity(String equipmentNumber, String productName, Integer capa) throws IOException;

    /**
     * @param object: 添加的设备类型对象
     * @return boolean:返回设备类型对象是否添加成功
     * @description 添加设备类型
     **/
    boolean addType(Object object) throws IOException;

    /**
     * @return List<Object>:返回所有的设备类型对象
     * @description 得到所有设备信息
     **/
    List<Object> getTypeList() throws IOException;

    /**
     * @param type: 删除的设备类型
     * @return boolean:返回设备类型是否删除成功
     * @description 删除设备类型
     **/
    boolean deleteType(String type) throws IOException;

    /**
     * @param name: 设备类型
     * @return Object:返回查找的设备类型对象
     * @description 查找设备类型信息
     **/
    Object searchEquipmentType(String name) throws IOException;

    /**
     * @param original:              原设备类型
     * @param equipmentType:更改后的设备类型
     * @return boolean:返回设备类型是否更改成功
     * @description 修改设备类型
     **/
    boolean modifyType(String original, String equipmentType) throws IOException;
}
