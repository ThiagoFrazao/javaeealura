package javaee.loja.dao;

import java.util.List;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import javaee.loja.models.Livro;

public class LivroDao {
	
	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager livroManager;	
	
	public void salvar(Livro livro){
		livroManager.persist(livro);
	}
	
	public List<Livro> listar(){
		String jpql = "select distinct(l) from Livro l join fetch l.autores";		
		return livroManager.createQuery(jpql, Livro.class).getResultList();
	}

	public List<Livro> ultimosLancamentos() {
		String jpql = "select distinct(l) from Livro l join fetch l.autores order by l.id desc";
		//pega apenas os ultimos 5 livros como sendo Ultimos Lancamentos
		return livroManager.createQuery(jpql, Livro.class)
						   .setMaxResults(5)
						   .getResultList();
	}
	

	public List<Livro> todosLivros() {
		String jpql = "select distinct(l) from Livro l join fetch l.autores order by l.id desc";		
		//pega apenas os ultimos 5 livros como sendo Ultimos Lancamentos
		return livroManager.createQuery(jpql, Livro.class)
						   .setFirstResult(5)
						   .getResultList();
	}

	public Livro buscaPorId(Integer idLivro) {
		//return livroManager.find(Livro.class, idLivro);
		String jpql = "select l from Livro l join fetch l.autores where l.id = :idLivro";
		return livroManager.createQuery(jpql,Livro.class).setParameter("idLivro", idLivro).getSingleResult();
	}
	
	public void limparCache(){
		Cache cache = livroManager.getEntityManagerFactory().getCache();
		cache.evictAll();
	}
}
