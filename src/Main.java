import Tablas.TablaAfterEffects;
import UI.HomePanel;

import javax.swing.*;
import java.sql.*;
public class Main extends JFrame {

    Conexion c = new Conexion();
    public static void main(String[] args) throws SQLException {
        Conexion.getConnection();
        Conexion.crearBaseDeDatosSiNoExiste();
        Conexion.crearTablasSiNoExisten();
        HomePanel hp = new HomePanel();
    }
}