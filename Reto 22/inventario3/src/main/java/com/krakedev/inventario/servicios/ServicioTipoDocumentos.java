package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.ProveedoresBDD;
import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventario.exepciones.krakeDevException;

@Path("tipodocumento")
public class ServicioTipoDocumentos {
	// METODO RECUPERAR TIPO DE DOCUMENTO PARA AGREGAR UN PROVEEDOR EN EL FUTURO
		@Path("recuperar")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response obtenerTipoDocumento() {
			ProveedoresBDD pro = new ProveedoresBDD();
			ArrayList<TipoDocumento> tipoDocumento = null;
			try {
				tipoDocumento = pro.BuscarTipoDocumento();
				return Response.ok(tipoDocumento).build();
			} catch (krakeDevException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
}
