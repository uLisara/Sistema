

package daos;
import entidades.Administrador;
import entidades.Medico;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import util.MySQLConexion;


public class DAORegistro {
    
     public List<Administrador> lisAdmi() {
        List<Administrador> lis = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "select * from Administrador";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Administrador a = new Administrador();
                a.setCodad(rs.getString(1));
                a.setIptip(rs.getString(2));
                a.setNombre(rs.getString(3));
                a.setSexo(rs.getString(4).charAt(0));
                a.setFecha(rs.getString(5));
                a.setCorreo(rs.getString(6));
                a.setPswd(rs.getString(7));
                a.setDni(rs.getInt(8));
                lis.add(a);
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
    
    
     public String genCodAdmi() {
        String cod;
        if (lisAdmi().size() == 0) {
            cod = "A001";
        } else {
            int fin = lisAdmi().size() - 1;
            cod = lisAdmi().get(fin).getCodad();
            int nro = Integer.parseInt(cod.substring(1)) + 1;
            DecimalFormat sd = new DecimalFormat("000");
            cod = "A" + sd.format(nro);
        }
        return cod;
    }
    
    public void addAdmi(Administrador a) {
        int resp = 0;           
        Connection conn = null; 
        try {
            String sql = "Insert into Administrador values(?,?,?,?,?,?,?,?)";
            conn = MySQLConexion.getConexion();
            PreparedStatement st = conn.prepareStatement(sql);
           st.setString(1,genCodAdmi());
            st.setString(2, a.getIptip());
            st.setString(3, a.getNombre());
            st.setString(4, String.valueOf(a.getSexo()));
            st.setString(5, a.getFecha());
            st.setString(6, a.getCorreo());
            st.setString(7, a.getPswd());
            st.setInt(8, a.getDni());
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
    
    public String busCodEsp(String nomes) {
        String cod= "";           
        Connection conn = null; 
        try {
            String sql = "Select codes from especialidad where nombre=?";
            conn = MySQLConexion.getConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, nomes);
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
    
    public List<Medico> lisMed() {
        List<Medico> lis = new ArrayList<>();
        Connection conn = null;
        String sql="";
        try {
           conn = MySQLConexion.getConexion();
            
                sql = "select * from Medico";
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                Medico a = new Medico();
                a.setCodmed(rs.getString(1));
                a.setCodes(rs.getString(2));
                a.setIptip(rs.getString(3));
                a.setNombre(rs.getString(4));
                a.setSexo(rs.getString(5).charAt(0));
                a.setFecha(rs.getString(6));
                a.setCorreo(rs.getString(7));
                a.setPswd(rs.getString(8));
                a.setDni(rs.getInt(9));
                //a.setIdhorario(rs.getString(10));
                a.setAsistencia(rs.getString(10));
                a.setFlagHorario(rs.getString(11));
                lis.add(a);
            
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
    
    public List<Medico> asistencia() {
        List<Medico> lis = new ArrayList<>();
        Connection conn = null;
        String sql="";
        try {
           conn = MySQLConexion.getConexion();
            
                sql = "select codmed, m.nombre doctor, e.nombre especialidad,asis from medico as m inner join especialidad e on m.codes=e.codes";
                PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                Medico a = new Medico();
                a.setCodmed(rs.getString(1));
                a.setNombre(rs.getString(2));
                a.setCodes(rs.getString(3));
                a.setAsistencia(rs.getString(4));
                lis.add(a);
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
    
    //cargar por default 
    public List<Medico> lisMedNuevos(){
        List<Medico> lis = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql="select m.nombre, e.nombre especialidad  from medico as m inner join especialidad as e on m.codes=e.codes where idhor=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, "");
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
               Medico a = new Medico();
                a.setNombre(rs.getString(1));
                a.setCodes(rs.getString(2));
                //a.setIdhorario(rs.getString(3));
                lis.add(a);
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
    
    public String genCodMed() {
        String cod;
        if (lisMed().size() == 0) {
            cod = "M001";
        } else {
            int fin = lisMed().size() - 1;
            cod = lisMed().get(fin).getCodmed();
            int nro = Integer.parseInt(cod.substring(1)) + 1;
            DecimalFormat sd = new DecimalFormat("000");
            cod = "M" + sd.format(nro);
        }
        return cod;
    }
    
    public void addMed(Medico a) {
        int resp = 0;           
        Connection conn = null; 
        try {
            String sql = "Insert into Medico (codmed, codes,idtip, nombre, sexo, nac, correo, pswd, dni, asis) values(?,?,?,?,?,?,?,?,?,?)";
            conn = MySQLConexion.getConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1,genCodMed());
            st.setString(2,a.getCodes());
            st.setString(3, a.getIptip());
            st.setString(4, a.getNombre());
            st.setString(5,String.valueOf(a.getSexo()));
            st.setString(6, a.getFecha());
            st.setString(7, a.getCorreo());
            st.setString(8, a.getPswd());
            st.setInt(9, a.getDni());
            //st.setString(10,a.getIdhorario());
            st.setString(10, a.getAsistencia());
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
    
    public List<String> lisNomEsp() {
        List<String> lis = new ArrayList<>();
        Connection conn = null;

        try {
            conn = MySQLConexion.getConexion();
            String sql = "select nombre from especialidad";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                lis.add(rs.getString(1));
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
    
    public String nomEsp(String id) {
       String nom="";
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select nombre from especialidad where codes=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
               nom=rs.getString(1);
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
        return nom;
    }   
    
}
