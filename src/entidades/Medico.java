

package entidades;

public class Medico extends Usuario {
    private String codmed;
    private String codes; 
    private String asistencia;
    private String flagHorario;
    private String nomEsp;
   public Medico(){
          }

    public Medico(String codes, String iptip, String fecha, String nombre, char sexo, String correo, String pswd, int dni,String idhorario,String asistencia ) {
        super(iptip, fecha, nombre, sexo, correo, pswd, dni);
        this.codes = codes;this.asistencia=asistencia;
    }

    public Medico(String codmed, String codes, String asistencia, String flagHorario, String nomEsp, String iptip, String fecha, String nombre, char sexo, String correo, String pswd, int dni) {
        super(iptip, fecha, nombre, sexo, correo, pswd, dni);
        this.codmed = codmed;
        this.codes = codes;
        this.asistencia = asistencia;
        this.flagHorario = flagHorario;
        this.nomEsp = nomEsp;
    }  

    public Medico(String codmed, String codes, String asistencia, String flagHorario, String iptip, String fecha, String nombre, char sexo, String correo, String pswd, int dni) {
        super(iptip, fecha, nombre, sexo, correo, pswd, dni);
        this.codmed = codmed;
        this.codes = codes;
        this.asistencia = asistencia;
        this.flagHorario = flagHorario;
    }    

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getCodmed() {
        return codmed;
    }

    public void setCodmed(String codmed) {
        this.codmed = codmed;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getFlagHorario() {
        return flagHorario;
    }

    public void setFlagHorario(String flagHorario) {
        this.flagHorario = flagHorario;
    }

    public String getNomEsp() {
        return nomEsp;
    }

    public void setNomEsp(String nomEsp) {
        this.nomEsp = nomEsp;
    }

    
    
    
    
    
}
