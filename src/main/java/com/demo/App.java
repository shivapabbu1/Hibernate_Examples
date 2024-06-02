package com.demo;

import com.demo.entity.Department;
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
        configuration.addAnnotatedClass(Department.class);

        // Build the session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a new session
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Create and save Department
        Department department = new Department();
        department.setName("HR");
        session.save(department);

        // Create and save Employee
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment(department);
        session.save(employee);

        transaction.commit();
        session.close();

        // Fetching Employee
        Session newSession = sessionFactory.openSession();
        System.out.println("Fetching Employee...");
        Employee fetchedEmployee = newSession.get(Employee.class, employee.getId());
        System.out.println("Employee fetched.");

        // Accessing Employee's name
        System.out.println("Employee Name: " + fetchedEmployee.getName());

        // Accessing Employee's Department
        System.out.println("Accessing Employee's Department...");
        System.out.println("Department Name: " + fetchedEmployee.getDepartment().getName()); // Triggers lazy loading

        newSession.close();
        sessionFactory.close();
    }
}
