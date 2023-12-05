package com.krakedev.inventario.entidades;

import java.math.BigDecimal;

public class Producto {
	//ATRIBUTOS
	private int codigo;
	private String nombre;
	private UnidadesMedidas unidadesMedidas;
	private BigDecimal precio;
	private boolean iva;
	private BigDecimal coste;
	private Categoria categoria;
	private int stock;
	//CONSTRUCTORES
	public Producto() {
		super();
	}
	public Producto(int codigo, String nombre, UnidadesMedidas unidadesMedidas, BigDecimal precio, boolean iva,
			BigDecimal coste, Categoria categoria, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.unidadesMedidas = unidadesMedidas;
		this.precio = precio;
		this.iva = iva;
		this.coste = coste;
		this.categoria = categoria;
		this.stock = stock;
	}
	//METODO GETTERS AND SETTERS 
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public UnidadesMedidas getUnidadesMedidas() {
		return unidadesMedidas;
	}
	public void setUnidadesMedidas(UnidadesMedidas unidadesMedidas) {
		this.unidadesMedidas = unidadesMedidas;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public boolean isIva() {
		return iva;
	}
	public void setIva(boolean iva) {
		this.iva = iva;
	}
	public BigDecimal getCoste() {
		return coste;
	}
	public void setCoste(BigDecimal coste) {
		this.coste = coste;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	//METODO TO STRING
	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", unidadesMedidas=" + unidadesMedidas
				+ ", precio=" + precio + ", iva=" + iva + ", coste=" + coste + ", categoria=" + categoria + ", stock="
				+ stock + "]";
	}
	
}
