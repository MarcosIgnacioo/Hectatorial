package Tablas;

import javax.swing.*;
import java.awt.*;

public class MoldeMiniPanel extends JPanel {
    JPanel contenedor_Panel;
    JLabel nombre_Tabla_Lbl;
    JLabel hectareas_sembradas_Lbl;
    JLabel icono_Tabla;
    public MoldeMiniPanel(String nombre_tabla, String hectareas_sembradas, String icono_direccion) {
        contenedor_Panel = new JPanel();
        contenedor_Panel.setPreferredSize(new Dimension(100,100));
        contenedor_Panel.setVisible(true);
        contenedor_Panel.setLayout(new BoxLayout(contenedor_Panel, BoxLayout.Y_AXIS));
            this.nombre_Tabla_Lbl = new JLabel(nombre_tabla);
            this.hectareas_sembradas_Lbl = new JLabel(hectareas_sembradas+" hectareas sembradas");
            this.icono_Tabla = new JLabel();
            this.icono_Tabla.setIcon(new ImageIcon(icono_direccion));
        contenedor_Panel.add(this.nombre_Tabla_Lbl);
        contenedor_Panel.add(this.hectareas_sembradas_Lbl);
        contenedor_Panel.add(this.icono_Tabla);
    }
}
