package com.lxz.servicesimpl;

import com.lxz.entity.Tender;
import com.lxz.services.TenderService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: CMAPP
 * @description 投标实现
 * @author: 李星泽
 * @create: 2021-01-09 10:28
 **/
public class TenderServiceImpl implements TenderService {
    private final FileUtils fileUtils = new FileUtils();
    private final GsonUtils gsonUtils = new GsonUtils();

    /**
     *
     * @param object :添加对象
     * @return boolean
     * @throws: IOException
     */
    @Override
    public boolean add(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("Tender", jsonString, true);
        return true;
    }

    /**
     *
     * @return List<Object>
     * @throws IOException :异常抛出
     */
    @Override
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("Tender");
        return gsonUtils.toObjectList(jsonString, Tender.class);
    }

    /**
     *
     * @param orderNumber :订单编号
     * @return Object
     * @throws IOException :异常抛出
     */
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

    /**
     *
     * @param iniCode 初始编号
     * @param iniPeople 初始收货人
     * @param state 状态
     * @return boolean
     * @throws IOException :异常抛出
     */
    public boolean modify(String iniCode, String iniPeople, String state) throws IOException {
        List<Object> objects = getList();
        // 判断是否远程操作成功
        int flag = 0;
        for (int i = objects.size() - 1; i >= 0; i--) {
            Tender tender = (Tender) objects.get(i);
            if (tender.getOrderNumber().equals(iniCode) && tender.getTenderPeo().equals(iniPeople)) {
                tender.setTenderState(state);
                flag = 1;
            }
            String jsonString = gsonUtils.toJson(tender);
            fileUtils.saveData("Tender", jsonString, i != objects.size() - 1);
        }

        return flag == 1;
    }

    /**
     *
     * @return List<Tender>
     * @throws IOException :异常抛出
     */
    public List<Tender> sortList() throws IOException {
        List<Object> objects = getList();
        List<Tender> tenders = new ArrayList<>();
        for (Object obj : objects) {
            tenders.add((Tender) obj);
        }
        tenders.sort((o1, o2) -> o1.getOrderNumber().compareTo(o2.getOrderNumber()));
        return tenders;
    }
}
