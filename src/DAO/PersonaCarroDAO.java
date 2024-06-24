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
import principal.Entrada;
import principal.Espacio;
import principal.Personas;
/**
 *
 * @author Trabajo
 */
public class PersonaCarroDAO {
    public void insertarPersonaYCarro(Personas persona, Carros carro, Espacio espacio) {
        String checkEspacioSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
        String checkPersonaSql = "SELECT COUNT(*) FROM personas WHERE persona_id = ?";
        String checkPlacaSql = "SELECT COUNT(*) FROM carros WHERE placa = ?";
        String insertPersonaSql = "INSERT INTO personas (persona_id, nombre, telefono) VALUES (?, ?, ?)";
        String insertCarroSql = "INSERT INTO carros (placa, marca, modelo, color, persona_id) VALUES (?, ?, ?, ?, ?)";
        String updateEspacioSql = "UPDATE espacio SET Disponible = ? WHERE espacio_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkEspacioStmt = conn.prepareStatement(checkEspacioSql);
             PreparedStatement checkPersonaStmt = conn.prepareStatement(checkPersonaSql);
             PreparedStatement checkPlacaStmt = conn.prepareStatement(checkPlacaSql);
             PreparedStatement insertPersonaStmt = conn.prepareStatement(insertPersonaSql);
             PreparedStatement insertCarroStmt = conn.prepareStatement(insertCarroSql);
             PreparedStatement updateEspacioStmt = conn.prepareStatement(updateEspacioSql)) {

            // Verificar disponibilidad del espacio
            checkEspacioStmt.setInt(1, espacio.getid());
            ResultSet rsEspacio = checkEspacioStmt.executeQuery();

            if (rsEspacio.next()) {
                boolean disponible = rsEspacio.getBoolean("Disponible");

                if (!disponible) {
                    System.out.println("El espacio no está disponible.");
                    return; // Cancelar el proceso
                }
            } else {
                System.out.println("El espacio no existe.");
                return; // Cancelar el proceso
            }

            // Verificar si la persona ya existe
            checkPersonaStmt.setObject(1, persona.getid());
            ResultSet rsPersona = checkPersonaStmt.executeQuery();
            rsPersona.next();
            int personaCount = rsPersona.getInt(1);

            if (personaCount > 0) {
                System.out.println("La persona con esta identificación ya existe.");
                return; // Cancelar el proceso
            }

            // Verificar si la placa ya existe
            checkPlacaStmt.setString(1, carro.getplaca());
            ResultSet rsPlaca = checkPlacaStmt.executeQuery();
            rsPlaca.next();
            int placaCount = rsPlaca.getInt(1);

            if (placaCount > 0) {
                System.out.println("La placa ya existe.");
                return; // Cancelar el proceso
            }

            // Solo proceder si todas las verificaciones pasaron
            if (personaCount == 0 && placaCount == 0) {
                // Insertar persona
                insertPersonaStmt.setObject(1, persona.getid());
                insertPersonaStmt.setString(2, persona.getNombre());
                insertPersonaStmt.setObject(3, persona.getTelefono());
                insertPersonaStmt.executeUpdate();

                // Insertar carro
                insertCarroStmt.setString(1, carro.getplaca());
                insertCarroStmt.setString(2, carro.getmarca());
                insertCarroStmt.setString(3, carro.getmodelo());
                insertCarroStmt.setString(4, carro.getcolor());
                insertCarroStmt.setInt(5, carro.getid());
                insertCarroStmt.executeUpdate();

                // Actualizar disponibilidad del espacio
                updateEspacioStmt.setBoolean(1, false); // Marcar como no disponible
                updateEspacioStmt.setInt(2, espacio.getid());
                updateEspacioStmt.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }
    public void insertarEntrada(Entrada entrada, Carros carro) {
    String checkPlacaSql = "SELECT COUNT(*) FROM Carros WHERE placa = ?";
    String insertSql = "INSERT INTO Entrada (placa, espacio_id, fecha_entrada) VALUES (?, ?, CURRENT_TIMESTAMP())";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement checkPlacaStmt = conn.prepareStatement(checkPlacaSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
        
        // Verificar si la placa ya existe
        checkPlacaStmt.setString(1, carro.getplaca());
        ResultSet rsPlaca = checkPlacaStmt.executeQuery();
        rsPlaca.next();
        int placaCount = rsPlaca.getInt(1);
                
         if (placaCount == 0) {
             System.out.println("La placa ya existe.");
            return; // Cancelar el proceso  
         }
        
        if (placaCount > 0) {
        insertStmt.setString(1, entrada.getplaca());
        insertStmt.setInt(2, entrada.getid());
        insertStmt.executeUpdate();
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}

