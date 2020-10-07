package com.lxz.servicesimpl;

import com.lxz.entity.EquipmentInfo;
import com.lxz.entity.EquipmentType;
import com.lxz.services.EquipmentService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description  设备数据处理层
 * @author: 李星泽
 * @create: 2020-07-19 09:49
 **/
public class EquipmentServiceImpl implements EquipmentService {

    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    /**
     * @param object:云工厂创建的新对象
     * @return boolean:返回是否成功添加
     * @description 添加设备信息
     **/
    @Override
    public boolean add(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("EquipmentInfo", jsonString, true);
        return true;
    }

    /**
     * @return List<Object>:返回所有设备对象
     * @description 得到所有的设备信息
     **/
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("EquipmentInfo");
        List<Object> objects = gsonUtils.toObjectList(jsonString, EquipmentInfo.class);
        return objects;
    }

    /**
     * @param productNumber:通过设备编号删除设备
     * @return boolean:返回是否删除成功
     * @description 删除相应设备
     **/
    @Override
    public boolean delete(String productNumber) throws IOException {
        List<Object> objects = getList();
        // 判断是否为第一次添加
        int flag = 0, num = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            // 根据设备编号或者设备类型删除
            if (equipmentInfo.getEquipmentNumber().equals(productNumber)
                    || equipmentInfo.getEquipmentType().equals(productNumber)) {

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

    /**
     * @param name: 设备名称
     * @return Object:返回查找的设备对象
     * @description 通过设备名称查找设备信息
     **/
    @Override
    public Object search(String name) throws IOException {

        List<Object> objects = getList();
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentName().equals(name)) {
                return equipmentInfo;
            }
        }
        return null;
    }

    /**
     * @param name: 设备名称
     * @return boolean:返回远程控制是否成功
     * @description 远程开关机
     **/
    @Override
    public boolean remoteControll(String name) throws IOException {
        List<Object> objects = getList();
        // 判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(name)) {
                if (equipmentInfo.getEquipmentState().equals("已关闭")) {
                    equipmentInfo.setEquipmentState("闲置中");
                    flag = 1;
                } else {
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
    @Override
    public boolean modify(String equipmentNumber, String equipmentName, String equipmentType, String equipmentSpec,
                          String equipmentDesc, String equipmentState, String isRented, String affiliation) throws IOException {
        List<Object> objects = getList();
        // 判断是否远程操作成功
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
                flag = 1;
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

    /**
     * @param equipmentNumber: 设备编号
     * @param aff:设备所属
     * @return boolean:返回租用是否成功
     * @description 租用设备
     **/
    @Override
    public boolean rent(String equipmentNumber, String aff) throws IOException {
        List<Object> objects = getList();
        // 判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(equipmentNumber)) {
                //将租用信息设置为已租用
                equipmentInfo.setRented("已租用");
                //租用后更改设备所属
                equipmentInfo.setAffiliation(aff);
                //远程操作成功flag=1
                flag = 1;
            }
            String jsonString = gsonUtils.toJson(equipmentInfo);
            //判断是对文件覆盖还是续写，第一次文件添加为覆盖，后为续写
            if (i == objects.size() - 1) {
                fileUtils.saveData("EquipmentInfo", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentInfo", jsonString, true);
            }
        }
        //判断远程操作是否成功
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param equipmentNumber: 设备编号
     * @param productName:设备名称
     * @param capa:设备配置的产品产能
     * @return boolean:返回产能是否配置成功
     * @description 产能配置
     **/
    @Override
    public boolean setCapacity(String equipmentNumber, String productName, Integer capa) throws IOException {
        List<Object> objects = getList();
        // 判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) objects.get(i);
            if (equipmentInfo.getEquipmentNumber().equals(equipmentNumber)) {
                equipmentInfo.addMap(productName, capa);
                flag = 1;
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

    /**
     * @param object: 添加的设备类型对象
     * @return boolean:返回设备类型对象是否添加成功
     * @description 添加设备类型
     **/
    @Override
    public boolean addType(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("EquipmentType", jsonString, true);
        return true;
    }

    /**
     * @return List<Object>:返回所有的设备类型对象
     * @description 得到所有设备信息
     **/
    @Override
    public List<Object> getTypeList() throws IOException {
        String jsonString = fileUtils.readFile("EquipmentType");
        List<Object> objects = gsonUtils.toObjectList(jsonString, EquipmentType.class);
        return objects;
    }

    /**
     * @param type: 删除的设备类型
     * @return boolean:返回设备类型是否删除成功
     * @description 删除设备类型
     **/
    public boolean deleteType(String type) throws IOException {
        List<Object> objects = getTypeList();
        // 判断是否为第一次添加
        int flag = 0, num = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentType equipmentType = (EquipmentType) objects.get(i);
            // 根据设备编号或者设备类型删除
            if (equipmentType.getTypeName().equals(type)) {
                num = 1;
                continue;
            }
            String jsonString = gsonUtils.toJson(equipmentType);
            if (flag == 0) {
                fileUtils.saveData("EquipmentType", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentType", jsonString, true);
            }
            flag++;
        }
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param name: 设备类型
     * @return Object:返回查找的设备类型对象
     * @description 查找设备类型信息
     **/
    @Override
    public Object searchEquipmentType(String name) throws IOException {
        List<Object> objects = getTypeList();
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentType equipmentType = (EquipmentType) objects.get(i);
            if (equipmentType.getTypeName().equals(name)) {
                return equipmentType;
            }
        }
        return null;
    }

    /**
     * @param original:              原设备类型
     * @param equipmentType:更改后的设备类型
     * @return boolean:返回设备类型是否更改成功
     * @description 修改设备类型
     **/
    @Override
    public boolean modifyType(String original, String equipmentType) throws IOException {
        //改变设备类型
        int num = 0;
        List<Object> objects = getTypeList();
        for (int i = objects.size() - 1; i >= 0; i--) {
            EquipmentType type = (EquipmentType) objects.get(i);
            if (type.getTypeName().equals(original)) {
                type.setTypeName(equipmentType);
                num = 1;
            }
            String jsonString = gsonUtils.toJson(type);
            if (i == objects.size() - 1) {
                fileUtils.saveData("EquipmentType", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentType", jsonString, true);
            }

        }

        // 改变设备信息中的设备类型
        List<Object> object = getList();
        int flag = 0;
        for (int i = object.size() - 1; i >= 0; i--) {
            EquipmentInfo equipmentInfo = (EquipmentInfo) object.get(i);
            if (equipmentInfo.getEquipmentType().equals(original)) {
                equipmentInfo.setEquipmentType(equipmentType);

                flag = 1;
            }
            String jsonString = gsonUtils.toJson(equipmentInfo);
            if (i == objects.size() - 1) {
                fileUtils.saveData("EquipmentInfo", jsonString, false);
            } else {
                fileUtils.saveData("EquipmentInfo", jsonString, true);
            }
        }

        //判断操作是否成功
        if (flag == 1 || num == 1) {
            return true;
        } else {
            return false;
        }
    }
}
