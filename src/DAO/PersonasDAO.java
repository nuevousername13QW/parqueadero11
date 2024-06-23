/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import principal.DatabaseConnection;
import principal.Personas;
import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;

/**
 *
 * @author Trabajo
 */
public class PersonasDAO {
    public void insertarPersona(Personas persona) {
        String sql = "INSERT INTO personas (persona_id,nombre,telefono) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, persona.getid());
            pstmt.setString(2, persona.getNombre());
            pstmt.setInt(3, persona.getTelefono());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
