package com.jee.dynamic_instanciation.dao;

public class IDaoImplV2 implements IDao {
    @Override
    public int getData() {
        System.out.println("Getting data from WS");
        return 55;
    }
}
