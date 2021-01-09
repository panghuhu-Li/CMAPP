package com.lxz.servicesimpl;

import com.lxz.entity.EquipmentInfo;
import com.lxz.entity.Order;
import com.lxz.entity.Tender;
import com.lxz.services.OrderService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-07 20:17
 **/
public class OrderServiceImpl implements OrderService {

    private final FileUtils fileUtils = new FileUtils();
    private final GsonUtils gsonUtils = new GsonUtils();

    @Override
    public boolean add(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("Order", jsonString, true);
        return true;
    }

    @Override
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("Order");
        return gsonUtils.toObjectList(jsonString, Order.class);
    }

    @Override
    public Object search(String orderNumber) throws IOException {
        List<Object> objects = getList();
        for (int i = objects.size() - 1; i >= 0; i--) {
            Order order = (Order) objects.get(i);
            if (order.getOrderNumber().equals(orderNumber)) {
                return order;
            }
        }
        return null;
    }

    public boolean delete(String orderNumber) throws IOException {
        List<Object> objects = getList();
        // 判断是否为第一次添加
        int flag = 0, num = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            Order order = (Order) objects.get(i);
            // 根据设备编号或者设备类型删除
            if (order.getOrderNumber().equals(orderNumber)) {
                num = 1;
                continue;
            }
            String jsonString = gsonUtils.toJson(order);
            fileUtils.saveData("Order", jsonString, flag != 0);
            flag++;
        }
        if (objects.size() - 1 == 0 && num == 1) {
            fileUtils.saveData("Order", null, false);
        }
        return num == 1;
    }

    public boolean modify(String iniCode, String iniName, String iniNumber, String iniDate, String iniDeadline, String iniPeople, String iniLinlWay, String iniPlace) throws IOException {
        List<Object> objects = getList();
        List<Object> objectsTender = getListTender();
        // 判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            Order order = (Order) objects.get(i);
            if (order.getOrderNumber().equals(iniCode)) {
                order.setProductName(iniName);
                order.setOrderAmount(iniNumber);
                order.setDayOfDeliver(iniDate);
                order.setDayOfDecline(iniDeadline);
                order.setConsignee(iniPeople);
                order.setContactWay(iniLinlWay);
                order.setPlaceOfReceive(iniPlace);
                flag = 1;
            }
            String jsonString = gsonUtils.toJson(order);
            fileUtils.saveData("Order", jsonString, i != objects.size() - 1);
        }

        for (int i = objectsTender.size() - 1; i >= 0; i--) {
            Tender tender = (Tender) objectsTender.get(i);
            if (tender.getOrderNumber().equals(iniCode)) {
                tender.setOrderName(iniName);
                flag = 1;
            }
            String jsonString = gsonUtils.toJson(tender);
            fileUtils.saveData("Tender", jsonString, i != objects.size() - 1);
        }

        return flag == 1;
    }

    public List<Object> getListTender() throws IOException {
        String jsonString = fileUtils.readFile("Tender");
        return gsonUtils.toObjectList(jsonString, Tender.class);
    }

}
