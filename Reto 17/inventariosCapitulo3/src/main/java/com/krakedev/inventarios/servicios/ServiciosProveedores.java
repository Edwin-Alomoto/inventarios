package com.krakedev.inventarios.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProveedoresBDD;
import com.krakedev.inventarios.entidades.Proveedores;
import com.krakedev.inventarios.exepciones.krakeDevException;
@Path("proveedores")
public class ServiciosProveedores {
	// METODO RECUPERAR O BUSCAR UN PROVEEDOR QUE CONTENGA UN SUCADENA
	@Path("buscar/{sub}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerProveedor(@PathParam("sub") String subcadena) {
		ProveedoresBDD pro = new ProveedoresBDD();
		ArrayList<Proveedores> proveedor = null;
		try {
			proveedor = pro.buscarRecuperarTodos(subcadena);
			return Response.ok(proveedor).build();
		} catch (krakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
