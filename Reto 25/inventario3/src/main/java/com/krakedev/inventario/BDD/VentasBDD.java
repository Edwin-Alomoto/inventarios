package com.krakedev.inventario.BDD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.inventario.entidades.DetalleVentas;
import com.krakedev.inventario.entidades.Ventas;
import com.krakedev.inventario.exepciones.krakeDevException;
import com.krakedev.inventario.util.ConexionBDD;

public class VentasBDD {
	// METODO INSERTAR CABECERA DE VENTA Y DE DETALLE DE VENTA Y TAMBIEN ACTUALIZAR
	// HISTORIAL STOCK
	public void insertarActualizarVenta(Ventas venta) throws krakeDevException {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement psDet = null;
		PreparedStatement psStock = null;
		ResultSet rsClave = null;
		int codigoCabecera = 0;

		Date fechaActual = new Date(); // FECHA DEL SISTEMA DEL JAVA UTIL
		Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());
		// TABLA CABECERA TABLA
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement("insert into cabecera_ventas(fecha,total_sin_iva,iva,total) values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS); // DESPUES INSERT QUE ME RETORNE LAS CLAVES GENERADAS
			BigDecimal cantidad = new BigDecimal(0);
			ps.setTimestamp(1, fechaHoraActual);
			ps.setBigDecimal(2, cantidad);
			ps.setBigDecimal(3, cantidad);
			ps.setBigDecimal(4, cantidad);
			ps.executeUpdate();
			// RECUPERAR LA CLAVE GENERADA
			rsClave = ps.getGeneratedKeys();
			if (rsClave.next()) {
				codigoCabecera = rsClave.getInt(1);
			}

			// INSERTAR UNO POR UNO LOS DETALLES VENTAS, ACTUALIZAR E INSERTAR HISTORIAL DE
			// STOCK
			// INSERTAR DETALLES DE VENTAS
			ArrayList<DetalleVentas> detallesVentas = venta.getDetalles(); // RECUPERAR LOS DETALLLES DEL PEDIDO
			DetalleVentas det;
			for (int i = 0; i < detallesVentas.size(); i++) {
				det = detallesVentas.get(i);
				psDet = con.prepareStatement(
						"insert into detalle_ventas(cabecera_ventas,producto,precio_venta,cantidad,subtotal,subtotal_iva) values(?,?,?,?,?,?)");
				psDet.setInt(1, codigoCabecera);
				psDet.setInt(2, det.getProducto().getCodigo());
				psDet.setInt(3, det.getCantidad());
				BigDecimal pv = det.getProducto().getPrecio();
				psDet.setBigDecimal(4, pv);
				Boolean IVABoolean = det.getProducto().isIva();
				BigDecimal cantidad2 = new BigDecimal(det.getCantidad());
				BigDecimal porcentaje = new BigDecimal(1.12);
				BigDecimal subtotal = pv.multiply(cantidad2);
				BigDecimal iva = subtotal.multiply(porcentaje);
				psDet.setBigDecimal(5, subtotal);
				if (IVABoolean) {
					psDet.setBigDecimal(6, iva);

				} else {
					psDet.setBigDecimal(6, subtotal);
				}
				psDet.executeUpdate();
				// ACTUALIZAR DETALLE DE VENTA
				ps = con.prepareStatement(
						"update cabecera_ventas set total_sin_iva = ?, iva=?, total=? where codigo_cv=?");
				BigDecimal sumarIva = iva.add(iva);
				BigDecimal sumarNoIva = subtotal.add(subtotal);
				BigDecimal total = sumarIva.add(sumarNoIva);
				ps.setBigDecimal(1, sumarNoIva);
				ps.setBigDecimal(2, sumarIva);
				ps.setBigDecimal(3, total);
				ps.setInt(4, codigoCabecera);
				ps.executeUpdate();
				// HISOTIAL DE STOCK CON VENTA
				psStock = con.prepareStatement( "insert into historial_stock(fecha,referencia,producto,cantidad) values(?,?,?,?)");
				psStock.setTimestamp(1, fechaHoraActual);
				psStock.setString(2, "VENTA " + codigoCabecera);
				psStock.setInt(3, det.getProducto().getCodigo());
				psStock.setInt(4, (-1)*det.getCantidad());
				psStock.executeUpdate();
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
