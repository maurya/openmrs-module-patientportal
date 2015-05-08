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

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.patientportal.PatientPortalPrivilege;
import org.openmrs.module.patientportal.api.db.PatientPortalPrivilegeDAO;

/**
 * Hibernate implementation of the Data Access Object
 *
 * @author maurya
 */
public class HibernatePatientPortalPrivilegeDAO implements PatientPortalPrivilegeDAO {
	
	protected final Log log = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;


	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		
	}

	@Override
	public PatientPortalPrivilege getPatientPortalPrivilege(Integer id) {
		return (PatientPortalPrivilege) this.sessionFactory.getCurrentSession().get(PatientPortalPrivilege.class, id);
	}

	@Override
	public PatientPortalPrivilege savePatientPortalPrivilege(PatientPortalPrivilege priv) {
		sessionFactory.getCurrentSession().saveOrUpdate(priv);
		return priv;
	}

	@Override
	public void deletePatientPortalPrivilege(PatientPortalPrivilege priv) {
		sessionFactory.getCurrentSession().delete(priv);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatientPortalPrivilege> getAllPatientPortalPrivileges() {
		final Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientPortalPrivilege.class);
		crit.addOrder(Order.asc("privilege"));
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatientPortalPrivilege> getByPrivilege(String priv) {
		final Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientPortalPrivilege.class);
		crit.add(Restrictions.like("requiredRole", "%" + priv + "%"));
		crit.addOrder(Order.desc("requiredRole"));
		final List<PatientPortalPrivilege> list = crit.list();
		if (list.size() >= 1) {
			return list;
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PatientPortalPrivilege> getByRole(String role) {
		this.log.debug("PatientPortalServiceImpl:isUrlAllowed->" + role);
		//sessionFactory.getCurrentSession().createQuery("from PatientPortalPrivilege where privilege = 'View Treatment Summary' ").list();
		//Query query = sessionFactory.getCurrentSession().createQuery("from PatientPortalPrivilege where privilege = :url ");
		//query.setParameter("url", url);
		//List list0 = query.list();
		final Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(PatientPortalPrivilege.class);
		crit.add(Restrictions.eq("privilege", role));
		crit.addOrder(Order.desc("privilege"));
		final List<PatientPortalPrivilege> list = crit.list();
		if (list.size() >= 1) {
			return list;
		} else {
			return null;
		}	}

}
