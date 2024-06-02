package com.demo;

import com.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class App {
    public static void main(String[] args) {
    	// Create Hibernate configuration
        Configuration configuration = new Configuration().configure();

        // Build the session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a new session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        Transaction transaction = session.beginTransaction();
        
        Employee employee0 = new Employee();
        employee0.setName("John Doe");
        employee0.setDepartment("IT");
        
        Employee employee1 = new Employee();
        employee1.setName("John ");
        employee1.setDepartment("HR");
        
        Employee employee2 = new Employee();
        employee2.setName(" Doe");
        employee2.setDepartment("Admin");
        
        
        session.save(employee0);
        session.save(employee1);
        session.save(employee2);

     // Open a new session and load the employee
        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();
        

        // First time loading, should hit the database
        Employee emp1 = session2.get(Employee.class, employee0.getId());
      

        session2.getTransaction().commit();
        session2.close();

        // Open another new session and load the employee again
        Session session3 = sessionFactory.openSession();
        session3.beginTransaction();
        

        // This time should be loaded from the second-level cache
        Employee emp2 = session3.get(Employee.class, employee0.getId());
      

        session3.getTransaction().commit();
        session3.close();

        // Close the session factory
        sessionFactory.close();
    }
}