
package daos;
import entidades.*;
import controlador.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JOptionPane;
import util.MySQLConexion;
import vistaa.*;

public class DAOLog { 
    
    
    public String validarUsuario(String cr, String pws,VLogueo log){ 
        //Logueo buscar=new VLogueo();
        Connection con = null;
        String cod = "f";
        int busqueda = 0;
        try {
            String SQL = "select codmed from medico where correo=? and pswd=?";
            con = MySQLConexion.getConexion();
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, cr.trim());
            st.setString(2, pws.trim());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                busqueda = 1;
                cod = rs.getString(1);
                if (busqueda == 1) {
                    Medico med = busMed(cod);
                    JOptionPane.showMessageDialog(null, "Acceso Exitoso Doc");
                    log.setVisible(false);
                    VMedico form = new VMedico();
                    CtrlMedico ctrmed = new CtrlMedico(form, med);
                    
                }
                return cod;
            } else if (busqueda == 0) {

                String SQL2 = "select codad from administrador where correo=? and pswd=?";
                //int busqueda2=1;
                PreparedStatement st2 = con.prepareStatement(SQL2);
                st2.setString(1, cr.trim());
                st2.setString(2, pws.trim());
                ResultSet rs2 = st2.executeQuery();
                if (rs2.next()) {
                    //String nombre=rs.getString(1);
                    cod = rs2.getString(1);
                    busqueda = 2;
                    if (busqueda == 2) {
                        Administrador admi = busAdmi(cod);
                        JOptionPane.showMessageDialog(null, "Acceso Exitoso Admi");
                        log.setVisible(false);
                        VAdmi form2 = new VAdmi();
                        CtrlAdmin ctradm = new CtrlAdmin(form2, admi);
                        
                    }
                    return cod;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Error de Conexion");
        }
        return cod;
    }
    
        public Administrador busAdmi(String id) {
        Administrador a=null;
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "select * from administrador where codad=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a=new Administrador();
                a.setCodad(rs.getString(1));
                a.setIptip(rs.getString(2));
                a.setNombre(rs.getString(3));
                a.setSexo(rs.getString(4).charAt(0));
                a.setFecha(rs.getString(5));
                a.setCorreo(rs.getString(6));
                a.setPswd(rs.getString(7));
                a.setDni(rs.getInt(8));
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

        return a;

    }
        
        public Medico busMed(String id) {
        Medico m=null;
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            
            String sql = "select * from medico where codmed=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                m=new Medico();
                m.setCodmed(rs.getString(1));
                m.setCodes(rs.getString(2));
                m.setIptip(rs.getString(3));
                m.setNombre(rs.getString(4));
                m.setSexo(rs.getString(5).charAt(0));
                m.setFecha(rs.getString(6));
                m.setCorreo(rs.getString(7));
                m.setPswd(rs.getString(8));
                m.setDni(rs.getInt(9));
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

        return m;

    }
    
    public void actAsis(String cod,String asis){
         
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
    
    public Usuario modUser(Usuario user, String cod, String nom, String fec, String correo, String pswd){
        Connection conn = null;
	 try{
             String sql="";
                if(user instanceof Medico ){
                    sql = "Update medico set nombre=?, nac=?, correo=?, pswd=? where codmed=?";
                }
                if(user instanceof Administrador ){
                   sql = "Update administrador set nombre=?, nac=?, correo=?, pswd=? where codad=?";
                }
             
             conn = MySQLConexion.getConexion();
             PreparedStatement st = conn.prepareStatement(sql);
             st.setString(1, nom);
             st.setString(2, fec);
             st.setString(3, correo);
             st.setString(4, pswd);
             st.setString(5, cod);
             st.executeUpdate();
	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {
			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
            return user;
    }
    
     public String busNuevosMed() {
        int nueMed=0; String txt="";
         List<Medico> med=new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select count(*) from medico where flagHorario='0'";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                nueMed=rs.getInt(1);               
            }
            txt="Horario de Médicos ("+nueMed+")";
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

        return txt;

    }
    
   
   
}
