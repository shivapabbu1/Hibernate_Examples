package com.demo;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.demo.entity.Department;
import com.demo.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;






public class App {
    public static void main(String[] args) {
        // Create EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();

        // Create and save a department
        Department dept = new Department();
        dept.setName("Developer");
        em.persist(dept);

        // Create and save an employee
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setDepartment(dept);
        em.persist(employee);

        // Commit transaction
        em.getTransaction().commit();

        // Start a new transaction for querying
        em.getTransaction().begin();

        // Writing JPQL to retrieve employees by department
        String jpql = "SELECT e FROM Employee e WHERE e.department.name = :deptName";
        List<Employee> employees = em.createQuery(jpql, Employee.class)
                                     .setParameter("deptName", "Developer")
                                     .getResultList();

        // Printing retrieved employees
        for (Employee emp : employees) {
            System.out.println("ID: " + emp.getId() + ", Name: " + emp.getName() + ", Department: " + emp.getDepartment().getName());
        }

        // Commit transaction and close EntityManager
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}