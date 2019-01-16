package javaee.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javaee.loja.models.Livro;

public class LivroDao {
	
	@PersistenceContext
	private EntityManager livroManager;	
	
	public void salvar(Livro livro){
		livroManager.persist(livro);
	}
	
	public List<Livro> listar(){
		String jpql = "select distinct(l) from Livro l join fetch l.autores";
		
		return livroManager.createQuery(jpql, Livro.class).getResultList();
	}
}
