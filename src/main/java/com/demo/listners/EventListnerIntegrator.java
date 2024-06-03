package com.demo.listners;

import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class EventListnerIntegrator implements Integrator {
	
	   public void integrate( SessionFactoryImplementor 
	         sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

	      EventListenerRegistry eventListenerRegistry = 
	            serviceRegistry.getService(EventListenerRegistry.class);

	      eventListenerRegistry.getEventListenerGroup(EventType.SAVE_UPDATE)
	                     .appendListener(new SaveUpdateEventListenerImpl());
	      
	      eventListenerRegistry.getEventListenerGroup(EventType.LOAD)
	                     .appendListener(new LoadEventListenerImpl());
	      
	      eventListenerRegistry.getEventListenerGroup(EventType.REFRESH)
	                     .appendListener(new RefreshEventListenerImpl());
	   }

	   @Override
	   public void disintegrate(SessionFactoryImplementor sessionFactory,
	         SessionFactoryServiceRegistry serviceRegistry) {

	   }
	}