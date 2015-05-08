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

import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.patientportal.PatientPortalSharingToken;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PatientPortalSharingTokenService extends OpenmrsService {

	@Transactional(readOnly = true)
	public PatientPortalSharingToken getSharingToken(Patient requestedPatient, Person requestedPerson, User user);

	@Transactional(readOnly = true)
	public List<PatientPortalSharingToken> getSharingTokenByPatient(Patient pat);

	@Transactional(readOnly = true)
	public List<PatientPortalSharingToken> getSharingTokenByPerson(Person person);

	@Transactional(readOnly = true)
	public PatientPortalSharingToken getSharingToken(String sharingToken);

	@Transactional(readOnly = false)
	public PatientPortalSharingToken savePatientPortalSharingToken(PatientPortalSharingToken token);

	@Transactional(readOnly = true)
	public PatientPortalSharingToken getPatientPortalSharingToken(Integer id);

	@Transactional(readOnly = false)
	public void deletePatientPortalSharingToken(PatientPortalSharingToken token);

	@Transactional(readOnly = false)
	public void deletePatientPortalSharingToken(Integer id);

	@Transactional(readOnly = false)
	public void updateSharingToken(User user, Person person, String sharingToken);
}
