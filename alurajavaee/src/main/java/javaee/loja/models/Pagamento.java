package javaee.loja.models;

import java.util.ArrayList;
import java.util.List;

public class Pagamento {
	
	private float valor;
	
	
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
}
