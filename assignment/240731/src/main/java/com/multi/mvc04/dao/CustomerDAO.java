package com.multi.mvc04.dao;

import com.multi.mvc04.vo.CustomerVO;
import com.multi.mvc04.vo.ProductVO;

import java.util.HashMap;
import java.util.Map;

public class CustomerDAO {
    private Map<String, CustomerVO> customers = new HashMap<>();

    public void insertCustomer(CustomerVO customer) {customers.put(customer.getId(), customer);
    }

    public void deleteCustomer(String id) {customers.remove(id);
    }
}
