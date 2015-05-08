package org.openmrs.module.patientportal.web.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientPortalPatientResourceController {
	
	protected final Log log = LogFactory.getLog( getClass() );
	
	@RequestMapping( value = "/patientportal/getpatient/{patientId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getPatientPortalPatient( @PathVariable( "patientId" ) String patientId)
        throws Exception
    {
		
		 Patient patient =  Context.getPatientService().getPatientByUuid(patientId);
		 
		return patient;
    }

}
