
package metodos;
import vistaa.*;import daos.*;
import controlador.*;
import entidades.*;
import java.awt.Image;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MethodsMed extends MethodsMain{
    
    List<Cita> calc = new ArrayList();
    DefaultTableModel tabl;

    public MethodsMed() {
    }
    
    public void visualizarReportCita(JTable tabla, String cod) {
        String[] cab1 = {"ID", "Fecha", "Hora", "Nombre Paciente", "Estado"};
        String[][] data1 = {};
        tabl = new DefaultTableModel(data1, cab1);
        tabla.setModel(tabl);
        calc = daoC.lisReportCita(cod); 
        for (Cita x : calc) {
            String[] fila = {x.getIdCita(), x.getDiacit(), x.getHoracit(), x.getNompac(), x.getEstadopac()};
            tabl.addRow(fila);
        }
        ajustarColumns(tabla, 3, 160);
        ajustarColumns(tabla, 4, 90);
        editarEstado(tabla, 4);
    }
    
   /* public void inHorario(String nom, JLabel dias, JLabel horas){
        Horario hor=daoH.busHorario(nom,1);
        if(hor!=null){
        dias.setText(hor.getDias().toUpperCase());
        horas.setText(hor.getHinicio() + "-" + hor.getHfin());}
        else{
            JOptionPane.showMessageDialog(null, "¡Coordine con el administrador para que le asigne un horario!");
            dias.setText("No asignado");
            horas.setText("No asignado");
        }
            
    }*/
    
    public boolean validation(VMedico vMed){
        boolean value=true;
    
                if (!validarCorreoNomDNI(3, vMed.configNom, vMed.txtAlertNombre)) {
                    value = false;
                }
                if (!validarCorreoNomDNI(1, vMed.configCorreo, vMed.txtAlertCorreo)) {
                    value = false;
                }
                if (!esContraseña(vMed.configPswd.getText(), vMed.txtAlertContra)) {
                    value = false;
                }          
        
        return value;
    }
    
   

    
}
