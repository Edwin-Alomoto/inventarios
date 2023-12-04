package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProveedoresBDD;
import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.exepciones.krakeDevException;

@Path("proveedores")
public class ServiciosProveedores {
	//BUSCAR O RECUPERAR EL LISTADO DE PROVEEDORES CUYO NOMBRE ES UNA SUBCADENA
	@Path("buscar/{subcadena}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("subcadena") String subcadena) {
		ProveedoresBDD pro = new ProveedoresBDD();
		ArrayList<Proveedor> proveedores = null;
		try {
			proveedores = pro.recuperarBuscarProveedor(subcadena);
			return Response.ok(proveedores).build();
		} catch (krakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	//INSERTAR UN PROVEEDOR NUEVO 
	@Path("insertar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Proveedor proveer) {
		ProveedoresBDD pro = new ProveedoresBDD();
		try {
			pro.insertarProveedor(proveer);
			return Response.ok().build();
		} catch (krakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
		
	}
	
}
