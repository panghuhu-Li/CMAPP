package com.lxz.controllers;

import com.lxz.Factory.MyServiceFactory;
import com.lxz.services.EquipmentService;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 设备操作数据和结果接受
 * @author: 李星泽
 * @create: 2020-07-19 09:48
 **/
public class EquipmentController {

    // 用工厂模式减少直接new对象
    private EquipmentService equipmentService = (EquipmentService) MyServiceFactory.createService("Equipment");

    /**
     * @param object:云工厂创建的新对象
     * @return boolean:返回是否成功添加
     * @description 添加设备信息
     **/
    public boolean add(Object object) throws IOException {
        return equipmentService.add(object);
    }

    /**
     * @return List<Object>:返回所有设备对象
     * @description 得到所有的设备信息
     **/
    public List<Object> getEquipmentInfo() throws IOException {
        return equipmentService.getList();
    }

    /**
     * @param productNumber:通过设备编号删除设备
     * @return boolean:返回是否删除成功
     * @description 删除相应设备
     **/
    public boolean delete(String productNumber) throws IOException {
        return equipmentService.delete(productNumber);
    }

    /**
     * @param name: 设备名称
     * @return Object:返回查找的设备对象
     * @description 通过设备名称查找设备信息
     **/
    public Object searchEquipment(String name) throws IOException {
        return equipmentService.search(name);
    }

    /**
     * @param name: 设备名称
     * @return boolean:返回远程控制是否成功
     * @description 远程开关机
     **/
    public boolean remoteControll(String name) throws IOException {
        return equipmentService.remoteControll(name);
    }

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
    public boolean modify(String equipmentNumber, String equipmentName, String equipmentType, String equipmentSpec,
                          String equipmentDesc, String equipmentState, String isRented, String affiliation) throws IOException {
        return equipmentService.modify(equipmentNumber, equipmentName, equipmentType, equipmentSpec, equipmentDesc,
                equipmentState, isRented, affiliation);
    }

    /**
     * @param equipmentNumber: 设备编号
     * @param aff:设备所属
     * @return boolean:返回租用是否成功
     * @description 租用设备
     **/
    public boolean rent(String equipmentNumber, String aff) throws IOException {
        return equipmentService.rent(equipmentNumber, aff);
    }

    /**
     * @param equipmentNumber: 设备编号
     * @param productName:设备名称
     * @param capa:设备配置的产品产能
     * @return boolean:返回产能是否配置成功
     * @description 产能配置
     **/
    public boolean setCapacity(String equipmentNumber, String productName, Integer capa) throws IOException {
        return equipmentService.setCapacity(equipmentNumber, productName, capa);
    }

    /**
     * @param object: 添加的设备类型对象
     * @return boolean:返回设备类型对象是否添加成功
     * @description 添加设备类型
     **/
    public boolean addType(Object object) throws IOException {
        return equipmentService.addType(object);
    }

    /**
     * @return List<Object>:返回所有的设备类型对象
     * @description 得到所有设备信息
     **/
    public List<Object> getEquipmentType() throws IOException {
        return equipmentService.getTypeList();
    }

    /**
     * @param type: 删除的设备类型
     * @return boolean:返回设备类型是否删除成功
     * @description 删除设备类型
     **/
    public boolean deleteType(String type) throws IOException {
        return equipmentService.deleteType(type);
    }

    /**
     * @param name: 设备类型
     * @return Object:返回查找的设备类型对象
     * @description 查找设备类型信息
     **/
    public Object searchEquipmentType(String name) throws IOException {
        return equipmentService.searchEquipmentType(name);
    }

    /**
     * @param original:              原设备类型
     * @param equipmentType:更改后的设备类型
     * @return boolean:返回设备类型是否更改成功
     * @description 修改设备类型
     **/
    public boolean modifyType(String original, String equipmentType) throws IOException {
        return equipmentService.modifyType(original, equipmentType);
    }
}
