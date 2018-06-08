package br.com.cdc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Venda {
	@Id @GeneratedValue
	private Long id;
	private String numero;
	private String produto;
	private String cliente;
	
	public Venda() {}
	
	
	

	public String getNumero() {
		return numero;
	}




	public void setNumero(String numero) {
		this.numero = numero;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	
	
}