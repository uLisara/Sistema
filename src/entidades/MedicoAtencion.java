
package entidades;

public class MedicoAtencion {
    private String codmed;
    private String dia;
    private String flagestado;
    private String idHorario;

    public MedicoAtencion() {
    }

    public MedicoAtencion(String codmed, String dia, String flagestado, String idHorario) {
        this.codmed = codmed;
        this.dia = dia;
        this.flagestado = flagestado;
        this.idHorario = idHorario;
    }

    public String getCodmed() {
        return codmed;
    }

    public void setCodmed(String codmed) {
        this.codmed = codmed;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFlagestado() {
        return flagestado;
    }

    public void setFlagestado(String flagestado) {
        this.flagestado = flagestado;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }
    
    
}
