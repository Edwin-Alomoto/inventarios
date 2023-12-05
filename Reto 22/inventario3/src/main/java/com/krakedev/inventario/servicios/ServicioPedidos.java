package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.inventario.BDD.PedidosBDD;
import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventario.exepciones.krakeDevException;

@Path("pedidos")
public class ServicioPedidos {
	// INSERTAR UN CABECERA PEDIDO
	@Path("registrar")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response insertar(Pedido pedido) {
		PedidosBDD pro = new PedidosBDD();
		try {
			pro.insertarPedidos(pedido);
			return Response.ok().build();
		} catch (krakeDevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}

	}
}
