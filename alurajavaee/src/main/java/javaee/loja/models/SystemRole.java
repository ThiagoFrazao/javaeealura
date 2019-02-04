package javaee.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SystemRole {
	
	@Id	
	private String roleName;
	
	public SystemRole(){
		
	}
	
	public SystemRole(String nome){
		roleName = nome;		
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
