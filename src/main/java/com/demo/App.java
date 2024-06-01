package com.demo;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.demo.entity.Address;
import com.demo.entity.Employee;
import com.demo.util.HibernateUtil;


public class App {
	public static void main(String[] args) {
        // Create an Address instance
        Address address = new Address("123 Main St", "Anytown", "12345");

        // Create an Employee instance with the address
        Employee employee = new Employee("John Doe", address);

        // Open a session
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Begin a transaction
            Transaction transaction = session.beginTransaction();

            // Save the employee entity
            session.save(employee);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close the session factory
        HibernateUtil.getSessionFactory().close();
    }
}