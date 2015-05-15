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

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.codehaus.jackson.JsonProcessingException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.webservices.rest.SimpleObject;

public interface PatientService extends OpenmrsService {
	/**
	 * Get patient resource by uuid
	 *
	 * @param id uuid of the patient
	 * @return patient resource and will return null if patient not found for the given id
	 */
	SimpleObject getPatient(String id);

	/**
	 * update patient resource
	 *
	 * @param patientObject json provided by the client
	 * @return patient resource and will return null if patient not found for the given id
	 */
	SimpleObject updatePatient(String patientObject) throws JsonProcessingException, IOException, ParseException;
	
	/**
	 * get all patient resources
	 *
	 * 
	 * @return list of all patients
	 */
	List<SimpleObject> getAllPatients() ;

}
