package com.krakedev.inventarios.servicios;

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProveedoresBDD;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.exepciones.krakeDevException;

@Path("tipodocumento")
public class ServiciosTipoDocumento {
	// METODO RECUPERAR TIPO DE DOCUMENTO PARA AGREGAR UN PROVEEDOR EN EL FUTURO
	@Path("recuperar")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerTipoDocumento() {
		ProveedoresBDD pro = new ProveedoresBDD();
		ArrayList<TipoDocumento> tipoDocumento = null;
		try {
			tipoDocumento = pro.tipoDocumento();
			return Response.ok(tipoDocumento).build();
		} catch (krakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
}
