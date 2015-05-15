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

import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportal.api.PersonService;
import org.openmrs.module.patientportal.api.util.PatientPortalResourceUtil;
import org.openmrs.module.webservices.rest.SimpleObject;

public class PersonServiceImpl extends BaseOpenmrsService implements PersonService {

	@Override
	public SimpleObject getPerson(String personId) {
		Person person =  Context.getPersonService().getPersonByUuid(personId);
		if (person != null)
		return PatientPortalResourceUtil.generatePerson(person);
		
		return null;
	}
}
