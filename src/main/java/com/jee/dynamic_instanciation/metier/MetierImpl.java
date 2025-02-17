package com.jee.dynamic_instanciation.metier;

import com.jee.dynamic_instanciation.dao.IDao;

public class MetierImpl implements IMetier {

    private IDao dao;

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    public MetierImpl(){}

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public int calcul() {
        int data = dao.getData();
        return data * 10;
    }
}
