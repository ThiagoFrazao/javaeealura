package javaee.loja.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Cacheable
public class Livro {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@DecimalMin(value="0.5")
	private float preco;
	@DecimalMin("50")
	private int numPaginas;
	
	@NotBlank
	private String titulo;
	@Lob
	private String descricao;	
	private String pathCapaImg;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataPublicacao;
	
	@NotNull
	@Size(min=1)	
	@ManyToMany
	private List<Autor> autores = new ArrayList<Autor>();
	

	public Integer getId() {
		return id;
	}	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	
	public List<Autor> getAutores() {
		return autores;
	}
	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}
	
	public Calendar getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(Calendar dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	public String getPathCapaImg() {
		return pathCapaImg;
	}
	public void setPathCapaImg(String pathCapaImg) {
		this.pathCapaImg = pathCapaImg;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Livro [id=" + getId() + ", preco=" + preco + ", numPaginas=" + numPaginas + ", titulo=" + titulo
				+ ", descricao=" + descricao + ", autores=" + autores + "]";
	}
}
