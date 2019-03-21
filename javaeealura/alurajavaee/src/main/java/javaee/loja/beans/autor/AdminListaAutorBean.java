package javaee.loja.beans.autor;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import javaee.loja.dao.AutorDao;
import javaee.loja.models.Autor;

@Model
public class AdminListaAutorBean {
	
	@Inject
	private AutorDao autorDao;
	
	private List<Autor> autores = new ArrayList<>();
	
	public List<Autor> listaAutores(){
		this.autores = autorDao.listarAutores();
		return autores;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	} 
}
