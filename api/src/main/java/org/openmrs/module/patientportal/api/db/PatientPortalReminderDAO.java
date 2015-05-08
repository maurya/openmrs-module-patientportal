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

import java.util.Date;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.module.patientportal.PatientPortalReminder;

/**
 * Data Access Object for reminder table
 * 
 * @author maurya
 */
public interface PatientPortalReminderDAO {

	/**
     * Get a reminder object
     * 
     * @param id id of reminder
     * @return reminder object
     */
	PatientPortalReminder getPatientPortalReminder(Integer id);

	/**
     * Save reminder object to database
     * 
     * @param reminder reminder object
     */
	PatientPortalReminder savePatientPortalReminder(PatientPortalReminder reminder);

	/**
     * delete a reminder
     * 
     * @param reminder reminder object
     */
    void deletePatientPortalReminder(PatientPortalReminder reminder);

	/**
     * Get all reminder entries
     * 
     * @return a list of reminder objects
     */
    List<PatientPortalReminder> getAllPatientPortalReminders();

	/**
     * Get all reminders of a given patient
     * 
     * @param pat patient object
     * @return a list of reminders for that patient
     */
    List<PatientPortalReminder> getPatientPortalReminders(Patient pat);

	/**
     * Get completed tests for a given patient
     * 
     * @param pat a given patient
     * @return completed tests of the given patient
     */
    List<PatientPortalReminder> getPatientPortalRemindersCompleted(Patient pat);

	/**
     * Get reminder object for a given patient, care type and target date 
     * 
     * @param patient patient object
     * @param careType type of care
     * @param targetDate target date
     * @return reminder object
     */
    PatientPortalReminder getPatientPortalReminder(Patient patient, Concept careType, Date targetDate);

	/**
     * Get reminders recommended by patient's providers
     * 
     * @param pat a given patient
     * @return list of reminders/follow up care recommended
     */
    List<PatientPortalReminder> getPatientPortalRemindersByProvider(Patient pat);

	/**
     * Delete recommended follow-up care
     * 
     * @param patientId patient id
     * @param targetDate target/recommended date of the care 
     * @param careType type of care
     */
    void deletePatientPortalReminder(Integer patientId, Date targetDate, String careType);
}
