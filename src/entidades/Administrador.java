
package entidades;

public class Administrador extends Usuario {
    private String codad;  

    public Administrador() {
       
    }

    public Administrador(String iptip, String fecha, String nombre, char sexo, String correo, String pswd, int dni) {
        super(iptip, fecha, nombre, sexo, correo, pswd, dni);
    }
   

    public String getCodad() {
        return codad;
    }

    public void setCodad(String codad) {
        this.codad = codad;
    }

    
   

   
    
    
    
}
