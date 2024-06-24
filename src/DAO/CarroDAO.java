/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import principal.DatabaseConnection;
import principal.Carros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import principal.Espacio;
/**
 *
 * @author Trabajo
 */
public class CarroDAO {
     public void insertarCarro(Carros carro, Espacio espacio) {
    String checkEspacioSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
    String checkPlacaSql = "SELECT COUNT(*) FROM carros WHERE placa = ?";
    String insertSql = "INSERT INTO carros (placa, marca, modelo, color, persona_id) VALUES (?, ?, ?, ?, ?)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement checkEspacioStmt = conn.prepareStatement(checkEspacioSql);
         PreparedStatement checkPlacaStmt = conn.prepareStatement(checkPlacaSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

        // Verificar disponibilidad del espacio
        checkEspacioStmt.setInt(1, espacio.getid());
        ResultSet rsEspacio = checkEspacioStmt.executeQuery();
        
        if (rsEspacio.next()) {
            boolean disponible = rsEspacio.getBoolean("Disponible");

            if (disponible) {
                // Verificar si la placa ya existe
                checkPlacaStmt.setString(1, carro.getplaca());
                ResultSet rsPlaca = checkPlacaStmt.executeQuery();
                rsPlaca.next();
                int count = rsPlaca.getInt(1);

                if (count == 0) {
                    // Insertar carro
                    insertStmt.setString(1, carro.getplaca());
                    insertStmt.setString(2, carro.getmarca());
                    insertStmt.setString(3, carro.getmodelo());
                    insertStmt.setString(4, carro.getcolor());
                    insertStmt.setInt(5, carro.getid());
                    insertStmt.executeUpdate();
                } else {
                    System.out.println("La placa ya existe.");
                }
            } else {
                System.out.println("El espacio no está disponible.");
            }
        } else {
            System.out.println("El espacio no existe.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
