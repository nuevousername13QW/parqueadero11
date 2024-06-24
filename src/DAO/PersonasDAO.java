/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import principal.DatabaseConnection;
import principal.Personas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import principal.Espacio;
import java.sql.ResultSet;
/**
 *
 * @author Trabajo
 */
public class PersonasDAO {
    public void insertarPersona(Personas persona, Espacio espacio) {
    String checkSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
    String checkPersonaSql = "SELECT COUNT(*) FROM personas WHERE persona_id = ?";
    String insertSql = "INSERT INTO personas (persona_id, nombre, telefono) VALUES (?, ?, ?)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement checkStmt = conn.prepareStatement(checkSql);
         PreparedStatement checkPersonaStmt = conn.prepareStatement(checkPersonaSql);
         PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

        // Verificar disponibilidad del espacio
        checkStmt.setInt(1, espacio.getid());
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            boolean disponible = rs.getBoolean("Disponible");

            if (disponible) {
                // Verificar si la persona ya existe
                checkPersonaStmt.setObject(1, persona.getid());
                ResultSet rsPersona = checkPersonaStmt.executeQuery();
                rsPersona.next();
                
                int count = rsPersona.getInt(1);
                // Insertar persona
                if (count == 0) {
                    // Insertar persona
                    insertStmt.setObject(1, persona.getid());
                    insertStmt.setString(2, persona.getNombre());
                    insertStmt.setObject(3, persona.getTelefono());
                    insertStmt.executeUpdate();
                } else {
                    System.out.println("La persona con esta identificación ya existe.");
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

