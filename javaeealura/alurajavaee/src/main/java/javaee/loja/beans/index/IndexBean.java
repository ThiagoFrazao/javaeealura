package javaee.loja.beans.index;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import javaee.loja.dao.LivroDao;
import javaee.loja.models.Livro;

@Model
public class IndexBean {
	
	@Inject
	private LivroDao livroDao;	
	
	public List<Livro> ultimosLancamentos(){
		return livroDao.ultimosLancamentos();		
	}
	
	public List<Livro> todosLivros(){
		return livroDao.todosLivros();
	}
}
