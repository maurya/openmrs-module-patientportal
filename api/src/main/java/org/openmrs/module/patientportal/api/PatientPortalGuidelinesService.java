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


package org.openmrs.module.patientportal.api;

import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.module.patientportal.PatientPortalReminder;
import org.openmrs.module.patientportal.api.db.PatientPortalGuidelineDAO;
import org.openmrs.module.patientportal.api.db.PatientPortalReminderDAO;

/**Service class of the module
*
*@author maurya
*/
public interface PatientPortalGuidelinesService {

	/**
     * Auto generated method comment
     * 
     * @return
     */
	PatientPortalGuidelineDAO getGuidelineDao();

	/**
     * Auto generated method comment
     * 
     * @param guidelineDao
     */
    void setGuidelineDao(PatientPortalGuidelineDAO guidelineDao);

	/**
     * Auto generated method comment
     * 
     * @return
     */
    PatientPortalReminderDAO getReminderDao();

	/**
     * Auto generated method comment
     * 
     * @param reminderDao
     */
    void setReminderDao(PatientPortalReminderDAO reminderDao);

	/**
     * Auto generated method comment
     * 
     * @param pat
     * @return
     */
    List<PatientPortalReminder> getReminders(Patient pat);

	/**
     * Auto generated method comment
     * 
     * @param pat
     * @return
     */
    List<PatientPortalReminder> getRemindersCompleted(Patient pat);

    /**
     * Auto generated method comment
     * 
     * @param pat
     * @return
     */
    List<Concept> getSideEffects(Patient pat);

	/**
     * Auto generated method comment
     * 
     * @param patient
     * @param indexDate
     * @return
     */
    List<PatientPortalReminder> getReminders(Patient patient, Date indexDate);

	List<PatientPortalReminder> addRemindersCompleted(Patient pat);

}
