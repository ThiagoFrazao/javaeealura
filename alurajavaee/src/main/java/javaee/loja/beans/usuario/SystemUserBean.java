package javaee.loja.beans.usuario;

import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import javaee.loja.dao.RolesDao;
import javaee.loja.dao.SecurityDao;
import javaee.loja.models.SystemRole;
import javaee.loja.models.SystemUser;

@Model
public class SystemUserBean {
	
	@Inject
	private HttpServletRequest request;
	@Inject
	private SecurityDao securityDao;
	@Inject
	private RolesDao roleDao;
	private SystemUser current = new SystemUser();
	
	@PostConstruct
	public void loadCurrentUser(){
		Principal principal =  request.getUserPrincipal();
		if(principal != null){
			String email = request.getUserPrincipal().getName();
			this.current = securityDao.findByLogin(email);
		}		
	}
	
	public boolean hasRole(String role){
		return request.isUserInRole(role);
	}	
	
	public void logout(){
		this.current = new SystemUser("");
	}
	
	@Transactional
	public void salvarUsuario(SystemUser systemUser){
		this.securityDao.salvar(systemUser);
	}
	
	public List<SystemRole> getRoles(){
		return this.roleDao.getRoles();
	}

	public SystemUser getCurrent() {
		return current;
	}

	public void setCurrent(SystemUser current) {
		this.current = current;
	}	
}
