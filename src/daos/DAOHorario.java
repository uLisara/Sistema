
package daos;

import entidades.*;
import java.sql.*;
import java.text.DecimalFormat;
import util.*;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class DAOHorario {
    
     public List<String> allHorarios() {
        List<String> lis=new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select detalle from horarios";
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
     
    public List<String> allHorariosMenores(String timep) {
        List<String> lis=new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select detalle from horarios where detalle > ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, timep);
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
    
        public void listHorariosEsp(String codes,JComboBox jcbx) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT * FROM horario_dias_especialidad WHERE codes=? and flagEstado='1' and flagAssig='0'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, codes);
            ResultSet rs = st.executeQuery();  
            jcbx.addItem(Utils.SL);
            while (rs.next()) {
                
                //lis.add(rs.getString(1));
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
    }
    
     public void addHorarioDiaEspecialidad(HorarioDiaEspecialidad hde) {
        Connection conn = null; 
        try {
            String sql = "Insert into horario_dias_especialidad (codes,dia,flagEstado,hinicio,hfin) values(?,?,?,?,?)";
            conn = MySQLConexion.getConexion();
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, hde.getCodes());
            st.setString(2, hde.getDia());
            st.setString(3,hde.getFlagEstado());
            st.setString(4,hde.getHinicio());
            st.setString(5, hde.getHfin());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horario añadido exitosamente");
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
     

    
     public int busHorarioCreado(HorarioDiaEspecialidad h) {
        int cont=0;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select count(*) from horario_dias_especialidad where codes=? and dia=? and flagEstado='1'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, h.getCodes());
            st.setString(2, h.getDia());
            ResultSet rs = st.executeQuery();            
            while (rs.next()) {
                cont=Integer.parseInt(rs.getString(1));
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
        return cont;
    }
     
         public List<HorarioDiaEspecialidad> lisHorariosDiaEsp(String codes){
         HorarioDiaEspecialidad h=null;
         List<HorarioDiaEspecialidad> lisH=new ArrayList();
         Connection conn = null;
	 try{
             conn = MySQLConexion.getConexion();
            String sql = "select id,dia,hinicio,hfin from horario_dias_especialidad where codes=? and flagEstado='1'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, codes); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                h=new HorarioDiaEspecialidad();
                h.setId(rs.getString(1));
                h.setDia(rs.getString(2));
                h.setHinicio(rs.getString(3).substring(0, 5));
                h.setHfin(rs.getString(4).substring(0, 5));
                lisH.add(h);
            }	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
	return lisH;	
	}
     
         public HorarioDiaEspecialidad busHorariosDiaEsp(String id){
         HorarioDiaEspecialidad h=null;
         Connection conn = null;
	 try{
             conn = MySQLConexion.getConexion();
            String sql = "select id,dia,hinicio,hfin,codes from horario_dias_especialidad where id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println("REALIZA LA CONSULTA");
                h=new HorarioDiaEspecialidad();
                h.setId(rs.getString(1));
                h.setDia(rs.getString(2));
                h.setHinicio(rs.getString(3).substring(0, 5));
                h.setHfin(rs.getString(4).substring(0, 5));
                h.setCodes(rs.getString(5));
            }	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
	return h;	
	}
         

    
        public void deleteHorario (String id){
         
         Connection conn = null;
	 try{
             String sql = "Update horario_dias_especialidad set flagEstado='0' where id=?";
             conn = MySQLConexion.getConexion();
             PreparedStatement st = conn.prepareStatement(sql);
             st.setString(1,id);
             st.executeUpdate();
             JOptionPane.showMessageDialog(null, "¡Horario Actualizado!");
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
		
	}
        
        public List<Medico> lisAllMedHorarios(){
         Medico m=null;
         List<Medico> lisM=new ArrayList();
         Connection conn = null;
	 try{
             conn = MySQLConexion.getConexion();
            String sql = "SELECT m.codmed, m.nombre, m.flagHorario, e.nombre  FROM medico m left join especialidad e on  m.codes=e.codes order by m.flagHorario,m.codes";
            PreparedStatement st = conn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                m=new Medico();
                m.setCodmed(rs.getString(1));
                m.setNombre(rs.getString(2));
                if(rs.getString(3).equals("0")){
                    m.setFlagHorario("NO");
                }else{
                    m.setFlagHorario("SÍ");
                }
                m.setNomEsp(rs.getString(4));
                lisM.add(m);
            }	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
	return lisM;	
	}
        
      public List<Medico> searchFiltersHorMed(String codes, String estado) {
        List<Medico> lis = new ArrayList<>();
        Connection conn = null;
         // System.out.println("LLEGAN code: "+codes+" estado: "+estado);
        try {
            conn = MySQLConexion.getConexion();
       
            String sql = "{call spSearchHMed(?,?)}"; //llamar al procedimiento...
            CallableStatement st = conn.prepareCall(sql);
            st.setString(1, codes);
            st.setString(2, estado);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            while (rs.next()) {
                Medico m = new Medico();
                m.setCodmed(rs.getString(1));
                m.setNombre(rs.getString(2));
                if(rs.getString(3).equals("0")){
                    m.setFlagHorario("NO");
                }else{
                    m.setFlagHorario("SÍ");
                }
                m.setNomEsp(rs.getString(4));
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
              public String actHorario(HorarioDiaEspecialidad hde){
         String val=null;
         Connection conn = null;
	 try{
              conn = MySQLConexion.getConexion();
             String sql = "{call spddModificarHorario(?,?,?,?,?,?)}"; //llamar al procedimiento...
            CallableStatement st = conn.prepareCall(sql);
             st.setString(3, hde.getDia());
             st.setString(1, hde.getCodes());
             st.setString(4,hde.getHinicio());
             st.setString(5,hde.getHfin());
             st.setString(2,hde.getId());
             st.registerOutParameter(6, java.sql.Types.VARCHAR);
            //ResultSet rs = st.executeQuery();
            st.execute();
             val=st.getString(6);
                //System.out.println("valor"+st.getString(1));
    
             
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		} 
            System.out.println("valor 2-> "+val);
		return val;
	}
      
      
        public List<String> DetalleHorarioDisp(String codmed, String dia) {
        List<String> lis = new ArrayList<>();
        Connection conn = null;
        
        try {
            conn = MySQLConexion.getConexion();
       
            String sql = "{call spHoraDisp(?,?)}"; //llamar al procedimiento...
            CallableStatement st = conn.prepareCall(sql);
            st.setString(1, codmed);
            st.setString(2, dia);
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            System.out.println("LISTA ALL");
            while (rs.next()) {
          
                lis.add(rs.getString(1).substring(0, 5));
                System.out.print("i: "+rs.getString(1).substring(0, 5));
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
      
      
        public List<HorarioDiaEspecialidad> lisHorAsigMed(String codmed){
         HorarioDiaEspecialidad h=null;
         List<HorarioDiaEspecialidad> lisH=new ArrayList();
         Connection conn = null;
	 try{
             conn = MySQLConexion.getConexion();
            String sql = "SELECT hde.id,hde.dia,hde.hinicio, hde.hfin FROM horario_dias_especialidad hde LEFT JOIN\n" +
                          "medico_atencion ma ON hde.id=ma.idHorario where ma.codmed=? and ma.flagEstado='1'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, codmed); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                h=new HorarioDiaEspecialidad();
                h.setId(rs.getString(1));
                h.setDia(rs.getString(2));
                h.setHinicio(rs.getString(3).substring(0, 5));
                h.setHfin(rs.getString(4).substring(0, 5));
                lisH.add(h);
            }	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
	return lisH;	
	} 
        
        public List<HorarioDiaMedico> lisHorioMed(String codmed){
         HorarioDiaMedico h=null;
         List<HorarioDiaMedico> lisHM=new ArrayList();
         Connection conn = null;
	 try{
             conn = MySQLConexion.getConexion();
            String sql = "SELECT hde.dia,hde.hinicio, hde.hfin FROM horario_dias_especialidad hde LEFT JOIN\n" +
                          "medico_atencion ma ON hde.id=ma.idHorario where ma.codmed=? and ma.flagEstado='1'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, codmed); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                h=new HorarioDiaMedico();
                h.setDia(rs.getString(1));
                h.setHinicio(rs.getString(2).substring(0, 5));
                h.setHfin(rs.getString(3).substring(0, 5));
                lisHM.add(h);
            }	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
	return lisHM;	
	} 
        
        public HorarioDiaEspecialidad lisHorariosOnly(String id){
         HorarioDiaEspecialidad h=null;
         Connection conn = null;
	 try{
             conn = MySQLConexion.getConexion();
             String sql = "SELECT id, hinicio, hfin FROM horario_dias_especialidad \n"
                     + "WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, id); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                h=new HorarioDiaEspecialidad();
                h.setId(rs.getString(1));
                h.setHinicio(rs.getString(2).substring(0, 5));
                h.setHfin(rs.getString(3).substring(0, 5));
            }	
	 }catch(Exception ex){
		 ex.printStackTrace();
	 }finally{
			try {			
				if(conn!= null) conn.close();
			} catch (Exception e2) {}
		}
	return h;	
	}
        
    public void adMedHorario(MedicoAtencion ma) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "{call spAddMedAtencion(?,?)}";
            CallableStatement st = conn.prepareCall(sql);
            st.setString(1, ma.getCodmed());
            st.setString(2, ma.getIdHorario());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Horario asignado exitosamente");
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    public void delMedHorario(MedicoAtencion ma) {
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "{call spDelMedAtencion(?,?)}";
            CallableStatement st = conn.prepareCall(sql);
            st.setString(1, ma.getCodmed());
            st.setString(2, ma.getIdHorario());
            st.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Horario de médico eliminado");

        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }
    }
    
    /*    public String genCodHor() {
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
    }*/
     public List<String> horariosOcupadosporDia(String day, String codmed) {
        List<String> lis=new ArrayList<>();
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "select hora from citas where feccit=? and codmed=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, day);
            st.setString(2, codmed);
            ResultSet rs = st.executeQuery();   
            System.out.println("LISTA DE OCUPADO");
            while (rs.next()) {
                lis.add(rs.getString(1).substring(0, 5));
            System.out.print("j: "+rs.getString(1).substring(0, 5));   
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
