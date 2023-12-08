package com.krakedev.inventario.BDD;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.inventario.entidades.Categoria;
import com.krakedev.inventario.entidades.Producto;
import com.krakedev.inventario.entidades.Proveedor;
import com.krakedev.inventario.entidades.TipoDocumento;
import com.krakedev.inventario.entidades.UnidadesMedidas;
import com.krakedev.inventario.exepciones.krakeDevException;
import com.krakedev.inventario.util.ConexionBDD;

public class ProveedoresBDD {
	// METODO RECUPERAR O BUSCAR LISTA DE PROVEEDORES LO CUAL CUYO NOMBRE TIENE UNA
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

	// METODO DE BUSCAR O RECUPERAR TIPO DE DOCUMENTO
	public ArrayList<TipoDocumento> BuscarTipoDocumento() throws krakeDevException {
		ArrayList<TipoDocumento> tipoDocumento = new ArrayList<TipoDocumento>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TipoDocumento td = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement("select codigo,descripcion from tipo_documento");
			rs = ps.executeQuery();
			while (rs.next()) {
				String codigo = rs.getString("codigo");
				String descripcion = rs.getString("descripcion");
				td = new TipoDocumento(codigo, descripcion);
				tipoDocumento.add(td);
			}
		} catch (krakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new krakeDevException("ERROR AL CONSULTAR. DETALLE:" + e.getMessage());

		}
		return tipoDocumento;
	}

	// METODO DE INSERTAR UN PROVEEDOR NUEVO
	public void insertarProveedor(Proveedor proveedores) throws krakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"insert into proveedores(indentificador, tipo_documento,nombre,telefono,correo,direccion) values (?,?,?,?,?,?)");
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

	// METODO RECUPERAR O BUSCAR LISTA DE PRODUCTO LO CUAL CUYO NOMBRE TIENE UNA
	// SUBCADENA
	public ArrayList<Producto> recuperarBuscarProducto(String subcadena) throws krakeDevException {
		ArrayList<Producto> producto = new ArrayList<Producto>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Producto prod = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			ps = con.prepareStatement("select pro.codigo_producto, pro.nombre as nombre_producto, udm.nombre as nombre_categoria,udm.descripcion as descripcion_udm, \r\n"
					+ "cast(pro.precio as decimal (5,2) ),pro.iva,cast(pro.coste as decimal (5,2)), pro.categoria,ct.nombre as nombre_categoria,pro.stock\r\n"
					+ "from producto pro , udm udm, categorias ct \r\n"
					+ "where (pro.unidades_medidas = udm.nombre) \r\n"
					+ "and (pro.categoria = ct.codigo_cat)\r\n"
					+ "and upper (pro.nombre) like ?");
			ps.setString(1, "%" + subcadena.toUpperCase() + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				int codigoProducto = rs.getInt("codigo_producto");
				String nombreProducto = rs.getString("nombre_producto");
				String udmNombreCategoria= rs.getString("nombre_categoria");
				String udDescripcion = rs.getString("descripcion_udm");
				BigDecimal precio = rs.getBigDecimal("precio");
				Boolean iva = rs.getBoolean("iva");
				BigDecimal coste = rs.getBigDecimal("coste");
				int categoria = rs.getInt("categoria");
				String nombreCategoria = rs.getString("nombre_categoria");
				int stock = rs.getInt("stock");
				
				//TABLA UNIDADES DE MEDIDAD 
				UnidadesMedidas UD = new UnidadesMedidas();
				UD.setNombre(udmNombreCategoria);
				UD.setDescripcion(udDescripcion);
				//TABLA CATEGORIAS
				Categoria cat = new Categoria();
				cat.setCodigo(categoria);
				cat.setNombre(nombreCategoria);
				//TABLA PRODUCTOS
				prod = new Producto();
				prod.setCodigo(codigoProducto);
				prod.setNombre(nombreProducto);
				prod.setUnidadesMedidas(UD);
				prod.setPrecio(precio);
				prod.setIva(iva);
				prod.setCoste(coste);
				prod.setCategoria(cat);
				prod.setStock(stock);
				producto.add(prod);
			}
		} catch (krakeDevException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new krakeDevException("ERROR AL CONSULTAR. DETALLE:" + e.getMessage());

		}
		return producto;
	}
	// METODO CREAR PRODUCTO NUEVO
	public void insertarProducto(Producto producto) throws krakeDevException {
		Connection con = null;
		try {
			con = ConexionBDD.ObtenerConexion();
			PreparedStatement ps = con.prepareStatement(
					"insert into producto(codigo_producto,nombre,unidades_medidas,precio,iva,coste,categoria,stock)\r\n"
					+ "values(?,?,?,?,?,?,?,?)");
			ps.setInt(1, producto.getCodigo());
			ps.setString(2, producto.getNombre());
			ps.setString(3, producto.getUnidadesMedidas().getNombre());
			ps.setBigDecimal(4, producto.getPrecio());
			ps.setBoolean(5, producto.isIva());
			ps.setBigDecimal(6, producto.getCoste());
			ps.setInt(7, producto.getCategoria().getCodigo());
			ps.setInt(8, producto.getStock());
			
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
