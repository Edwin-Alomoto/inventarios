package com.krakedev.inventario.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.krakedev.inventario.exepciones.krakeDevException;


//CONEXION DE DATASOURCES
public class ConexionBDD {
	public static Connection ObtenerConexion() throws krakeDevException {
		Context ctx=null;
		DataSource ds=null;
		Connection con=null;
			try {
				ctx = new InitialContext();
				//JNDI PODER BUSCAR ELEMENTOS DENTRO DEL SERVIDOR
				ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/ConexionInv");
				con = ds.getConnection();
			} catch (NamingException | SQLException e) { // CAPTURO LA EXCEPCION 
				e.printStackTrace();
				throw new krakeDevException("ERROR DE LA CONEXION"); //RELANSO LAS EXEPCIONES CON UN MENSAJE DE USUARIO
			}
			
		return con;
	}
}
