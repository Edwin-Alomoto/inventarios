package com.krakedev.inventario.entidades;

public class CategoriaUDM {
	// ATRIBUTO
	private String codigo;
	private String nombre;

	// CONSTRUCTORE
	public CategoriaUDM() {
		super();
	}

	public CategoriaUDM(String codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}

	// METODO GETTERS AND SETTERS
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// METODO TO STRING
	@Override
	public String toString() {
		return "CategoriaUDM [codigo=" + codigo + ", nombre=" + nombre + "]";
	}

}
