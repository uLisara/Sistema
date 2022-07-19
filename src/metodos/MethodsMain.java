

package metodos;

import com.toedter.calendar.JDateChooser;
import entidades.*;
import daos.*;
import entidades.Medico;
import java.awt.Desktop;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import util.Utils;
import vistaa.VMedico;



public class MethodsMain {
    private static final String URLIcono="/imagenes/iconoSistema.png";
    private static final String URLDocF="/imagenes/docF.png";
    private static final String URLDocM="/imagenes/docM.png";
    private static final String URLAdmiM="/imagenes/admiM.png";
    private static final String URLAdmiF="/imagenes/admiFe.png";
    protected DAOLog daoL=new DAOLog();
    protected DAORegistro daoR=new DAORegistro();
    protected DAOCitas daoC=new DAOCitas();
    protected DAOHorario daoH=new DAOHorario();
    
    public MethodsMain() {
       
    }
    
    public void esIcono(JFrame loge){
         loge.setIconImage(new ImageIcon(getClass().getResource(URLIcono)).getImage());
     }
    
    
    public void openJFrame(JFrame fr, String titulo){
        fr.setTitle(titulo);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
        esIcono(fr);
    }
    
    public void welcome(Usuario user,JButton btn,JLabel label,String nom,int ind){
        String inicio = "";
        if(user.getSexo()== 'F') inicio = "Bienvenida, ";
        if(user.getSexo()== 'M') inicio = "Bienvenido, ";
        
        if(user instanceof Medico ){
            if(ind==1){
                nom=daoL.busMed(((Medico) user).getCodmed()).getNombre();
            }
            if (user.getSexo() == 'F') addIMG(btn, URLDocF);
            if (user.getSexo() == 'M') addIMG(btn, URLDocM);
        }
        if(user instanceof Administrador ){
            if(ind==1){
                nom=daoL.busAdmi(((Administrador) user).getCodad()).getNombre();
            }
            if (user.getSexo() == 'F') addIMG(btn, URLAdmiF);
            if (user.getSexo() == 'M') addIMG(btn, URLAdmiM);
        }
        label.setText(inicio + nom.split(" ")[0].trim());
    }
    
    public void addIMG(JButton btn, String url){
        btn.setIcon(new ImageIcon(getClass().getResource(url)));
    }
    
    public void configUser(Usuario user,JTextField jtfNom ,JDateChooser jdcNac, JTextField jtfCorreo, JTextField jtfPswd){
       String nom="",correo="",fecha="",pswd="";
        if(user instanceof Medico){
           Medico med=daoL.busMed(((Medico) user).getCodmed());
           nom=med.getNombre(); correo=med.getCorreo(); fecha=med.getFecha(); pswd=med.getPswd();
       }
        if(user instanceof Administrador){
          Administrador admi=daoL.busAdmi(((Administrador) user).getCodad());
          nom=admi.getNombre(); correo=admi.getCorreo(); fecha=admi.getFecha(); pswd=admi.getPswd();
       }
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaconv = null;
        jtfNom.setText(nom);
        jtfCorreo.setText(correo);
        try {
                fechaconv = formato.parse(fecha);
                jdcNac.setDate(fechaconv);
            } catch (Exception ex) {
                System.out.println("F");
            }
        jtfPswd.setText(pswd);
        
    }
    
    
     public void mostrarEsp(JComboBox jcb){
            jcb.removeAllItems();
            jcb.addItem(Utils.SL);
            List<String> lisEsp = daoR.lisNomEsp();
            int max = lisEsp.size();
            for (int i = 0; i < max; i++) {
                jcb.addItem(lisEsp.get(i));
            }
        }
     
     public void mostrarHor(JComboBox jcb,int tipo,String var){
         //jcb.;
         jcb.removeAllItems();
         jcb.addItem(Utils.SL);
         //tipo 1: todos, tipo 2: horarios de mayor rango
         List<String> lisHorarios=null;
         switch(tipo){
             case 1: lisHorarios=daoH.allHorarios();break;
             //variable:horario
             case 2: lisHorarios=daoH.allHorariosMenores(var);break; 
             
          }
         String cad=""; 
         int max=lisHorarios.size();
         if(max>0){      
              for (int i = 0; i < max; i++) {
                 cad = "";
                 cad = lisHorarios.get(i).substring(0, 5);
                 jcb.addItem(cad);
             }
         } 
     } 
     
     public void mostHorioCitas(JComboBox jcb,List<String> name){
         //jcb.;
         jcb.removeAllItems();
         jcb.addItem(Utils.SL);
         //tipo 1: todos, tipo 2: horarios de mayor rango
         String cad=""; 
         int max=name.size();
         if(max>0){      
              for (int i = 0; i < max; i++) {
                 cad = "";
                 cad = name.get(i).substring(0, 5);
                 jcb.addItem(cad);
             }
         } 
     }
     
     public void mostrarHorSetModel(){
         
     }
     
     public String mostrarCodEsp(JComboBox jcb){
         String esp=jcb.getSelectedItem().toString();
         return daoR.busCodEsp(esp);
     }
    
     public void support(String direc){
        if(Desktop.isDesktopSupported()){
               Desktop desk=Desktop.getDesktop();
               if(desk.isSupported(Desktop.Action.BROWSE)){
                   try{
                   URI uri=new URI(direc);
                   desk.browse(uri);
                   }catch (Exception ex) {
                       
                   }
               }
           }
    }
    
   public void actDatosUser(Usuario user, String cod, JDateChooser fac, String nom, String correo, String pswd){
       SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
       String nac=formato.format(fac.getDate());
        daoL.modUser(user,cod,nom, nac,correo,pswd);
    }
   
   public void ajustarColumns(JTable jt, int ind, int siz){
       TableColumn col;
       col=jt.getColumnModel().getColumn(ind);
       col.setPreferredWidth(siz);
   }
   
   public void editarEstado(JTable jt, int ind){
       String[] datos={"Pendiente","Cancelado","Postergado"};
       JComboBox jcb=new JComboBox(datos);
       TableColumn tcol=jt.getColumnModel().getColumn(ind);
       TableCellEditor tce= new DefaultCellEditor(jcb);
       tcol.setCellEditor(tce);
   }
   
   public void f5EstadoCita(JTable jt, int indEs) {
        int col = jt.getRowCount();
        List<Cita> arr = new ArrayList();
        Cita cita;
        Object obEstado, obID;
        String estado = "", id = "";
        for (int j = 0; j < col; j++) {
            obEstado = jt.getValueAt(j, indEs);
            obID = jt.getValueAt(j, 0);
            estado = obEstado.toString();
            id = obID.toString();
            cita = new Cita();
            cita.setIdCita(id);
            cita.setEstadopac(estado);
            arr.add(cita);
         }
    
        daoC.actEstadoCita(arr);
    }

       public boolean validarCorreoNomDNI(int tipo, JTextField txtF, JLabel label){
       String path="",msj="";
        switch (tipo){
            //correo
            case 1: path="^[^@]+@[^@]+\\.[a-zA-Z]{2,}$";
                    msj="Correo inválido";break;
            //dni 
            case 2:path="[0-9]{8}";
                   msj="DNI inválido";break;
            //nombres
            case 3:path="^[A-Za-z\\s]{12,50}+$";
                   msj="Nombre no cumple parámetros requeridos";break;

        }
        //vAdmi.alertCorreo
         if(methodPattern(txtF.getText(),path)==true){
                 label.setText("");
                 return true;
         }else{ 
                 label.setText(msj);
                 return false;
              }
        
    } 
       
    public boolean validarCorreoNumDNIPac(int tipo, JTextField txtF, JLabel label){
       String path="",msj="";
        switch (tipo){
            //dni
            case 1:path="[0-9]{8}";
                   msj="DNI inválido";break;
            //nombres
            case 2:path="^[a-zA-Z]+[\\-'\\s]?[a-zA-Z]{10,80}+$";
                   msj="Nombre no cumple parámetros requeridos";break; 
            // numero 
                  case 3:path="[0-9]{9}";
                   msj="Número inválido";break;

        } 
         if(methodPattern(txtF.getText(),path)==true){
                 label.setText("");
                 return true;
         }else{ 
                 label.setText(msj);
                 return false;
              }
    }   
        
    
       
          public boolean methodPattern(String comparacion, String path){
       //^[^@]+@[^@]+\.[a-zA-Z]{2,}$
       //^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})S
       Pattern patron= Pattern.compile(path);
       Matcher comparar=patron.matcher(comparacion);
       if(comparar.matches()){
           return true;
       }else{
           return false;
       }
       //return comparar.find();
   } 
   
   public boolean esContraseña(String contra, JLabel txtAlert){
       String password=contra; int sum=0;
       char clave;
         byte  contNumero = 0, contLetraMay = 0, contLetraMin=0, contEspecial=0;
       for (byte i = 0; i < password.length(); i++) {
                clave = password.charAt(i);
               String passValue = String.valueOf(clave);
                if (passValue.matches("[A-Z]")) {
                    contLetraMay++;
                } else if (passValue.matches("[a-z]")) {
                    contLetraMin++;
                } else if (passValue.matches("[0-9]")) {
                    contNumero++;
                }else if (passValue.matches("[@,%,.,$]")) {
                    contEspecial++;
                }
        }
       sum=contNumero+contLetraMay+contLetraMin+contEspecial;
       System.out.println("(AZ)"+contLetraMay+" az:"+contLetraMin+" 0-9:"+contNumero+" speci:"+contEspecial);
       if(contNumero>0 && contLetraMay>0 && contLetraMin>0 && contEspecial>0 && sum>=8 && sum<=12 ){
           txtAlert.setText(""); 
           return true;
       }
       else{
           txtAlert.setText("Contraseña no cumple los parámetros");
           return false;
       }
   }

   
}
