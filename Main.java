import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//COMPILAR: javac -cp .:ojdbc11.jar ManejoTablas.java Interfaz.java Main.java
//EJECUTAR: java -cp .:ojdbc11.jar Main


public class Main{

    public static void main(String[] args){
        
        // Información de la base de datos
        String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=oracle0.ugr.es)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=practbd)))";
        String usuario = "...";   // Usuario de Oracle
        String clave = "...";  // Contraseña de Oracle
    
        Connection conexion = null;
        boolean cerrar_conexion = false;
    
        try {
            // Cargar el driver JDBC de Oracle
            Class.forName("oracle.jdbc.OracleDriver");
    
            // Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión exitosa!");
            
            //ABRIMOS INTERFAZ
            Interfaz UI = new Interfaz(conexion);

            
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de conexión a la base de datos");
            e.printStackTrace();
        } 
    }
};





