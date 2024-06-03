package com.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;





@NamedQuery(name = "GET_EMP_COUNT", query = "select count(1) from EMPLOYEE")

//Using @NamedQueries for multiple JPQL or HQL
@NamedQueries({ @NamedQuery(name = "GET_EMP_BY_ID", query = "from Employee where id=:id"),
		@NamedQuery(name = "GET_ALL_EMPS", query = "from Employee") })


@Entity


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