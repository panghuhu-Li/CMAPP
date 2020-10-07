package com.lxz.servicesimpl;

import com.lxz.entity.CloudFactory;
import com.lxz.services.CloudInterface;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 云工厂数据处理层
 * @author: 李星泽
 * @create: 2020-07-16 20:27
 **/
public class CloudInterfaceImpl implements CloudInterface {

    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    /**
     * @param object:云工厂创建的新对象
     * @return boolean:返回是否成功添加
     * @description 添加云工厂
     **/
    @Override
    public boolean add(Object object) throws IOException {
        String cloudFactory = gsonUtils.toJson(object);
        fileUtils.saveData("CloudFactory", cloudFactory, true);
        return true;
    }

    /**
     * @return List<Object>:返回所有工厂对象
     * @description 返回所有的工厂对象
     **/
    @Override
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("CloudFactory");
        List<Object> objects = gsonUtils.toObjectList(jsonString, CloudFactory.class);
        return objects;
    }

    /**
     * @param factoryName:云工厂名称
     * @return Object:返回查找的云工厂对象
     * @description 通过工厂名称查找工厂并返回工厂对象
     **/
    @Override
    public Object search(String factoryName) throws IOException {
        List<Object> objects = getList();
        for (Object object : objects) {
            CloudFactory cloudFactory = (CloudFactory) object;
            if (cloudFactory.getFactoryNameString().equals(factoryName)) {
                return object;
            }
        }
        return null;
    }

    /**
     * @param accountNumber:云工厂负责人的登录账号
     * @return boolean:返回是否修改成功
     * @description 修改工厂工作状态
     **/
    @Override
    public boolean modifyStatus(String accountNumber) throws IOException {
        List<Object> objects = getList();
        int i = 0;
        //从后往前添加
        for (int count = objects.size() - 1; count >= 0; count--) {
            CloudFactory cloudFactory = (CloudFactory) objects.get(count);
            if (cloudFactory.getAccountNumber().equals(accountNumber)) {
                if (cloudFactory.isStatus()) {
                    cloudFactory.setStatus(false);
                } else {
                    cloudFactory.setStatus(true);
                }
                i = 1;
            }
            String cloudString = gsonUtils.toJson(objects.get(count));
            if (count == objects.size() - 1) {
                fileUtils.saveData("CloudFactory", cloudString, false);
            } else {
                fileUtils.saveData("CloudFactory", cloudString, true);
            }
        }
        //判断是否修改成功
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }
}
