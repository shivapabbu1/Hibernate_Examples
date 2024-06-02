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

        

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment("IT");
        
        
        session.save(employee);
        

        // Execute native SQL query
        String sql = "SELECT * FROM Employee WHERE department = :dept";
        NativeQuery<Employee> query = session.createNativeQuery(sql, Employee.class);
        query.setParameter("dept", "IT");

        // Get the result list
        List<Employee> employees = query.list();

        // Printing retrieved employees
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + ", Department: " + emp.getDepartment());
        }

        // Commit transaction and close session
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }
}
