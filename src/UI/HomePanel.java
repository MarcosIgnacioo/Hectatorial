package UI;

import Tablas.TablaAfterEffects;
import SQL.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HomePanel extends JFrame {
    public HomePanel(){
        setSize(1000, 1000);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la aplicación al cerrar la ventana
        setLayout(null);
        // Agregar componentes a la ventana
        DefaultTableModel modelo = new DefaultTableModel();

        // Agregar columnas al modelo
        modelo.addColumn("Nombre");
        modelo.addColumn("Edad");
        modelo.addColumn("Fecha de Nacimiento");

        // Agregar filas al modelo
        modelo.addRow(new Object[]{"Juan", 25,  SQL.parseDate("1998-03-15")});
        modelo.addRow(new Object[]{"María", 30, SQL.parseDate("1991-07-28")});
        modelo.addRow(new Object[]{"Pedro", 35, SQL.parseDate("1988-11-05")});
        modelo.addRow(new Object[]{"Laura", 28, SQL.parseDate("1993-09-10")});
        TablaAfterEffects af = new TablaAfterEffects(modelo);
        JScrollPane js = new JScrollPane(af);
        js.setSize(500,700);
        js.setVisible(true);
        js.setLocation(0,0);
        add(js);
        // Mostrar la ventana
        setVisible(true);
    }
}
