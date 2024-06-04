package com.demo.listners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import com.demo.entity.Employee;

public class PreInsertEventListenerImpl implements PreInsertEventListener {
    @Override
    public boolean onPreInsert(PreInsertEvent event) throws HibernateException {
        Object obj = event.getEntity();
        if (obj instanceof Employee) {
            Employee emp = (Employee) obj;
            System.out.println("Inserting Employee: " + emp);
        }
        return true; // Allow the insert to proceed
    }
}