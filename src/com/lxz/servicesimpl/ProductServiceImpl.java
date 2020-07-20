package com.lxz.servicesimpl;

import com.lxz.entity.CloudFactory;
import com.lxz.entity.ProductInfo;
import com.lxz.services.ProductService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-18 15:42
 **/
public class ProductServiceImpl implements ProductService {
    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    @Override
    public boolean add(Object object) throws IOException {
        List<Object> objects = getProductInfo();
        ProductInfo productInfo = (ProductInfo) object;
        for (Object o : objects) {
            ProductInfo product = (ProductInfo) o;
            if (product.getProductNumber().equals("")) {
                return false;
            }
        }
        String jsonString = gsonUtils.toJson(productInfo);
        fileUtils.saveData("ProductInfo", jsonString, true);
        return true;
    }

    public List<Object> getProductInfo() throws IOException {
        String jsonString = fileUtils.readFile("ProductInfo");
        List<Object> objects = gsonUtils.toObjectList(jsonString, ProductInfo.class);
        return objects;
    }

    @Override
    public boolean delete(String produceNumber) throws IOException {
        List<Object> objects = getProductInfo();
        //判断是否存在该编号的产品信息 flag==1：存在
        int flag = 0;
        //从后向前添加因为在Json字符串转换对象时顺序会发生反转
        for (int i = objects.size()-1; i >= 0; i--) {
            ProductInfo productInfo = (ProductInfo) objects.get(i);
            if (!productInfo.getProductNumber().equals(produceNumber)) {
                String jsonString = gsonUtils.toJson(objects.get(i));
                if (flag == 0) {
                    fileUtils.saveData("ProductInfo", jsonString, false);
                } else {
                    fileUtils.saveData("ProductInfo", jsonString, true);
                }
                flag++;
            }
        }
        if (flag == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object searchProductInfo(String productName) throws IOException {
        List<Object> objects = getProductInfo();
        for (Object object : objects) {
            ProductInfo productInfo = (ProductInfo) object;
            if (productInfo.getProductName().equals(productName)){
                return productInfo;
            }
        }
        return null;
    }

    @Override
    public boolean modify(String productNumber,String productName, String productType, String productSpec, String producyDesc) throws IOException {
        List<Object> objects = getProductInfo();
        int flag=0;
        for(int i=objects.size()-1;i>=0;i--){
            ProductInfo productInfo = (ProductInfo) objects.get(i);
            if (productInfo.getProductNumber().equals(productNumber)){
                productInfo.setProductName(productName);
                productInfo.setProductType(productType);
                productInfo.setProductSpec(productSpec);
                productInfo.setProductDesc(producyDesc);
                flag=1;
            }
            String jsonString = gsonUtils.toJson(objects.get(i));
            if (i==objects.size()-1){
                fileUtils.saveData("ProductInfo", jsonString, false);
            }else {
                fileUtils.saveData("ProductInfo", jsonString, true);
            }
        }
        if (flag == 0) {
            return false;
        } else {
            return true;
        }
    }
}
