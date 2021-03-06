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

import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportal.PatientPortalPrivilege;
import org.openmrs.module.patientportal.api.PatientPortalPrivilegeService;
import org.openmrs.module.patientportal.api.db.PatientPortalPrivilegeDAO;
import org.springframework.transaction.annotation.Transactional;

public class PatientPortalPrivilegeServiceImpl extends BaseOpenmrsService implements 
		PatientPortalPrivilegeService {
	
	private PatientPortalPrivilegeDAO dao;

	public void setDao(PatientPortalPrivilegeDAO dao) {
		this.dao = dao;
	}

	
	@Override
	@Transactional(readOnly = false)
	public PatientPortalPrivilege savePatientPortalPrivilege(PatientPortalPrivilege priv) {
		return dao.savePatientPortalPrivilege(priv);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PatientPortalPrivilege> getByPrivilege(String privilege) {
		return dao.getByPrivilege(privilege);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PatientPortalPrivilege> getAllPatientPortalPrivileges() {
		return dao.getAllPatientPortalPrivileges();
	}

	@Override
	@Transactional(readOnly = false)
	public void deletePatientPortalPrivilege(PatientPortalPrivilege PatientPortalPrivilege) {
		dao.deletePatientPortalPrivilege(PatientPortalPrivilege);

	}

}
