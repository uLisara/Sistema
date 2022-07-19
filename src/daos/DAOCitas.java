
package daos;
import entidades.*;
import controlador.*;
import dto.EspecialidadEstDTO;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JOptionPane;
import util.MySQLConexion;
import vistaa.*;

public class DAOCitas {
    
    //LISTAR UNA CITA
    
    public List<Cita> lisCita() {
        List<Cita> lis = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "select id, hora, feccit, nomp, c.dnip, estado\n" +
                         "from citas c inner join paciente p\n" +
                         "on c.dnip=p.dnip";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getString(1));
                c.setDiacit(rs.getString(2));
                c.setHoracit(rs.getString(3));
                c.setNompac(rs.getString(4));
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
    
    //GENERAR CÓDIGO DE ALUMNO SIN STORE PROCEDURE
    public String genCodCita(){
        String cod="";
        if(lisCita().size()==0){
            cod="CT0001";
        }else{
            int fin=lisCita().size()-1;//Definir en números cual es el último número del código
            cod=lisCita().get(fin).getIdCita();//Una vez definido el número, lo ubicas en el sistema de "A000?"
            int nro=Integer.parseInt(cod.substring(2))+1;//Aquí le aumentas el valor de 1 a la parte numérica
            DecimalFormat sd=new DecimalFormat("0000");//Aquí se define el formato del número "A000?"
            cod="CT"+sd.format(nro);//Se usa el formato "A000?" aumentándole el número que obtuvimos consecuente al último valor
            
        }
        return cod;
    }
    
    public void addCita(Cita c) {
        int resp = 0;           
        Connection conn = null; 
        System.out.println("DNIPAC" + c.getDnipac());
        System.out.println("CODMED "+c.getCodmed());
   
        try {
            String sql = "Insert into citas values(?,?,?,?,?,?)";
            conn = MySQLConexion.getConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, genCodCita());
            st.setInt(2, c.getDnipac());
            st.setString(3,c.getCodmed());
            st.setString(4, c.getDiacit());
            st.setString(5, "Pendiente");
            st.setString(6, c.getHoracit());
            
            resp = st.executeUpdate();
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

    }
    public void addPaci(Paciente p) {
        int resp = 0;           
        Connection conn = null; 
        try {
            String sql = "Insert into paciente values(?,?,?,?)";
            conn = MySQLConexion.getConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1,p.getDni());
            st.setString(2, p.getIdtip());
            st.setString(3, p.getNomp());
            st.setInt(4, p.getNumero());
            resp = st.executeUpdate();
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

    }
    public Paciente showPac(String id){
      Paciente pac=null; 
        //Cita c=null;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select nomp, numero from paciente where dnip=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                pac=new Paciente();
                pac.setNomp(rs.getString(1));
                pac.setNumero(rs.getInt(2));
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
        return pac;  
    }
    public List<Medico> lisMedEs(String id) {
        List<Medico> lis = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "select nombre from medico where codes=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Medico m = new Medico();
                m.setNombre(rs.getString(1));
                lis.add(m);
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
    
   
    
    public String busPac(String id){
        String est="";
        Cita c=null;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select dnip from paciente where dnip=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                est="Encontrado";
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
        return est;
    }  
    
    public double busCosto(String cod){
        double cos=0;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select costo from especialidad where codes=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cod);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
               cos=rs.getDouble(1);
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
        return cos;
    } 
    
    public Map<String, Integer> contEspReg(){
      Map<String, Integer> lis = new HashMap<>();
      Connection conn = null;
      try {
            String sql = "select count(*), especialidad.nombre from citas INNER JOIN medico ON citas.codmed=medico.codmed "
                    + "INNER JOIN especialidad ON medico.codes=especialidad.codes group by especialidad.nombre order by nombre; ";
            conn = MySQLConexion.getConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            //st.setString(1,p);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lis.put(rs.getString(2), rs.getInt(1));
                lis.get("Medicina General");
               // e.setCont(rs.getInt(1)); 
                //e.setNomesp(rs.getString(2)); 
                //lis.add(e);
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
    
    public List<Cita> lisReportCita(String cod) {
        List<Cita> lis = new ArrayList<>();
        Connection conn = null; 
        try { 
            conn = MySQLConexion.getConexion();
            String sql = "select id, paciente.nomp, feccit, estado, hora from citas INNER JOIN paciente ON citas.dnip=paciente.dnip where codmed=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, cod); 
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getString(1));
                c.setNompac(rs.getString(2));
                c.setDiacit(rs.getString(3));
                c.setEstadopac(rs.getString(4));
                c.setHoracit(rs.getString(5)); 
                
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
    
    
    public String codMedNom(String nom) {
        String cod="";
        Connection conn = null; 
        try { 
            conn = MySQLConexion.getConexion();
            String sql = "select codmed\n" +
                         "from medico where nombre=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nom); 
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                cod=rs.getString(1);
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
        return cod;
    }

  public List<Cita> lisListarCita(String cod) {
        List<Cita> lis = new ArrayList<>(); Connection conn = null; 
        try { 
            conn = MySQLConexion.getConexion();
            String sql = "select id, paciente.dnip, paciente.nomp, feccit, estado, hora from citas INNER JOIN "
                    + "paciente ON citas.dnip=paciente.dnip INNER JOIN medico ON citas.codmed=medico.codmed where nombre=?";
            PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, cod); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cita c = new Cita();
                c.setIdCita(rs.getString(1));
                c.setDnipac(rs.getInt(2));
                c.setNompac(rs.getString(3));
                c.setDiacit(rs.getString(4));
                c.setEstadopac(rs.getString(5));
                c.setHoracit(rs.getString(6));
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
  
    public double actCosto(String nom, double costo){
         
         Connection conn = null;
	 try{
             String sql = "Update especialidad set costo=? where codes=?";
             conn = MySQLConexion.getConexion();
             PreparedStatement st = conn.prepareStatement(sql);
             st.setDouble(1, costo);
             st.setString(2, nom);
             st.executeUpdate();
             JOptionPane.showMessageDialog(null, "¡Costo Actualizado!");
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {
			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
         return costo;
		
	}

     public void actEstadoCita(List<Cita> lis){
         Cita cit=null;
         Connection conn = null;
	 try{
             int siz=lis.size();
             String sql="";
             conn = MySQLConexion.getConexion();
             for (int i = 0; i < siz; i++) {
                 cit = lis.get(i);
                 if(cit.getEstadopac().equals("Cancelado")){
                     sql = "Delete from citas where id=?";
                     PreparedStatement st = conn.prepareStatement(sql);
                     st.setString(1, cit.getIdCita());
                     st.executeUpdate();
                 }
                 else {
                     sql = "Update citas set estado=? where id=?";
                     PreparedStatement st = conn.prepareStatement(sql);
                     st.setString(1, cit.getEstadopac());
                     st.setString(2, cit.getIdCita());
                     st.executeUpdate();
                 }
                
             }
         
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {
			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}

		
	}
     
    
    
}
