
package entidades;

public class Cita {
    private String codmed;
    private int dnipac;
    private String estadopac;
    private String diacit;
    private String horacit;
    private String idCita;
    private String nompac;
    private String esp;

    public Cita() {
    }

    public Cita(String codmed, int dnipac, String estadopac, String diacit, String horacit, String idCita, String nompac, String esp) {
        this.codmed = codmed;
        this.dnipac = dnipac;
        this.estadopac = estadopac;
        this.diacit = diacit;
        this.horacit = horacit;
        this.idCita = idCita;
        this.nompac=nompac;
        this.esp=esp; 
    }

    public String getCodmed() {
        return codmed;
    }

    public void setCodmed(String codmed) {
        this.codmed = codmed;
    }

    public int getDnipac() {
        return dnipac;
    }

    public void setDnipac(int dnipac) {
        this.dnipac = dnipac;
    }

    public String getEstadopac() {
        return estadopac;
    }

    public void setEstadopac(String estadopac) {
        this.estadopac = estadopac;
    }

    public String getDiacit() {
        return diacit;
    }

    public void setDiacit(String diacit) {
        this.diacit = diacit;
    }

    public String getHoracit() {
        return horacit;
    }

    public void setHoracit(String horacit) {
        this.horacit = horacit;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getNompac() {
        return nompac;
    }

    public void setNompac(String nompac) {
        this.nompac = nompac;
    }
    
    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }
}
