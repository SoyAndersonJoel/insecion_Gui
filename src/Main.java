import java.sql.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Poo";
        String user = "root";
        String password = "";

        String sql = "INSERT INTO estudiantes (cedula, nombre, b1, b2) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement cadenaPreparada = connection.prepareStatement(sql);
            String cedula = JOptionPane.showInputDialog(null, "Introduzca la cédula");
            String nombre = JOptionPane.showInputDialog(null, "Introduzca el nombre");
            String b1Str = JOptionPane.showInputDialog(null, "Introduzca la nota de b1");
            String b2Str = JOptionPane.showInputDialog(null, "Introduzca la nota de b2");

            Double b1 = Double.parseDouble(b1Str);
            Double b2 = Double.parseDouble(b2Str);

            Estudiante estudiante = new Estudiante(cedula, nombre, b1, b2);

            cadenaPreparada.setString(1, estudiante.getCedula());
            cadenaPreparada.setString(2, estudiante.getNombre());
            cadenaPreparada.setDouble(3, estudiante.getB1());
            cadenaPreparada.setDouble(4, estudiante.getB2());

            cadenaPreparada.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos insertados", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error ", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
