package com.demo.listners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

import com.demo.entity.Employee;

public class SaveUpdateEventListenerImpl implements SaveOrUpdateEventListener {

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		  Object obj = event.getEntity();
	      if (obj instanceof Employee) {
	    	  Employee emp = (Employee) obj;
	         System.out.println(emp);
	      }
	   }
	}