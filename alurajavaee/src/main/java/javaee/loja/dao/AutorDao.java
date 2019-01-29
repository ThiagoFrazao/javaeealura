package javaee.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.QueryHints;

import javaee.loja.models.Autor;

public class AutorDao {
	
	@PersistenceContext
	private EntityManager autorManager;
	
	public List<Autor> listarAutores(){
		return autorManager.createQuery("select atores from Autor atores", Autor.class).setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
	}
	
	public void salvarAutor(Autor novoAutor){
		autorManager.persist(novoAutor);
	}

}
