package com.jee.spring.annotations.presentation;

import com.jee.spring.annotations.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Presentation {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.jee.spring.annotations");
        IMetier metier =  context.getBean(IMetier.class);
        System.out.println(metier.calcul());
    }
}
