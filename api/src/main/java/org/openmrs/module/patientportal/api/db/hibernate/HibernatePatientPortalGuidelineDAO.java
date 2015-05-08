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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Concept;
import org.openmrs.module.patientportal.PatientPortalGuideline;
import org.openmrs.module.patientportal.api.db.PatientPortalGuidelineDAO;

/**
 * Hibernate implementation of the Data Access Object
 * 
 * @author maurya
 */
public class HibernatePatientPortalGuidelineDAO implements
		PatientPortalGuidelineDAO {

	 protected final Log log = LogFactory.getLog(getClass());
	 
	 private SessionFactory sessionFactory;
	    
	    public void setSessionFactory(SessionFactory sessionFactory) {
	        this.sessionFactory = sessionFactory;
	    }
	    
	 
	@Override
	public PatientPortalGuideline getPatientPortalGuideline(Integer id) {
		return (PatientPortalGuideline) sessionFactory.getCurrentSession().get(PatientPortalGuideline.class, id);
	}

	@Override
	public PatientPortalGuideline savePatientPortalGuideline(
			PatientPortalGuideline guideline) {
		 sessionFactory.getCurrentSession().saveOrUpdate(guideline);
	        return guideline;
	}

	@Override
	public void deletePatientPortalGuideline(PatientPortalGuideline guideline) {
		sessionFactory.getCurrentSession().delete(guideline);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PatientPortalGuideline> getAllPatientPortalGuidelines() {
		 Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalGuideline.class);
	        //crit.addOrder(Order.asc("privilege"));
	        return (List<PatientPortalGuideline>) crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PatientPortalGuideline> getPatientPortalGuideline(
			Concept cancerType, Concept cancerStage) {
		  //Query query = sessionFactory.getCurrentSession().createQuery("from LafGuideline").list();
        //query.setParameter("url", url);
        //List list0 = query.list();        
        Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalGuideline.class);
        crit.add(Restrictions.eq("cancerType", cancerType));
        crit.add(Restrictions.eq("cancerStage", cancerStage));
        List<PatientPortalGuideline> list = (List<PatientPortalGuideline>) crit.list();
        if (list.size() >= 1)
            return list;
        else
            return null;
	}

}
