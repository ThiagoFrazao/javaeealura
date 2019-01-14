package javaee.loja.beans;

import javax.enterprise.context.RequestScoped;
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
	private Autor autor = new Autor();
	
	@Transactional
	public void salvarAutor(){
		autorDao.salvarAutor(autor);
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
