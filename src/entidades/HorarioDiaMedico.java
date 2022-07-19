
package entidades; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import util.MySQLConexion;
import util.Utils;

public class HorarioDiaMedico { 
    
  
    private String codes;
    private String dia; 
    private String flagEstado; 
    private String hinicio;
    private String hfin; 

    public HorarioDiaMedico() {
    }

    public HorarioDiaMedico(String codes, String dia, String flagEstado, String hinicio, String hfin) {
        this.codes = codes;
        this.dia = dia;
        this.flagEstado = flagEstado;
        this.hinicio = hinicio;
        this.hfin = hfin;
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
    
    
    
}
