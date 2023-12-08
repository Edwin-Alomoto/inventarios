package com.krakedev.inventario.BDD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventario.entidades.Pedido;
import com.krakedev.inventario.entidades.DetallePedido;
import com.krakedev.inventario.exepciones.krakeDevException;
import com.krakedev.inventario.util.ConexionBDD;

public class PedidosBDD {
	// METODO CREAR PRODUCTO NUEVO
	public void insertarPedidos(Pedido pedido) throws krakeDevException {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		Date fechaActual = new Date(); // FECHA DEL SISTEMA DEL JAVA UTIL
		java.sql.Date fechaSql = new java.sql.Date(fechaActual.getTime()); // CONSTRUIR LA FECHA SQL
		ResultSet rsClave = null;
		int codigoCabecera = 0;
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement("insert into cabecera_pedidos(proveedor,fecha,estado)" + "values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS); // DESPUES INSERT QUE ME RETORNE LAS CLAVES GENERADAS
			ps.setString(1, pedido.getProveedor().getIdentificador());
			ps.setDate(2, fechaSql);
			ps.setString(3, "S");
			ps.executeUpdate();
			// RECUPERAR LA CLAVE GENERADA
			rsClave = ps.getGeneratedKeys();
			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}
			// INSERTAR UNO POR UNO LOS DETALLES
			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles(); // RECUPERAR LOS DETALLLES DEL PEDIDO
			DetallePedido det;
			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);
				psDet = con.prepareStatement(
						"insert into detalle_pedido(cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida) values(?,?,?,?,?)");
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidadSolicitada());
				BigDecimal pv = det.getProducto().getPrecio();
				BigDecimal cantidad = new BigDecimal(det.getCantidadSolicitada());
				BigDecimal subtotal = pv.multiply(cantidad);
				psDet.setBigDecimal(4, subtotal);
				psDet.setInt(5, 0);
				psDet.executeUpdate();
			}

		} catch (SQLException e) { // OTRA EXCEPTION SERIA UN NUEVO MENSAJE CAPTURA Y LO RELANZA
			e.printStackTrace();
			throw new krakeDevException("ERROR AL INSERTAR AL PEDIDO. DETALLE:" + e.getMessage());
		} catch (krakeDevException e) {
			throw e; // RELANZAR NUEVAMENTE YA QUE YA TIENE UN MENSAJE EN CNOEXION BDD
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // CERRAR LA CONEXION
	}

	// METODO ACTULIZAR PEDIDO DEL ESTADO Y DE LOS DETALLES DEL PEDIDO
	public void actualizarPedido(Pedido pedido) throws krakeDevException {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement("update cabecera_pedidos set estado = ? where numero = ? "); 
			ps.setString(1, "R");
			ps.setInt(2, pedido.getNumero());
			
			ps.executeUpdate();
			
			// INSERTAR UNO POR UNO LOS DETALLES
			ArrayList<DetallePedido> detallesPedido = pedido.getDetalles(); // RECUPERAR LOS DETALLLES DEL PEDIDO
			DetallePedido det;
			for (int i = 0; i < detallesPedido.size(); i++) {
				det = detallesPedido.get(i);
				psDet = con.prepareStatement("update detalle_pedido set producto=?, cantidad_recibida=? where codigo = ?");
				psDet.setInt(1, det.getProducto().getCodigo());
				psDet.setInt(2, det.getCantidadRecibida());
				psDet.setInt(3, det.getCodigo());
				psDet.executeUpdate();
			}

		} catch (SQLException e) { // OTRA EXCEPTION SERIA UN NUEVO MENSAJE CAPTURA Y LO RELANZA
			e.printStackTrace();
			throw new krakeDevException("ERROR AL INSERTAR AL PEDIDO. DETALLE:" + e.getMessage());
		} catch (krakeDevException e) {
			throw e; // RELANZAR NUEVAMENTE YA QUE YA TIENE UN MENSAJE EN CNOEXION BDD
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // CERRAR LA CONEXION
	}
}
