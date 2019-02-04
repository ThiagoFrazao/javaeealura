package javaee.loja.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class SystemUser {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Email
	@NotBlank
	private String login;
	@NotBlank
	private String nome;
	@NotBlank
	private String senha;	
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<SystemRole> roles = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void addRole(SystemRole role){
		this.roles.add(role);
	}
	public List<SystemRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SystemRole> roles) {
		this.roles = roles;
	}
}
