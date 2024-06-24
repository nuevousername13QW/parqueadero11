/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import principal.Factura;
import principal.DatabaseConnection;
import principal.Personas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Trabajo
 */
public class PersonasDAO {
    public void insertarPersona(Personas persona) {
        String sql = "INSERT INTO personas (persona_id,nombre,telefono) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setObject(1, persona.getid());
            pstmt.setString(2, persona.getNombre());
            pstmt.setObject(3, persona.getTelefono());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void sacarPersona (Personas persona){
    
        String sql = "SELECT persona_id, nombre FROM personas";
    }
}
