package com.krakedev.inventarios.entidades;

public class TipoDocumento {
	// ATRIBUTO
	private String codigo;
	private String descripcion;

	// CONSTRUCTORES
	public TipoDocumento() {
		super();
	}

	public TipoDocumento(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	// METODO GETTERS AND SETTERS 

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	//METODO TO STRING 

	@Override
	public String toString() {
		return "TipoDocumento [codigo=" + codigo + ", descripcion=" + descripcion + "]";
	}
	
}
