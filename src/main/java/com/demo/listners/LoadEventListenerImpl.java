package com.demo.listners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

import com.demo.entity.Employee;

public class LoadEventListenerImpl implements LoadEventListener {

	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
				Object obj = event.getResult();
	      if (obj instanceof Employee) {
		         Employee emp = (Employee) obj;
		         System.out.println(emp);
		      }
		   }
}