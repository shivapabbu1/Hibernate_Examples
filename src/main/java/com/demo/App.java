package com.demo;

import com.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Initialize Hibernate SessionFactory
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open session
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        

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
        
        
        Long empId = 2L; // Example employee ID
        List<Employee> employees = session.createNamedQuery("findEmployeeById", Employee.class)
                                      .setParameter("empId", empId)
                                      .getResultList();

        for (Employee employee : employees) {
            System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Department: " + employee.getDepartment());
        }

      
        // Commit transaction and close session
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
