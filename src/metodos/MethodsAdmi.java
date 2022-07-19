

package metodos;

import entidades.Administrador;
import vistaa.*;import controlador.*;
import java.awt.Desktop;
import java.net.URI;
import javax.swing.*;
import daos.*;
import dto.EspecialidadEstDTO;
import entidades.*;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import util.Utils;




public class MethodsAdmi extends MethodsMain{
    
    VAdmi vAdmi; 
    Utils util;
    DefaultTableModel tablaFE; 
    DefaultTableModel tabl;  
    private String codHorario;
    private String asigHCodEsp;
    private String asigHEstado;
    private String codEspSelected;
    private String codMedSelected; 
    private String selectedDay;

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }
    
    
    public MethodsAdmi(VAdmi vAdmi){
        this.vAdmi=vAdmi;
        this.asigHCodEsp=null;
        this.asigHEstado=null;
        this.util =  new Utils();
    }
    
    public void inJcbxEspecialidad(){
        mostrarEsp(vAdmi.jcbArea);
        mostrarEsp(vAdmi.jcbxEspecialidadNC);
        mostrarEsp(vAdmi.jcbxEspAsignarH);
        //jcbx horarios
        mostrarEsp(vAdmi.jcbxEspHorariosCreate);
        mostrarEsp(vAdmi.jcbxEspHorariosList);
        //jcbx costos
        mostrarEsp(vAdmi.jcbxEspCosto);      
    } 
    
    public void inJcbxHorarios(){
        //mostrará todos los horario
        mostrarHor(vAdmi.jcbxHInicio, 1, null);
    }
    
    
    public void jcbxLisMedicos(JComboBox jcbMed, JComboBox jcbEsp){
        String cod2=mostrarCodEsp(jcbEsp);
        jcbMed.removeAllItems();
        jcbMed.addItem("-Seleccionar-");
        List<Medico> lista = daoC.lisMedEs(cod2);
        int max = lista.size();
        for (int i = 0; i < max; i++) {
            jcbMed.addItem(lista.get(i).getNombre());
        }
    } 
  

   public void costo(String nom,JTextField prec){
       String cod=daoR.busCodEsp(nom);
        double cost=daoC.busCosto(cod); 
        prec.setText(String.valueOf(cost));
   } 

   /* public void mostrarHor(){
        String nomd = vAdmi.jcbxDoctorNC.getSelectedItem().toString();
        Horario hor=daoH.busHorario(nomd,1);
        vAdmi.taHorario.setText("Dias: " + hor.getDias() + "\n" + hor.getHinicio() + "-" + hor.getHfin());
    }
    */
    public void busPaciente(){
        String dnit = vAdmi.txtDNIPac.getText();
        String val = daoC.busPac(dnit);
        Paciente paciente=daoC.showPac(dnit);
        if (val.equals("Encontrado")) {
            JOptionPane.showMessageDialog(null, "El paciente ya se encuentra Registrado");
            vAdmi.txtNombrePac.setText(paciente.getNomp());
            vAdmi.txtNumeroPac.setText(String.valueOf(paciente.getNumero()));
            mostrarDataPac();
            
        } else {
            mostrarDataPac(); 
            JOptionPane.showMessageDialog(null, "El paciente no está registrado, inserte datos de nuevo paciente");
        }
    }
    public void mostrarDataPac(){
        vAdmi.txtNombrePac.setVisible(true);
            vAdmi.txtNumeroPac.setVisible(true);
            vAdmi.lbNumero.setVisible(true);
            vAdmi.lbNombre.setVisible(true);
    }
    public void inTablaAsisMed(){
        String[] cab1={"Codigo","Medic@","Especialidad","Asistio"};
        String[][] data1={};
        tablaFE=new DefaultTableModel(data1,cab1); JTable jt=vAdmi.jTAsistencia;
        jt.setModel(tablaFE);
        ajustarColumns(jt, 0, 55);
        ajustarColumns(jt, 1, 150);
        ajustarColumns(jt, 2, 120);
        ajustarColumns(jt, 3, 50);
        acTabla();
    }
    
    /* public void tablaShowSchedule(){
         String[] cabecera={"IDHorario","Días","Horario"};String[][] data={};
         DefaultTableModel tabSS=new DefaultTableModel(data, cabecera);JTable jt=vAdmi.jTHorariosDisp;
        jt.setModel(tabSS);
        String name=vAdmi.jcbxEspAsignarH.getSelectedItem().toString();
        String codes=daoR.busCodEsp(name);
        List<Horario> lisH=daoH.lisHorarios(codes); int max=lisH.size();
        for(Horario h:lisH){
            String[] fila={h.getIdhor(),h.getDias(),h.getHinicio()+"-"+h.getHfin()};
            tabSS.addRow(fila);
        }
        ajustarColumns(jt, 2, 95);
        //ajustarColumns(jt, 0, 95);
    }*/
    
    public void acTabla(){
        List<Medico> med=daoR.asistencia(); int max=med.size();
        for(Medico m:med){
            String[] fila={m.getCodmed(),m.getNombre(),m.getCodes(),m.getAsistencia()}; 
            tablaFE.addRow(fila);
        }
     }
    public void borrarTabla(){
        List<Medico> med=daoR.asistencia(); int max=med.size();
        for(int i=0;i<max;i++){
             tablaFE.removeRow(i);
        }
    }
    public void Validacion(){
        System.out.println("DNI"+vAdmi.txtDNIPac.getText());
    }
    public void regCita(VAdmi vAdmi){
        SimpleDateFormat fordia=new SimpleDateFormat("yyyy-MM-dd");
        Cita c = new Cita();
        c.setCodmed(daoC.codMedNom(vAdmi.jcbxDoctorNC.getSelectedItem().toString()));
        c.setDnipac(Integer.parseInt(vAdmi.txtDNIPac.getText()));
        c.setDiacit(fordia.format(vAdmi.dtCita.getDate()));
        c.setHoracit(vAdmi.jcbxHoraInicioNuevaCita.getSelectedItem().toString());
        try {            
            Paciente p2=daoC.showPac(vAdmi.txtDNIPac.getText());
            if(p2==null){ 
                Paciente p = new Paciente();
            p.setDni(Integer.parseInt(vAdmi.txtDNIPac.getText()));
            p.setIdtip("U003");
            p.setNomp(vAdmi.txtNombrePac.getText());
            p.setNumero(Integer.parseInt(vAdmi.txtNumeroPac.getText()));
            daoC.addPaci(p);
            }            
            daoC.addCita(c);
            JOptionPane.showMessageDialog(null, "Paciente registrado");
        } catch (Exception ex) {
            
        }
        JOptionPane.showMessageDialog(null, "Cita generada");
    }
    
    public void resetAsis(){
        String asist="NO";
       List<Medico> med=daoR.lisMed();int max=med.size();
       for(int i=0;i<max;i++){
           daoL.actAsis(med.get(i).getCodmed(), asist);
          // getDaoL().actAsis(med.get(i).getCodmed(), asist);
       }
       JOptionPane.showMessageDialog(null, "Asistencia Reiniciada");
       acTabla();
       borrarTabla();
    } 
    
    public void obtNumCitasEsp(int numcita, String esp){
        
    }
    
   public void visualizarListaCita(JTable tabla,String nombre){
        String[] cab1 = {"Nro Cita", "Hora", "Fecha Cita", "Nombre Paciente", "DNI", "Estado"};
        String[][] data1 = {};
        tabl = new DefaultTableModel(data1, cab1);
        tabla.setModel(tabl); 
        List<Cita> calc=new ArrayList(); 
        calc = daoC.lisListarCita(nombre);
        for (Cita x : calc) {
            String[] fila = {x.getIdCita(), x.getHoracit(), x.getDiacit(), x.getNompac(), String.valueOf(x.getDnipac()), x.getEstadopac()};
            tabl.addRow(fila);
        } 
        ajustarColumns(tabla, 3, 135);
        ajustarColumns(tabla, 5, 80);
        editarEstado(tabla, 5);
    }
   public void visualizarGraf(){ 
       EspecialidadEstDTO name= new EspecialidadEstDTO();
       List<EspecialidadEstDTO> esp=new ArrayList(); 
        //esp = daoC.contEspReg(numero); 
       DefaultPieDataset datos=new DefaultPieDataset(); 
       Map<String, Integer> m=daoC.contEspReg();
       switch(name.getNomesp()){
           case "Medicina General": datos.setValue("Medicina General",m.get("Medicina General")); break;
           case "Obstetricia": datos.setValue("Obstetricia",m.get("Obstetricia")); 
           case "Otorrinolaringologia": datos.setValue("Otorrinolaringologia",m.get("Otorrinolaringologia")); break;
           case "Pediatria": datos.setValue("Pediatria",m.get("Otorrinolaringologia"));break;
           case "Psicologia": datos.setValue("Psicologia", m.get("Pediatria")); break;
           case "Radiologia": datos.setValue("Radiologia",m.get("Radiologia")); break;
           case "Traumatologia": datos.setValue("Traumatologia", m.get("Traumatologia")); break;
       } 
       JFreeChart grafico=ChartFactory.createPieChart("Citas por Especialidad",datos,true,true,false); 
       
         //vAdmi.GraficoCircular=new ChartPanel(grafico);
        //panel.setMouseWheelEnabled(true); 
        
   }
   
   public double actualizarCosto(){
       String nombre=vAdmi.jcbxEspCosto.getSelectedItem().toString();
       String codes=daoR.busCodEsp(nombre);
       return daoC.actCosto(codes, Double.parseDouble(vAdmi.jTextCosto.getText()));
   }
   
   public void tablaBotonesExtra(JTable tabla,String nombre,int id){
       JButton btnDel=new JButton("");
       JButton btnMod=new JButton("");
       tabla.setDefaultRenderer(Object.class, new Render());
       btnMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/modificarH.png")));
       String[][] data1 = {};
       if(id==1){
           btnMod.setName("btnMod");
           btnDel.setName("btnDel");
           btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
           String[] cab2={"ID", "Día","H. Inicio","H. Fin","Modificar","Eliminar"};
           tabl = new DefaultTableModel(data1, cab2);
           String codes = daoR.busCodEsp(nombre);
           List<HorarioDiaEspecialidad> lisH=daoH.lisHorariosDiaEsp(codes);
           if(lisH.size()==0){
                      JOptionPane.showMessageDialog(null, "No hay registros de horarios");
             }
           else{
             for (HorarioDiaEspecialidad x : lisH) {
               Object[] fila = {x.getId(),x.getDia(), x.getHinicio(), x.getHfin(), btnMod, btnDel};
               tabl.addRow(fila);
            }        
           }           

        }
       //Tabla inicial Horarios-Medicos
       if(id==2){
           btnMod.setName("btnMod");
           String[] cab={"Cod. Médico","Nombres","¿Asignado?","Especialidad","Modificar"};
           tabl = new DefaultTableModel(data1, cab);
           List<Medico> lisM=daoH.searchFiltersHorMed(asigHCodEsp, asigHEstado); 
           System.out.println("tamanio: "+lisM.size());
           for(Medico m:lisM){
             Object[] fil={m.getCodmed(),m.getNombre(),m.getFlagHorario(),m.getNomEsp(),btnMod};
             tabl.addRow(fil);
           }           
       }
       //llenar data de tabla de médico y horarios asignados
       if(id==3){
           btnDel.setName("btnDel");
           btnDel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png")));
           String[] cab3={"Código", "Día","H. Inicio","H. Fin","Eliminar"};
           tabl = new DefaultTableModel(data1, cab3);
           List<HorarioDiaEspecialidad> lisH=daoH.lisHorAsigMed(getCodMedSelected());
            for (HorarioDiaEspecialidad x : lisH) {
               Object[] fila = {x.getId(),x.getDia(), x.getHinicio(), x.getHfin(), btnDel};
               tabl.addRow(fila);
            }    

       }
       if(id==4){ 
           String codMed=daoC.codMedNom(vAdmi.jcbxDoctorNC.getSelectedItem().toString());
           String[] cab4={"Días", "Hora Inicio","Hora Fin"}; 
           tabl = new DefaultTableModel(data1, cab4); 
           List<HorarioDiaMedico> lisH=daoH.lisHorioMed(codMed);
           String dia="";int i=0;
            for (HorarioDiaMedico x : lisH) {
                if(!x.getDia().equals(dia)){
                    i=1;
                }
                
               Object[] fila = {x.getDia(), x.getHinicio(), x.getHfin()};
               tabl.addRow(fila);
            }   
       }
    
        tabla.setModel(tabl);
        ajustarColumns(tabla, 2, 80);
        tabla.setRowHeight(30);
    }
   
   public void metodosinTable(JTable jt, MouseEvent e,int id,VAdmi vad){
    int column=jt.getColumnModel().getColumnIndexAtX(e.getX());
    int row=e.getY()/jt.getRowHeight();
     if(column<=jt.getColumnCount() && column>=0 && row<=jt.getRowCount() && row >=0){
          Object ob=jt.getValueAt(row, column); 
          if(ob instanceof JButton){
             ((JButton)ob).doClick();
             JButton botones=(JButton)ob;
             //Opción: Crear Horario
              if (id == 1) {
                  if (botones.getName().equals("btnMod")) {
                      //habilitamos el botón de editar, pero deshabilitamos el de Crear
                      vad.btnActHorario.setEnabled(true);
                      vad.btnCreateHorarios.setEnabled(false);
                      String cod = String.valueOf(jt.getValueAt(row,0));
                      System.out.println("COD: "+cod);
                      setearDataModHorario(cod, vad);
                  }
                  if (botones.getName().equals("btnDel")) {
                      String cod = String.valueOf(jt.getValueAt(row, 0));
                      int op = 0;
                      try {
                          op = Integer.parseInt(JOptionPane.showInputDialog("¿Está seguro que eliminará el horario de código: " + cod
                                  + "?\n1.SÍ\n2.NO"));
                          if (op == 1) {
                              deleteHorario(cod);
                              tablaBotonesExtra(vad.jTHorarios, vad.jcbxEspHorariosList.getSelectedItem().toString(), 1);
                          }
                          if (op == 2) {
                              JOptionPane.showMessageDialog(null, "No eliminado");
                          }
                      } catch (Exception ex) {
                          //JOptionPane.showMessageDialog(null, "¡ERROR! \nDetalle: "+ex);
                      }
                  }
              }
             //Opción: Asignar Horarios a Médicos
               if (id == 2) {
                   if (botones.getName().equals("btnMod")) {
                       try {                           
                           setCodEspSelected(daoR.busCodEsp(String.valueOf(jt.getValueAt(row, 3))));
                           String nomMedico = String.valueOf(jt.getValueAt(row, 1));
                           setCodMedSelected(String.valueOf(jt.getValueAt(row, 0)));
                           //listar la tabla de horarios asignados
                           vAdmi.jLabelNomMed.setText(nomMedico);
                           tablaBotonesExtra(vad.jTableAsigHorMed, null, 3);
                           //Cargar combo con días disponibles para dicha especialidad
                           vAdmi.jLabelNomEsp.setText(String.valueOf(jt.getValueAt(row, 3)));
                           listarDiasDisp(String.valueOf(jt.getValueAt(row, 3)));
                          } catch (Exception ex) {

                       }
                   }
               }
               //Elimminar horarios-medico
               if(id==3){
                   if(botones.getName().equals("btnDel")){

                       try{
                           String idHorario=String.valueOf(jt.getValueAt(row,0));
                           int op = 0;
                          op = Integer.parseInt(JOptionPane.showInputDialog("¿Está seguro en eliminar la asignación del horario con código: " + idHorario
                                  + "?\n1.SÍ\n2.NO"));
                          if (op == 1) {
                              MedicoAtencion ma = new MedicoAtencion();
                              ma.setIdHorario(idHorario);
                              ma.setCodmed(getCodMedSelected());
                              System.out.println("idHorario: "+idHorario);
                              System.out.println("codMED: "+getCodMedSelected());
                              daoH.delMedHorario(ma);
                              cleanAsigMedHor();
                          }
                          if (op == 2) {
                              JOptionPane.showMessageDialog(null, "No eliminado");
                          }

                       }catch(Exception ex){
                   System.out.println("CAEEEEEEEEEE ELIMINAR"+ex.getMessage());

                       }
                   }
               }
           }
       }
   }
   
   public void setearDataModHorario(String id,VAdmi vad){
       HorarioDiaEspecialidad h=daoH.busHorariosDiaEsp(id);
       this.codHorario=id;
       String esp=daoR.nomEsp(h.getCodes());
       switch (h.getDia()){
           case Utils.L : vad.jRBLunes.setSelected(true);break;
           case Utils.Ma : vad.jRBMartes.setSelected(true);break;
           case Utils.Mi : vad.jRBMiercoles.setSelected(true);break;
           case Utils.J : vad.jRBJueves.setSelected(true);break;
           case Utils.V : vad.jRBViernes.setSelected(true);break;
           case Utils.S : vad.jRBSabado.setSelected(true);break;
           case Utils.D : vad.jRBDomingo.setSelected(true);break;
       }
       vad.jcbxEspHorariosCreate.setSelectedItem(esp);
       vad.jcbxHInicio.setSelectedItem(h.getHinicio());
       vad.jcbxHFin.setSelectedItem(h.getHfin());
   }
   
   
    public void crearH() {
        String codes="" , hInicio="", hFin="", dia=""; boolean flg=true; 
        if(!vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString().equals("-Seleccionar-")){
           codes = daoR.busCodEsp(vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString());
         }
        if(!vAdmi.jcbxHInicio.getSelectedItem().toString().equals("-Seleccionar-")){
           hInicio=vAdmi.jcbxHInicio.getSelectedItem().toString();
        }
        if(!vAdmi.jcbxHFin.getSelectedItem().toString().equals("-Seleccionar-")){
            hFin=vAdmi.jcbxHFin.getSelectedItem().toString();    
        }               
        if(vAdmi.jRBLunes.isSelected()) dia=Utils.L;
        if(vAdmi.jRBMartes.isSelected()) dia=Utils.Ma;
        if(vAdmi.jRBMiercoles.isSelected()) dia=Utils.Mi;
        if(vAdmi.jRBJueves.isSelected()) dia=Utils.J;
        if(vAdmi.jRBViernes.isSelected()) dia=Utils.V;
        if(vAdmi.jRBSabado.isSelected()) dia=Utils.S;
        if(vAdmi.jRBDomingo.isSelected()) dia=Utils.D;
        
        if(codes.equals("") || hInicio.equals("") || hFin.equals("") || dia.equals("")){
            flg=false;
        }
        
        if(flg==true){
        //Horario dias especialidad
        HorarioDiaEspecialidad hde=new HorarioDiaEspecialidad(codes, dia, "1",hInicio, hFin);
            if(daoH.busHorarioCreado(hde)>0){
               JOptionPane.showMessageDialog(null, "Ya se asignó un horario para el "+dia+ " en "+vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString());
            }else{
                daoH.addHorarioDiaEspecialidad(hde);
                //setear al combo box la especialidad cargada
                vAdmi.jcbxEspHorariosList.setSelectedItem(vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString());
                tablaBotonesExtra(vAdmi.jTHorarios, vAdmi.jcbxEspHorariosList.getSelectedItem().toString(), 1);
                cleanHorario();
            }                
        }
        else{
            JOptionPane.showMessageDialog(null, "¡Faltan campos por completar!");
        }           
    }
    
    public void actHorario(){
     String codes="" , hInicio="", hFin="", dia=""; boolean flg=true; 
        if(!vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString().equals(Utils.SL)){
           codes = daoR.busCodEsp(vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString());
         }
        if(!vAdmi.jcbxHInicio.getSelectedItem().toString().equals(Utils.SL)){
           hInicio=vAdmi.jcbxHInicio.getSelectedItem().toString();
        }
        if(!vAdmi.jcbxHFin.getSelectedItem().toString().equals(Utils.SL)){
            hFin=vAdmi.jcbxHFin.getSelectedItem().toString();    
        }               
        if(vAdmi.jRBLunes.isSelected()) dia=Utils.L;
        if(vAdmi.jRBMartes.isSelected()) dia=Utils.Ma;
        if(vAdmi.jRBMiercoles.isSelected()) dia=Utils.Mi;
        if(vAdmi.jRBJueves.isSelected()) dia=Utils.J;
        if(vAdmi.jRBViernes.isSelected()) dia=Utils.V;
        if(vAdmi.jRBSabado.isSelected()) dia=Utils.S;
        if(vAdmi.jRBDomingo.isSelected()) dia=Utils.D;     
        
        if(codes.equals("") || hInicio.equals("") || hFin.equals("") || dia.equals("")){
            flg=false;
        }
        
        if(flg==true){         
            HorarioDiaEspecialidad hde=new HorarioDiaEspecialidad(this.codHorario,codes, dia, "1",hInicio, hFin);
            if(daoH.actHorario(hde).equals("2")){
               JOptionPane.showMessageDialog(null, "Ya se asignó un horario para el "+dia+ " en "+vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString());
            }else{            
            //daoH.actHorario(hde);
            //setear al combo box la especialidad cargada
            vAdmi.jcbxEspHorariosList.setSelectedItem(vAdmi.jcbxEspHorariosCreate.getSelectedItem().toString());
            tablaBotonesExtra(vAdmi.jTHorarios, vAdmi.jcbxEspHorariosList.getSelectedItem().toString(),1);
            vAdmi.btnActHorario.setEnabled(false);
            vAdmi.btnCreateHorarios.setEnabled(true);
            cleanHorario();
            JOptionPane.showMessageDialog(null, "Horario asignado correctamente");

            }
        }
        else{
            JOptionPane.showMessageDialog(null, "¡Faltan campos por completar!");
        }  
        
    }
    
    
   public void deleteHorario(String id){
       daoH.deleteHorario(id);
   }
   
   public void cleanHorario(){
       vAdmi.grupoDias.clearSelection();       
       vAdmi.jcbxEspHorariosCreate.setSelectedItem(Utils.SL);
       vAdmi.jcbxHInicio.setSelectedItem(Utils.SL);
       vAdmi.jcbxHFin.setSelectedItem(Utils.SL);
       vAdmi.btnCreateHorarios.setEnabled(true);
       vAdmi.btnActHorario.setEnabled(false);
    }
   
   
   //MÉDICOS NUEVOS   
   public void nuevosMedicos(){
       String estado="";
       estado=daoL.busNuevosMed();
      vAdmi.btnAsigHor.setText(estado);
   }
  
   public void limpiarNC(VAdmi f){ 
       f.txtDNIPac.setText("");
       f.txtNombrePac.setText("");
       f.txtNumeroPac.setText("");
       f.jtxtPrecioNC.setText("");
       
       /*
       LIMPIAR Nuevos botones de horario?
       f.taHorario.setText("");
       f.txtHora.setText("");*/
       
       f.dtCita.setCalendar(null); 
      f.jcbxEspecialidadNC.setSelectedIndex(0);
      f.jcbxDoctorNC.setSelectedIndex(0);
      f.lbNombre.setVisible(false);f.txtNombrePac.setVisible(false);
      f.lbNumero.setVisible(false); f.txtNumeroPac.setVisible(false);
       f.txtDNIPac.requestFocus();
       
   }
   
   public void inRegistro(){
        vAdmi.nuevRegistro.setTitle("Registro");
        vAdmi.nuevRegistro.setLocationRelativeTo(null);
        vAdmi.nuevRegistro.setVisible(true);        
        esIcono(vAdmi.nuevRegistro);
        vAdmi.jlEspecialidad.setVisible(false);
        vAdmi.jcbxEspecialidad1.setVisible(false);
   
}
   
   //Filtros de búsqueda de horarios
   public void filtrarHorMed(){
       String esp=vAdmi.jcbxEspAsignarH.getSelectedItem().toString();
       String estado=vAdmi.jcbxEstadoAsigH.getSelectedItem().toString();
       
         if(esp.equals(Utils.SL)){
           this.asigHCodEsp = null;
         }else{
             this.asigHCodEsp=daoR.busCodEsp(esp);
         }
         if(estado.equals(Utils.SL)){
           this.asigHEstado = null;
         }
         else{
             if(estado.equals("Asignado"))
                 this.asigHEstado="1";
             else
                 this.asigHEstado="0";
         }         
       tablaBotonesExtra(vAdmi.jTableMedHor, null, 2);
   }
   
  
   
   public void listarDiasDisp(String nameEsp){
       
        HorarioDiaEspecialidad h=new HorarioDiaEspecialidad();
        
        if(h.mostrarHorDiaEsp(getCodEspSelected()).size()==1){
            JOptionPane.showMessageDialog(null, "No hay horarios disponibles");
            vAdmi.jcbDiasAsigH.setEnabled(false);
            vAdmi.jtxHoraInAsigH.setText("");
            vAdmi.jtxHoraFinAsigH.setText("");
        }
        else{
            DefaultComboBoxModel modeloHorariosDiaEsp = new DefaultComboBoxModel(h.mostrarHorDiaEsp(getCodEspSelected()));
            vAdmi.jcbDiasAsigH.setModel(modeloHorariosDiaEsp);
            vAdmi.jcbDiasAsigH.setEnabled(true);
        }
         vAdmi.btnActHorarioMedAsigH.setEnabled(false);

      //mostrarHor(vAdmi.jcbDiasAsigH, 3, getCodEspSelected(),vAdmi.btnActHorarioMedAsigH);
      //limpiar data de horas
 
   }
   
   public void cleanHorDisp(int caso){       
       vAdmi.jtxHoraInAsigH.setText("");
       vAdmi.jtxHoraFinAsigH.setText("");
       vAdmi.jcbDiasAsigH.removeAllItems();
       vAdmi.jcbDiasAsigH.setEnabled(false);
       //en caso quiera limpiar todo
       if(caso==1){
          vAdmi.jLabelNomEsp.setText("");  
          vAdmi.jLabelNomMed.setText("");
          cleanTabHorAsig();
       }
       else if (caso==2){
           listarDiasDisp(asigHCodEsp);
       }       
   

   }
   
   public void cleanTabHorAsig(){
       List<HorarioDiaEspecialidad> lisH=daoH.lisHorAsigMed(getCodMedSelected());
       int max=lisH.size();
           String[] cab3={"Código", "Día","H. Inicio","H. Fin","Eliminar"};
        String[][] data1={};
       DefaultTableModel tablaHorAsig=new DefaultTableModel(data1,cab3); JTable jt=vAdmi.jTableAsigHorMed;
        jt.setModel(tablaHorAsig);
        vAdmi.jLabelNomMed.setText("");
   }
   
   public void cleanTabHorariosMedC(){
       //COLOCAR LA CABECERA CORRESPONDIENTE
       String[] cab3={"Código", "Día","H. Inicio","H. Fin","Eliminar"};
        String[][] data1={};
       DefaultTableModel tablaHorAsig=new DefaultTableModel(data1,cab3); 
       //COLOCAR EL JTABLE CORRESPONDIENTE
       JTable jt=vAdmi.jTableAsigHorMed;
        jt.setModel(tablaHorAsig);
   }
   
   public void asigNuevHorarioMed(){
      HorarioDiaEspecialidad hor=(HorarioDiaEspecialidad)vAdmi.jcbDiasAsigH.getSelectedItem();
      MedicoAtencion ma= new MedicoAtencion();
      ma.setCodmed(getCodMedSelected());
      ma.setIdHorario(hor.getId());
      daoH.adMedHorario(ma);
      cleanAsigMedHor();
   }
   
   public void cleanAsigMedHor(){
      //reload tabla Horarios de médicos
      tablaBotonesExtra(vAdmi.jTableMedHor, null, 2);
      //reload combobox de Horarios disponibles
      cleanHorDisp(2);//limpieza parcial
      //listar horarios de todos los médicos o del filtro que exista
      tablaBotonesExtra(vAdmi.jTableAsigHorMed, null, 3);       
   }
   
    public void busHorariosDispMedico(String day) {
        String codmed = daoC.codMedNom(vAdmi.jcbxDoctorNC.getSelectedItem().toString());
        SimpleDateFormat fordia = new SimpleDateFormat("yyyy-MM-dd");

        String fechcita = fordia.format(vAdmi.dtCita.getDate());
        List<String> listHorarioDispMed = daoH.DetalleHorarioDisp(codmed, day);
        List<String> listHorarioOcupMed = daoH.horariosOcupadosporDia(fechcita, codmed);
          for (int i = 0; i < listHorarioDispMed.size(); i++) {
            for (int j = 0; j < listHorarioOcupMed.size(); j++) {
                if (listHorarioDispMed.get(i).equals(listHorarioOcupMed.get(j))) {
                    listHorarioDispMed.remove(i);
                }                
            }
            
        }
        mostHorioCitas(vAdmi.jcbxHoraInicioNuevaCita, listHorarioDispMed);
    }
    
    public void validarHorDisp(){
        String codMed=daoC.codMedNom(vAdmi.jcbxDoctorNC.getSelectedItem().toString());
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date date = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
            //fecha pasada
            int i = 0;
            if (vAdmi.dtCita.getDate().before(date)) {
                disabledDias();
                JOptionPane.showMessageDialog(null, "Fecha pasada");
            } else {
                List<HorarioDiaMedico> lisH=daoH.lisHorioMed(codMed);
                setSelectedDay(util.metNameDay(vAdmi.dtCita.getDate().getDay()));
            for (HorarioDiaMedico x : lisH) {
                if(!x.getDia().equals(selectedDay)){
                    i=1;
                }
            }      
                if (i == 1) {
                    disabledDias();
                    JOptionPane.showMessageDialog(null, "Dia no disponible de atención");
                } else {
                    vAdmi.jcbxHoraInicioNuevaCita.setEnabled(true);
                    busHorariosDispMedico(selectedDay);
                }

            }
    }
    
        public void disabledDias(){
        vAdmi.jcbxHoraInicioNuevaCita.removeAllItems();
        vAdmi.jcbxHoraInicioNuevaCita.setEnabled(false);
    }

    public String getCodEspSelected() {
        return codEspSelected;
    }

    public void setCodEspSelected(String codEspSelected) {
        this.codEspSelected = codEspSelected;
    }

    public String getCodMedSelected() {
        return codMedSelected;
    }

    public void setCodMedSelected(String codMedSelected) {
        this.codMedSelected = codMedSelected;
    }
   
 
   
   
}
