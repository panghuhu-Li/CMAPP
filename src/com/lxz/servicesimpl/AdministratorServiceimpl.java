package com.lxz.servicesimpl;

import com.lxz.entity.Administrator;
import com.lxz.services.AdministratorService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @program: CMAPP
 * @description 用户数据处理层
 * @author: 李星泽
 * @create: 2020-07-16 06:26
 **/
public class AdministratorServiceimpl implements AdministratorService {
    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    /**
     * @param object:管理员对象
     * @return boolean:判断是否添加成功
     * @description 添加管理员注册
     **/
    @Override
    public boolean add(Object object) throws IOException {

        // 判断是否已存在账号相同的管理员
        Administrator admini = (Administrator) object;
        // 得到从字符串转换的对象
        List<Object> administrators = getList();
        // 判断是否已经存在该用户
        for (Object obj : administrators) {
            Administrator administrator = (Administrator) obj;
            if (administrator.getaccountNumber().equals(admini.getaccountNumber())) {
                return false;
            }
        }
        String user = gsonUtils.toJson(object);
        fileUtils.saveData("User", user, true);
        return true;
    }

    /**
     * @param accountNumber:管理员登录账号
     * @return String:返回是否成功删除
     * @description 根据管理员账户删除
     **/
    @Override
    public boolean delete(String accountNumber) throws IOException {

        List<Object> administrators = getList();
        int flag = 0;
        //从后向前避免顺序发生变化
        for (int i = administrators.size() - 1; i >= 0; i--) {
            Administrator administrator = (Administrator) administrators.get(i);
            if (!administrator.getaccountNumber().equals(accountNumber)) {
                String user = gsonUtils.toJson(administrators.get(i));
                if (flag == 0) {
                    fileUtils.saveData("User", user, false);
                } else {
                    fileUtils.saveData("User", user, true);
                }
                flag++;
            }
        }
        return true;
    }
    /**
     * @param accountNumber:管理员登录账号
     * @param name:管理员姓名
     * @param linkWay:管理员联系方式
     * @return boolean:返回是否修改成功
     * @description 根据管理员登录账号修改管理员姓名和联系方式
     **/
    @Override
    public boolean modify(String accountNumber, String name, String linkWay) throws IOException {
        List<Object> objects = getList();
        int flag = 0;
        //从后往前添加，因为从JSON转化为数组后JSON是从后向前读取
        for (int count = objects.size() - 1; count >= 0; count--) {
            Administrator administrator = (Administrator) objects.get(count);
            //判断登录账号是否相等
            if (administrator.getaccountNumber().equals(accountNumber)) {
                administrator.setName(name);
                administrator.setContactWay(linkWay);
                flag = 1;
            }
            String user = gsonUtils.toJson(administrator);
            if (count == objects.size() - 1) {
                fileUtils.saveData("User", user, false);
            } else {
                fileUtils.saveData("User", user, true);
            }
        }
        //判断是否更改成功
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * @param name:管理员姓名
     * @return Object:返回该管理员对象
     * @description 根据管理员姓名进行查找
     **/
    @Override
    public Object search(String name) throws IOException {
        List<Object> administrators = getList();
        for (Object obj : administrators) {
            Administrator administrator = (Administrator) obj;
            if (administrator.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }

    /**
     * @param accountNumber:管理员登录账号
     * @param password:管理员登录密码
     * @return String:返回管理员登录类型
     * @description 登录人员权限判断
     **/
    @Override
    public String whoRegister(String accountNumber, String password) throws IOException {
        // 得到从字符串转换的对象
        List<Object> administrators = getList();
        // 判断密码和账号是否正确
        for (Object obj : administrators) {
            Administrator administrator = (Administrator) obj;
            if (administrator.getaccountNumber().equals(accountNumber)
                    && administrator.getPassword().equals(password)) {
                return "success" + administrator.getRegisterType()+"_"+administrator.getName();
            }
        }
        return "wrong";

    }

    /**
     * @return List<Object>:返回所有管理员信息
     * @description 获得所有管理员信息
     **/
    @Override
    public List<Object> getList() throws IOException {
        // TODO 自动生成的方法存根
        List<Object> administrators = new ArrayList<Object>();
        String objString = fileUtils.readFile("User");
        administrators = gsonUtils.toObjectList(objString, Administrator.class);
        return administrators;
    }
}
