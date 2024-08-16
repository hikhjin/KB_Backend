package com.multi.mvc04.dao;

import com.multi.mvc04.vo.ProductVO;

import java.util.HashMap;
import java.util.Map;

public class ProductDAO {
    private Map<String, ProductVO> products = new HashMap<>();

    public void insertProduct(ProductVO product) {products.put(product.getId(), product);
    }

    public void deleteProduct(String id) {products.remove(id);
    }
}
