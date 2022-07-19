

package testeoAsistencia;

import vistaa.*;
import controlador.*;
import daos.*;
import entidades.HorarioDiaEspecialidad;
import java.util.List;
import java.util.Map;
//
public class main {
    public static void main(String[] args) {
      
        
      DAOCitas daoC=new DAOCitas();
      Map<String, Integer> m=daoC.contEspReg();
        System.out.println("Cant: "+m.get("Obstetricia"));
        
      
    }
}
