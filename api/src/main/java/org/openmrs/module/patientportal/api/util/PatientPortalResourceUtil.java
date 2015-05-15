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

package org.openmrs.module.patientportal.api.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonName;

import org.openmrs.api.context.Context;
import org.openmrs.module.webservices.rest.SimpleObject;

public class PatientPortalResourceUtil {
	
	public static final String PHONE_NUMBER_ATTRIBUTE = "Telephone Number";
	public static final String EMAIL_ATTRIBUTE = "Email";
	public static SimpleObject generatePerson(Person person) {
		
		PersonAddress personAddress = new PersonAddress();
		
		//get the latest address
		for(PersonAddress pa :person.getAddresses()){
			if (personAddress.isBlank())
				personAddress=pa;
			else if(pa.getDateCreated().after(personAddress.getDateCreated()))
				personAddress=pa;	
		}
		SimpleObject personAddressObject = new SimpleObject()
		 .add("id", personAddress.getUuid())
		 .add("Address1", personAddress.getAddress1())
		 .add("Address2", personAddress.getAddress2())
		 .add("City/Village", personAddress.getCityVillage())
		 .add("State/Province", personAddress.getStateProvince())
		 .add("Country", personAddress.getCountry())
		 .add("PostalCode", personAddress.getPostalCode())
		;
		
		
		SimpleObject personObject = new SimpleObject()
		.add("id", person.getUuid()) 
		.add("GivenName", person.getGivenName())
        .add("MiddleName", person.getMiddleName())
        .add("FamilyName", person.getFamilyName())
        .add("Age", person.getAge())
        .add("DOB", person.getBirthdate())
        .add("Gender", person.getGender())
        .add("Phone", person.getAttribute(PHONE_NUMBER_ATTRIBUTE).getValue())
      //  .add("Email", person.getAttribute(EMAIL_ATTRIBUTE).getValue())
        .add("Address", personAddressObject)
         ;
		 
		return personObject;
	}
	
public static SimpleObject generatePatient(Patient patient) {
		
	SimpleObject patientObject=generatePerson(patient);
		 
		return patientObject;
	}

public static String updatePatientObject(String patientObject) throws JsonProcessingException, IOException, ParseException{
	
	JsonFactory factory = new JsonFactory();
	 ObjectMapper mapper = new ObjectMapper(factory);
     JsonNode rootNode = mapper.readTree(patientObject);  
     if(rootNode.get("id").isNull())
  	   return null;
     String personId = rootNode.path("id").getTextValue();
     Person person =  Context.getPersonService().getPersonByUuid(personId);
     if(!rootNode.path("GivenName").isNull() || !rootNode.path("FamilyName").isNull()){
     PersonName personName = new PersonName(rootNode.path("GivenName").getTextValue(),rootNode.path("MiddleName").getTextValue(),rootNode.path("FamilyName").getTextValue());
     Set<PersonName> personNames = person.getNames();
     boolean personNameExists=false;
    
     
     for (PersonName pn: personNames){
  	   if(pn.equalsContent(personName)) 
  		   personNameExists=true;
     }
     if(!personNameExists){	  
  	   for (PersonName pn: personNames){
	    	   if(pn.getPreferred()) 
	    		   pn.setPreferred(false);
	       }
  	   personName.setPreferred(true);
  	   personNames.add(personName);
     }
     
     person.setNames(personNames);
     }
     if(!rootNode.get("DOB").isNull()){
     DateFormat df = new SimpleDateFormat();
     person.setBirthdate(df.parse(rootNode.path("DOB").getTextValue()));
     }
     if(!rootNode.get("Gender").isNull())
     person.setGender(rootNode.path("Gender").getTextValue());
     if(!rootNode.get("Phone").isNull())
     person.getAttribute("Telephone Number").setValue(rootNode.path("Phone").getTextValue());
     if(!rootNode.get("Email").isNull())
     person.getAttribute("Email").setValue(rootNode.path("Email").getTextValue());
    
     if(!rootNode.get("Address").isNull()){
     JsonNode addressNode =  rootNode.get("Address");
     PersonAddress personAddress = new PersonAddress();
     personAddress.setAddress1(addressNode.path("Address1").getTextValue());
     personAddress.setAddress2(addressNode.path("Address2").getTextValue());
     personAddress.setCityVillage(addressNode.path("City/Village").getTextValue());
     personAddress.setStateProvince(addressNode.path("State/Province").getTextValue());
     personAddress.setCountry(addressNode.path("Country").getTextValue());
     personAddress.setPostalCode(addressNode.path("PostalCode").getTextValue());
		boolean addressExists=false;
     Set<PersonAddress> personAddresses = person.getAddresses();
     
     for (PersonAddress pa: personAddresses){
  	   if(pa.equalsContent(personAddress)) 
  		   addressExists=true;
     }
     if(!addressExists)
     personAddresses.add(personAddress);
     
     person.setAddresses(personAddresses);
     }
     Context.getPersonService().savePerson(person);
     return personId;
}

}
