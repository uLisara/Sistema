
package controlador;
import entidades.Administrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import daos.*;import entidades.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vistaa.*; import metodos.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.LocalDate.now;
import java.time.ZoneId;
import java.util.*;
import javax.swing.*;
import util.Utils;

public class CtrlAdmin implements ActionListener, MouseListener{

    VAdmi vAdmi; 
    MethodsAdmi metAdmi;
    MethodsLog metLog;
    DAOHorario daoH=new DAOHorario();
    Administrador objAdmi;
    SimpleDateFormat fordia=new SimpleDateFormat("yyyy-MM-dd");
    VAdmi vAdmi2=new VAdmi(); 

    
       public CtrlAdmin(VAdmi vAdmi, Administrador objAdmi){
        this.vAdmi = vAdmi; this.objAdmi=objAdmi; 
        metAdmi=new MethodsAdmi(this.vAdmi);
        metLog=new MethodsLog(this.vAdmi);
        //JOptionPane.showMessageDialog(null, "Espere 10 segundos, por favor");
        metAdmi.welcome(this.objAdmi, this.vAdmi.btnImagen, this.vAdmi.jLBienvenidoA, this.objAdmi.getNombre(),0);
        metAdmi.inJcbxEspecialidad();
        metAdmi.inJcbxHorarios();
        metAdmi.nuevosMedicos();
        metAdmi.openJFrame(this.vAdmi,"ADMINISTRADOR");
        //BOTONES
        this.vAdmi.btnConfigUser.addActionListener(this);
        this.vAdmi.btnLogout.addActionListener(this);
        this.vAdmi.btnSalir2.addActionListener(this);
        this.vAdmi.btnSalir3.addActionListener(this);
        this.vAdmi.btnReportes.addActionListener(this);
        this.vAdmi.btnNuevoP.addActionListener(this);
        this.vAdmi.btnRetornar1.addActionListener(this);
        this.vAdmi.btnRetornar2.addActionListener(this);
        this.vAdmi.btnRetornar3.addActionListener(this);
        this.vAdmi.btnRetornar5.addActionListener(this);
        this.vAdmi.btnRetornar6.addActionListener(this);
        this.vAdmi.btnVolverAsigH.addActionListener(this);
        this.vAdmi.btnBusPaciente.addActionListener(this);
        this.vAdmi.btnRegistrarCita.addActionListener(this);
        this.vAdmi.btnAsistenciaM.addActionListener(this);
        this.vAdmi.btnReiniciar.addActionListener(this);
        this.vAdmi.btnMostrarCita.addActionListener(this);
        //this.vAdmi.btnHorario.addActionListener(this);
        this.vAdmi.btnActPerfil.addActionListener(this);
        this.vAdmi.btnActReportes.addActionListener(this);
        
        //btns Horario
        //guardar nuevo horario
        this.vAdmi.btnCreateHorarios.addActionListener(this);
        this.vAdmi.jcbxHInicio.addActionListener(this);
        
        this.vAdmi.btnActCosto.addActionListener(this);
        this.vAdmi.btnCleanNC.addActionListener(this);
        this.vAdmi.btnHorarioCostos.addActionListener(this);
        this.vAdmi.btnAsigHor.addActionListener(this);
        this.vAdmi.btnSalirAsigH.addActionListener(this);
        
        //listar horarios
        this.vAdmi.jcbxEspHorariosList.addActionListener(this);
        //actualizar horairo
        this.vAdmi.btnActHorario.addActionListener(this);
        
        //btns Limpiar
        //this.vAdmi.btnCleanList.addActionListener(this);
        this.vAdmi.btnCleanCreateHorario.addActionListener(this);
        
        //Horarios-Medico
        this.vAdmi.btnBusMedicoAsigH.addActionListener(this);
        this.vAdmi.jcbDiasAsigH.addActionListener(this);
        
        this.vAdmi.btnActHorarioMedAsigH.addActionListener(this);
        this.vAdmi.btnVizHorarioMed.addActionListener(this);
        this.vAdmi.btnBusDiaHora.addActionListener(this);
        
        //////////////////////////////////////////////////
        
        
        //COMBO BOX
        this.vAdmi.jcbxEspecialidadNC.addActionListener(this);
        this.vAdmi.jcbxDoctorNC.addActionListener(this);
        this.vAdmi.jcbArea.addActionListener(this); 
        this.vAdmi.jcbMedicos.addActionListener(this);
        this.vAdmi.jcbxEspCosto.addActionListener(this);
        this.vAdmi.jcbxEspHorariosCreate.addActionListener(this);
        this.vAdmi.jcbxEspAsignarH.addActionListener(this);
        this.vAdmi.jcbxHoraInicioNuevaCita.addActionListener(this);
        //TABLAS
        this.vAdmi.jTHorarios.addMouseListener(this);
        this.vAdmi.jTableMedHor.addMouseListener(this);
        this.vAdmi.jTableAsigHorMed.addMouseListener(this);
        this.vAdmi.jtblHorarioNuevaCita.addMouseListener(this);
        
        //VALOR EXTRA - AYUDA 
        this.vAdmi.jmAyudaRC.addMouseListener(this);
        this.vAdmi.jmAyudaNC.addMouseListener(this);
        this.vAdmi.jmAyuda.addMouseListener(this);
        this.vAdmi.jmAyuda2.addMouseListener(this);
        this.vAdmi.jmAyuda3.addMouseListener(this);
        this.vAdmi.jmAyuda4.addMouseListener(this);
        this.vAdmi.jmAyuda5.addMouseListener(this);
        //btn registrar main 
        this.vAdmi.btnRetornar1.addActionListener(this);
        this.vAdmi.btnRegistrarPersonal.addActionListener(this);

        //registro personal
        this.vAdmi.btnRetornar.addActionListener(this);
        this.vAdmi.btnSalir.addActionListener(this);
        this.vAdmi.btnRegistro.addActionListener(this);
    } 
    
        
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vAdmi.btnLogout){
           System.exit(0);
        }
        if(e.getSource()==vAdmi.btnSalir2){
           System.exit(0);
        }
        if(e.getSource()==vAdmi.btnSalir3){
           System.exit(0);
        }
        if(e.getSource()==vAdmi.btnSalirAsigH){
           System.exit(0);
        }
        if(e.getSource()==vAdmi.btnConfigUser){
            metAdmi.configUser(objAdmi, vAdmi.configNombre, vAdmi.configNac, vAdmi.configCorreo, vAdmi.configPswd);
            vAdmi.setVisible(false);
            metAdmi.openJFrame(vAdmi.jfConfigP, "Configurar Usuario");
        }
        
        if(e.getSource()==vAdmi.btnReportes){
            vAdmi.setVisible(false);
            metAdmi.openJFrame(vAdmi.jfReporteCitas, "Citas"); 
          }
        
        /*if(e.getSource()==vAdmi.btnCleanList){
            vAdmi.jtxtIdHorario.setText("");
            vAdmi.jtxtDiasH.setText("");
            vAdmi.jtxtHoraEntrada.setText("");
            vAdmi.jtxtHoraSalida.setText("");
          }*/
        
        if(e.getSource()==vAdmi.btnRegistrarPersonal){      
            metAdmi.mostrarEsp(vAdmi.jcbxEspecialidad1);
             vAdmi.setVisible(false);
             metAdmi.inRegistro(); 
            
        }
         //redirección HORARIO MÉDICOS
         if(e.getSource()==vAdmi.btnAsigHor){
            metAdmi.cleanHorDisp(1);//limpiar todo caso=1
            vAdmi.jtxHoraInAsigH.setEditable(false);
            vAdmi.jtxHoraFinAsigH.setEditable(false);  
            vAdmi.jcbDiasAsigH.setEnabled(false);
             metAdmi.tablaBotonesExtra(vAdmi.jTableMedHor, "", 2);
            metAdmi.openJFrame(vAdmi.jfAsignarHorarios, "Horarios de Médicos");
            //bloquear botones de horario que nunca tendrá data editable

          }        
              
         /*
         TABLA HORARIOS
         if(e.getSource()== vAdmi.btnAddHorario){
           metAdmi.addHorarioN();
           metAdmi.tablaBotonesExtra(vAdmi.jTHorarios, vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString(),1);
          }       */
        
        if(e.getSource()==vAdmi.btnNuevoP){
            vAdmi.setVisible(false);
            //Ocultar datos de más en caso el paciente ya esté registrado
            vAdmi.lbNombre.setVisible(false); vAdmi.lbNumero.setVisible(false);
            vAdmi.txtNombrePac.setVisible(false);vAdmi.txtNumeroPac.setVisible(false);
            vAdmi.jtxtPrecioNC.setEditable(false);
            //vAdmi.taHorario.setEditable(false);
            metAdmi.openJFrame(vAdmi.jfNuevaCita, "Citas");
        } 
        
        if(e.getSource()==vAdmi.btnMostrarCita){
            metAdmi.visualizarListaCita(vAdmi.jTReportCitaA, vAdmi.jcbMedicos.getSelectedItem().toString());
            
        } 
        if (e.getSource() == vAdmi.jcbArea) {
            metAdmi.jcbxLisMedicos(vAdmi.jcbMedicos,vAdmi.jcbArea);
         }

        if(e.getSource()==vAdmi.jcbxEspecialidadNC){
            metAdmi.jcbxLisMedicos(vAdmi.jcbxDoctorNC,vAdmi.jcbxEspecialidadNC);
            metAdmi.costo(vAdmi.jcbxEspecialidadNC.getSelectedItem().toString(), vAdmi.jtxtPrecioNC);
         }
        if(e.getSource()==vAdmi.jcbxEspCosto){
           metAdmi.costo(vAdmi.jcbxEspCosto.getSelectedItem().toString(),vAdmi.jTextCosto);
        }
       
        /*0if(e.getSource()==vAdmi.btnGenCod){
           vAdmi.jtxtIdHorario.setText(daoH.genCodHor());
            //metAdmi.mostrarCosto();
        }
        
        if(e.getSource()==vAdmi.btnHorario){
            metAdmi.mostrarHor();
        }
        
        
        if(e.getSource()==vAdmi.jcbxEspHorariosCreate){
            metAdmi.tablaBotonesExtra(vAdmi.jTHorarios, vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString(),1);
            //IMPLEMENTAR LA LISTA 
        }
        
         /*if(e.getSource()==vAdmi.jcbxEspAsignarH){
            metAdmi.tablaShowSchedule();
        }*/
        
        if (e.getSource() == vAdmi.btnBusPaciente) { 
             if(validationsWithPatternsPac(2)==true){
              metAdmi.busPaciente();
          }
           //metAdmi.busPaciente();
        }
        
       if(e.getSource()==vAdmi.btnRegistrarCita){
            if(validationsWithPatternsPac(1)==true){
              metAdmi.regCita(this.vAdmi);
          }             // metAdmi.regCita(this.vAdmi);
        }

        
        if(e.getSource()==vAdmi.btnAsistenciaM){
        vAdmi.setVisible(false);
        metAdmi.openJFrame(vAdmi.jfAsisMedica,"Asistencia");
        metAdmi.inTablaAsisMed();
        }
        
        if(e.getSource()==vAdmi.btnReiniciar){
            metAdmi.resetAsis();
        }
        
      
        if(e.getSource()==vAdmi.btnActCosto){
            double c=metAdmi.actualizarCosto();
            vAdmi.jTextCosto.setText(String.valueOf(c));
        }
       
        if(e.getSource()== vAdmi.btnActReportes){
            metAdmi.f5EstadoCita(vAdmi.jTReportCitaA,5);
            metAdmi.visualizarListaCita(vAdmi.jTReportCitaA, vAdmi.jcbMedicos.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null, "Actualización exitosa");
        } 
        if (e.getSource()== vAdmi.btnCleanNC){
            metAdmi.limpiarNC(vAdmi);
        } 
        
        if (e.getSource()== vAdmi.btnRegistrarPersonal){
            //metAdmi.visualizarGraf();
        }
        
         //BotonesRetornar
        if(e.getSource()==vAdmi.btnRetornar1){
            vAdmi.setVisible(true);
            vAdmi.jfConfigP.setVisible(false);
        }        
        if(e.getSource()==vAdmi.btnRetornar2){
            vAdmi.setVisible(true);
            vAdmi.jfReporteCitas.setVisible(false);
        }
        
        if(e.getSource()==vAdmi.btnRetornar5){
            vAdmi.setVisible(true);
            vAdmi.jfAsisMedica.setVisible(false);
        }        
        if(e.getSource()==vAdmi.btnRetornar3){
            vAdmi.setVisible(true);
            vAdmi.jfNuevaCita.setVisible(false);
        }
        if(e.getSource()==vAdmi.btnRetornar6){
            vAdmi.setVisible(true);
            vAdmi.jfModHoraCoste.setVisible(false);
        }
        
         if(e.getSource()==vAdmi.btnVolverAsigH){
            //metAdmi.nuevosMedicos();
            vAdmi.setVisible(true);
            vAdmi.jfAsignarHorarios.setVisible(false);
        }
         //retorno desde vista de registro
          if(e.getSource()==vAdmi.btnRetornar){
           vAdmi.nuevRegistro.setVisible(false);
           metAdmi.openJFrame(vAdmi,"Centro de Salud San Luis");
        }
        
        //Actualizar Perfil
        if(e.getSource()==vAdmi.btnActPerfil){
            
            if(vAdmi.configNombre.getText().equals("")||vAdmi.configPswd.getText().equals("")||vAdmi.configCorreo.getText().equals("")||vAdmi.configNac==null){
                JOptionPane.showMessageDialog(null, "Faltan datos por ingresar");
        }else{
                if(validationsWithPatterns(2)){
           metAdmi.actDatosUser(objAdmi,objAdmi.getCodad(),vAdmi.configNac,
                                vAdmi.configNombre.getText(),vAdmi.configCorreo.getText(),
                                vAdmi.configPswd.getText());
           metAdmi.welcome(objAdmi, vAdmi.btnImagen,vAdmi.jLBienvenidoA, "",1);
            JOptionPane.showMessageDialog(null, "Datos actualizados");
            
        }}
        }
        
        //resgitrar
         /*if(e.getSource()==vAdmi.btnRegistrarCita){
             metAdmi.mostrarEsp(vAdmi.jcbxEspecialidad1);
             vAdmi.setVisible(false);
             //metAdmi.inRegistro();
        }*/
         
         //registro
        if(e.getSource()==vAdmi.btnSalir){
           System.exit(0);
        } 
        
        if(e.getSource()==vAdmi.btnRegistro){
             //int c=0;
             try{
                    metLog.obtenerDataVAdmi();
                    if(metLog.getdCorreo().equals("") || metLog.getdPswd().equals("") || metLog.getdTip().equals("U000") || metLog.getdSexo()=='X'){
                       JOptionPane.showMessageDialog(null, "¡Faltan datos por ingresar!!");
                       
                   }else{
                        if(validationsWithPatterns(1)==true){
                       if(metLog.getdTip()=="U001"){                        
                            metLog.regAdmi();
                            JOptionPane.showMessageDialog(null, "Registrado con exito, Admi"); 
                            vAdmi.setVisible(false);vAdmi.nuevRegistro.setVisible(false);
                            CtrlAdmin ctAdmin=new CtrlAdmin(vAdmi2,this.objAdmi); 
                       }
                       
                       if(metLog.getdTip()=="U002"&& vAdmi.jcbxEspecialidad1.getSelectedIndex()==0){
                           JOptionPane.showMessageDialog(null, "Ingrese especialidad");
                           vAdmi.jlEspecialidad.setVisible(true);
                           vAdmi.jcbxEspecialidad1.setVisible(true);
                          
                       }
                       //if(mlog.getdEsp().equals("-Seleccionar-"))c++;
                       if(metLog.getdTip()=="U002"&& vAdmi.jcbxEspecialidad1.getSelectedIndex()!=0){
                           metLog.regMed();
                           vAdmi.setVisible(false);vAdmi.nuevRegistro.setVisible(false);
                       CtrlAdmin ctAdmin=new CtrlAdmin(vAdmi2,this.objAdmi);
                           JOptionPane.showMessageDialog(null, "Registrado con exito, Doc"); 
                       }
                        }
                        else{
                            System.out.println("CAE AQUÍ");
                        }
                   }
                     
                 
              }catch (NullPointerException ex) {
              JOptionPane.showMessageDialog(null, "¡Faltan datos por ingresar!\nError: "+ex); 
             }catch(NumberFormatException exx){
                     JOptionPane.showMessageDialog(null, "¡Inserte correctamente su DNI!\nError: "+exx.getMessage()); 
                 }
             
         }        
        
        //MÉTODOS HORARIO-CREAR-LISTAR
        
        //Redirección principal
        if(e.getSource()==vAdmi.btnHorarioCostos){
            metAdmi.openJFrame(vAdmi.jfModHoraCoste, "Horarios & Costos");
            vAdmi.jcbxHFin.setEnabled(false);
            vAdmi.btnActHorario.setEnabled(false);
        }
        
        //Generar nuevo horario           
        if(e.getSource()==vAdmi.btnCreateHorarios){
             metAdmi.crearH();
             //
        }     
        if(e.getSource()==vAdmi.jcbxHInicio){
            metAdmi.mostrarHor(vAdmi.jcbxHFin, 2, vAdmi.jcbxHInicio.getSelectedItem().toString());
            this.vAdmi.jcbxHFin.setEnabled(true);
        }
        //Listar horarios por especialidad para edit
        if(e.getSource()==vAdmi.jcbxEspHorariosList){
            if(!vAdmi.jcbxEspHorariosList.getSelectedItem().toString().equals(Utils.SL)){
                metAdmi.tablaBotonesExtra(vAdmi.jTHorarios, vAdmi.jcbxEspHorariosList.getSelectedItem().toString(),1);
             }
        }
        //Listar horarios por medico
         if(e.getSource()==vAdmi.btnVizHorarioMed){             
             metAdmi.tablaBotonesExtra(vAdmi.jtblHorarioNuevaCita,null,4);
         }
        //botón de editar horario
        if(e.getSource()==vAdmi.btnActHorario){
           metAdmi.actHorario();
        }
        
        
        //Crear/Editar horario
         if(e.getSource()==vAdmi.btnCleanCreateHorario){
             metAdmi.cleanHorario();
         }
        
         //HORARIOS MÉDICOS
         //Lupa de buscar con filtros
         if(e.getSource()==vAdmi.btnBusMedicoAsigH){
             metAdmi.cleanHorDisp(1);//opción 1 porque limpiará todo
             metAdmi.cleanTabHorAsig();
             metAdmi.filtrarHorMed();
         }
         
         if(e.getSource()==vAdmi.btnActHorarioMedAsigH){             
             metAdmi.asigNuevHorarioMed();     
         }
        
         /*if(e.getSource()==vAdmi.btnBusDiasDisponibles){
             metAdmi.listarDiasDisp();
         }*/
         
         /*if(e.getSource()==vAdmi.jcbDiasAsigH){
              System.out.println("EJECUTANDO rangoHorarios()");
           metAdmi.rangoHorarios();
         }*/
        
         //listado horario-citas
         //vAdmi.btnBusDiaHora
         if (e.getSource() == vAdmi.btnBusDiaHora) {
               metAdmi.validarHorDisp();
        }
         
        
        
    }
    

    
    public boolean validationsWithPatterns(int tipo){
        boolean value=true;
    
        switch(tipo){
            case 1:   
                //validación de correo    
                if (!metAdmi.validarCorreoNomDNI(1, this.vAdmi.txCorreo, this.vAdmi.alertCorreo)) {
                    value = false;
                }
                //DNI        
                if (!metAdmi.validarCorreoNomDNI(2, this.vAdmi.txtDNI1, this.vAdmi.txtAlertNombre)) {
                    value = false;
                }
                //validación de pswd
                if (!metAdmi.esContraseña(this.vAdmi.txContra.getText(), this.vAdmi.txtAlertContra)) {
                    value = false;
                }
                if (!metAdmi.validarCorreoNomDNI(3, this.vAdmi.txtNombre1, this.vAdmi.txtAlertNombre)) {
                    value = false;
                }break;
            case 2:
                //
                if (!metAdmi.validarCorreoNomDNI(3, this.vAdmi.configNombre, this.vAdmi.txtConfigAlertName)) {
                    value = false;
                }
                if (!metAdmi.validarCorreoNomDNI(1, this.vAdmi.configCorreo, this.vAdmi.txtConfigAlertCorreo)) {
                    value = false;
                }
                if (!metAdmi.esContraseña(this.vAdmi.configPswd.getText(), this.vAdmi.txtConfigAlertContra)) {
                    value = false;
                }          
        }
        return value;
    } 
    
     public boolean validationsWithPatternsPac(int tipo){
        boolean value=true;
    
        switch(tipo){
            case 1:   
                //DNI        
                if (!metAdmi.validarCorreoNomDNI(2, this.vAdmi.txtDNIPac, this.vAdmi.jlExDNI)) {
                    value = false;
                }
                //nombre
               if (!metAdmi.validarCorreoNomDNI(3, this.vAdmi.txtNombrePac, this.vAdmi.jlExNombre)) {
                    value = false;
                } 
                //numero 
                if (!metAdmi.validarCorreoNumDNIPac(3, this.vAdmi.txtNumeroPac, this.vAdmi.jlExNumero)) {
                    value = false;
                }break;
            case 2:
                //DNI        
                if (!metAdmi.validarCorreoNomDNI(2, this.vAdmi.txtDNIPac, this.vAdmi.jlExDNI)) {
                    value = false;
                }break;
        }
        return value;
    } 
     
  
   

    @Override
    public void mouseClicked(MouseEvent e) {
        
       if(e.getSource()==vAdmi.jTHorarios){
          metAdmi.metodosinTable(vAdmi.jTHorarios, e, 1,vAdmi);
        }
       if(e.getSource()==vAdmi.jTableMedHor){
          metAdmi.metodosinTable(vAdmi.jTableMedHor, e, 2,vAdmi);
        }
        if (e.getSource()==vAdmi.jTableAsigHorMed) {
            metAdmi.metodosinTable(vAdmi.jTableAsigHorMed, e, 3, vAdmi);
        }
       
       
        if(e.getSource()==vAdmi.jmAyudaRC){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nCombo box: Seleccionador que tiene una pestaña desplegable en la que se "
                    + "\npueden elegir entre distintas opciones"
                    + "\nPasos para el uso de esta ventana:"
                    + "\n1-. Seleccione el combo box de la especialidad a la que desea hacer la consulta de citas"
                    + "\n2-. Al seleccionar la especialidad, se muestran los respectivos médicos en el combo box "
                    + "\nde médicos según la especialidad que usted eligió, seleccione el médico al cual desearía"
                    + "\nhacer la consulta de citas"
                    + "\n3-.Al terminar de seleccionar sus opciones de preferencia en el combo box, debe hacerle "
                    + "\n clic al botón 'Mostrar' para que se visualicen los datos en la tabla, en el caso de que"
                    + "\n se haya modificado un dato de la tabla en ese instante, se debería dar clic en el botón"
                    + "\n 'Actualizar' para mostrar los datos nuevos"
                    + "\n4-. Para activar el mecanismo de actualizar, primero se tiene que modificar la tabla, en la columna "
                    + "\n    'Estado' y la fila que pertenezca a la cita a la que le quiere editar el estado, existen 3 tipos"
                    + "\n    de estado, que son el Pendiente, que viene por defecto al agregar una cita, el postergado, cuando"
                    + "\n    el médico o el paciente postergan la cita, y el cancelado, cuando la cita queda cancelada, para que"
                    + "\n    esta última tenga efecto y elimine la cita, se debe presionar el botón 'Actualizar' inmediatamente"
                    + "\n    después de que se modifique el dato en la tabla, para realizar la eliminación de la cita."
                    + "\n5-. Si desea volver a la ventana anterior, de clic en el botón 'Retornar'"
                    + "\n6-. En caso desee salir del programa, de clic en el botón 'Salir'";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vAdmi.jmAyudaNC){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nCombo box: Seleccionador que tiene una pestaña desplegable en la que se "
                    + "\npueden elegir entre distintas opciones"
                    + "\nPasos para el uso de esta ventana:"
                    + "\n1-. Seleccione el combo box de la especialidad a la que desea hacer la agregación de citas"
                    + "\n2-. Al seleccionar la especialidad, se muestran los respectivos médicos en el combo box "
                    + "\nde médicos según la especialidad que usted eligió, seleccione el médico al cual desearía"
                    + "\nhacer la agregación de una cita"
                    + "\n3-.Al terminar de seleccionar sus opciones de preferencia en el combo box, debe hacerle "
                    + "\nclic al botón con ícono de calendario para que se visualicen los horarios de cada médico"
                    + "\n4-. Ingrese el DNI del paciente, y presione en el botón con forma de Lupa, este indicará "
                    + "\nsi es que el paciente ya existe en la base de datos, en caso de que no exista entonces a"
                    + "\nusted se le solicitará ingresar los demás datos del paciente nuevo, que son Nombre y Teléfono"
                    + "\n5-. Luego de rellenar todos los campos, de clic en registrar"
                    + "\n6-. El botón de brocha borra manualmente todos los datos dentro de los recuadros de texto cuando"
                    + "\nusted lo requiera"
                    + "\n7-. Si desea volver a la ventana anterior, de clic en el botón 'Retornar'"
                    + "\n8-. En caso desee salir del programa, de clic en el botón 'Salir'";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vAdmi.jmAyuda){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nSeleccione el botón requerido para acceder a la ventana a la que usted desea ingresar"
                    + "\nSi no desea continuar, accione el botón Salir";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vAdmi.jmAyuda2){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\nEn esta ventana se pueden modificar los datos del administrador, tales como Nombre, Fecha de Nacimiento,"
                    + "\ncorreo y contraseña, y luego de eso presionar el botón actualizar para procesar la modificación";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vAdmi.jmAyuda3){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\n Este apartado puede se listan la asistencia en el día de los médicos"
                    + "\n En caso de que acabe el día, debe dar click en el botón 'Reiniciar'."
                    + "\n De esta manera al siguiente día todos los médicos deben de marcar de nuevo una asistencia ";
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vAdmi.jmAyuda4){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\n //HORARIOS//"
                    + "\n Elija la especialidad para ver todos los horarios disponibles"
                    + "\n MODIFICAR: Seleccione el botón con un lapiz para modificar el horario. Luego dé click en el botón actualizar en caso se haya efectuado un cambio"
                    + "\n ELIMINAR: Seleccione el botón con un tacho para eliminar el horario de dicha fila. Confirme la eliminación 1 o 2 para no eliminarlo"
                    + "\n AÑADIR:   1.Seleccione la especialidad que añadirá el horario en el combobox superior"
                    + "\n           2.Click en el botón de generar ID"
                    + "\n           3.Ingrese los demás datos "
                    + "\n           4.Finalmente dar click en el botón de 'Añadir'"
                    + "\n Nota: En caso que desee limpiar todos los datos en los campos de horario, dar click en el botón con la brocha"
                    + "\n\n  //COSTO//"
                    + "\n En caso quiera modificar algún costo de una especialidad"
                    + "\n 1.Seleccionar la especialidad en el combobox inferior"
                    + "\n 2.Ingresar el nuevo valor de coste para la especialidad"
                    + "\n 3.Dar click en el botón 'Actualizar' y listo." ;
            JOptionPane.showMessageDialog(null, mensaje);
        }
        if(e.getSource()==vAdmi.jmAyuda5){
            String mensaje= "-----------------------------Ayuda-----------------------------"
                    + "\n La presente vista es para asignar un horario a un médico nuevo ingresado al sistema"
                    + "\n 1.Revisar los ID de horarios disponibles según la especialidad"
                    + "\n 2.Dar click en la celda de IDHorario y escribir el ID que se asignará al médico"
                    + "\n 3.Dé click en el botón con un lápiz en la fila modificar"
                    + "\n 4.Finalmente elige entre 1 o 2 para aceptar o rechazar lo asignado. Luego de esto, se actualizará en la base de datos con los nuevos datos  ";
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
