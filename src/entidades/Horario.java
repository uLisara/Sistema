
package entidades;



public class Horario {
    
    private int idHorario;    
    private String detalle;

    public Horario() {
    }

    public Horario(int idHorario, String detalle) {
        this.idHorario = idHorario;
        this.detalle = detalle;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }



}
