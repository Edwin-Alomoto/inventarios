package com.krakedev.inventario.entidades;

import java.util.Date;

public class HistorialStock {
	// ATRIBUTO
	private Date fecha;
	private String referencia;
	private Producto producto;
	private int cantidad;

	// CONSTRUCTORES
	public HistorialStock() {
		super();
	}

	public HistorialStock(Date fecha, String referencia, Producto producto, int cantidad) {
		super();
		this.fecha = fecha;
		this.referencia = referencia;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	//METODO GETTERS AND SETTERS 

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	//METODO TO STRING

	@Override
	public String toString() {
		return "HistorialStock [fecha=" + fecha + ", referencia=" + referencia + ", producto=" + producto
				+ ", cantidad=" + cantidad + "]";
	}
	
}
