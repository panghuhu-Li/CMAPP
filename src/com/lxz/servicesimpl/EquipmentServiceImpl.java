package com.lxz.servicesimpl;

import com.lxz.entity.EquipmentInfo;
import com.lxz.entity.ProductInfo;
import com.lxz.services.EquipmentService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-19 09:49
 **/
public class EquipmentServiceImpl implements EquipmentService {

    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    //添加设备信息
    @Override
    public boolean add(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("EquipmentInfo", jsonString, true);
        return true;
    }

    //获得所有设备信息
    public List<Object> getEquipmentInfo() throws IOException {
        String jsonString = fileUtils.readFile("EquipmentInfo");
        List<Object> objects = gsonUtils.toObjectList(jsonString, EquipmentInfo.class);
        return objects;
    }

    @Override
    public boolean delete(String productNumber) throws IOException {
        List<Object> objects = getEquipmentInfo();
        //判断是否为第一次添加
        int flag = 0, num = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(productNumber)) {
                num = 1;
                continue;
            }
            String jsonString = gsonUtils.toJson(equipmentInfo);
            if (flag == 0) {
                fileUtils.saveData("EquipmentInfo", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentInfo", jsonString, true);
            }
            flag++;
        }
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object searchEquipment(String name) throws IOException {

        List<Object> objects = getEquipmentInfo();
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentName().equals(name)) {
                return equipmentInfo;
            }
        }
        return null;
    }

    @Override
    public boolean remoteControll(String name) throws IOException {
        List<Object> objects = getEquipmentInfo();
        //判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(name)) {
                if (equipmentInfo.getEquipmentState().equals("已关闭")) {
                    equipmentInfo.setEquipmentState("闲置中");
                    flag = 1;
                }else {
                    equipmentInfo.setEquipmentState("已关闭");
                    flag = 1;
                }
            }
            String jsonString = gsonUtils.toJson(equipmentInfo);
            if (i == objects.size() - 1) {
                fileUtils.saveData("EquipmentInfo", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentInfo", jsonString, true);
            }
        }

        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean modify(String equipmentNumber, String equipmentName, String equipmentType, String equipmentSpec, String equipmentDesc, String equipmentState, String isRented, String affiliation) throws IOException {
        List<Object> objects = getEquipmentInfo();
        //判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(equipmentNumber)) {
                equipmentInfo.setEquipmentName(equipmentName);
                equipmentInfo.setEquipmentType(equipmentType);
                equipmentInfo.setEquipmentSpec(equipmentSpec);
                equipmentInfo.setEquipmentDesc(equipmentDesc);
                equipmentInfo.setEquipmentState(equipmentState);
                equipmentInfo.setRented(isRented);
                equipmentInfo.setAffiliation(affiliation);
                flag=1;
            }
            String jsonString = gsonUtils.toJson(equipmentInfo);
            if (i == objects.size() - 1) {
                fileUtils.saveData("EquipmentInfo", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentInfo", jsonString, true);
            }
        }

        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean rent(String equipmentNumber,String aff) throws IOException {
        List<Object> objects = getEquipmentInfo();
        //判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(equipmentNumber)) {
                equipmentInfo.setRented("已租用");
                equipmentInfo.setAffiliation(aff);
                flag=1;
            }
            String jsonString = gsonUtils.toJson(equipmentInfo);
            if (i == objects.size() - 1) {
                fileUtils.saveData("EquipmentInfo", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentInfo", jsonString, true);
            }
        }

        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean setCapacity(String equipmentNumber,String productName, Integer capa) throws IOException {
        List<Object> objects = getEquipmentInfo();
        //判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(equipmentNumber)) {
                equipmentInfo.addMap(productName, capa);
               flag=1;
            }
            String jsonString = gsonUtils.toJson(equipmentInfo);
            if (i == objects.size() - 1) {
                fileUtils.saveData("EquipmentInfo", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentInfo", jsonString, true);
            }
        }

        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }
}
