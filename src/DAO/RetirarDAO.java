package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Retirar;

public class RetirarDAO {
    private final String url = "jdbc:mysql://localhost:3306/parqueadero";
    private final String userid = "root";
    private final String password = "David%2006";

    public void retirarVehiculo(Retirar retirar) {
        try (Connection connection = DriverManager.getConnection(url, userid, password)) {
            String placaIngresada = retirar.getPlaca(); // Obtener la placa ingresada
            System.out.println("Placa en PreparedStatement: " + placaIngresada); // Declaración de depuración

            // Actualizar la disponibilidad del espacio y eliminar la placa
            String updateEspacioQuery = "UPDATE Espacio e " +
                                        "JOIN Entrada en ON e.espacio_id = en.espacio_id " +
                                        "SET e.disponible = true, en.placa = NULL " +
                                        "WHERE en.placa = ?";

            try (PreparedStatement updateEspacioStmt = connection.prepareStatement(updateEspacioQuery)) {
                updateEspacioStmt.setString(1, placaIngresada);
                int rowsUpdated = updateEspacioStmt.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Disponibilidad del espacio actualizada y placa eliminada exitosamente.");
                } else {
                    System.out.println("No se encontró la placa o no se actualizó la disponibilidad.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores (puedes personalizarlo según tus necesidades)
        }
    }
}
