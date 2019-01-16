package javaee.loja.beans.livro;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import javaee.loja.dao.AutorDao;
import javaee.loja.dao.LivroDao;
import javaee.loja.models.Autor;
import javaee.loja.models.Livro;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	@Inject
	private FacesContext currentContext;
	
	private Livro livro = new Livro();
	private List<Integer> autoresId = new ArrayList<Integer>();	
	
	@Transactional
	public String salvar(){
		for(Integer autorId: autoresId){
			livro.getAutores().add(new Autor(autorId));
		}
		livroDao.salvar(getLivro());
		currentContext.getExternalContext().getFlash().setKeepMessages(true);
		currentContext.addMessage(null, new FacesMessage("Livro "+ livro.getTitulo()+ " cadastrado com sucesso."));
		return "/livros/listaLivros?faces-redirect=true";
	}	

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Autor> getAutores() {
		return autorDao.listarAutores();
	}

	public List<Integer> getAutoresId() {
		return autoresId;
	}

	public void setAutoresId(List<Integer> autoresId) {
		this.autoresId = autoresId;
	}
}
