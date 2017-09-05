package service;

import bean.Product;
import dao.ProductDao;
import helper.log.MyLogTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by apple on 2016/12/30.
 */
public class DealProduct {
    ProductDao productDao = null;
    ApplicationContext ctx =null;
    MyLogTest log = null;

    private static DealProduct dealProduct;

    public static DealProduct getInstance() {
        if (dealProduct == null) {
            synchronized (DealProduct.class) {
                if (dealProduct == null) {
                    dealProduct = new DealProduct();
                }
            }
        }
        return dealProduct;
    }

    //方法0：构造函数，文件加载，类加载初始化
    public DealProduct(){
        ctx = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        productDao = (ProductDao) ctx.getBean("productDao");
        log = MyLogTest.getInstance();
    }

    public Boolean addproduct(Product product){
        productDao.addproduct(product);
        return product.getProductid()>=0?true:false;
    }

    public Boolean isProductExist(String productname){
        Product product = productDao.selectByname(productname);
        if(product == null ){
            return false;

        }else
            return true;
    }

}
