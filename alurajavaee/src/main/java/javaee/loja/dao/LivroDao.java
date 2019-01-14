package javaee.loja.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.loja.models.Livro;

public class LivroDao {
	
	@PersistenceContext
	private EntityManager livroManager;	
	
	public void salvar(Livro livro){
		livroManager.persist(livro);
	}
}
