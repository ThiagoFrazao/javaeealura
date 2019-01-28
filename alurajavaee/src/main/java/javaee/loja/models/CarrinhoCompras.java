package javaee.loja.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import javaee.loja.dao.CompraDao;
import javaee.loja.dao.UsuarioDao;
import lojas.utils.ClientUtils;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable {
	
	private static final long serialVersionUID = 6030311931713431183L;
	private Set<ItemVenda> itens = new HashSet<>();
	
	@Inject
	private CompraDao compraDao;
	@Inject
	private UsuarioDao usuarioDao;	
	
	
	public void add(ItemVenda novoItem){
		if(itens.contains(novoItem)){
			for(ItemVenda item : itens){
				if(item.equals(novoItem)){
					item.setQuantidade(item.getQuantidade() + 1);
				}
			}
		}
		else{
			itens.add(novoItem);
		}		
	}
	

	public String finalizar(Compra compra) {		
		compra.setItensCompra(this.toJson());
		//usuarioDao.salvar(usuario);
		compraDao.salvar(compra);
		ClientUtils.pagarCompra(this.totalCarrinho());
		
		return "/compra/finalizandoPagamento.xhtml?faces-redirect=true";
		
	}
	
	public List<String> getTitulosLivros(){
		List<String> retorno = new ArrayList<>();
		
		for(ItemVenda venda : itens){
			retorno.add(venda.getLivroVenda().getTitulo());
		}
		
		return retorno;
	}
	
	public String toJson(){
		JsonArrayBuilder builder = Json.createArrayBuilder();
		for (ItemVenda itemVenda : itens) {
			builder.add(Json.createObjectBuilder()
					        .add("titulo", itemVenda.getLivroVenda().getTitulo())
					        .add("preco", itemVenda.getLivroVenda().getPreco())
					        .add("quantidade", itemVenda.getQuantidade())
					        .add("total", this.totalCarrinho()));
		}
		return builder.build().toString();
	}
	
	public void remove(ItemVenda item) {
		this.itens.remove(item);
	}
	
	public float totalCarrinho(){
		return (float) itens.stream().mapToDouble(item -> (item.getLivroVenda().getPreco() * item.getQuantidade()) ).sum();
	}
	
	public int qntTotalItens(){		
		return itens.stream().mapToInt(item -> item.getQuantidade()).sum();
	}	
	
	public List<ItemVenda> getItens() {
		return new ArrayList<ItemVenda>(itens);
	}

	public void setItens(Set<ItemVenda> itens) {
		this.itens = itens;
	}

	
}
