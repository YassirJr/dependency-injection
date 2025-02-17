package com.jee.static_instanciation.metier;

import com.jee.static_instanciation.dao.IDao;

public class MetierImpl implements IMetier {

    private final IDao dao;

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public int calcul() {
        int data = dao.getData();
        return data * 10;
    }
}
