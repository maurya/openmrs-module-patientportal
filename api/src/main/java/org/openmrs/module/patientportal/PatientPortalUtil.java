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

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientportal.api.PatientPortalGuidelinesService;


/**Some utility functions to assist the module
 *
 *@author maurya
 */
public class PatientPortalUtil {
	/** Logger for this class and subclasses */
	private final static Log log = LogFactory.getLog(PatientPortalUtil.class);
	
		
    public static PatientPortalGuidelinesService getService() {
        return Context.getService(PatientPortalGuidelinesService.class);
    }

    public static void main(final String[] args) {
    }
    
    public static Date clearDate(Date dateTime) {
    	if(dateTime == null) {
    		return null;
    	}
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date dateWithoutTime = cal.getTime();
        
        return dateWithoutTime;
    }
}
