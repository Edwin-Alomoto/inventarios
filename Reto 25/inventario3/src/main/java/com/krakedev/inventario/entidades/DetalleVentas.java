package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class DetalleVentas {
	//ATRIBUTO
	private int codigo;
	private Ventas cabecera;
	private Producto producto;
	private int cantidad;
	private BigDecimal precio;
	private BigDecimal subtotal;
	private BigDecimal subtotalIva;
	//CONSTRUCTORES
	public DetalleVentas() {
		super();
	}
	public DetalleVentas(int codigo, Ventas cabecera, Producto producto, int cantidad, BigDecimal precio,
			BigDecimal subtotal, BigDecimal subtotalIva) {
		super();
		this.codigo = codigo;
		this.cabecera = cabecera;
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.subtotal = subtotal;
		this.subtotalIva = subtotalIva;
	}
	//METODO GETTERS AND SETTERS
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Ventas getCabecera() {
		return cabecera;
	}
	public void setCabecera(Ventas cabecera) {
		this.cabecera = cabecera;
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
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getSubtotalIva() {
		return subtotalIva;
	}
	public void setSubtotalIva(BigDecimal subtotalIva) {
		this.subtotalIva = subtotalIva;
	}
	//METODO TO STRING
	@Override
	public String toString() {
		return "DetalleVentas [codigo=" + codigo + ", cabecera=" + cabecera + ", producto=" + producto + ", cantidad="
				+ cantidad + ", precio=" + precio + ", subtotal=" + subtotal + ", subtotalIva=" + subtotalIva + "]";
	}
	
}