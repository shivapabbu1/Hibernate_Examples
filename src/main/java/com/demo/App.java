package com.demo;

import com.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Create Hibernate configuration
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Employee.class);

        // Build the session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a new session and save an Employee
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment("IT");
        session.save(employee);

        transaction.commit();
        session.close();

        // Open another session to demonstrate dirty checking
        Session newSession = sessionFactory.openSession();
        Transaction newTransaction = newSession.beginTransaction();

        // Fetch the Employee
        Employee fetchedEmployee = newSession.get(Employee.class, employee.getId());
        System.out.println("Fetched Employee: " + fetchedEmployee.getName() + ", Department: " + fetchedEmployee.getDepartment());

        // Modify the Employee's department
        fetchedEmployee.setDepartment("HR");

        // No explicit call to update() is required
        System.out.println("Modified Employee Department to: " + fetchedEmployee.getDepartment());

        newTransaction.commit();
        newSession.close();

        sessionFactory.close();
    }
}
