package com.demo;

import com.demo.entity.Employee;

import java.util.List;

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
        Employee emp1 = new Employee();
        emp1.setName("John Doe");
        emp1.setDepartment("HR");
        emp1.setActive(true);
        session.save(emp1);

        Employee emp2 = new Employee();
        emp2.setName("Jane Smith");
        emp2.setDepartment("Finance");
        emp2.setActive(false);
        session.save(emp2);

        transaction.commit();
        session.close();

        // Open a new session to apply filter and query
        session = sessionFactory.openSession();
        session.enableFilter("activeFilter").setParameter("isActive", true);
        
        // Query to get all active employees
        List<Employee> activeEmployees = session.createQuery("from Employee", Employee.class).list();
        System.out.println("Active Employees:");
        for (Employee emp : activeEmployees) {
            System.out.println(emp.getName());
        }

        session.disableFilter("activeFilter");

        // Query to get all employees without filter
        List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).list();
        System.out.println("All Employees:");
        for (Employee emp : allEmployees) {
            System.out.println(emp.getName());
        }

        session.close();
        sessionFactory.close();
    }
}