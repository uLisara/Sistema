
package entidades;

public class Paciente {
    
    private int dni;
    private String idtip;
    private String nomp;
    private int numero;

    public Paciente() {
    }

    public Paciente(int dni, String idtip, String nomp, int numero) {
        this.dni = dni;
        this.idtip = idtip;
        this.nomp = nomp;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getIdtip() {
        return idtip;
    }

    public void setIdtip(String idtip) {
        this.idtip = idtip;
    }

    public String getNomp() {
        return nomp;
    }

    public void setNomp(String nomp) {
        this.nomp = nomp;
    }
    
    
}
