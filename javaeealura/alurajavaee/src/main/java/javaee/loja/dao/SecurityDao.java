package javaee.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.loja.models.SystemUser;

public class SecurityDao {
	
	@PersistenceContext
	private EntityManager securityManager;	

	public SystemUser findByLogin(String login) {
		return securityManager.createQuery("select su from SystemUser su where su.login = :login",SystemUser.class)
				              .setParameter("login", login).getSingleResult();
	}

	public void salvar(SystemUser systemUser) {
		this.securityManager.persist(systemUser);		
	}
}
