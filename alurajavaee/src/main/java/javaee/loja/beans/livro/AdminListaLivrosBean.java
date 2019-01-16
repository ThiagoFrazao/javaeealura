package javaee.loja.beans.livro;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javaee.loja.dao.LivroDao;
import javaee.loja.models.Livro;

@Named
@RequestScoped
public class AdminListaLivrosBean {
	
	@Inject
	private LivroDao livroDao;
	
	private List<Livro> livros = new ArrayList<>();
	
	public List<Livro> listar(){
		this.livros = livroDao.listar();
		return livros;
	}	

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}
	

}
