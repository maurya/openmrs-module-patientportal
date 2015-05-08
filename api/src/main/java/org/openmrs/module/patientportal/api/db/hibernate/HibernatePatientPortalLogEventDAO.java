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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.module.patientportal.PatientPortalLogEvent;
import org.openmrs.module.patientportal.api.db.PatientPortalLogEventDAO;

/**
 * Hibernate implementation of the Data Access Object
 *
 * @author maurya
 */
public class HibernatePatientPortalLogEventDAO implements PatientPortalLogEventDAO {
	
	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;


	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

	@Override
	public PatientPortalLogEvent savePatientPortalEventLog(PatientPortalLogEvent event) {
		sessionFactory.getCurrentSession().saveOrUpdate(event);
		return event;
	}

}
