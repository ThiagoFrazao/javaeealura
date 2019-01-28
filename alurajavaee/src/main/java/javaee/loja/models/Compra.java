package javaee.loja.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Compra {
	

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	private Usuario usuarioCompra;
	@Lob
	private String itensCompra;
	
	private String uuId;

	public Compra(){
		
	}
	public Compra(Usuario usuario) {
		this.usuarioCompra = usuario;
		this.itensCompra = "{}";
	}
	
	public Compra(Usuario usuario, String itens) {
		this.usuarioCompra = usuario;
		this.itensCompra = itens;
	}

	public Integer getId() {
		return id;
	}

	public Usuario getUsuarioCompra() {
		return usuarioCompra;
	}

	public void setUsuarioCompra(Usuario usuarioCompra) {
		this.usuarioCompra = usuarioCompra;
	}

	public String getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(String itensCompra) {
		this.itensCompra = itensCompra;
	}
	public String getUuId() {
		this.createUUID();
		return uuId;
	}
	@PrePersist
	public void createUUID() {
		this.uuId = UUID.randomUUID().toString();
	}
}
