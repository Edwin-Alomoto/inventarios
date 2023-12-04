package com.krakedev.inventario.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventario.exepciones.krakeDevException;
import com.krakedev.inventario.util.ConexionBDD;

public class ProveedoresBDD {
	// METODO RECUPERAR O BUSCAR LISTA DE PROVEEDORES LO CUAL YO NOMBRE TIENE UNA
	// SUBCADENA
	public ArrayList<Proveedor> recuperarBuscarProveedor(String subcadena) throws krakeDevException {
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedor pro = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement(
					"select prov.indentificador,tipo_documento,td.descripcion,prov.nombre,prov.telefono,prov.correo,prov.direccion from proveedores prov, tipo_documento td\r\n"
							+ "where prov.tipo_documento = td.codigo and upper(nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				String indentificador = rs.getString("indentificador");
				String CodigoTipoDocumento = rs.getString("tipo_documento");
				String descripcionTipoDocumento = rs.getString("descripcion");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				TipoDocumento td = new TipoDocumento(CodigoTipoDocumento, descripcionTipoDocumento);
				pro = new Proveedor(indentificador, td, nombre, telefono, correo, direccion);
				proveedores.add(pro);
			}
		} catch (krakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new krakeDevException("ERROR AL CONSULTAR. DETALLE:" + e.getMessage());

		}
		return proveedores;
	}

	// METODO DE INSERTAR UN PROVEEDOR NUEVO
	public void insertarProveedor(Proveedor proveedores) throws krakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			PreparedStatement ps = con.prepareStatement("insert into proveedores(indentificador, tipo_documento,nombre,telefono,correo,direccion) values (?,?,?,?,?,?)");
			ps.setString(1, proveedores.getIdentificador());
			ps.setString(2, proveedores.getTipoDocumento().getCodigo());
			ps.setString(3, proveedores.getNombre());
			ps.setString(4, proveedores.getTelefono());
			ps.setString(5, proveedores.getCorreo());
			ps.setString(6, proveedores.getDireccion());
			ps.executeUpdate();
		} catch (SQLException e) { // OTRA EXCEPTION SERIA UN NUEVO MENSAJE CAPTURA Y LO RELANZA
			e.printStackTrace();
			throw new krakeDevException("ERROR AL INSERTAR AL CLIENTE. DETALLE:" + e.getMessage());
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
