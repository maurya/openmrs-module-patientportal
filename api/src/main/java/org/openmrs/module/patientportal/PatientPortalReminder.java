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

import java.util.Comparator;
import java.util.Date;

import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientportal.PatientPortalUtil;;

/**Represent a single follow up care reminder for a patient with or without various indicators
 *  
 *  
 * @author maurya
 */
public class PatientPortalReminder {

	/**
	 * Response type
	 */
	public static final String RESPONSE_COMPLETED = "Completed";
	/**
	 * Response type
	 */
	public static final String RESPONSE_SKIPPED = "Skipped";
	/**
	 * Various flags used for calendar display
	 */
	public static final String FLAG_NOT_PERFORMED_YES = "NOT PERFORMED: YES"; //removed its display from follow-up care calendar
	/**
	 * Various flags used for calendar display
	 */
	public static final String FLAG_NOT_PERFORMED_NO = "NOT PERFORMED: NO";
	/**
	 * Various flags used for calendar display
	 */
	public static String FLAG_COMPLETED = "COMPLETED";
	/**
	 * Various flags used for calendar display
	 */
	public static String FLAG_SCHEDULED = "SCHEDULED";
	/**
	 * Various flags used for calendar display
	 */
	public static String FLAG_SNOOZED = "SNOOZED"; //removed its entries from database
	/**
	 * Various flags used for calendar display
	 */
	public static String FLAG_ALERTED = "NEXT DUE";
	/**
	 * Various flags used for calendar display
	 */
	public static String FLAG_SKIPPED = "SKIPPED"; //removed its display from follow-up care calendar

	private Integer id;	
	private Patient patient;
	private Concept followProcedure;
	private String followProcedureName;
	private Date responseDate;
	private String responseType; //follow-up care results
	private String responseAttributes;
	private String responseComments;
	private User responseUser;
	private Date targetDate;
	private Date CompleteDate;
	private String doctorName; 
	private String flag;
    
    /**
     * Get response date in a given format
     * 
     * @return formated response date
     */
    public String getResponseDateFormated() {
    	return (responseDate==null? null : Context.getDateFormat().format(responseDate));
    }

    /**
     * Get target date in a given format
     * 
     * @return formatted target date
     */
    public String getTargetDateFormated() {
    	return (targetDate==null? null : Context.getDateFormat().format(targetDate));
    }
    
	

	/**
     * Sort by start_date
     * 
     * @return date comparator
     */
    public static Comparator<PatientPortalReminder> getDateComparator() {
        return new Comparator<PatientPortalReminder>() {
            
        	//in ascending order
            @Override 
            public int compare(final PatientPortalReminder g1, final PatientPortalReminder g2) {
                return (g2.getTargetDate()==null||g1.getTargetDate()==null) ? 1 : g1.getTargetDate().compareTo(g2.getTargetDate());
            }
        };
    }
    
    /**
     * get reminder id
     * 
     * @return id
     */
    public Integer getId() {
    	return id;
    }
	
    public void setId(Integer id) {
    	this.id = id;
    }
	
    public Patient getPatient() {
    	return patient;
    }
	
    public void setPatient(Patient patient) {
    	this.patient = patient;
    }
	
    public Concept getFollowProcedure() {
    	return followProcedure;
    }
	
    public void setFollowProcedure(Concept followProcedure) {
    	this.followProcedure = followProcedure;
    }
	
    public Date getResponseDate() {
    	return responseDate;
    }
	
    public void setResponseDate(Date responseDate) {
    	this.responseDate = responseDate;
    }
	
    public String getResponseType() {
    	return responseType;
    }
	
    public void setResponseType(String responseType) {
    	this.responseType = responseType;
    }
	
    public String getResponseAttributes() {
    	return responseAttributes;
    }
	
    public void setResponseAttributes(String responseAttributes) {
    	this.responseAttributes = responseAttributes;
    }
	
    public String getResponseComments() {
    	return responseComments;
    }
	
    public void setResponseComments(String responseComments) {
    	this.responseComments = responseComments;
    }
	
    public User getResponseUser() {
    	return responseUser;
    }
	
    
    public String getFlag() {
    	return flag;
    }

	
    public void setFlag(String flag) {
    	this.flag = flag;
    }

	public void setResponseUser(User responseUser) {
    	this.responseUser = responseUser;
    }

	
    public Date getTargetDate() {    	
    	return PatientPortalUtil.clearDate(targetDate);
    }

	
    public void setTargetDate(Date targetDate) {
    	this.targetDate = PatientPortalUtil.clearDate(targetDate);
    }

	
    public Date getCompleteDate() {
    	return PatientPortalUtil.clearDate(CompleteDate);
    }

	public void setCompleteDate(Date completeDate) {
    	CompleteDate = PatientPortalUtil.clearDate(completeDate);
    }	
    
    public String getDoctorName() {
    	this.doctorName = responseAttributes;
    	return doctorName;
    }

	
    public void setDoctorName(String doctorName) {
    	this.doctorName = doctorName;
    	this.responseAttributes = doctorName;
    }

	
    public String getFollowProcedureName() {
    	if(followProcedureName == null && followProcedure != null) {
    		followProcedureName = followProcedure.getName().getName();
    	}
    	return followProcedureName;
    }

	
    public void setFollowProcedureName(String followProcedureName) {
    	this.followProcedureName = followProcedureName;
    }
}
