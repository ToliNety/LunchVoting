package org.tolinety.springrest;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

/**
 * Created by ToliNeTy on 29.05.2017.
 */
public class Main {
    public static void main(String[] args) {
        try (GenericXmlApplicationContext context = new GenericXmlApplicationContext()) {
            context.load("spring/spring-app.xml", "spring/spring-db.xml");
            context.refresh();

            System.out.println("Hello JSON API!");
            System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
        }


    }
}
