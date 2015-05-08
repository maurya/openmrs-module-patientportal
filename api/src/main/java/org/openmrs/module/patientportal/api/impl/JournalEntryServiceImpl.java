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

import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportal.JournalEntry;
import org.openmrs.module.patientportal.api.JournalEntryService;
import org.openmrs.module.patientportal.api.db.JournalEntryDAO;

/**
 *
 */
public class JournalEntryServiceImpl extends BaseOpenmrsService implements JournalEntryService {
	
	protected JournalEntryDAO dao;
	
	public void setJournalEntryDAO(JournalEntryDAO dao){
		this.dao = dao;
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.JournalEntryService#deleteJournalEntry(org.openmrs.module.phrjournal.domain.JournalEntry)
	 */
	public void deleteJournalEntry(JournalEntry entry) throws APIException {
		dao.deleteJournalEntry(entry);
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.JournalEntryService#getAllJournalEntries()
	 */
	public List<JournalEntry> getAllJournalEntries() {
		return dao.getAllJournalEntries();
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.JournalEntryService#getJournalEntry(java.lang.Integer)
	 */
	public JournalEntry getJournalEntry(Integer entryId) {
		return dao.getJournalEntry(entryId);
	}
	
	/**
	 * @see org.openmrs.module.phrjournal.JournalEntryService#saveJournalEntry(org.openmrs.module.phrjournal.domain.JournalEntry)
	 */
	public void saveJournalEntry(JournalEntry entry) throws APIException {
		dao.saveJournalEntry(entry);
	}

	/**
     * @see org.openmrs.module.phrjournal.JournalEntryService#getJournalEntryForPerson(org.openmrs.Person)
     */
    public List<JournalEntry> getJournalEntryForPerson(Person p, Boolean orderByDateDesc ) {
	    return dao.getJournalEntryForPerson(p, orderByDateDesc);
    }

	/**
     * @see org.openmrs.module.phrjournal.JournalEntryService#findEntries(java.lang.String, org.openmrs.Person, java.lang.Boolean)
     */
    public List<JournalEntry> findEntries(String searchText, Person p, Boolean orderByDateDesc) {
	    return dao.findEntries(searchText,p,orderByDateDesc);
    }
    
	public void softDelete(JournalEntry entry) {
		dao.softDelete(entry);
	}

	public List<JournalEntry> findComments(JournalEntry entry){
        return dao.findComments(entry);	    
	}
	
}
