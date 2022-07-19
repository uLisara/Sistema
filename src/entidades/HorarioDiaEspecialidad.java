
package entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import util.MySQLConexion;
import util.Utils;

public class HorarioDiaEspecialidad {
    
    private String id;
    private String codes;
    private String dia; 
    private String flagEstado; 
    private String hinicio;
    private String hfin;

    public HorarioDiaEspecialidad() {
    }

    public HorarioDiaEspecialidad(String id, String codes, String dia, String flagEstado,String hinicio, String hfin) {
        this.codes = codes;
        this.dia = dia;
        this.flagEstado = flagEstado;
        this.hinicio=hinicio;
        this.hfin=hfin;
        this.id=id;
    }
        public HorarioDiaEspecialidad(String codes, String dia, String flagEstado,String hinicio, String hfin) {
        this.codes = codes;
        this.dia = dia;
        this.flagEstado = flagEstado;
        this.hinicio=hinicio;
        this.hfin=hfin;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFlagEstado() {
        return flagEstado;
    }

    public void setFlagEstado(String flagEstado) {
        this.flagEstado = flagEstado;
    }

    public String getHinicio() {
        return hinicio;
    }

    public void setHinicio(String hinicio) {
        this.hinicio = hinicio;
    }

    public String getHfin() {
        return hfin;
    }

    public void setHfin(String hfin) {
        this.hfin = hfin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String toString(){
        return this.dia;
    }
    
    public Vector<HorarioDiaEspecialidad> mostrarHorDiaEsp(String _codes){
                Connection conn = null;
                
        Vector<HorarioDiaEspecialidad> vecHorDiaEsp=new Vector<HorarioDiaEspecialidad>();
        HorarioDiaEspecialidad dat=null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT * FROM horario_dias_especialidad WHERE codes=? and flagEstado='1' and flagAssig='0'";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, _codes);
            ResultSet rs = st.executeQuery();  
            
            dat=new HorarioDiaEspecialidad();
            dat.setId("0");
            dat.setDia(Utils.SL);
            vecHorDiaEsp.add(dat);
            
            while (rs.next()) {
            dat=new HorarioDiaEspecialidad();
            dat.setId(rs.getString("id"));
            dat.setDia(rs.getString("dia"));
            vecHorDiaEsp.add(dat);
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
        return vecHorDiaEsp;
    }
    
    
    
}
