/**
 * The contents of this file are subject to the Regenstrief Public License
 * Version 1.0 (the "License"); you may not use this file except in compliance with the License.
 * Please contact Regenstrief Institute if you would like to obtain a copy of the license.
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) Regenstrief Institute.  All Rights Reserved.
 */

package org.openmrs.module.patientportal.api.db.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientportal.PatientPortalReminder;
import org.openmrs.module.patientportal.PatientPortalUtil;
import org.openmrs.module.patientportal.api.db.PatientPortalReminderDAO;

/**
 * Hibernate implementation of the Data Access Object
 * 
 * @author maurya
 */
public class HibernatePatientPortalReminderDAO implements PatientPortalReminderDAO {

	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;
	    
	/**
	 * set a session factory
	 * 
	 * @param sessionFactory Hibernate session factory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	    
	@Override
	public PatientPortalReminder getPatientPortalReminder(Integer id) {
		 return (PatientPortalReminder) sessionFactory.getCurrentSession().get(PatientPortalReminder.class, id);
	}

	@Override
	public PatientPortalReminder savePatientPortalReminder(
			PatientPortalReminder reminder) {
		log.debug("Save reminder - reminder.getFollowProcedure()=" + reminder.getFollowProcedure() + 
	    		 ", reminder.getFollowProcedureName() = " + reminder.getFollowProcedureName());
	    	
	    	if(reminder.getFollowProcedureName() != null) {
	    		reminder.setFollowProcedure(Context.getConceptService().getConceptByName(reminder.getFollowProcedureName()));    		
	        	log.debug("New reminder.getFollowProcedure()=" + reminder.getFollowProcedure()); 
	    	}
	    	
	        Session sess = sessionFactory.openSession();
	        Transaction tx = sess.beginTransaction();
	        sess.setFlushMode(FlushMode.COMMIT); // allow queries to return stale state
	        sess.saveOrUpdate(reminder);
	        tx.commit();
	        //sess.flush();
	        sess.close();
	        //sessionFactory.getCurrentSession().saveOrUpdate(token);
	        return reminder;
	}

	@Override
	public void deletePatientPortalReminder(PatientPortalReminder reminder) {
		 //sessionFactory.getCurrentSession().delete(token);
        //sessionFactory.getCurrentSession().close();
        Session sess = sessionFactory.openSession();
        Transaction tx = sess.beginTransaction();
        sess.setFlushMode(FlushMode.COMMIT); // allow queries to return stale state
        sess.delete(reminder);
        tx.commit();
        sess.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatientPortalReminder> getAllPatientPortalReminders() {
		 Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalReminder.class);
		//crit.addOrder(Order.asc("privilege"));
        return (List<PatientPortalReminder>) crit.list();
	}

	@Override
	public List<PatientPortalReminder> getPatientPortalReminders(Patient pat) {
		 //Query query = sessionFactory.getCurrentSession().createQuery("from LafReminder where allowedUrl = :url ");
        //query.setParameter("url", url);
        //List list0 = query.list();        
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalReminder.class);
        crit.add(Restrictions.eq("patient", pat));
        crit.add(Restrictions.isNull("completeDate"));
        crit.add(Restrictions.isNotNull("targetDate"));
        crit.addOrder(Order.asc("targetDate"));
        @SuppressWarnings("unchecked")
        List<PatientPortalReminder> list = (List<PatientPortalReminder>) crit.list();
        if (list.size() >= 1)
            return list;
        else
            return null;
	}

	@Override
	public List<PatientPortalReminder> getPatientPortalRemindersCompleted(
			Patient pat) {
		 //Query query = sessionFactory.getCurrentSession().createQuery("from LafReminder where allowedUrl = :url ");
        //query.setParameter("url", url);
        //List list0 = query.list();        
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalReminder.class);
        crit.add(Restrictions.eq("patient", pat));
        crit.add(Restrictions.isNotNull("completeDate"));
        crit.add(Restrictions.isNull("targetDate"));
        crit.addOrder(Order.asc("completeDate"));
        @SuppressWarnings("unchecked")
        List<PatientPortalReminder> list = (List<PatientPortalReminder>) crit.list();
        if (list.size() >= 1)
            return list;
        else
            return null;
	}

	@Override
	public PatientPortalReminder getPatientPortalReminder(Patient patient,
			Concept careType, Date targetDate) {
		 	Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalReminder.class);
	        crit.add(Restrictions.eq("patient", patient));
	        crit.add(Restrictions.eq("followProcedure", careType));
	        crit.add(Restrictions.eq("targetDate", PatientPortalUtil.clearDate(targetDate)));
	        //crit.add(Restrictions.lt("targetDate", oneDayLater(targetDate)));
	        crit.add(Restrictions.isNull("completeDate"));
	        crit.addOrder(Order.asc("targetDate"));
	        @SuppressWarnings("unchecked")
	        List<PatientPortalReminder> list = (List<PatientPortalReminder>) crit.list();
	        if (list.size() == 1) {
	        	log.debug("One reminder is found: patient=" + patient + "|careType=" + careType + "|targetDate=" + targetDate);        	
	            return list.get(0);
	        }
	        else if(list.size() > 1) {
	        	log.error("More than one reminder is found: patient=" + patient + "|careType=" + careType + "|targetDate=" + targetDate);
	        	return list.get(0);
	        }
	        else
	            return null;
	}

	@Override
	public List<PatientPortalReminder> getPatientPortalRemindersByProvider(
			Patient pat) {
		  Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalReminder.class);
	        crit.add(Restrictions.eq("patient", pat));
	        crit.add(Restrictions.isNull("completeDate"));
	        crit.add(Restrictions.isNotNull("targetDate"));
	        crit.add(Restrictions.eq("responseType", "PHR_PROVIDER"));
	        crit.addOrder(Order.asc("targetDate"));
	        @SuppressWarnings("unchecked")
	        List<PatientPortalReminder> list = (List<PatientPortalReminder>) crit.list();
	        if (list.size() >= 1)
	            return list;
	        else
	            return null;
	}

	@Override
	public void deletePatientPortalReminder(Integer patientId, Date targetDate,
			String careType) {
		//delete follow up care recommended by patient's providers
    	deletePatientPortalReminder(getPatientPortalReminder(Context.getPatientService().getPatient(patientId), Context.getConceptService().getConceptByName(careType), targetDate));   
		
	}

}
