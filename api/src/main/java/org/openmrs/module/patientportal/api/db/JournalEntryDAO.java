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

import org.openmrs.Person;
import org.openmrs.module.patientportal.JournalEntry;

public interface JournalEntryDAO {

    public void deleteJournalEntry(JournalEntry entry);

    public List<JournalEntry> getAllJournalEntries();

    public JournalEntry getJournalEntry(Integer entryId);

    public void saveJournalEntry(JournalEntry entry);

    public List<JournalEntry> getJournalEntryForPerson(Person p, Boolean orderByDateDesc);

    public List<JournalEntry> findEntries(String searchText, Person p, Boolean orderByDateDesc);

	public void softDelete(JournalEntry entry);
	
    public List<JournalEntry> findComments(JournalEntry entry);

}