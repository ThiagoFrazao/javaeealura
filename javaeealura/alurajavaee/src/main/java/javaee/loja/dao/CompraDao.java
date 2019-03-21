package javaee.loja.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.loja.models.Compra;

public class CompraDao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8215283902244402401L;
	@PersistenceContext
	EntityManager compraManager;

	public void salvar(Compra compra) {
		compraManager.persist(compra);
	}

	public Compra buscaPorUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

}
