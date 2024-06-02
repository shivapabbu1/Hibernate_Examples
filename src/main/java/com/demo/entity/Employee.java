package com.demo.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;

@Entity
@NamedNativeQuery(
	    name = "findEmployeeById",
	    query = "SELECT * FROM Employee WHERE id = :empId",
	    resultClass = Employee.class
	)
@NamedQuery(
	    name = "findEmployeeByName",
	    query = "SELECT e FROM Employee e WHERE e.name = :name"
	)
public class Employee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;

  
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
}