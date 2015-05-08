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

package org.openmrs.module.patientportal.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportal.PatientPortalLogEvent;
import org.openmrs.module.patientportal.api.PatientPortalLogEventService;
import org.openmrs.module.patientportal.api.db.PatientPortalLogEventDAO;
import org.springframework.transaction.annotation.Transactional;

public class PatientPortalLogEventServiceImpl extends BaseOpenmrsService implements
		PatientPortalLogEventService {
	
	PatientPortalLogEventDAO dao;
	
	public void setDao(PatientPortalLogEventDAO dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(readOnly = false)
	public PatientPortalLogEvent savePatientPortalEventLog(PatientPortalLogEvent eventLog) {
		return dao.savePatientPortalEventLog(eventLog);
	}

}
