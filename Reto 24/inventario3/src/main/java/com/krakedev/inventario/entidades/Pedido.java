package com.krakedev.inventario.entidades;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	// ATRIBUTOS
	private int numero;
	private Proveedor proveedor;
	private Date fecha;
	private EstadoPedido estado;
	private ArrayList<DetallePedido> detalles;

	// CONSTRUCTORES
	public Pedido() {
		super();
	}

	public Pedido(int numero, Proveedor proveedor, Date fecha, EstadoPedido estado, ArrayList<DetallePedido> detalles) {
		super();
		this.numero = numero;
		this.proveedor = proveedor;
		this.fecha = fecha;
		this.estado = estado;
		this.detalles = detalles;
	}

	// METODO GETTERS AND SETTERS
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public ArrayList<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	// METODO O STRING
	@Override
	public String toString() {
		return "CabeceraPedido [numero=" + numero + ", proveedor=" + proveedor + ", fecha=" + fecha + ", estado="
				+ estado + ", detalles=" + detalles + "]";
	}

}