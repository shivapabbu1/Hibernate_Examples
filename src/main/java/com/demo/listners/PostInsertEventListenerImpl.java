package com.demo.listners;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

import com.demo.entity.Employee;

public class PostInsertEventListenerImpl implements PostInsertEventListener {
    @Override
    public void onPostInsert(PostInsertEvent event) throws HibernateException {
        Object obj = event.getEntity();
        if (obj instanceof Employee) {
            Employee emp = (Employee) obj;
            System.out.println("Inserted Employee: " + emp);
        }
    }

	@Override
	public boolean requiresPostCommitHandling(EntityPersister persister) {
		
		return false;
	}
}