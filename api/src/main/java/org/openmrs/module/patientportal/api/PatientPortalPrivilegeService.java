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

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.patientportal.PatientPortalPrivilege;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface PatientPortalPrivilegeService extends OpenmrsService {

	@Transactional(readOnly = false)
	public PatientPortalPrivilege savePatientPortalPrivilege(PatientPortalPrivilege priv);

	@Transactional(readOnly = true)
	public List<PatientPortalPrivilege> getByPrivilege(String privilege);

	@Transactional(readOnly = true)
	public List<PatientPortalPrivilege> getAllPatientPortalPrivileges();

	@Transactional(readOnly = false)
	public void deletePatientPortalPrivilege(PatientPortalPrivilege PatientPortalPrivilege);
}
