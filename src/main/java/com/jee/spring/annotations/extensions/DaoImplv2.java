package com.jee.spring.annotations.extensions;

import com.jee.spring.annotations.dao.IDao;
import org.springframework.stereotype.Repository;

@Repository("dao2")
public class DaoImplv2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Getting data from Web Service");
        return 45;
    }
}
