package com.demo.listners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.RefreshContext;
import org.hibernate.event.spi.RefreshEvent;
import org.hibernate.event.spi.RefreshEventListener;

import com.demo.entity.Employee;

public class RefreshEventListenerImpl implements RefreshEventListener {

	@Override
	public void onRefresh(RefreshEvent event) throws HibernateException {
		Object obj = event.getObject();
	      if (obj instanceof Employee) {
	         Employee emp = (Employee) obj;
	         System.out.println(emp);
	      }
	   }
	@Override
	public void onRefresh(RefreshEvent event, RefreshContext refreshedAlready) throws HibernateException {
		

	}

}
