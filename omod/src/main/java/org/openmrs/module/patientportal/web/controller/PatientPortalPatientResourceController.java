package org.openmrs.module.patientportal.web.controller;




import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import org.openmrs.User;
import org.openmrs.module.patientportal.api.PatientService;

import org.openmrs.module.webservices.rest.SimpleObject;

import org.openmrs.api.context.Context;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientPortalPatientResourceController {
	
	protected final Log log = LogFactory.getLog( getClass() );
	
	@RequestMapping( value = "/patientportal/getpatient/{patientId}")
    @ResponseBody
    public Object getPatientPortalPatient( @PathVariable( "patientId" ) String patientId)
        throws Exception
    {
		PatientService patientService = Context.getService(PatientService.class);
		SimpleObject patientObject = patientService.getPatient(patientId);
		return patientObject;
    }
	
	@RequestMapping( value = "/patientportal/getallpatients")
    @ResponseBody
    public Object getAllPatientPortalPatients()
        throws Exception
    {
		PatientService patientService = Context.getService(PatientService.class);
		List<SimpleObject> patientsObject = patientService.getAllPatients();
		return patientsObject;
    }
	
	@RequestMapping( value = "/patientportal/updatepatient", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePatientPortalPatient(@RequestBody String patientObject)
        throws Exception
    {
	       PatientService patientService = Context.getService(PatientService.class);
			SimpleObject updatedPatientObject = patientService.updatePatient(patientObject);
			return updatedPatientObject;   
	    
    }
	
	@RequestMapping( value = "/patientportal/getuser")
    @ResponseBody
    public Object getPatientPortalUser()
        throws Exception
    {
		User  user=Context.getAuthenticatedUser();
		SimpleObject authenticatedUser = new SimpleObject()
		 .add("id", user.getUuid())
		 .add("Name", user.getDisplayString())
		;
		
		return authenticatedUser;
    }
	

}
