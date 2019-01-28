package loja.servlet;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import javaee.loja.dao.CompraDao;
import javaee.loja.models.Compra;
import lojas.utils.ClientUtils;

@Path("/pagamento")
public class PagamentoService {
	
	@Inject
	private CompraDao compraDao;
	
	@POST
	public Response pagar(@QueryParam("uuid") String uuid){
		Compra compra = compraDao.buscaPorUuid(uuid);
		ClientUtils.pagarCompra(compra.getTotal());
		
		
		return null;
		
	}

}
