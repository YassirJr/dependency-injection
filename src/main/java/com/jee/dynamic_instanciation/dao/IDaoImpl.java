package com.jee.dynamic_instanciation.dao;

public class IDaoImpl implements IDao {
    @Override
    public int getData() {
        System.out.println("Getting data from database");
        return 10;
    }
}
