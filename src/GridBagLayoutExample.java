import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridBagLayoutExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 600)); // Tamaño deseado para la ventana

        JPanel panel = new JPanel(new GridBagLayout());

        // Crear las restricciones
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH; // Rellenar tanto en horizontal como en vertical
        constraints.weightx = 1.0; // Peso horizontal para expandirse
        constraints.weighty = 1.0; // Peso vertical para expandirse

        // Agregar un componente al grid
        JButton button1 = new JButton("Button 1");
        constraints.gridx = 0; // Columna 0
        constraints.gridy = 0; // Fila 0
        constraints.gridwidth = 10; // Ancho de 1 celda
        constraints.gridheight = 10; // Alto de 1 celda
        panel.add(button1, constraints);

        // Agregar otro componente al grid
        JButton button2 = new JButton("Button 2");
        constraints.gridx = 1; // Columna 1
        constraints.gridy = 0; // Fila 0
        constraints.gridwidth = 10; // Ancho de 1 celda
        constraints.gridheight = 10; // Alto de 1 celda
        panel.add(button2, constraints);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
