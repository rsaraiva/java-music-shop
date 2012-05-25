package br.com.people.musicshop.entity;

public class Produto {

	private Integer id;
	private Categoria categoria;
	private String nome;
	private Float valor;
	
	public Produto() {
	}
	
	public Produto(Integer id) {
		this.id = id;
	}

	public Produto(Categoria categoria, String nome, Float valor) {
		this.categoria = categoria;
		this.nome = nome;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Float getValor() {
		return valor;
	}
	
	public void setValor(Float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return id + " - " + nome + " - " + valor + " / " + categoria;
	}
}