package javaee.loja.beans.usuario;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import javaee.loja.dao.RolesDao;
import javaee.loja.models.SystemRole;

@Model
public class RolesControlerBean {
	
	@Inject
	private RolesDao rolesDao;
	
	private List<SystemRole> systemRoles;
	
	@Transactional
	public List<SystemRole> getSystemRoles(){
		this.systemRoles = this.rolesDao.getRoles();
		return this.systemRoles;
	}
}
