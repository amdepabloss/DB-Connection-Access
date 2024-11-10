import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Date;
import java.time.ZoneId;


public class ManejoTablas {
    
    public void ManejoTablasBD(Connection conexion) {
        
        try {
            Statement stmt = conexion.createStatement();
            
            // Eliminar tablas
            stmt.executeUpdate("DROP TABLE Detalle_Pedido CASCADE CONSTRAINTS");
            stmt.executeUpdate("DROP TABLE Pedido CASCADE CONSTRAINTS");
            stmt.executeUpdate("DROP TABLE Stock CASCADE CONSTRAINTS");

            // Crear tabla Stock
            String crear_stock = "CREATE TABLE Stock ("
                    + "Cproducto NUMBER PRIMARY KEY, "
                    + "Cantidad NUMBER)";
            stmt.executeUpdate(crear_stock);
            System.out.println("Tabla Stock creada exitosamente.");

            // Crear tabla Pedido
            String crear_pedido = "CREATE TABLE Pedido ("
                    + "Cpedido NUMBER PRIMARY KEY, "
                    + "Ccliente NUMBER, "
                    + "Fecha_pedido DATE)";
            stmt.executeUpdate(crear_pedido);
            System.out.println("Tabla Pedido creada exitosamente.");

            // Crear tabla Detalle_Pedido
            String crear_detalle_pedido = "CREATE TABLE Detalle_Pedido ("
                    + "Cproducto NUMBER, "
                    + "Cpedido NUMBER, "
                    + "Cantidad NUMBER, "
                    + "PRIMARY KEY (Cproducto, Cpedido), "
                    + "FOREIGN KEY (Cproducto) REFERENCES Stock(Cproducto), "
                    + "FOREIGN KEY (Cpedido) REFERENCES Pedido(Cpedido))";
            stmt.executeUpdate(crear_detalle_pedido);
            System.out.println("Tabla Detalle_Pedido creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarTuplasInicial(Connection conexion) {
        try {
            Statement stmt = conexion.createStatement();
            // Insertar datos en la tabla Stock
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (1, 100)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (2, 50)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (3, 30)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (4, 20)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (5, 15)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (6, 10)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (7, 100)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (8, 75)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (9, 60)");
            stmt.executeUpdate("INSERT INTO Stock (Cproducto, Cantidad) VALUES (10, 45)");
    
            System.out.println("Datos insertados en la tabla Stock.");
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertarTuplasPedido(Connection conexion, String s1, String s2) {
        try {
            // Obtener la fecha actual
            conexion.setAutoCommit(false);
            Date fechaActual = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            
            // Convertir la fecha actual a un String
            String fechaComoString = formatoFecha.format(fechaActual);
            
            // Crear la declaración SQL
            Statement stmt = conexion.createStatement();
            
            // Agregar comillas simples alrededor de la fecha
            String sql = "INSERT INTO Pedido (Ccliente, Cpedido, Fecha_pedido) VALUES (" + s1 + ", " + s2 + ", TO_DATE('" + fechaComoString + "', 'YYYY-MM-DD'))";
            
            // Ejecutar la actualización
            stmt.executeUpdate(sql);
            System.out.println("Datos insertados");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarTuplasDetalle(Connection conexion, String s1, String s2, String s3) {
        try {
            // Crear la declaración SQL
            conexion.setAutoCommit(false);
            Statement stmt = conexion.createStatement();
    
            // Consultar la cantidad actual de Stock
            ResultSet resultado = stmt.executeQuery("SELECT Cantidad FROM Stock WHERE Cproducto = " + s2);
            
            if (resultado.next()) {
                int cantidad = resultado.getInt(1); // Obtener la cantidad del producto en Stock
                
                // Verificar si hay suficiente cantidad en stock
                if (cantidad >= Integer.parseInt(s3)) {
                    // Insertar en Detalle_Pedido
                    String sql = "INSERT INTO Detalle_Pedido (Cpedido, Cproducto, Cantidad) VALUES (" + s1 + ", " + s2 + ", " + s3 + ")";
                    stmt.executeUpdate(sql);
                    System.out.println("Datos insertados en Detalle_Pedido");
    
                    // Actualizar la cantidad en Stock
                    int nuevaCantidad = cantidad - Integer.parseInt(s3);
                    sql = "UPDATE Stock SET Cantidad = " + nuevaCantidad + " WHERE Cproducto = " + s2;
                    stmt.executeUpdate(sql);
                    System.out.println("Stock actualizado. Nueva cantidad: " + nuevaCantidad);
                } else {
                    System.out.println("No hay cantidad suficiente");
                }
            } else {
                System.out.println("El producto no existe en el stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String mostrarTuplas(Connection conexion) { //TO-DO: PONERLO BONITO
        String tablas = "";
        try {
            Statement stmt = conexion.createStatement();
            
            // Definimos las consultas para cada tabla que queremos mostrar
            String[] consultasSQL = {
                "SELECT * FROM Stock",
                "SELECT * FROM Pedido",
                "SELECT * FROM Detalle_Pedido"
            };
    
            // Recorremos cada consulta
            for (String consultaSQL : consultasSQL) {
                ResultSet resultado = stmt.executeQuery(consultaSQL);
    
                // Añadimos un título para cada tabla
                tablas += "<br>";
                
                // Obtener el número de columnas
                int columnas = resultado.getMetaData().getColumnCount();
    
                // Recorrer y mostrar el contenido de cada fila de la tabla
                while (resultado.next()) {
                    for (int i = 1; i <= columnas; i++) {
                        tablas += (resultado.getString(i) + "      ");
                    }
                    tablas += "<br>";
                }
                tablas += "<br>"; // Separador entre tablas
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablas;
    }

    public void Commit(Connection conexion){
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate("COMMIT WORK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RollBack(Connection conexion){
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate("ROLLBACK WORK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SavePoint(Connection conexion){
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate("SAVEPOINT SAVEPOINT1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void RollBackTo(Connection conexion){
        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate("ROLLBACK TO SAVEPOINT SAVEPOINT1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

