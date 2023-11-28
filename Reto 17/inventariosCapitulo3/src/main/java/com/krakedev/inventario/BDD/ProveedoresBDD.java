package com.krakedev.inventario.BDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventarios.entidades.Proveedores;
import com.krakedev.inventarios.entidades.TipoDocumento;
import com.krakedev.inventarios.exepciones.krakeDevException;
import com.krakedev.inventarios.utils.ConexionBDD;

public class ProveedoresBDD {
	// METODO RECUPERAR O BUSCAR UN PROVEEDOR CUYO NOMBRE TENGA UNA SUBCADENA
	public ArrayList<Proveedores> buscarRecuperarTodos(String subcadena) throws krakeDevException {
		ArrayList<Proveedores> proveedor = new ArrayList<Proveedores>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Proveedores pro = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement(
					"select indentificador, tipo_documento,nombre,telefono,correo,direccion from proveedores \r\n"
							+ "where upper(nombre) like ?");// RECUPERA TODO LOS PROVEEDORES QUE TIENE LA BASE DE DATOS
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				String indentificador = rs.getString("indentificador");
				String tipo_documento = rs.getString("tipo_documento");
				String nombre = rs.getString("nombre");
				String telefono = rs.getString("telefono");
				String correo = rs.getString("correo");
				String direccion = rs.getString("direccion");
				pro = new Proveedores(indentificador, tipo_documento, nombre, telefono, correo, direccion);
				proveedor.add(pro);
			}
		} catch (krakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new krakeDevException("ERROR AL CONSULTAR. DETALLE:" + e.getMessage());
		}
		return proveedor;
	}

	// METODO RECUPERAR TIPO DE DOCUMENTO PARA AGREGAR UN PROVEEDOR EN EL FUTURO
	public ArrayList<TipoDocumento> tipoDocumento() throws krakeDevException {
		ArrayList<TipoDocumento> tipoDeDocumento = new ArrayList<TipoDocumento>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TipoDocumento td = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement("select codigo,descripcion from tipo_documento");// RECUPERA TODO TIPO DE DOCUMENTO QUE TIENE LA BASE DE DATOS
			rs = ps.executeQuery();
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				td = new TipoDocumento(codigo, descripcion);
				tipoDeDocumento.add(td);
			}
		} catch (krakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new krakeDevException("ERROR AL CONSULTAR. DETALLE:" + e.getMessage());
		}
		return tipoDeDocumento;
	}

}
