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

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.OpenmrsService;

/**
 * Interface of the services provided by PatientPortal module
 * 
 * @author maurya
 */
public interface PatientPortalService extends OpenmrsService {
    
    
    /**
     * Defines all sorts of relation type
     * 
     */
    public enum PatientPortalRelationType {
        /**
         * Relation Type
         */
        DOCTOR("Doctor"), 
        /**
         * Relation Type
         */
        CAREGIVER("Caregiver"),
        /**
         * Relation Type
         */
        SPOUSE("Spouse"),
        /**
         * Relation Type
         */
        SIBLING("Sibling"),
        /**
         * Relation Type
         */
        CHILD("Child"),
        /**
         * Relation Type
         */
        OTHER("Other");
        
        private final String value;
        
        PatientPortalRelationType(final String type) {
            this.value = type;
        }
       
        
        /**
         * Get a string value of the relation type
         * 
         * @return string value of this relation type
         */
        public String getValue() {
            return value;
        }
    };
    
    
    /**
     * Defines all sorts of PatientPortal basic roles
     * 
     */
    public enum PatientPortalBasicRole {
        /**
         * Basic Role
         */
        PatientPortal_ADMINISTRATOR("PatientPortal Administrator"), 
        /**
         * Basic Role
         */
        PatientPortal_PATIENT("PatientPortal Patient"), 
        /**
         * Basic Role
         */
       PatientPortal_RESTRICTED_USER("PatientPortal Restricted User");
        
        private final String value;
        
        PatientPortalBasicRole(final String role) {
            this.value = role;
        }
        
        /**
         * Get the string representation of the basic role
         * 
         * @return string value of the basic role
         */
        public String getValue() {
            return value;
        }
    };
    
    /**
     * Define all sorts of PatientPortal basic privileges
     * 
     */
    public enum PatientPortalBasicPrivilege {        
        /**
         * Basic Privilege
         */
        PatientPortal_ADMINISTRATOR_PRIV("PatientPortal All Patients Access"), 
        /**
         * Basic Privilege
         */
        PatientPortal_PATIENT_PRIV("PatientPortal Single Patient Access"), 
        /**
         * Basic Privilege
         */
        PatientPortal_RESTRICTED_USER_PRIV("PatientPortal Restricted Patient Access");
        
        private final String value;
        
        PatientPortalBasicPrivilege(final String priv) {
            this.value = priv;
        }
        
        /**
         * String value of basic privilege
         * 
         * @return a string value of basic privilege
         */
        public String getValue() {
            return value;
        }
    };
    
    /**
     * Check if a given privilege is granted to a given user to access data of a given patient or person
     * 
     * @param privilege a given privilege checked for
     * @param requestedPatient patient whose data is contained in the request
     * @param requestedPerson person whose data is contained in the request
     * @param requestingUser user who made the request
     * @return true is this URL is allowed
     */
    public boolean hasPrivilege(String privilege, Patient requestedPatient, Person requestedPerson, User requestingUser);
    
    /**
     * Get a list of dynamic roles assigned to a given user to access data of a given patient or person
     * 
     * @param requestedPatient patient whose data is contained in the request
     * @param requestedPerson person whose data is contained in the request
     * @param requestingUser user who made the request
     * @return true is this URL is allowed
     */
    public List<String> getDynamicRoles(Patient requestedPatient, Person requestedPerson, User requestingUser);
    
    /**
     * Get the list persons who are related to a given person
     * 
     * @param person a given person object
     * @return a list of persons who have relationship with the given person
     */
    public List<Person> getRelatedPersons(Person person);
    
    /**
     * Log a specific event
     * 
     * @param eventType event type
     * @param eventDate event date
     * @param user user who generated this event
     * @param sessionId user session id
     * @param patient patient whose data is associated with this event
     * @param eventContent additional information of this event 
     */
    public void logEvent(String eventType, Date eventDate, User user, 
                         String sessionId, Patient patient, String eventContent);
   
    /**
     * Get PatientPortal role of a given user
     * 
     * @param user a given user
     * @return PatientPortal role as a string value
     */
    public String getPatientPortalRole(User user);
    
    /**
     * Get all sharing types
     * 
     * @return all sharing types
     */
    public Set<String> getSharingTypes();

    /**
     * Check if a given Person has a given basic role or not   
     * This check is based on PatientPortal security rule table and user role and relationship to given patient
     * 
     * @param person person whose privilege is checked
     * @param role basic role to check
     * @return true if the person has the given basic PatientPortal role
     */
    boolean hasBasicRole(Person person, PatientPortalBasicRole role);
    
}
