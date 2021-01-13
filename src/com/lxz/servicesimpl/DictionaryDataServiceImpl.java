package com.lxz.servicesimpl;

import com.lxz.entity.Dictionary;
import com.lxz.entity.EquipmentInfo;
import com.lxz.entity.EquipmentType;
import com.lxz.services.DictionaryDataService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-05 21:08
 **/
public class DictionaryDataServiceImpl implements DictionaryDataService {
    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    @Override
    public boolean add(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("Dictionary", jsonString, true);
        return true;
    }

    @Override
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("Dictionary");
        return gsonUtils.toObjectList(jsonString, Dictionary.class);
    }

    @Override
    public Object search(String name) throws IOException {
        return null;
    }

    public boolean delete(String dictionaryNumber) throws IOException {
        List<Object> objects = getList();
        // 判断是否为第一次添加
        int flag = 0, num = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            Dictionary dictionary = (Dictionary) objects.get(i);
            // 根据设备编号或者设备类型删除
            if (dictionary.getDictionaryNumber().equals(dictionaryNumber)) {
                num = 1;
                continue;
            }
            String jsonString = gsonUtils.toJson(dictionary);
            if (flag == 0) {
                fileUtils.saveData("Dictionary", jsonString, false);
            } else {
                fileUtils.saveData("Dictionary", jsonString, true);
            }
            flag++;
        }
        if (num == 1) {
            return true;
        } else {
            return false;
        }
    }
}
