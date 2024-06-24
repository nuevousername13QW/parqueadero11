/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import principal.DatabaseConnection;
import principal.Espacio;

/**
 *
 * @author Trabajo
 */
public class EspacioDAO {
    public void actualizarDisponibilidad(Espacio espacio) {
        String checkSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
        String updateSql = "UPDATE espacio SET Disponible = ? WHERE espacio_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // Verificar disponibilidad
            checkStmt.setInt(1, espacio.getid());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                boolean disponible = rs.getBoolean("Disponible");

                if (disponible) {
                    // Actualizar disponibilidad a no disponible
                    updateStmt.setBoolean(1, false);
                    updateStmt.setInt(2, espacio.getid());
                    updateStmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "El espacio ha sido asignado y marcado como no disponible.");
                } else {
                    JOptionPane.showMessageDialog(null, "El espacio ya está ocupado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El espacio no existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public boolean isEspacioDisponible(int espacioId) {
        String checkSql = "SELECT Disponible FROM espacio WHERE espacio_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

            checkStmt.setInt(1, espacioId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("Disponible");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si no se encuentra el espacio o hay un error, se asume no disponible
    }
    
}

