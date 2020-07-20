package com.lxz.servicesimpl;

import com.lxz.entity.CloudFactory;
import com.lxz.services.CloudInterface;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-16 20:27
 **/
public class CloudInterfaceImpl implements CloudInterface {

    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    @Override
    public boolean add(Object object) throws IOException {
        String cloudFactory = gsonUtils.toJson(object);
        fileUtils.saveData("CloudFactory", cloudFactory, true);
        return true;
    }

    @Override
    public List<Object> getFactory() throws IOException {
        String jsonString = fileUtils.readFile("CloudFactory");
        List<Object> objects = gsonUtils.toObjectList(jsonString, CloudFactory.class);
        return objects;
    }

    @Override
    public Object searchFactory(String factoryName) throws IOException {
        List<Object> objects=getFactory();
        for (Object object : objects) {
            CloudFactory cloudFactory=(CloudFactory) object;
            if (cloudFactory.getFactoryNameString().equals(factoryName)){
                return object;
            }
        }
        return null;
    }

    @Override
    public boolean modifyStatus(String accountNumber) throws IOException {
        List<Object> objects=getFactory();
        int i=0;
        //从后往前添加
        for (int count=objects.size()-1;count>=0;count--){
            CloudFactory cloudFactory=(CloudFactory) objects.get(count);
            if (cloudFactory.getAccountNumber().equals(accountNumber)){
                if (cloudFactory.isStatus()){
                    cloudFactory.setStatus(false);
                }else {
                    cloudFactory.setStatus(true);
                }
                i=1;
            }
            String cloudString = gsonUtils.toJson(objects.get(count));
            if (count==objects.size()-1) {
                fileUtils.saveData("CloudFactory", cloudString, false);
            }else {
                fileUtils.saveData("CloudFactory", cloudString, true);
            }
        }
        //判断是否修改成功
        if (i==1){
            return true;
        }else {
            return false;
        }
    }

}
