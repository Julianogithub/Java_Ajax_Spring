package com.JMRocha.demoajax.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@SuppressWarnings("serial")
@Entity
@Table(name = "promocoes")
public class Promocao implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "titulo", nullable = false) //Campo obrigatori na aplicação ( nullable = false )
	private String titulo;
	
	@Column(name = "link_promocao", nullable = false)
	private String linkPromocao;
	
	@Column(name = "site_promocao", nullable = false)
	private String site;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "link_imagem", nullable = false)
	private String linkImagem;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(name = "preco_promocao", nullable = false)
	private BigDecimal preco;
	
	@Column(name = "total_likes")
	private int likes;
	
	@Column(name = "data_cadastro", nullable = false)
	private LocalDateTime dtCadastro;
	
	// Relacionamento entre classes ( *N promoção para uma 1 Categoria )
	@ManyToOne // muitas promoção para uma categoria 
	@JoinColumn(name = "categoria_fk") // Relacionamento entre tabelas no banco
	private Categoria categoria;

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

	public String getLinkPromocao() {
		return linkPromocao;
	}

	public void setLinkPromocao(String linkPromocao) {
		this.linkPromocao = linkPromocao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLinkImagem() {
		return linkImagem;
	}

	public void setLinkImagem(String linkImagem) {
		this.linkImagem = linkImagem;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "\n\tPromocao \n\t{\n\t|Id = " + id + ",\n\t|Titulo = " + titulo + ",\n\t|Link de Promocao = " + linkPromocao + 
				",\n\t|Site = " + site + ",\n\t|Descricao = " + descricao + ",\n\t|Link da Imagem = " + linkImagem + 
				",\n\t|Preco = " + preco + ",\n\t|Likes = " + likes	+ ",\n\t|Data do cadastro Cadastro = " + dtCadastro + 
				",\n\t|Categoria = " + categoria + "\n\t}";
	}	
}