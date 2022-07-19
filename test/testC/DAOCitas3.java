/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testC;

import entidades.Cita;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.MySQLConexion;

/**
 *
 * @author Mishi
 */
public class DAOCitas3 {
    public List<Cita> lisListarCita(String cod) {
        List<Cita> lis = new ArrayList<>();
        Connection conn = null; 
        try { 
            conn = MySQLConexion.getConexion();
            String sql = "select id, paciente.nomp, feccit, estado, hora from citas INNER JOIN paciente ON citas.dnip=paciente.dnip INNER JOIN medico ON citas.codmed=medico.codmed where nombre=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cod); 
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getString(1));
                c.setHoracit(rs.getString(2));
                c.setDiacit(rs.getString(3));
                c.setNompac(rs.getString(4));
                //c.setDnipac(rs.getInt(5));
                c.setEstadopac(rs.getString(5));
                lis.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

        return lis;
    }  
}
