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

package org.openmrs.module.patientportal;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.module.patientportal.PatientPortalReminder;
import org.openmrs.notification.Alert;


/**Represents an extended patient object customized for this module
 *  
 *  
 * @author maurya
 */
public class PatientPortalPatient {

	private Patient patient;
	private List<PatientPortalReminder> reminders; //follow-up care per guideline
	private List<PatientPortalReminder> remindersCompleted; //follow-up care actually performed
	private List<Alert> alerts;
	private String[] responseTypes = {"Normal", "Abnormal", "Don't know"};
	private String[] careTypes = {
			"Colonoscopy", "History and Physical",
			"CEA Tests", "CT Scan Chest/Abdomen", 
			"CT Scan Pelvis", "Flex Sigmoidoscopy"};
	
	/**
	 * Constructor
	 * @param pat OpenMRS patient
	 * @param rem reminder list
	 * @param remCompl completed reminder list
	 */
	public PatientPortalPatient(Patient pat, List<PatientPortalReminder> rem, List<PatientPortalReminder> remCompl) {
		this.patient = pat;
		this.reminders = rem;
		this.remindersCompleted = remCompl;
		
	}

	/**
	 * Constructor
	 * @param pat OpenMRS Patient object
	 * @param alerts list of alerts generated
	 */
	public PatientPortalPatient(Patient pat, List<Alert> alerts) {
		this.patient = pat;
		this.alerts = alerts;
	}
	
    /**
     * Get the list of reminders for the patient
     * 
     * @return list of reminders
     */
    public List<PatientPortalReminder> getReminders() {
    	return reminders;
    }
	
    /**
     * Set a list of reminders for the patient
     * 
     * @param reminders a list of reminders
     */
    public void setReminders(List<PatientPortalReminder> reminders) {
    	this.reminders = reminders;
    }

	
    /**
     * Get OpenMRS Patient object
     * 
     * @return OpenMRS Patient object
     */
    public Patient getPatient() {
    	return patient;
    }

	
    /**
     * Set OpenMRS object
     * 
     * @param patient openmrs object
     */
    public void setPatient(Patient patient) {
    	this.patient = patient;
    }

	
    /**
     * Get completed reminders
     * 
     * @return reminders completed by the patient
     */
    public List<PatientPortalReminder> getRemindersCompleted() {
    	return remindersCompleted;
    }

	
    /**
     * Set reminders completed by the patient
     * 
     * @param remindersCompleted reminders completed by the patient
     */
    public void setRemindersCompleted(List<PatientPortalReminder> remindersCompleted) {
    	this.remindersCompleted = remindersCompleted;
    }

	
    /**
     * Get response types
     * 
     * @return response types
     */
    public String[] getResponseTypes() {
    	return responseTypes;
    }

	
    /**
     * Set response types
     * 
     * @param responseTypes repsone types
     */
    public void setResponseTypes(String[] responseTypes) {
    	this.responseTypes = responseTypes;
    }

	
    /**
     * Get care types
     * 
     * @return care types
     */
    public String[] getCareTypes() {
    	return careTypes;
    }

	
    /**
     * Set care types
     * 
     * @param careTypes care types
     */
    public void setCareTypes(String[] careTypes) {
    	this.careTypes = careTypes;
    }

	
    /**
     * Get all alerts for a patient
     * 
     * @return list of alerts
     */
    public List<Alert> getAlerts() {
    	return alerts;
    }

	
    /**
     * set alerts
     * 
     * @param alerts alerts for a patient
     */
    public void setAlerts(List<Alert> alerts) {
    	this.alerts = alerts;
    }
}
