package com.jee.static_instanciation.presentation;

import com.jee.static_instanciation.dao.IDao;
import com.jee.static_instanciation.dao.IDaoImpl;
import com.jee.static_instanciation.metier.IMetier;
import com.jee.static_instanciation.metier.MetierImpl;

public class Presentation {
    public static void main(String[] args) {
        IDao dao = new IDaoImpl();
        IMetier metier = new MetierImpl(dao);
        System.out.println(metier.calcul());
    }
}
