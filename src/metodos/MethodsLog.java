

package metodos;
import entidades.Administrador;
import entidades.Medico;
import vistaa.*;import controlador.*;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.*;import java.util.*;
import daos.*;
import java.text.SimpleDateFormat;




public class MethodsLog extends MethodsMain{
    
    VLogueo log;
    VAdmi vAdmi;
    String dNom; String dNac; private String dTip;int dDNI;private String dCorreo;private String dPswd;
    private String dEsp; private char dSexo;
    SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
    
    public MethodsLog(){}
    
    public MethodsLog(VLogueo log){
        this.log=log;
    }
    
    public MethodsLog(VAdmi vAdmi){
        this.vAdmi=vAdmi;
    }
    
     /*public void inRegistro(){
        log.nuevRegistro.setTitle("Registro");
        log.nuevRegistro.setLocationRelativeTo(null);
        log.nuevRegistro.setVisible(true);        
        esIcono(log.nuevRegistro);
        log.jlEspecialidad.setVisible(false);
        log.jcbxEspecialidad1.setVisible(false);
     }*/
     
      
        public void obtenerDataV1(){
        dNom=log.txtNombre.getText();
        dNac=formato.format(this.log.dateNacimiento.getDate());
        dDNI=Integer.parseInt(this.log.txtDNI.getText());
        if(log.jrbMasculino.isSelected()) {
            dSexo='M';
        } else if(log.jrbFemenino.isSelected()) {
            dSexo='F';
        } else dSexo='X';
        dCorreo=log.txCorreo.getText();
        dPswd=log.txContra.getText();
        String tipo="";
        tipo=log.jcbTipoUser.getSelectedItem().toString();
        if(tipo.equals("Medico")) dTip="U002";
        if(tipo.equals("Administrador")) dTip="U001";
        if(tipo.equals("-Seleccionar-")) dTip="U000";
        if(dTip.equals("U002")){
            dEsp=log.jcbxEspecialidad1.getSelectedItem().toString();
        }
        }
        
        public void obtenerDataVAdmi(){
        dNom=vAdmi.txtNombre1.getText();
        dNac=formato.format(this.vAdmi.dateNacimiento.getDate());
        dDNI=Integer.parseInt(this.vAdmi.txtDNI1.getText());
        if(vAdmi.jrbMasculino.isSelected()) {
            dSexo='M';
        } else if(vAdmi.jrbFemenino.isSelected()) {
            dSexo='F';
        } else dSexo='X';
        dCorreo=vAdmi.txCorreo.getText();
        dPswd=vAdmi.txContra.getText();
        String tipo="";
        tipo=vAdmi.jcbTipoUser.getSelectedItem().toString();
            System.out.println("TipoUsuario: "+tipo);
        if(tipo.equals("Medico")) dTip="U002";
        if(tipo.equals("Administrador")) dTip="U001";
        if(tipo.equals("-Seleccionar-")) dTip="U000";
        if(dTip.equals("U002")){
            dEsp=vAdmi.jcbxEspecialidad1.getSelectedItem().toString();
        }
        }
        
        public void regAdmi(){
            Administrador a=new Administrador(dTip,dNac, dNom, dSexo,dCorreo, dPswd, dDNI);
            daoR.addAdmi(a);
         }
        
              
      public void regMed(){
            String codes=daoR.busCodEsp(dEsp);
            String idhorario="";
            Medico md=new Medico(codes, dTip, dNac,dNom,dSexo, dCorreo, dPswd, dDNI,idhorario,"NO");
            daoR.addMed(md);
           //System.out.println("AGREGANDO MEDICO");
        }

    public String getdTip() {
        return dTip;
    }

    public String getdCorreo() {
        return dCorreo;
    }

    public String getdPswd() {
        return dPswd;
    }

    public String getdEsp() {
        return dEsp;
    }

    public char getdSexo() {
        return dSexo;
    }

   
}
