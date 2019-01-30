package javaee.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import javaee.loja.models.Autor;

public class AutorDao {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager autorManager;
	
	public List<Autor> listarAutores(){
		return autorManager.createQuery("select atores from Autor atores", Autor.class).getResultList();
	}
	
	public void salvarAutor(Autor novoAutor){
		autorManager.persist(novoAutor);
	}

}
