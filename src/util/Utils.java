
package util;

public class Utils {
    
    public static final String L="Lunes";
    public static final String Ma="Martes";
    public static final String Mi="Miércoles";
    public static final String J="Jueves";
    public static final String V="Viernes";
    public static final String S="Sábado";
    public static final String D="Domingo";
    public static final String SL="-Seleccionar-"; 
    
    public String metNameDay(int numDay){
        String day="";
         switch (numDay) {
            case 1:
                day = Utils.L;
                break;
            case 2:
                day = Utils.Ma;
                break;
            case 3:
                day = Utils.Mi;
                break;
            case 4:
                day = Utils.J;
                break;
            case 5:
                day = Utils.V;
                break;
            case 6:
                day = Utils.S;
                break;
            case 7:
                day = Utils.D;
                break;

        }
        return day;
    }
}
