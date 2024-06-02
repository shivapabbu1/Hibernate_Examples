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

        // Load an employee with ID 1
        Long employeeId = 1L;
        System.out.println("Loading Employee with ID: " + employeeId);
        Employee emp1 = session.get(Employee.class, employeeId);

        // Load the same employee again in the same session
        System.out.println("Loading the same Employee again");
        Employee emp2 = session.get(Employee.class, employeeId);

        // Commit the transaction
        transaction.commit();

        // Close the session
        session.close();

        // Close the session factory
        sessionFactory.close();
    }
}
