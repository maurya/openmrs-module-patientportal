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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.patientportal.PatientPortalLogEvent;
import org.openmrs.module.patientportal.PatientPortalPrivilege;
import org.openmrs.module.patientportal.PatientPortalSharingToken;
import org.openmrs.module.patientportal.api.PatientPortalLogEventService;
import org.openmrs.module.patientportal.api.PatientPortalPrivilegeService;
import org.openmrs.module.patientportal.api.PatientPortalService;
import org.openmrs.module.patientportal.api.PatientPortalSharingTokenService;

public class PatientPortalServiceImpl  extends BaseOpenmrsService implements PatientPortalService {

	 protected final Log log = LogFactory.getLog(getClass());
	    
	@Override
	public boolean hasPrivilege(String privilege, Patient requestedPatient,
			Person requestedPerson, User requestingUser) {
		  this.log.debug("PatientPortalServiceImpl:hasPrivilege->" + privilege + "|" + requestedPatient + "|" + requestedPerson
	                + "|" + requestingUser);
	        
	        //Handle "PatientPortal Authenticated" and "PatientPortal Administrator" specially
	        String PatientPortalRole = this.getPatientPortalRole(requestingUser);
	        if((PatientPortalRole != null && "PatientPortal Authenticated".equalsIgnoreCase(privilege)) ||
	           (PatientPortalRole != null && "PatientPortal Administrator".equalsIgnoreCase(privilege) && 
	            "PatientPortal Authenticated".equalsIgnoreCase(PatientPortalRole))) {
	            this.log.debug("PatientPortalServiceImpl:hasPrivilege returns true: PatientPortalRole=" + PatientPortalRole);
	            return true;
	        }
	        
	        //When url privilege is not specified, or requested patient or person is null for sharing pages,
	        //always returns true
	        if(privilege == null || privilege.trim().isEmpty()) 
	        {
	            this.log.debug("PatientPortalServiceImpl:hasPrivilege returns true because no privilege is specified!");
	            return true;
	        }
	                
	        //When url privilege is specified, check the database for authorized roles
	        final List<PatientPortalPrivilege> rules = Context.getService(PatientPortalPrivilegeService.class).getByPrivilege(privilege);
	        final List<String> roles = getDynamicRoles(requestedPatient, requestedPerson, requestingUser);
	        if (rules != null) {
	            //for each required privilege/role
	            for (final PatientPortalPrivilege rule : rules) {
	                if (rule != null) {
	                    final String reqRole = rule.getRequiredRole().toUpperCase();
	                    
	                    if (roles != null) {
	                        //for each role held
	                        for (final String role : roles) {
	                            if (reqRole.contains(role.toUpperCase()) ||
	                                "Administrator".equalsIgnoreCase(role) ||
	                                ("Share All".equalsIgnoreCase(role) 
	                                 && !"PatientPortal Administrator".equalsIgnoreCase(reqRole)
	                                 && !"View Relationships".equalsIgnoreCase(privilege)
	                                 //&& !"View Messages".equalsIgnoreCase(privilege)
	                                 )) {
	                                this.log.debug("hasPrivilege returns true ->" + privilege + "|" + requestedPatient + "|"
	                                        + requestedPerson + "|" + requestingUser + "|reqRole=" + reqRole + "|role=" + role);
	                                return true; //held at least one required role
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        
	        this.log.debug("hasPrivilege returns false ->" + privilege + "|" + requestedPatient + "|" + requestedPerson + "|"
	                + requestingUser);
	        return false;  
	}

	  /**
     * Auto generated method comment
     * 
     * @param user
     * @param requestedPerson
     * @return
     */
    private boolean isSamePerson(final User user, final Person requestedPerson) {
        this.log.debug("PatientPortalServiceImpl:isSamePerson->" + user + "|" + requestedPerson);
        if ((user == null) || (requestedPerson == null)) {
            this.log.debug("isSamePerson(Person)=false ->" + user + "|" + requestedPerson);
            return false;
        }
        
        return user.getPerson().getPersonId().equals(requestedPerson.getPersonId());
    }
    
    
	@Override
	public List<String> getDynamicRoles(Patient requestedPatient,
			Person requestedPerson, User requestingUser) {
		 this.log.debug("PatientPortalServiceImpl:getDynamicRoles->" + requestedPatient + "|" + requestedPerson + "|" + requestingUser);
	        final List<String> roles = new ArrayList<String>();
	        
	        //check for administrator privilege
	        if (requestingUser.hasRole(PatientPortalBasicRole.PatientPortal_ADMINISTRATOR.getValue(), true)) {
	            roles.add("ADMINISTRATOR");
	            this.log.debug("getDynamicRoles->ADMINISTRATOR");
	        }
	        
	        //check for owner status
	        if ((requestedPatient==null && requestedPerson == null) || isSamePerson(requestingUser, requestedPatient) || isSamePerson(requestingUser, requestedPerson)) {
	            roles.add("OWNER");
	            this.log.debug("getDynamicRoles->OWNER");
	        } else {
	            //check for sharing authorization
	            final PatientPortalSharingToken token = Context.getService(PatientPortalSharingTokenService.class).getSharingToken(requestedPatient, requestedPerson, requestingUser);
	            
	            if (token != null) {
	                final String shareType = token.getShareType();
	                final String relationType = token.getRelationType();
	                if ((shareType != null) && !shareType.trim().isEmpty()) {
	                    this.log.debug("getDynamicRoles for shareType: " + shareType);
	                    roles.add(shareType.toUpperCase());
	                    roles.add(shareType.toUpperCase() + " & " + relationType);                    
	                }
	            }
	        }
	        
	        if (roles.isEmpty()) {
	            this.log.debug("getDynamicRoles returns null -> " + requestedPatient + "|" + requestedPerson + "|" + requestingUser);
	            return null;
	        }
	        
	        return roles;
	        	}

	@Override
	public List<Person> getRelatedPersons(Person person) {
		 final Patient pat = getPatient(person);
	        User usr = Context.getAuthenticatedUser();
	        boolean isAdmin = false;
	        if(usr != null) {
	            isAdmin = usr.hasRole(PatientPortalBasicRole.PatientPortal_ADMINISTRATOR.getValue(), false);
	        }
	        if(!isAdmin) {
	            if (pat != null) {
	                return getRelatedPersons(Context.getService(PatientPortalSharingTokenService.class).getSharingTokenByPatient(pat));
	            } else {
	                return getRelatedPatients(Context.getService(PatientPortalSharingTokenService.class).getSharingTokenByPerson(person));
	            }
	        } else{
	            return getAllPatientPortalUsers();
	        }
	}

	 /**
     * Get related persons from a given list of sharing tokens
     * 
     * @param tokens a list of sharing tokens
     * @return related persons
     */
    private List<Person> getRelatedPersons(final List<PatientPortalSharingToken> tokens) {
        final List<Person> persons = new ArrayList<Person>();
        for (final PatientPortalSharingToken token : tokens) {
            if(token.getRelatedPerson()!=null) {
                persons.add(token.getRelatedPerson());
            }
        }
        return persons;
    }
    
    /**
     * Get related patients from a given list of sharing tokens
     * 
     * @param tokens a list of sharing tokens
     * @return related patients
     */
    private List<Person> getRelatedPatients(final List<PatientPortalSharingToken> tokens) {
        final List<Person> persons = new ArrayList<Person>();
        for (final PatientPortalSharingToken token : tokens) {
            if(token.getPatient()!=null) {
                persons.add(token.getPatient());
            }
        }
        return persons;
    }    
    
    
    /**
     * Get all PatientPortal Users
     * 
     * @return person objects of all PatientPortal Users
     */
    private List<Person> getAllPatientPortalUsers() {
        final List<Person> persons = new ArrayList<Person>();
        final List<User> users = new ArrayList<User>();
        
        users.addAll(Context.getUserService().getUsersByRole(Context.getUserService().getRole(PatientPortalBasicRole.PatientPortal_ADMINISTRATOR.getValue())));
        users.addAll(Context.getUserService().getUsersByRole(Context.getUserService().getRole(PatientPortalBasicRole.PatientPortal_PATIENT.getValue())));
        users.addAll(Context.getUserService().getUsersByRole(Context.getUserService().getRole(PatientPortalBasicRole.PatientPortal_RESTRICTED_USER.getValue())));
        
        for (final User user : users) {
            if(user != null && user.getPerson()!=null) {
                persons.add(user.getPerson());
            }
        }
        return persons;
    }
    
    /**
     * Get patient object of a given person
     * 
     * @param person given person obhect
     * @return patient object
     */
    private Patient getPatient(final Person person) {
        // TODO Auto-generated method stub
        if (person != null) {
            return Context.getPatientService().getPatient(person.getPersonId());
        } else {
            return null;
        }
    }

	@Override
	public void logEvent(String eventType, Date eventDate, User user,
			String sessionId, Patient patient, String eventContent) {
		 User usr = user;
	        if(usr==null) {
	            usr = Context.getAuthenticatedUser();
	        }
	        
	        PatientPortalLogEvent eventLog = new PatientPortalLogEvent(eventType, eventDate, (usr==null ? null:usr.getUserId()),
	                                   sessionId, (patient==null?null:patient.getPatientId()), eventContent);
	        Context.getService(PatientPortalLogEventService.class).savePatientPortalEventLog(eventLog);

	}

	@Override
	public String getPatientPortalRole(User user) {
		this.log.debug("PatientPortalServiceImpl:igetPatientPortalRole->" + user);
        // TODO Auto-generated method stub
        if (user.hasRole(PatientPortalBasicRole.PatientPortal_ADMINISTRATOR.getValue(), true)) {
            return PatientPortalBasicRole.PatientPortal_ADMINISTRATOR.getValue();
        } else if (user.hasRole(PatientPortalBasicRole.PatientPortal_PATIENT.getValue(), true)) {
            return PatientPortalBasicRole.PatientPortal_PATIENT.getValue();
        } else if (user.hasRole(PatientPortalBasicRole.PatientPortal_RESTRICTED_USER.getValue(), true)) {
            return PatientPortalBasicRole.PatientPortal_RESTRICTED_USER.getValue();
        } else {
            return null;
        }
	}

	@Override
	public Set<String> getSharingTypes() {
		 List<PatientPortalPrivilege> privs = Context.getService(PatientPortalPrivilegeService.class).getAllPatientPortalPrivileges();
	        
	        TreeSet<String> types = new TreeSet<String>();
	        
	        for(PatientPortalPrivilege priv : privs) {
	            String[] roles = priv.getRequiredRole().toUpperCase().split(",");
	            for(String role : roles) {
	                if(role.trim().toUpperCase().startsWith("SHARE") && !role.trim().toUpperCase().endsWith("ALL") && !role.contains("&")) {
	                    types.add(role.trim().toUpperCase());
	                }
	            }            
	        }
	        return types;
	}

	@Override
	public boolean hasBasicRole(Person person, PatientPortalBasicRole role) {
		 this.log.debug("PatientPortalServiceImpl:hasBasicRole->" + person);
	        List<User> users = Context.getUserService().getUsersByRole(Context.getUserService().getRole(role.getValue()));
	        if(users==null) {
	            return false;
	        }
	        for(User u : users) {
	            if(u.getPerson().equals(person)) {
	                return true;
	            }
	        }
	        return false;
	}

}
