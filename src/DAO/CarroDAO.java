/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import principal.DatabaseConnection;
import principal.Carros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author Trabajo
 */
public class CarroDAO {
     public void insertarCarro(Carros carro) {
        String sql = "INSERT INTO carros (placa, marca, modelo, color,persona_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, carro.getplaca());
            pstmt.setString(2, carro.getmarca());
            pstmt.setString(3, carro.getmodelo());
            pstmt.setString(4, carro.getcolor());
            pstmt.setInt(5, carro.getid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
