package com.demo;

import com.demo.entity.Employee;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.service.ServiceRegistry;

public class App {
    public static void main(String[] args) {
        manageEmployees(args);
    }

    public static void manageEmployees(String[] args) {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Employee.class);
        

        // Build the session factory
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        

        // Open a new session
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Create new employees
        Employee newEmployee = new Employee();
        newEmployee.setName("Employee 1");
        newEmployee.setDepartment("Department A");
        session.saveOrUpdate(newEmployee);

        Employee existingEmployee = new Employee();
        existingEmployee.setName("Employee 2");
        existingEmployee.setDepartment("Department B");
        session.saveOrUpdate(existingEmployee);

        transaction.commit();
        session.close();

        // Open a new session for load and refresh
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        // Load existing employee
        Employee employeeToLoad = session.load(Employee.class, newEmployee.getId());
        System.out.println("Loaded Employee: " + employeeToLoad.getName());

        // Refresh existing employee
        session.refresh(employeeToLoad);
        System.out.println("Refreshed Employee: " + employeeToLoad.getName());

        transaction.commit();
        session.close();

        // Open a new session for update and delete
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        // Update existing employee
        Employee employeeToUpdate = session.get(Employee.class, newEmployee.getId());
        employeeToUpdate.setName("Updated Employee 1");
        session.saveOrUpdate(employeeToUpdate);

        // Delete existing employee
        Employee employeeToDelete = session.get(Employee.class, existingEmployee.getId());
        session.delete(employeeToDelete);

        transaction.commit();
        session.close();

        sessionFactory.close();
    }
}