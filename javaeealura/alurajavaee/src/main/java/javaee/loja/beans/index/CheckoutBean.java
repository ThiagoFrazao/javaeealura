package javaee.loja.beans.index;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import javaee.loja.models.CarrinhoCompras;
import javaee.loja.models.Compra;
import javaee.loja.models.Usuario;

@Model
public class CheckoutBean {
	
	@Inject
	private CarrinhoCompras carrinhoCompras;
	private Usuario usuario = new Usuario();
	
	@Transactional
	public void  finalizar(){
		Compra compra = new Compra();
		compra.setUsuarioCompra(usuario);
		carrinhoCompras.finalizar(compra);			
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public CarrinhoCompras getCarrinhoCompras() {
		return carrinhoCompras;
	}
	public void setCarrinhoCompras(CarrinhoCompras carrinhoCompras) {
		this.carrinhoCompras = carrinhoCompras;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}		
	
}
