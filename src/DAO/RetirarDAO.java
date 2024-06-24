/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public void Retirar(Retirar retirar) {
        String placaIngresada = retirar.getplaca(); // Obtener la placa ingresada

        String deleteCarQuery = "DELETE FROM carros WHERE placa = ?";
        String deletePersonQuery = "DELETE FROM personas WHERE placa = ?";

        try (Connection connection = DriverManager.getConnection(url, userid, password);
             PreparedStatement deleteCarStmt = connection.prepareStatement(deleteCarQuery);
             PreparedStatement deletePersonStmt = connection.prepareStatement(deletePersonQuery)) {

            deleteCarStmt.setString(1, placaIngresada);
            deletePersonStmt.setString(1, placaIngresada);

            int carRowsDeleted = deleteCarStmt.executeUpdate();
            int personRowsDeleted = deletePersonStmt.executeUpdate();

            // Verificar si se eliminaron filas correctamente
            if (carRowsDeleted > 0 && personRowsDeleted > 0) {
                System.out.println("Carro y persona retirados exitosamente.");
            } else {
                System.out.println("No se encontró la placa o no se eliminaron filas.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores
        }
    }
}
