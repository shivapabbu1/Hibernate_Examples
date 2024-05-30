package com.demo;

import java.util.List;


import com.demo.DAO.StudentDAO;
import com.demo.entity.Student;

public class App {
   
    	public static void main(String[] args) {
    		StudentDAO studentDao = new StudentDAO();
    		Student student = new Student("kiran", "kommu", "kirankommu@net.com");
    		studentDao.saveStudent(student);
    		
    		List<Student> students = studentDao.getStudents();
    		students.forEach(s -> System.out.println(s.getEmail()));
    	}
}