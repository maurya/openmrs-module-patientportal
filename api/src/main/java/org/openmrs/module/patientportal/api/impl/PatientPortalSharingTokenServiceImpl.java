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

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportal.PatientPortalSharingToken;
import org.openmrs.module.patientportal.api.PatientPortalSharingTokenService;
import org.openmrs.module.patientportal.api.db.PatientPortalSharingTokenDAO;
import org.springframework.transaction.annotation.Transactional;

public class PatientPortalSharingTokenServiceImpl  extends BaseOpenmrsService implements
		PatientPortalSharingTokenService {

	PatientPortalSharingTokenDAO dao;

	public void setDao(PatientPortalSharingTokenDAO dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(readOnly = true)
	public PatientPortalSharingToken getSharingToken(Patient requestedPatient,
			Person requestedPerson, User user) {
		return dao.getSharingToken(requestedPatient, requestedPerson, user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PatientPortalSharingToken> getSharingTokenByPatient(Patient pat) {
		return dao.getSharingTokenByPatient(pat);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PatientPortalSharingToken> getSharingTokenByPerson(Person person) {
		return dao.getSharingTokenByPerson(person);
	}

	@Override
	@Transactional(readOnly = true)
	public PatientPortalSharingToken getSharingToken(String sharingToken) {
		return dao.getSharingToken(sharingToken);
	}

	@Override
	@Transactional(readOnly = false)
	public PatientPortalSharingToken savePatientPortalSharingToken(
			PatientPortalSharingToken token) {
		return dao.savePatientPortalSharingToken(token);
	}

	@Override
	@Transactional(readOnly = true)
	public PatientPortalSharingToken getPatientPortalSharingToken(Integer id) {
		return dao.getPatientPortalSharingToken(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePatientPortalSharingToken(PatientPortalSharingToken token) {
		dao.deletePatientPortalSharingToken(token);
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePatientPortalSharingToken(Integer id) {
		dao.deletePatientPortalSharingToken(id);

	}

	@Override
	@Transactional(readOnly = false)
	public void updateSharingToken(User user, Person person, String sharingToken) {
		dao.updateSharingToken(user, person, sharingToken);

	}

}
