

package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import daos.*;import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vistaa.*; import metodos.*;


public class CtrlLogueo implements ActionListener, MouseListener{
    VLogueo log;
    MethodsLog mLog;
    DAOLog daoL=new DAOLog();
    VLogueo log2=new VLogueo();    
    
    public CtrlLogueo(VLogueo log){
        this.log=log;  mLog=new MethodsLog(this.log);
        mLog.openJFrame(this.log,"Centro de Salud San Luis");
        this.log.btnAcceder.addActionListener(this);
        //btn principal de salida
        this.log.btnLogout.addActionListener(this);
        //this.log.btnRegistrar.addActionListener(this);
        
        /*this.log.btnRetornar.addActionListener(this);
        this.log.btnSalir.addActionListener(this);
        this.log.btnRegistro.addActionListener(this);*/
        
        this.log.jmAyuda.addMouseListener(this);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
       if(e.getSource()==log.btnLogout){
           System.exit(0);
        }
       
         if (e.getSource() == log.btnAcceder) {
            String pws = log.jpssContra.getText();
            String cr = log.jtxtCorreo.getText();
            if (daoL.validarUsuario(cr, pws, log).equals("f")) {
                JOptionPane.showMessageDialog(null, "No está registrado \n Usuario-Contraseña incorrecta");
            }
        }
        
         /*if(e.getSource()==log.btnRegistrar){
             mLog.mostrarEsp(log.jcbxEspecialidad1);
             log.setVisible(false);
             mLog.inRegistro();
        }*/
          if(e.getSource()==log.btnLogout){
           System.exit(0);
        }
         if(e.getSource()==log.btnSalir){
           System.exit(0);
        }  
          if(e.getSource()==log.btnRetornar){
           log.nuevRegistro.setVisible(false);
           mLog.openJFrame(log,"Sistema CliniMAX");
        }
          //evento de Aceptar registro. Llamar a DAO
         if(e.getSource()==log.btnRegistro){
             //int c=0;
             try{
                    mLog.obtenerDataV1();
                    if(mLog.getdCorreo().equals("") || mLog.getdPswd().equals("") || mLog.getdTip().equals("U000") || mLog.getdSexo()=='X'){
                       JOptionPane.showMessageDialog(null, "¡Faltan datos por ingresar!!");
                       
                   }else{
                       if(mLog.getdTip()=="U001"){
                       mLog.regAdmi();
                       JOptionPane.showMessageDialog(null, "Registrado con exito, Admi"); 
                       log.setVisible(false);log.nuevRegistro.setVisible(false);
                       CtrlLogueo ctLog=new CtrlLogueo(log2);
                       }
                       
                       if(mLog.getdTip()=="U002"&& log.jcbxEspecialidad1.getSelectedIndex()==0){
                           JOptionPane.showMessageDialog(null, "Ingrese especialidad");
                           log.jlEspecialidad.setVisible(true);
                           log.jcbxEspecialidad1.setVisible(true);
                          
                       }
                       //if(mlog.getdEsp().equals("-Seleccionar-"))c++;
                       if(mLog.getdTip()=="U002"&& log.jcbxEspecialidad1.getSelectedIndex()!=0){
                           mLog.regMed();
                           log.setVisible(false);log.nuevRegistro.setVisible(false);
                       CtrlLogueo ctLog=new CtrlLogueo(log2);
                           JOptionPane.showMessageDialog(null, "Registrado con exito, Doc"); 
                       }
                   }
                     
                 
              }catch (NullPointerException ex) {
              JOptionPane.showMessageDialog(null, "¡Faltan datos por ingresar!\nError: "+ex); 
             }catch(NumberFormatException exx){
                     JOptionPane.showMessageDialog(null, "¡Inserte correctamente su DNI!\nError: "+exx.getMessage()); 
                 }
             
         }    
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==log.jmAyuda){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nIngrese los datos que desea que tenga su perfil y en caso sea médico, elija la especialidad"
                    + "\ndespués presione en el botón registrar para procesar sus datos. Que tenga un excelente día ";
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
