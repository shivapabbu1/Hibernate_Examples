package com.demo;

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

        // Build the session factory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a new session
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Batch insertion of Employees
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setName("Employee " + i);
            employee.setDepartment("Department " + (i % 10));
            session.save(employee);

            // Batch size is 20, so flush and clear the session every 20 inserts
            if (i % 2 == 0) {
                session.flush();
                session.clear();
            }
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
