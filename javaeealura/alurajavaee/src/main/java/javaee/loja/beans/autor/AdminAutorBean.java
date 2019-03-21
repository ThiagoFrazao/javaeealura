package javaee.loja.beans.autor;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import javaee.loja.dao.AutorDao;
import javaee.loja.models.Autor;

@Named
@RequestScoped
public class AdminAutorBean {
	
	@Inject
	private AutorDao autorDao;	
	@Inject
	private FacesContext context;
	private Autor autor = new Autor();
	
	@Transactional
	public String salvarAutor(){
		autorDao.salvarAutor(autor);
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Autor " + autor.getNome() + " cadastrador com sucesso."));
		
		return "/autor/listaAutores?faces-redirect=true";
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
