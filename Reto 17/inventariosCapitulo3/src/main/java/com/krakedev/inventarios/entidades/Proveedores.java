package com.krakedev.inventarios.entidades;

public class Proveedores {
	// ATRIBUTOS
	private String indetificador;
	private String tipoDocumento;
	private String nombre;
	private String telefono;
	private String correo;
	private String direccion;

	// CONSTRUCTORES
	public Proveedores() {
		super();
	}

	public Proveedores(String indetificador, String tipoDocumento, String nombre, String telefono, String correo,
			String direccion) {
		super();
		this.indetificador = indetificador;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}

	// METODO GETTERS AND SETTERS
	public String getIndetificador() {
		return indetificador;
	}

	public void setIndetificador(String indetificador) {
		this.indetificador = indetificador;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// METODO TO STRING
	@Override
	public String toString() {
		return "Proveedores [indetificador=" + indetificador + ", tipoDocumento=" + tipoDocumento + ", nombre=" + nombre
				+ ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + "]";
	}

}
