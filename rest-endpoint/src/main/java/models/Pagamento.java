package models;

import java.util.ArrayList;
import java.util.List;

public class Pagamento {
	
	private float valor;
	
	private List<String> tituloLivros = new ArrayList<>();
	
	public Pagamento(){
		
	}
	
	public Pagamento(float valor){
		this.valor = valor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public List<String> getTituloLivros() {
		return tituloLivros;
	}

	public void setTituloLivros(List<String> tituloLivros) {
		this.tituloLivros = tituloLivros;
	}
}
