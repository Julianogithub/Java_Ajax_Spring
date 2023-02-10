package com.JMRocha.demoajax.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo", nullable = false, unique = true)
	private String titulo;
	
	@OneToMany(mappedBy = "categoria") // Uma Categoria podera ter N Promoções
	private List<Promocao> promocoes;  // Relacionamento de dados ( 1 para *N )	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Promocao> getPromocoes() {
		return promocoes;
	}

	public void setPromocoes(List<Promocao> promocoes) {
		this.promocoes = promocoes;
	}

	@Override
	public String toString() {
		return "\n\tCategoria \n\tid=" + id + ",\n\t titulo=" + titulo + "\n\n";
	}
}
