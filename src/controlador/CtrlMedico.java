
package controlador;
import entidades.Medico;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import daos.*;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vistaa.*; import metodos.*;

public class CtrlMedico implements ActionListener, MouseListener {
    VMedico vMed; 
    MethodsMed metMed=new MethodsMed();
    Medico objMed;
    DAOLog daoL = new DAOLog();
    
    public CtrlMedico(VMedico vMed, Medico objMed){
        this.vMed = vMed; this.objMed=objMed;
        //marcar ASIS
        daoL.actAsis(this.objMed.getCodmed(), "SI");
        metMed.welcome(this.objMed, this.vMed.btnImagen, this.vMed.jLBienvenidoD, this.objMed.getNombre(),0);
        //metMed.inHorario(this.objMed.getNombre(), this.vMed.jLDiasAtencion, this.vMed.jLHorariosAtencion);
        metMed.openJFrame(this.vMed, "MEDICO");
        this.vMed.btnConfigUser.addActionListener(this);
        this.vMed.btnLogout.addActionListener(this);
        this.vMed.btnReportes.addActionListener(this);
        this.vMed.btnVolver.addActionListener(this);
        this.vMed.btnActPerfil.addActionListener(this);
        this.vMed.btnLogout1.addActionListener(this);
        this.vMed.btnVolver1.addActionListener(this);
        this.vMed.btnActCitas.addActionListener(this);
        //SUPPORT, falta implementar
        this.vMed.jmAyuda.addMouseListener(this);
        this.vMed.jmAyuda2.addMouseListener(this);
        this.vMed.jmAyuda3.addMouseListener(this);
    }
                         
     
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vMed.btnLogout) {
            System.exit(0);
        }

        if (e.getSource() == vMed.btnConfigUser) {
            metMed.configUser(objMed,vMed.configNom, vMed.configNac, vMed.configCorreo, vMed.configPswd);
            vMed.setVisible(false);
            metMed.openJFrame(vMed.jfConfigP, "Configurar Usuario");
            
        }
        if (e.getSource() == vMed.btnReportes) {
            metMed.visualizarReportCita(vMed.tablaReporte, objMed.getCodmed());
            vMed.setVisible(false);
            metMed.openJFrame(vMed.jfCitasMed, "Citas");
        }
        
        if (e.getSource() == vMed.btnVolver) {
            vMed.jfConfigP.setVisible(false);
            metMed.openJFrame(vMed, "MEDICO");
        }
        if (e.getSource() == vMed.btnActPerfil) {
                if(vMed.configNom.getText().equals("")||vMed.configPswd.getText().equals("")||vMed.configCorreo.getText().equals("")||vMed.configNac==null){
                JOptionPane.showMessageDialog(null, "Faltan datos por ingresar");
                }else {
                    if(metMed.validation(vMed)){                    
            metMed.actDatosUser(objMed, objMed.getCodmed(), 
                    vMed.configNac, vMed.configNom.getText(), 
                    vMed.configCorreo.getText(), vMed.configPswd.getText());
            metMed.welcome(objMed,vMed.btnImagen, vMed.jLBienvenidoD, "",1);
            JOptionPane.showMessageDialog(null, "Datos actualizados");
        }
                }
        }
    

        if (e.getSource() == vMed.btnLogout1) {
            System.exit(0);
        }

        if (e.getSource() == vMed.btnVolver1) {
            vMed.jfCitasMed.setVisible(false);
            metMed.openJFrame(this.vMed, "MEDICO");
        }
        if (e.getSource() == vMed.btnActCitas) {
            //indicar la posición de la columna de Estado de Cita
            metMed.f5EstadoCita(vMed.tablaReporte,4);
            metMed.visualizarReportCita(vMed.tablaReporte, objMed.getCodmed());
            JOptionPane.showMessageDialog(null, "Actualización exitosa");
        }
        
}

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==vMed.jmAyuda){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nEn esta ventana se pueden apreciar los datos principales del usuario médico que ingresó"
                    + "\n1-. El primer botón que es el de citas lo redirigirá a una ventana en la que el médico "
                    + "\n    podrá ver las citas pendientes que tiene con las fechas respectivas"
                    + "\n2-. El segundo botón que es el de configurar perfil, que te redirige a una ventana en la"
                    + "\n    que se se pueden modificar ciertos datos del usuario";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vMed.jmAyuda2){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nEn esta ventana se puede modificar "
                    + "\n1-. En este apartado puede apreciar una tabla que guarda la información de las citas programadas"
                    + "\n2-. El botón de actualizar mantiene al día los datos de la tabla en caso se haga alguna modificación"
                    + "\n    reciente."
                    + "\n3-. Para activar el mecanismo de actualizar, primero se tiene que modificar la tabla, en la columna "
                    + "\n    'Estado' y la fila que pertenezca a la cita a la que le quiere editar el estado, existen 3 tipos"
                    + "\n    de estado, que son el Pendiente, que viene por defecto al agregar una cita, el postergado, cuando"
                    + "\n    el médico o el paciente postergan la cita, y el cancelado, cuando la cita queda cancelada, para que"
                    + "\n    esta última tenga efecto y elimine la cita, se debe presionar el botón 'Actualizar' inmediatamente"
                    + "\n    después de que se modifique el dato en la tabla, para realizar la eliminación de la cita.";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vMed.jmAyuda3){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nEn esta ventana se pueden modificar los datos del médico, tales como Nombre, Fecha de Nacimiento,"
                    + "\ncorreo y contraseña, y luego de eso presionar el botón actualizar para procesar la modificación";
            JOptionPane.showMessageDialog(null, mensaje);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //
    }
}
