package javaee.loja.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.loja.models.Usuario;

public class UsuarioDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5354816017391283212L;
	@PersistenceContext
	EntityManager usuarioManager;
	
	public void salvar(Usuario novoUsuario){
		usuarioManager.persist(novoUsuario);
	}

}
