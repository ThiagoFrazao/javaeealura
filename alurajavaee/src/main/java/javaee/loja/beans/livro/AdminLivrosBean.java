package javaee.loja.beans.livro;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import javaee.loja.dao.AutorDao;
import javaee.loja.dao.LivroDao;
import javaee.loja.models.Autor;
import javaee.loja.models.Livro;
import lojas.utils.FileUtils;

@Named
@RequestScoped
public class AdminLivrosBean {
	
	
	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	@Inject
	private FacesContext currentContext;	
	
	
	//Variavel para imagens e outros arquivos
	private Part imgCapa;
	
	private Livro livro = new Livro();
	
	@Transactional
	public String salvar() throws IOException{
		livroDao.salvar(getLivro());
		livro.setPathCapaImg(FileUtils.getPathImg(imgCapa));
		
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

	public Part getImgCapa() {
		return imgCapa;
	}

	public void setImgCapa(Part imgCapa) {
		this.imgCapa = imgCapa;
	}
}
