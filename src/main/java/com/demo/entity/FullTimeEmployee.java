package com.demo.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity


public class FullTimeEmployee extends Employee {
    private double salary;

    // Getters and setters
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}
