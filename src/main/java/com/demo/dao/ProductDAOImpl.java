package com.demo.dao;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.stereotype.Repository;
 
import com.demo.model.ProductVO;
 
@Repository
public class ProductDAOImpl implements ProductDAO {
 
    public List<ProductVO> getAllProducts() 
    {
        List<ProductVO> products = new ArrayList<ProductVO>();
         
        ProductVO vo1 = new ProductVO();
        vo1.setId(1);
        vo1.setFirstName("Lokesh");
        vo1.setLastName("Gupta");
        products.add(vo1);
         
        ProductVO vo2 = new ProductVO();
        vo2.setId(2);
        vo2.setFirstName("Raj");
        vo2.setLastName("Kishore");
        products.add(vo2);
         
        return products;
    }
}