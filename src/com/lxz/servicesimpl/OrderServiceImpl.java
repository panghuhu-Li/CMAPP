package com.lxz.servicesimpl;

import com.lxz.entity.Dictionary;
import com.lxz.entity.Order;
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

    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    @Override
    public boolean add(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("Order", jsonString, true);
        return true;
    }

    @Override
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("Order");
        List<Object> objects = gsonUtils.toObjectList(jsonString, Order.class);
        return objects;
    }

    @Override
    public Object search(String name) throws IOException {
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
            fileUtils.saveData("Dictionary", jsonString, flag != 0);
            flag++;
        }
        return num == 1;
    }
}
