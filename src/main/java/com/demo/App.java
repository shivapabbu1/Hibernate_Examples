package com.demo;

import com.demo.entity.Address;
import com.demo.entity.Employee;
import com.demo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    public static void main(String[] args) {
    	
    	Configuration configuration = new Configuration().configure();
       
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  // 

        // Open session
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Create and save employee
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setState("IL");
        address.setZipCode("62704");

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment("IT");
        employee.setAddress(address);

        session.save(employee);
        session.getTransaction().commit();

        // Open new session for querying
        session = sessionFactory.openSession();
        session.beginTransaction();

        // Writing HQL to retrieve employees by department
        String hql = "FROM Employee WHERE department = :dept";
        List<Employee> employees = session.createQuery(hql, Employee.class)
                .setParameter("dept", "IT")
                .list();

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
