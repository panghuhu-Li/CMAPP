package com.lxz.servicesimpl;

import com.lxz.entity.EquipmentInfo;
import com.lxz.entity.Order;
import com.lxz.entity.ProductInfo;
import com.lxz.entity.Tender;
import com.lxz.services.TenderService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-09 10:28
 **/
public class TenderServiceImpl implements TenderService {
    private final FileUtils fileUtils = new FileUtils();
    private final GsonUtils gsonUtils = new GsonUtils();
    @Override
    public boolean add(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("Tender", jsonString, true);
        return true;
    }

    @Override
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("Tender");
        return gsonUtils.toObjectList(jsonString, Tender.class);
    }

    @Override
    public Object search(String orderNumber) throws IOException {
        List<Object> objects = getList();
        for (int i = objects.size() - 1; i >= 0; i--) {
            Tender tender = (Tender) objects.get(i);
            if (tender.getOrderNumber().equals(orderNumber)) {
                return tender;
            }
        }
        return null;
    }

    public boolean modify(String iniCode,String iniPeople,String state) throws IOException {
        List<Object> objects = getList();
        // 判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            Tender tender = (Tender) objects.get(i);
            if (tender.getOrderNumber().equals(iniCode)&&tender.getTenderPeo().equals(iniPeople)) {
                tender.setTenderState(state);
                flag = 1;
            }
            String jsonString = gsonUtils.toJson(tender);
            fileUtils.saveData("Tender", jsonString, i != objects.size() - 1);
        }

        return flag == 1;
    }
}
