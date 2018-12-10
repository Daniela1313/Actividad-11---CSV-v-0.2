
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class model {
    
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    private String nombre;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Connection conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3307/agenda_mvc", "user_mvc", "pass_mvc.2018");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error en el model de conexion: " + err.getMessage());
        }
        return conexion; 
    }
    
    
    public void moverPrimerRegistro() throws SQLException{
        System.out.print("Amigo tu boton primer registro funciona");
        rs.first();
        this.setNombre(rs.getString("nombre"));
        this.setEmail(rs.getString("email"));
    }
    
   
    public void moverSiguienteRegistro() throws SQLException{
        System.out.print("Amigo tu boton siguiente registro funciona");
        if (!rs.isLast()){
            rs.next();
    }
        this.setNombre(rs.getString("nombre"));
        this.setEmail(rs.getString("email"));
    }
    
  
    public void moverAnteriorRegistro() throws SQLException{
        System.out.print("Amigo tu boton anterior registro funciona");
        if (!rs.isFirst()){
            rs.previous();
    }
        this.setNombre(rs.getString("nombre"));
        this.setEmail(rs.getString("email"));
    }
    
    public void moverUltimoRegistro() throws SQLException{
        System.out.print("Programa accion moverUltimoRegistro");
        rs.last();
            this.setNombre(rs.getString("nombre"));
            this.setEmail(rs.getString("email"));
    }
    
    
    public void nuevo() throws SQLException{
        System.out.print("El de nuevo funciona");
        
    }
    public void insertar(String nombre,String email){
        try {
                System.out.print("El boton insertar funciona");
                conexion = null;
                conexion = conectarDB();
                ps = conexion.prepareStatement("INSERT INTO contactos (nombre, email) VALUES (?,?)");          ps.setString(1 , nombre );
                ps.setString(2, email);
                int resultado = ps.executeUpdate();
                if (resultado != 0) {
                    JOptionPane.showMessageDialog(null, "Registro insertado");
                }else{
                    JOptionPane.showMessageDialog(null, "El registro no se inserto correctamente");
                }     
                this.conectarDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error-insertar"+ e.getMessage());
        }
        
    }
    public void modificar(String nombre,String email) throws SQLException{
        System.out.print("El boton modificar funciona");
        String actualEmail = this.getEmail();
        st.executeUpdate("UPDATE contactos SET nombre='" + nombre + "',email='" + email + "' WHERE email='" + actualEmail + "';");
        this.conectarDB();
    }

    public void eliminar(String email) throws SQLException {
        System.out.print("El boton eliminar funciona");
        st.executeUpdate("DELETE FROM contactos WHERE email='" + email + "';"); 
        this.conectarDB();
    }
   }
