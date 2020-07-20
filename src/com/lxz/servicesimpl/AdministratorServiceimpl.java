package com.lxz.servicesimpl;

import com.lxz.entity.Administrator;
import com.lxz.services.AdministratorService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorServiceimpl implements AdministratorService {
    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    @Override
    public boolean add(Object object) throws IOException {

        // 判断是否已存在账号相同的管理员
        Administrator admini = (Administrator) object;
        // 从文件中读取字符串
        String objString = fileUtils.readFile("User");
        // 得到从字符串转换的对象
        List<Object> administrators = getAdministrator();
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

    //删除管理员
    @Override
    public boolean delete(String accountNumber) throws IOException {

        List<Object> administrators = getAdministrator();
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

    //修改管理员信息
    @Override
    public boolean modify(String accountNumber, String name, String linkWay) throws IOException {
        String objString = fileUtils.readFile("User");
        List<Object> objects = getAdministrator();
        int i = 0, flag = 0;
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

    @Override
    //查找管理员信息
    public Object search(String name) throws IOException {
        String objString = fileUtils.readFile("User");
        List<Object> administrators = getAdministrator();
        for (Object obj : administrators) {
            Administrator administrator = (Administrator) obj;
            if (administrator.getName().equals(name)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    //判断登录人的密码是否正确
    public String whoRegister(String accountNumber, String password) throws IOException {
        // 从文件中读取字符串
        String objString = fileUtils.readFile("User");
        // 得到从字符串转换的对象
        List<Object> administrators = getAdministrator();
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

    @Override
    //从文件中获取管理员对象
    public List<Object> getAdministrator() throws IOException {
        // TODO 自动生成的方法存根
        List<Object> administrators = new ArrayList<Object>();
        String objString = fileUtils.readFile("User");
        administrators = gsonUtils.toObjectList(objString, Administrator.class);
        return administrators;
    }

}
