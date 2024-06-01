package com.demo;




import org.hibernate.Session;
import org.hibernate.Transaction;

import com.demo.entity.FullTimeEmployee;
import com.demo.entity.PartTimeEmployee;
import com.demo.util.HibernateUtil;

import jakarta.persistence.EntityManager;

public class App {
    public static void main(String[] args){
    	 Session session = HibernateUtil.getSessionFactory().openSession();
         Transaction transaction = session.beginTransaction();

         FullTimeEmployee fullTimeEmployee = new FullTimeEmployee();
         fullTimeEmployee.setName("John Doe");
         fullTimeEmployee.setSalary(50000);

         PartTimeEmployee partTimeEmployee = new PartTimeEmployee();
         partTimeEmployee.setName("Jane Doe");
         partTimeEmployee.setHourlyRate(30);

         session.save(fullTimeEmployee);
         session.save(partTimeEmployee);

         transaction.commit();
         session.close();
     }

}