package javaee.loja.beans.index;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import javaee.loja.models.CarrinhoCompras;
import javaee.loja.models.Compra;
import javaee.loja.models.Usuario;

@Model
public class CheckoutBean {
	
	@Inject
	private CarrinhoCompras carrinhoCompras;
	@Inject
	private FacesContext faceContext;
	private Usuario usuario = new Usuario();
	
	@Transactional
	public String  finalizar(){
		Compra compra = new Compra();
		compra.setUsuarioCompra(usuario);
		carrinhoCompras.finalizar(compra);		
		
		String contextName = faceContext.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse) faceContext.getExternalContext().getResponse();
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", contextName+"/services/pagamento?id="+compra.getUuId());
		return "/compra/finalizandoPagamento.xhtml?faces-redirect=true";
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
