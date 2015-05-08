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
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.logic.LogicContext;
import org.openmrs.logic.LogicException;
import org.openmrs.logic.Rule;
import org.openmrs.logic.result.Result;
import org.openmrs.logic.result.Result.Datatype;
import org.openmrs.logic.rule.RuleParameterInfo;

/**
 * Implementing follow up care alerting rule
 * 
 * @author maurya
 */
public class FollowupCareAlertRule implements Rule {
    protected final Log log = LogFactory.getLog(getClass());
	
	/**
	 * @see org.openmrs.logic.Rule#eval(org.openmrs.logic.LogicContext, java.lang.Integer, java.util.Map)
	 */
	@Override
	public Result eval(LogicContext context, Integer patientId, Map<String, Object> parameters) throws LogicException {
		log.debug("Entering FollowupCareAlertRule:eval -> patientId = " + patientId);
		
		Patient patient = context.getPatient(patientId);
		List<PatientPortalReminder> reminders = PatientPortalUtil.getService().getReminders(patient, context.getIndexDate());
		Result finalResult = new Result();
		for(PatientPortalReminder reminder : reminders) {
			String isDue = " is due on ";
			if(reminder.getTargetDate().before(context.getIndexDate())) {
				isDue = " was due on ";
			}
			reminder.getFollowProcedure().setRetireReason(reminder.getFollowProcedure().getDisplayString() + isDue + Context.getDateFormat().format(reminder.getTargetDate()));
			reminder.getFollowProcedure().setDateRetired(reminder.getTargetDate());
			Result result = new Result(reminder.getFollowProcedure() );
			finalResult.add(result);
		}
		return finalResult;		
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getParameterList()
	 */
	public Set<RuleParameterInfo> getParameterList() {
		return null;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDependencies()
	 */
	public String[] getDependencies() {
		return null;
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getTTL()
	 */
	public int getTTL() {
		return 60; // 1 minute
	}
	
	/**
	 * @see org.openmrs.logic.Rule#getDefaultDatatype()
	 */
	public Datatype getDefaultDatatype() {
		return Datatype.CODED;
	}

}
