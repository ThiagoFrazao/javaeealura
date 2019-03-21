package javaee.loja.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

import javaee.loja.dao.LivroDao;
import javaee.loja.models.Livro;

@Path("/livros")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class LivrosResources {
	
	@Inject
	private LivroDao livroDao;
	
	@GET	
	@Path("/lancamentos")
	@Wrapped(element="livros")
	public List<Livro> ultimosLancamentos(){		
		return livroDao.ultimosLancamentos();		
	}
}
