package javaee.loja.beans.index;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import javaee.loja.models.CarrinhoCompras;
import javaee.loja.models.Usuario;

@Model
public class CheckoutBean {
	
	@Inject
	private CarrinhoCompras carrinhoCompras;
	private Usuario usuario = new Usuario();
	
	@Transactional
	public void finalizar(){
		carrinhoCompras.finalizar(usuario);
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
		
}
