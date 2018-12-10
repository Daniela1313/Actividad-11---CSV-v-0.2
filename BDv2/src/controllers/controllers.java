
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.model;
import view.view;

/**
 *
 * @author Daniela Garcia Rios
 */
public class controllers {
    
    model model;
    view view;
  
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == view.Jb_primero) {
                try {
                    jbtn_primero_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(controllers.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.Jb_anterior) {
                try {
                    jbtn_anterior_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(controllers.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.Jb_siguiente) {
                try {
                    jbtn_siguiente_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(controllers.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == view.Jb_ultimo) {
                try {
                    jbtn_ultimo_actionPerformed();
                } catch (SQLException ex) {
                    Logger.getLogger(controllers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (e.getSource() == view.jb_nuevo_registro){
                try{
                    jb_nuevo_actionPerformed();
                } catch (SQLException ex){
                    Logger.getLogger(controllers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (e.getSource() == view.jb_insertar_registro){
                jb_insertar_actionPerformed();
            }else if (e.getSource() == view.jb_modificar_registro){
                try{
                    jb_modificar_actionPerformed();
                } catch (SQLException ex){
                    Logger.getLogger(controllers.class.getName()).log(Level.SEVERE, null, ex);
                }  
            
            }else if (e.getSource() == view.Jb_eliminar){
                try{
                    jb_eliminar_actionPerformed();
                } catch (SQLException ex){
                    Logger.getLogger(controllers.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            }

        

    };

    
   
    public controllers(model model, view view) {
        this.model = model;
        this.view = view;
        initComponents();
        setActionListener();
        initDB();
    }

   
    public void initDB(){
        model.conectarDB();
        view.Jtf_nombre_agenda.setText(model.getNombre());
        view.Jtf_email_agenda.setText(model.getEmail());
    }
   
    public void initComponents() {
        view.setLocationRelativeTo(null);
        view.setTitle("Agenda");
        view.setVisible(true);
    }


    public void setActionListener() {
        view.Jb_primero.addActionListener(actionListener);
        view.Jb_anterior.addActionListener(actionListener);
        view.Jb_siguiente.addActionListener(actionListener);
        view.Jb_ultimo.addActionListener(actionListener);
        view.jb_nuevo_registro.addActionListener(actionListener);
        view.jb_modificar_registro.addActionListener(actionListener);
        view.Jb_eliminar.addActionListener(actionListener);
        view.jb_nuevo_registro.addActionListener(actionListener);
        view.jb_insertar_registro.addActionListener(actionListener);
        view.jb_modificar_registro.addActionListener(actionListener);
        view.Jb_eliminar.addActionListener(actionListener);
    }

    
    private void jbtn_primero_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_primero");
        model.moverPrimerRegistro();//invocar al metodo moverPrimerRegistro
        view.Jtf_nombre_agenda.setText(model.getNombre());//mostrar nombre en la vista
        view.Jtf_email_agenda.setText(model.getEmail());//mostar email en la vista
    }
    private void jbtn_anterior_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_anterior");
         model.moverAnteriorRegistro();//invocar al metodo moverPrimerRegistro
        view.Jtf_nombre_agenda.setText(model.getNombre());//mostrar nombre en la vista
        view.Jtf_email_agenda.setText(model.getEmail());//mostar email en la vista
    }

    /**
     * Método para ver el último registro de la tabla contactos
     */
    private void jbtn_ultimo_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_ultimo");
        model.moverUltimoRegistro();//invocar al metodo moverPrimerRegistro
        view.Jtf_nombre_agenda.setText(model.getNombre());//mostrar nombre en la vista
        view.Jtf_email_agenda.setText(model.getEmail());//mostar email en la vista
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos
     */
    private void jbtn_siguiente_actionPerformed() throws SQLException {
        System.out.println("Action del boton jbtn_siguiente");
         model.moverSiguienteRegistro();//invocar al metodo moverPrimerRegistro
        view.Jtf_nombre_agenda.setText(model.getNombre());//mostrar nombre en la vista
        view.Jtf_email_agenda.setText(model.getEmail());//mostar email en la vista
    }
    /**
     * 
     * metodo de botones de nuvo, insertar,modificar y eliminar
     */
   private void jb_nuevo_actionPerformed() throws SQLException{
       System.out.println("Action del boton jb_nuevo");
       model.setEmail(null);//pone el jtfemail vacio
       model.setNombre(null);//pone el jtfnombre vacio
       model.nuevo();//invocara al nuevo registro que quiera realizar
       view.Jtf_email_agenda.setText(model.getEmail());//permite modificar y reconocer el texto
       view.Jtf_nombre_agenda.setText(model.getNombre()); //permite modificar y reconocer el texto
   }
   private void jb_insertar_actionPerformed(){
       System.out.println("Action del boton insertar");
       model.setNombre(view.Jtf_nombre_agenda.getText());//llena el campo de nombre
       model.setEmail(view.Jtf_email_agenda.getText());//llena el campo de email
       model.insertar(model.getNombre(),model.getEmail());//Guarda el registro
   }
   private void jb_modificar_actionPerformed() throws SQLException{
       
       model.modificar(view.Jtf_nombre_agenda.getText(), view.Jtf_email_agenda.getText());
       JOptionPane.showMessageDialog(view," Tu registro ha sido modificado ");
   }
   private void jb_eliminar_actionPerformed() throws SQLException{
       System.out.println("Action del boton eliminar");
            System.out.println(model.getEmail());
            model.eliminar(model.getEmail());
            JOptionPane.showMessageDialog(view, "Advertencia tu registro se ha eliminado "); 
            
   }
}
