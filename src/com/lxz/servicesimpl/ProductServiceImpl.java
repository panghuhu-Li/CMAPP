package com.lxz.servicesimpl;

import com.lxz.entity.ProductInfo;
import com.lxz.entity.ProductType;
import com.lxz.services.ProductService;
import com.lxz.utils.FileUtils;
import com.lxz.utils.GsonUtils;
import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 产品数据处理层
 * @author: 李星泽
 * @create: 2020-07-18 15:42
 **/
public class ProductServiceImpl implements ProductService {
    private FileUtils fileUtils = new FileUtils();
    private GsonUtils gsonUtils = new GsonUtils();

    /**
     * @param object: 添加的产品对象
     * @return boolean:返回产品对象是否添加成功
     * @description 添加产品
     **/
    @Override
    public boolean add(Object object) throws IOException {
        List<Object> objects = getList();
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

    /**
     * @return List<Object>:返回所有的产品信息
     * @description 得到所有的产品信息
     **/
    public List<Object> getList() throws IOException {
        String jsonString = fileUtils.readFile("ProductInfo");
        List<Object> objects = gsonUtils.toObjectList(jsonString, ProductInfo.class);
        return objects;
    }

    /**
     * @param produceNumber: 产品编号
     * @return boolean:返回产品是否删除成功
     * @description 根据产品编号删除产品
     **/
    @Override
    public boolean delete(String produceNumber) throws IOException {
        List<Object> objects = getList();
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

    /**
     * @param productName: 产品名称
     * @return Object:返回要查找的产品对象
     * @description 根据产品名称进行检索
     **/
    @Override
    public Object search(String productName) throws IOException {
        List<Object> objects = getList();
        for (Object object : objects) {
            ProductInfo productInfo = (ProductInfo) object;
            if (productInfo.getProductName().equals(productName)){
                return productInfo;
            }
        }
        return null;
    }

    /**
     * @param productNumber:产品编号
     * @param productName:       产品名称
     * @param productType:产品类型
     * @param productSpec:产品规格
     * @param producyDesc:产品描述
     * @return boolean:返回产品信息是否修改成功
     * @description 修改产品信息
     **/
    @Override
    public boolean modify(String productNumber,String productName, String productType, String productSpec, String producyDesc) throws IOException {
        List<Object> objects = getList();
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

    /**
     * @param object: 要添加的产品类型对象
     * @return boolean: 返回产品类型是否添加成功
     * @description 添加产品类型
     **/
    @Override
    public boolean addType(Object object) throws IOException {
        String jsonString = gsonUtils.toJson(object);
        fileUtils.saveData("ProductType", jsonString, true);
        return true;
    }

    /**
     * @return List<Object>: 返回所有的产品类型信息
     * @description 得到所有的产品类型信息
     **/
    @Override
    public List<Object> getProductType() throws IOException {
        String jsonString = fileUtils.readFile("ProductType");
        List<Object> objects = gsonUtils.toObjectList(jsonString, ProductType.class);
        return objects;
    }

    /**
     * @param productName: 产品类型名称
     * @return Object: 返回要查找的产品类型对象
     * @description 检索产品类型
     **/
    @Override
    public Object searchProductType(String productName) throws IOException {
        List<Object> objects=getProductType();
        for (Object object : objects) {
            ProductType productType = (ProductType) object;
            if (productType.getTypeName().equals(productName)){
                return productType;
            }
        }
        return null;
    }

    /**
     * @param original:         原产品类型
     * @param typeName:修改后的产品类型
     * @return Object: 返回要查找的产品类型对象
     * @description 修改产品类型
     **/
    @Override
    public boolean modifyType(String original, String typeName) throws IOException {
        List<Object> objects=getProductType();
        int flag=0;
        for (int i=objects.size()-1;i>=0;i--) {
            ProductType productType = (ProductType) objects.get(i);
            if (productType.getTypeName().equals(original)){
                productType.setTypeName(typeName);
                flag=1;
            }
            String jsonString = gsonUtils.toJson(objects.get(i));
            if (i==objects.size()-1){
                fileUtils.saveData("ProductType", jsonString, false);
            }else {
                fileUtils.saveData("ProductType", jsonString, true);
            }
        }
        if (flag==1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @param produceNumber: 产品类型
     * @return boolean: 返回是否删除成功
     * @description 删除产品类型
     **/
    @Override
    public boolean deleteType(String produceNumber) throws IOException {
        List<Object> objects=getProductType();
        int flag=0,num=0;
        for (int i=objects.size()-1;i>=0;i--) {
            ProductType productType = (ProductType) objects.get(i);
            if (productType.getTypeName().equals(produceNumber)){
                flag=1;
                continue;
            }
            String jsonString = gsonUtils.toJson(objects.get(i));
            if (num==0){
                fileUtils.saveData("ProductType", jsonString, false);
            }else {
                fileUtils.saveData("ProductType", jsonString, true);
            }
            num++;
        }
        if (flag==1){
            return true;
        }else {
            return false;
        }
    }
}
