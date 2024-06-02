package com.demo;

import com.demo.entity.Employee;
import com.demo.entity.ProductInterceptor;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
    	Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Employee.class);
        configuration.setInterceptor(new ProductInterceptor());

        // Build the session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a new session
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Save data
        Employee employee1 = new Employee();
        employee1.setName("Employee 1");
        employee1.setDepartment("Department A");
        session.save(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Employee 2");
        employee2.setDepartment("Department B");
        session.save(employee2);

        transaction.commit();
        session.close();

        // Open a new session for update and delete
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        // Update data
        Employee employeeToUpdate = session.get(Employee.class, employee1.getId());
        employeeToUpdate.setName("Updated Employee 1");
        session.update(employeeToUpdate);

        // Delete data
        Employee employeeToDelete = session.get(Employee.class, employee2.getId());
        session.delete(employeeToDelete);

        transaction.commit();
        session.close();

        sessionFactory.close();
    }
}