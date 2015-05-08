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

import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.patientportal.JournalEntry;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
public interface JournalEntryService extends OpenmrsService {

	/**
	 * @return all journal entries
	 * @should return all journal entries
	 */
	@Transactional(readOnly = true)
	public List<JournalEntry> getAllJournalEntries();

	/**
	 * @param entryId
	 * @return the journal entry with the corresponding entryId
	 * @should return entry with given id
	 */
	@Transactional(readOnly = true)
	public JournalEntry getJournalEntry(Integer entryId);
	
	/**
	 * @param p the person who wrote the journal entries
	 * @return all journal entries written by person p
	 * @should return entries written by supplied person
	 * @should return entries ordered by date
	 */
	@Transactional(readOnly = true)
	public List<JournalEntry> getJournalEntryForPerson(Person p, Boolean orderByDateDesc);
	
	/**
	 * @param p the person who wrote the journal entries
	 * @param searchText the text to searc hfor
	 * @return all journal entries written by person p
	 * @should return entries written by supplied person
	 * @should return entries ordered by date
	 * @should return entries with searchString in title or body
	 */
	@Transactional(readOnly = true)
	public List<JournalEntry> findEntries(String searchText, Person p, Boolean orderByDateDesc);
	
	/**
	 * Create or update journal entry
	 */
	@Transactional
	public void saveJournalEntry(JournalEntry entry) throws APIException;

	/**
	 * Delete journal entry
	 */
	@Transactional
	public void deleteJournalEntry(JournalEntry entry) throws APIException;

	/**
	 * Sets the deleted flag to true
	 */
	@Transactional
	public void softDelete(JournalEntry entry);
	
	/**
     * find all comments of a given entry
	 * @param entry parent entry of comments
	 * @return all comments of a given entry
     */
    @Transactional
    public List<JournalEntry> findComments(JournalEntry entry);	
}
