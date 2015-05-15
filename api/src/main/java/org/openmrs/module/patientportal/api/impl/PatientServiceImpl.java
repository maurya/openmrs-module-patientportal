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

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportal.api.PatientService;
import org.openmrs.module.patientportal.api.util.PatientPortalResourceUtil;
import org.openmrs.module.webservices.rest.SimpleObject;

public class PatientServiceImpl extends BaseOpenmrsService implements PatientService {
	
	@Override
	public SimpleObject getPatient(String patientId){
		Patient patient= Context.getPatientService().getPatientByUuid(patientId);
		if (patient != null)
		return PatientPortalResourceUtil.generatePatient(patient);
		
		return null;
	}

	@Override
	public SimpleObject updatePatient(String patientObject) throws JsonProcessingException, IOException, ParseException {
		if (patientObject != null){
		 String patientId=PatientPortalResourceUtil.updatePatientObject(patientObject);
		 return getPatient(patientId);
		 }
		
		return null;
	}

	@Override
	public List<SimpleObject> getAllPatients() {
		List<SimpleObject> patientPortalPatients = new ArrayList<SimpleObject>();
		List<Patient> omrsPatients = Context.getPatientService().getAllPatients();
		for(Patient p: omrsPatients){
			patientPortalPatients.add(getPatient(p.getUuid()));
		}
		return patientPortalPatients;
	}
}
