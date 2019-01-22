package javaee.loja.beans.livro;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import javaee.loja.dao.LivroDao;
import javaee.loja.models.Livro;

@Model
public class LivroDetalheBean {
	
	@Inject
	private LivroDao livroDao;
	
	private Livro livro = new Livro();
	private Integer idLivro;
	
	public void carregaDetalhe(){
		this.setLivro(livroDao.buscaPorId(getIdLivro()));
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Integer idLivro) {
		this.idLivro = idLivro;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
