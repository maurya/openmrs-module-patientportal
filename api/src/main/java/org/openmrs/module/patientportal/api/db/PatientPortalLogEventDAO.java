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

package org.openmrs.module.patientportal.api.db;

import org.hibernate.SessionFactory;
import org.openmrs.module.patientportal.PatientPortalLogEvent;


/**
 * Data Access Object for PatientPortal_log_event table access
 * 
 * @author maurya
 */
public interface PatientPortalLogEventDAO {
    /**
     * Set hibernate session factory
     * 
     * @param sessionFactory Hibernate session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory);
       
    /**
     * Save PatientPortalEventLog object to database
     * 
     * @param event PatientPortalEventLog object
     * @return event log object saved
     */
    public PatientPortalLogEvent savePatientPortalEventLog(PatientPortalLogEvent event);    
}
