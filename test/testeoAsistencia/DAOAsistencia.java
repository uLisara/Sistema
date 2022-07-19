/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeoAsistencia;

import java.sql.*;
import util.MySQLConexion;

/**
 *
 *
 */
public class DAOAsistencia {
     public void actualizarAsistencia(String cod,String asis){
         
         Connection conn = null;
	 try{
             String sql = "Update medico set asis=? where codmed=?";
             conn = MySQLConexion.getConexion();
             
             PreparedStatement st = conn.prepareStatement(sql);
             
             st.setString(1, asis);
             st.setString(2, cod);
             st.executeUpdate();
	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {
			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}

		
	}
}
