/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;


/**
 *
 * @author delga
 */
public class RellenarCombox {
    
public void rellenarComboBox(String espacio, String valor, JComboBox<String> combo) throws SQLException {
        String sql = "SELECT * FROM " + espacio;
        DatabaseConnection con = new DatabaseConnection();
        java.sql.Connection conexion = con.conectar();

        try (Statement st = conexion.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                combo.addItem(rs.getString(valor));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Registra la excepción
        }
    }
}
