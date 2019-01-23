package javaee.loja.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class CarrinhoCompras implements Serializable {
	private static final long serialVersionUID = 6030311931713431183L;
	private Set<ItemVenda> itens = new HashSet<>();
	
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
	
	public void remove(ItemVenda item) {
		this.itens.remove(item);
	}
	
	public float totalCarrinho(){
		return (float) itens.stream().mapToDouble(item -> item.getLivroVenda().getPreco()).sum();
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
