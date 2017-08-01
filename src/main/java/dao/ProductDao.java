package dao;

import bean.Product;

import java.util.List;

/**
 * Created by apple on 2016/12/30.
 */
public interface ProductDao {
    public int addproduct(Product product);
    public Product selectByname(String productname);
}
