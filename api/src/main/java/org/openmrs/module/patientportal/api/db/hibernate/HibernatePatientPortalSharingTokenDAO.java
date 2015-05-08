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

package org.openmrs.module.patientportal.api.db.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.patientportal.PatientPortalSharingToken;
import org.openmrs.module.patientportal.api.db.PatientPortalSharingTokenDAO;

/**
 * Hibernate implementation of the Data Access Object
 *
 * @author maurya
 */
public class HibernatePatientPortalSharingTokenDAO implements PatientPortalSharingTokenDAO{
	
	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

	@Override
	public PatientPortalSharingToken getPatientPortalSharingToken(Integer id) {
		return (PatientPortalSharingToken) this.sessionFactory.getCurrentSession().get(PatientPortalSharingToken.class, id);	}

	@Override
	public PatientPortalSharingToken savePatientPortalSharingToken(
			PatientPortalSharingToken token) {
		sessionFactory.getCurrentSession().saveOrUpdate(token);
		return token;
	}

	@Override
	public void deletePatientPortalSharingToken(PatientPortalSharingToken token) {
		sessionFactory.getCurrentSession().delete(token);
		
	}

	@Override
	public List<PatientPortalSharingToken> getAllPatientPortalSharingTokens() {
		final Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientPortalSharingToken.class);
		crit.addOrder(Order.asc("patient_id"));
		this.log.debug("HibernatePatientPortalSharingTokenDAO:getAllPatientPortalSharingTokens->" + " | token count=" + crit.list().size());
		return crit.list();
	}

	@Override
	public List<PatientPortalSharingToken> getSharingTokenByPatient(Patient pat) {
		final Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientPortalSharingToken.class);
		crit.add(Restrictions.eq("patient", pat));
		crit.addOrder(Order.desc("dateCreated"));
		final List<PatientPortalSharingToken> list = crit.list();
		this.log.debug("HibernatePatientPortalSharingTokenDAO:getSharingTokenByPatient->" + pat + " | token count=" + list.size());
		if (list.size() >= 1) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public List<PatientPortalSharingToken> getSharingTokenByPerson(Person per) {
		if (per instanceof Patient) {
			return getSharingTokenByPatient((Patient) per);
		}

		final Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientPortalSharingToken.class);
		crit.add(Restrictions.eq("relatedPerson", per));
		crit.addOrder(Order.desc("dateCreated"));
		final List<PatientPortalSharingToken> list = crit.list();
		this.log.debug("HibernatePatientPortalSharingTokenDAO:getSharingTokenByPerson->" + per + " | token count=" + list.size());
		if (list.size() >= 1) {
			return list;
		} else {
			return null;
		}
	}

	@Override
	public PatientPortalSharingToken getSharingToken(Patient requestedPatient,
			Person requestedPerson, User requestingUser) {
		Patient pat = requestedPatient;

		if ((pat == null) && (requestedPerson != null)) {
			pat = Context.getPatientService().getPatient(requestedPerson.getPersonId());
			this.log.debug("getSharingToken for person|patient->" + requestedPerson + "|" + pat);
		}

		Person per = requestingUser.getPerson();

		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientPortalSharingToken.class);
		crit.add(Restrictions.eq("relatedPerson", per));
		crit.add(Restrictions.eq("patient", pat));
		crit.addOrder(Order.desc("dateCreated"));

		List<PatientPortalSharingToken> list = crit.list();

		this.log.debug("HibernatePatientPortalSharingTokenDAO:getSharingToken->" + requestedPatient + "|" + requestedPerson + "|"
				+ requestingUser + "|token count=" + list.size());

		if (list.size() >= 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void deletePatientPortalSharingToken(Integer id) {
		deletePatientPortalSharingToken(getPatientPortalSharingToken(id));
	}

	@Override
	public PatientPortalSharingToken getSharingToken(String tokenString) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(PatientPortalSharingToken.class);
		crit.add(Restrictions.eq("sharingToken", tokenString));
		final List<PatientPortalSharingToken> list = crit.list();
		this.log.debug("HibernatePatientPortalSharingTokenDAO:getSharingToken->" + tokenString + "|token count=" + list.size());
		if (list.size() >= 1) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void updateSharingToken(User user, Person person, String sharingToken) {
		final PatientPortalSharingToken token = getSharingToken(sharingToken);
		if (token != null) {
			final Date date = new Date();

			if (token.getExpireDate().after(date)) {
				if (token.getRelatedPerson() == null) {
					token.setRelatedPerson(person);
					token.setChangedBy(user);
					token.setDateChanged(date);
					token.setActivateDate(date);
					savePatientPortalSharingToken(token);
					this.log.debug("Sharing token updated: " + token.getId());
				} else {
					this.log.debug("Sharing token is igored because it was activated before by: " + token.getChangedBy()
							+ " at " + token.getActivateDate());
				}
			} else {
				this.log.debug("Sharing token is ignored because it expired at " + token.getExpireDate());
			}
		} else {
			this.log.debug("Sharing token is ignored because it is invalid: " + sharingToken);
		}

	}

}
