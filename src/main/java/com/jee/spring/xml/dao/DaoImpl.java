package com.jee.spring.xml.dao;

public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Getting data from database");
        return 89;
    }
}
