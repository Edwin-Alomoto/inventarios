package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProveedoresBDD;
import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventario.exepciones.krakeDevException;
@Path("productos")
public class ServicioProducto {
	//BUSCAR O RECUPERAR EL LISTADO DE PROVEEDORES CUYO NOMBRE ES UNA SUBCADENA
		@Path("buscar/{subcadena}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response buscar(@PathParam("subcadena") String subcadena) {
			ProveedoresBDD pro = new ProveedoresBDD();
			ArrayList<Producto> producto= null;
			try {
				producto = pro.recuperarBuscarProducto(subcadena);
				return Response.ok(producto).build();
			} catch (krakeDevException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
}
