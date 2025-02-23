package com.jee.spring.annotations.dao;

import org.springframework.stereotype.Repository;

@Repository("dao")
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Getting data from database");
        return 43;
    }
}
