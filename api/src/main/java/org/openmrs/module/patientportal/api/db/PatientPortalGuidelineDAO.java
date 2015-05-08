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


package org.openmrs.module.patientportal.api.db;

import java.util.List;

import org.openmrs.Concept;
import org.openmrs.module.patientportal.PatientPortalGuideline;

/**DAO Interface to retrieve data from the database
*
*@author maurya
*/

public interface PatientPortalGuidelineDAO {
	
	/**
     * Auto generated method comment
     * 
     * @param id
     * @return
     */
	PatientPortalGuideline getPatientPortalGuideline(Integer id);

	/**
     * Auto generated method comment
     * 
     * @param guideline
     * @return
     */
	PatientPortalGuideline savePatientPortalGuideline(PatientPortalGuideline guideline);

	/**
     * Auto generated method comment
     * 
     * @param guideline
     */
    void deletePatientPortalGuideline(PatientPortalGuideline guideline);

	/**
     * Auto generated method comment
     * 
     * @return
     */
    List<PatientPortalGuideline> getAllPatientPortalGuidelines();

	/**
     * Auto generated method comment
     * 
     * @param cancerType
     * @param cancerStage
     * @return
     */
    List<PatientPortalGuideline> getPatientPortalGuideline(Concept cancerType, Concept cancerStage);

}
