package javaee.loja.models;

public class ItemVenda {
	
	private Livro livroVenda;
	private Integer quantidade;
	
	public ItemVenda(){
		
	}
	
	public ItemVenda(Livro livro){
		this.livroVenda = livro;
		this.quantidade = 1;
	}
	
	public ItemVenda(Livro livro, Integer quantidade){
		this.livroVenda = livro;
		this.quantidade = quantidade;
	}
	
	public float valorVenda(){
		return this.livroVenda.getPreco() * this.quantidade;
	}	
	

	public Livro getLivroVenda() {
		return livroVenda;
	}
	public void setLivroVenda(Livro livroVenda) {
		this.livroVenda = livroVenda;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((livroVenda == null) ? 0 : livroVenda.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVenda other = (ItemVenda) obj;
		if (livroVenda == null) {
			if (other.livroVenda != null)
				return false;
		} else if (!livroVenda.equals(other.livroVenda))
			return false;
		return true;
	}
	
	

}
