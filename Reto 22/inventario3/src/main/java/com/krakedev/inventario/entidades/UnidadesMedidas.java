package com.krakedev.inventario.entidades;

public class UnidadesMedidas {
	// ATRIBUTO
	private String nombre;
	private String descripcion;
	private CategoriaUDM categoriaUDM;

	// CONSTRUCTORES
	public UnidadesMedidas() {
		super();
	}

	public UnidadesMedidas(String nombre, String descripcion, CategoriaUDM categoriaUDM) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoriaUDM = categoriaUDM;
	}

	// METODO GETTERS AND SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CategoriaUDM getCategoriaUDM() {
		return categoriaUDM;
	}

	public void setCategoriaUDM(CategoriaUDM categoriaUDM) {
		this.categoriaUDM = categoriaUDM;
	}

	// METODO TO STRING
	@Override
	public String toString() {
		return "UnidadesMedidas [nombre=" + nombre + ", descripcion=" + descripcion + ", categoriaUDM=" + categoriaUDM
				+ "]";
	}

}
