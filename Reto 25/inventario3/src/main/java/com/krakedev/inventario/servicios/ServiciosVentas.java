package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.VentasBDD;
import com.krakedev.inventario.entidades.Ventas;
import com.krakedev.inventario.exepciones.krakeDevException;

@Path("ventas")
public class ServiciosVentas {
	@Path("guardar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Ventas venta) {
		VentasBDD pro = new VentasBDD();
		try {
			pro.insertarActualizarVenta(venta);
			return Response.ok().build();
		} catch (krakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
}
