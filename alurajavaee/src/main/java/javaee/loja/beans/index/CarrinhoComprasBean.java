package javaee.loja.beans.index;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import javaee.loja.dao.LivroDao;
import javaee.loja.models.CarrinhoCompras;
import javaee.loja.models.ItemVenda;
import javaee.loja.models.Livro;

@Model
public class CarrinhoComprasBean {
	
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private CarrinhoCompras carrinhoCompras;
	
	public String add(Integer idLivro){
		Livro livro = livroDao.buscaPorId(idLivro);
		carrinhoCompras.add(new ItemVenda(livro));
		String retorno = "/carrinho?faces-redirect=true";
		System.out.println("Estou retornando..." + retorno);
		return retorno;
	}
	
	public void remove(ItemVenda item){
		this.carrinhoCompras.remove(item);
	}
	
	public List<ItemVenda> getItens(){
		return this.carrinhoCompras.getItens();
	}
}
