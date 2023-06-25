package Tablas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MoldeTablasPanel extends JPanel {
    JPanel contendor_tablas;
    JTable tabla_Datos;
    JLabel hectareas_Nuevas_Lbl;
    JTextField hectareas_Nuevas_TF;
    JLabel siembra_Lbl;
    JTextField siembra_TF;
    JLabel productor_Lbl;
    JComboBox productor_CB;

    public MoldeTablasPanel(DefaultTableModel dtm_Tabla, String [] nombres_Productores) {
        this.tabla_Datos = new JTable(dtm_Tabla);
        this.hectareas_Nuevas_Lbl = new JLabel("Ingrese las hectareas");
        this.hectareas_Nuevas_TF = new JTextField();
        this.siembra_Lbl = new JLabel("Ingrese qué es lo que se sembró");
        this.siembra_TF = new JTextField();
        this.productor_Lbl = new JLabel("Seleccione el nombre del productor");
        this.productor_CB = new JComboBox(nombres_Productores);

        this.contendor_tablas = new JPanel();
        this.contendor_tablas.setLayout(new GridBagLayout());
    }
}
