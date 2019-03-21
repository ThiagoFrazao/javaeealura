package javaee.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.loja.models.SystemRole;

public class RolesDao {	

	@PersistenceContext
	private EntityManager rolesManager;

	public List<SystemRole> getRoles() {
		String query = "select role from SystemRole role";
		return this.rolesManager.createQuery(query,SystemRole.class).getResultList();
	}
}
