package com.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.entity.Address;
import com.demo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();
        
        Address address = new Address();
        address.setStreet("123 Main St");
        address.setCity("Springfield");
        address.setState("IL");
        address.setZipCode("62704");

        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment("IT");
        employee.setAddress(address);
        
        em.persist(employee);
        em.persist(address);
        

        // Writing JPQL to retrieve employees by department
        String jpql = "SELECT e FROM Employee e WHERE e.department = :dept";
        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
        query.setParameter("dept", "IT");

        // Execute the query and get the results
        List<Employee> employees = query.getResultList();

        // Printing retrieved employees
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + ", Department: " + emp.getDepartment());
            System.out.println("Address: " + emp.getAddress().getStreet() + ", " + emp.getAddress().getCity() + ", " + emp.getAddress().getState() + ", " + emp.getAddress().getZipCode());
        }

        // Commit transaction and close EntityManager
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}