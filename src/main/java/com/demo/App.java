package com.demo;

import com.demo.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class App {
    public static void main(String[] args) {
    	// Create Hibernate configuration
        Configuration configuration = new Configuration().configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

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

                    List<Long> totalEmps = session.createNamedQuery("GET_EMP_COUNT", Long.class).getResultList();
                    System.out.println("Total Employees: " + totalEmps.get(0));

                    Employee employee = session.createNamedQuery("GET_EMPLOYEE_BY_ID", Employee.class)
                                                .setParameter("id", 1)
                                                .getSingleResult();

                    System.out.println("Employee Name: " + employee.getName());

                    List<Employee> emps = session.createNamedQuery("GET_ALL_EMPLOYEES", Employee.class).getResultList();
                    for (Employee emp : emps) {
                        System.out.println("ID: " + emp.getId() + "\tName: " + emp.getName());
                    }

                    // Commit the transaction
                    transaction.commit();

                    // Close the session
                    session.close();

                    // Close the session factory
                    sessionFactory.close();
                }
            }