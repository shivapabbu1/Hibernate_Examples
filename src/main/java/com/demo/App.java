package com.demo;

import com.demo.entity.Student;
import com.demo.util.JPAUtil;

import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args){

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        Student student = new Student("pabbu", "shivaa", "pabbuhiva@javaguides.com");
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();

        JPAUtil.shutdown();
    }

}