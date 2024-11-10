import javax. swing.*;
import java.awt.*;
import java.awt.event. ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Interfaz {

    public Interfaz (Connection conexion) {
        
        ManejoTablas manejo_tablas = new ManejoTablas();

        //----------------------------------------------------------------------------------------------------
        //FRAME------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------------------------------

        //setLayout: desactiva el administrador de diseño predeterminado de Java Swing, lo que permite colocar 
        //componentes en ubicaciones específicas usando coordenadas absolutas.

        Frame menu = new JFrame ("Seminario 1");
        menu.setSize( 840, 420);
        menu.setLayout (null) ;
        menu.setResizable(false);

        Frame menu_pedido = new JFrame ("Dar de alta nuevo pedido");
        menu_pedido.setSize( 840, 420);
        menu_pedido.setLayout (null) ;
        menu_pedido.setResizable(false);

        Frame menu_pedido_detalles = new JFrame("Detalles del pedido");
        menu_pedido_detalles.setSize( 840, 420);
        menu_pedido_detalles.setLayout (null) ;
        menu_pedido_detalles.setResizable(false);

        Frame incluir_detalles = new JFrame("Datos del detalle del pedido"); 
        incluir_detalles.setSize( 840, 420);
        incluir_detalles.setLayout (null) ;
        incluir_detalles.setResizable(false);

        Frame contenido_tablas = new JFrame("Contenido de las tablas");
        contenido_tablas.setSize( 840, 840);
        contenido_tablas.setLayout (null) ;
        contenido_tablas.setResizable(false);

        //----------------------------------------------------------------------------------------------------
        //LABEL------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------------------------------

        JLabel label_elige_opcion = new JLabel("Elija una opción");
        label_elige_opcion.setBounds (75,0,300,200);
        menu.add(label_elige_opcion);
        label_elige_opcion.setFont(new Font("Verdana", Font.BOLD, 30)); // optional

        JLabel label_datos_pedido = new JLabel("Introduzca los datos del nuevo pedido");
        label_datos_pedido.setBounds (75,0,700,200);
        menu_pedido.add(label_datos_pedido);
        label_datos_pedido.setFont(new Font("Verdana", Font.BOLD, 30)); // optional

        JLabel label_ccliente = new JLabel("Código de cliente: ");
        label_ccliente.setBounds (75,50,700,200);
        menu_pedido.add(label_ccliente);
        label_ccliente.setFont(new Font("Verdana", Font.BOLD, 30)); // optional

        JLabel label_cpedido = new JLabel("Código de pedido: ");
        label_cpedido.setBounds (75,100,700,200);
        menu_pedido.add(label_cpedido);
        label_cpedido.setFont(new Font("Verdana", Font.BOLD, 30)); // optional

        JLabel label_cproducto = new JLabel("Código de producto: ");
        label_cproducto.setBounds (75,50,700,200);
        incluir_detalles.add(label_cproducto);
        label_cproducto.setFont(new Font("Verdana", Font.BOLD, 30)); // optional

        JLabel label_cantidad = new JLabel("Cantidad: ");
        label_cantidad.setBounds (75,100,700,200);
        incluir_detalles.add(label_cantidad);
        label_cantidad.setFont(new Font("Verdana", Font.BOLD, 30)); // optional

        JLabel label_detalles= new JLabel("Introduzca los detalles del nuevo pedido");
        label_detalles.setBounds (75,0,700,200);
        incluir_detalles.add(label_detalles);
        label_detalles.setFont(new Font("Verdana", Font.BOLD, 30)); // optional

        JLabel label_contenido_tablas= new JLabel();
        label_contenido_tablas.setBounds (75,50,840,420);
        contenido_tablas.add(label_contenido_tablas);
        label_contenido_tablas.setFont(new Font("Verdana", Font.ITALIC, 20)); 

        JLabel label_titulo_stock= new JLabel("Tabla Stock, Tabla Pedido, Tabla Detalle_pedido: ");
        label_titulo_stock.setBounds (75,-175,840,420);
        contenido_tablas.add(label_titulo_stock);
        label_titulo_stock.setFont(new Font("Verdana", Font.BOLD, 20)); 

        //----------------------------------------------------------------------------------------------------
        //TEXTFIELD------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------------------------------

        JTextField ccliente = new JTextField(3);
        ccliente.setBounds(425,  140, 300,  30);
        menu_pedido.add(ccliente);
        
        JTextField cpedido= new JTextField(3);
        cpedido.setBounds(425,  190, 300,  30);
        menu_pedido.add(cpedido);

        JTextField cproducto = new JTextField(3);
        cproducto.setBounds(425,  140, 300,  30);
        incluir_detalles.add(cproducto);
        
        JTextField cantidad= new JTextField(3);
        cantidad.setBounds(425,  190, 300,  30);
        incluir_detalles.add(cantidad);

        //----------------------------------------------------------------------------------------------------
        //BUTTON------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------------------------------
        
        JButton button_crear_borrar = new JButton("Borrado y nueva creación");
        button_crear_borrar.setBounds ( 75,  150, 300,  30);
        menu.add(button_crear_borrar);

        JButton button_alta_pedido = new JButton("Dar de alta nuevo pedido");
        button_alta_pedido.setBounds ( 425,  150, 300,  30);
        menu.add(button_alta_pedido);

        JButton button_confirmar_pedido = new JButton("Confirmar pedido");
        button_confirmar_pedido.setBounds ( 425,  300, 300,  50);
        menu_pedido.add(button_confirmar_pedido);

        JButton button_anadir_detalle_prod = new JButton("Añadir detalle producto");
        button_anadir_detalle_prod.setBounds ( 75,  150, 300,  30);
        menu_pedido_detalles.add(button_anadir_detalle_prod);

        JButton button_confirmar_detalle = new JButton("Confirmar detalle");
        button_confirmar_detalle.setBounds ( 425,  300, 300,  50);
        incluir_detalles.add(button_confirmar_detalle);

        JButton button_eliminar_detalle_prod = new JButton("Eliminar detalle producto");
        button_eliminar_detalle_prod.setBounds ( 425,  150, 300,  30);
        menu_pedido_detalles.add(button_eliminar_detalle_prod);

        JButton button_cancelar = new JButton("Cancelar pedido");
        button_cancelar.setBounds ( 75,  200, 300,  30);
        menu_pedido_detalles.add(button_cancelar);

        JButton button_finalizar = new JButton("Finalizar pedido");
        button_finalizar.setBounds ( 425,  200, 300,  30);
        menu_pedido_detalles.add(button_finalizar);

        JButton button_mostrar_contenido = new JButton("Mostrar contenido tablas");
        button_mostrar_contenido.setBounds ( 75,  200, 300,  30);
        menu.add(button_mostrar_contenido);

        JButton button_salir_cerrar = new JButton("Salir y cerrar conexión");
        button_salir_cerrar.setBounds ( 425,  200, 300,  30);
        menu.add(button_salir_cerrar);

        JButton button_volver_menu = new JButton("Volver al menú");
        button_volver_menu.setBounds ( 425,  200, 300, 30);
        contenido_tablas.add(button_volver_menu);       

        //----------------------------------------------------------------------------------------------------
        //ACTION------------------------------------------------------------------------------------------------
        //----------------------------------------------------------------------------------------------------
            
        button_crear_borrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manejo_tablas.ManejoTablasBD(conexion);
                manejo_tablas.insertarTuplasInicial(conexion);
            }
        });

        button_alta_pedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                menu_pedido.setVisible(true);
            }
        });

        button_confirmar_pedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cliente = ccliente.getText();
                String pedido = cpedido.getText();
                manejo_tablas.insertarTuplasPedido(conexion, cliente, pedido);
                manejo_tablas.SavePoint(conexion);
                menu_pedido.setVisible(false);
                menu_pedido_detalles.setVisible(true);
            }
        });

        button_mostrar_contenido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu.setVisible(false);
                contenido_tablas.setVisible(true);
                label_contenido_tablas.setText("<html>" + manejo_tablas.mostrarTuplas(conexion) + "</html>");
                contenido_tablas.add(label_contenido_tablas); 
            }
        });

        button_salir_cerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (conexion != null) {
                        conexion.close(); // Cerrar la conexión
                        System.out.println("Cierre de conexión exitoso");
                        menu.setVisible(false);
                    }
                } catch (SQLException o) {
                    o.printStackTrace();
                }
            }
        });

        button_confirmar_detalle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String codigo_producto = cproducto.getText();
                String cant = cantidad.getText();
                manejo_tablas.insertarTuplasDetalle(conexion, cpedido.getText(), codigo_producto, cant);
                incluir_detalles.setVisible(false);
                menu_pedido_detalles.setVisible(true);
                label_contenido_tablas.setText("<html>" + manejo_tablas.mostrarTuplas(conexion) + "</html>");
                contenido_tablas.setVisible(true);
            }
        });

        button_anadir_detalle_prod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menu_pedido_detalles.setVisible(false);
                incluir_detalles.setVisible(true);
            }
        });

        button_eliminar_detalle_prod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manejo_tablas.RollBackTo(conexion);
                label_contenido_tablas.setText("<html>" + manejo_tablas.mostrarTuplas(conexion) + "</html>");
                contenido_tablas.setVisible(true);
            }
        });

        button_cancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manejo_tablas.RollBack(conexion);
                menu_pedido_detalles.setVisible(false);
                label_contenido_tablas.setText("<html>" + manejo_tablas.mostrarTuplas(conexion) + "</html>");
                contenido_tablas.setVisible(true);
                menu.setVisible(true);
            }  
        });

        button_finalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manejo_tablas.Commit(conexion);
                menu_pedido_detalles.setVisible(false);
                menu.setVisible(true);
            }
        });

        button_volver_menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contenido_tablas.setVisible(false);
                menu.setVisible(true);
            }
        });
        menu.setVisible(true);
    }
};
