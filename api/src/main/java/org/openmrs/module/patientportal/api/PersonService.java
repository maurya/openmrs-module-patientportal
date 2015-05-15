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

import java.util.List;

import org.openmrs.api.OpenmrsService;
import org.openmrs.module.webservices.rest.SimpleObject;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PersonService extends OpenmrsService {
	
	/**
	 * Get person resource by uuid
	 *
	 * @param id uuid of the patient
	 * @return patient resource and will return null if patient not found for the given id
	 */
	SimpleObject getPerson(String id);

	/**
	 * Search persons by uuid
	 *
	 * @param id the uuid to be search
	 * @return patient resource list
	 */
	//List<Person> searchPersonById(String id);

	/**
	 * Search all persons for given attributes
	 *
	 * @param name Name of person to search
	 * @param birthYear The year of birth to restrict
	 * @param gender The gender field to search on (Typically just "M" or "F")
	 * @return persons list
	 */
	//List<Person> searchPersons(String name, Integer birthYear, String gender);

	/**
	 * Search persons by name
	 *
	 * @param name the name to be search
	 * @return persons resource list
	 */
	//List<Person> searchPersonsByName(String name);

	/**
	 * creates omrs person
	 *
	 * @param person
	 * @return person
	 */
	//Person createPerson(Person person);

	/**
	 * update a OpenMRS Person
	 *
	 * @param person to be updated
	 * @param the uuid of the Person to be updated
	 * @return the updated Person Resource
	 */
	//Person updatePerson(Person person, String theId);

	/**
	 * makes a Person retired
	 *
	 * @param the uuid of the Person to retire
	 * @should make person void
	 */
	//void retirePerson(String id);

}
