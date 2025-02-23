package com.jee.spring.xml.extensions;


import com.jee.spring.xml.dao.IDao;

public class DaoImplv2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Getting data from WS");
        return 30;
    }
}
