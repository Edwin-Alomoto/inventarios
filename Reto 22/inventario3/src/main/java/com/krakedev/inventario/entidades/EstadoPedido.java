package com.krakedev.inventario.entidades;

public class EstadoPedido {
	// ATRIBUTO
	private String codigo;
	private String descripcioin;

	// CONSTRUCTORES
	public EstadoPedido() {
		super();
	}

	public EstadoPedido(String codigo, String descripcioin) {
		super();
		this.codigo = codigo;
		this.descripcioin = descripcioin;
	}

	// METODO GETTERS ANS SETTERS
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcioin() {
		return descripcioin;
	}

	public void setDescripcioin(String descripcioin) {
		this.descripcioin = descripcioin;
	}

	// METODO TO STRING
	@Override
	public String toString() {
		return "EstadoPedido [codigo=" + codigo + ", descripcioin=" + descripcioin + "]";
	}

}
