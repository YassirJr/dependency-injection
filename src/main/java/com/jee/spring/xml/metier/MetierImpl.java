package com.jee.spring.xml.metier;


import com.jee.spring.xml.dao.IDao;

public class MetierImpl implements IMetier {


    private IDao dao;

    public MetierImpl() {
    }

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 11;
    }
}
