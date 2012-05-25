package br.com.people.musicshop.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {

	private Integer id;
	private Cliente cliente;
	private Produto produto;
	private Date data;
	
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	public Venda() {
	}
	
	public Venda(Cliente cliente, Produto produto, Date date) {
		this.cliente = cliente;
		this.produto = produto;
		this.data = date;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return id + " / " + cliente + " / " + produto + " / " + 
				format.format(data);
	}
}