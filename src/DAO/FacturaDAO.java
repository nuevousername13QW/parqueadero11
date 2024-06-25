/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Trabajo
 */
    
    import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FacturaDAO {
    private String url = "jdbc:mysql://localhost:3306/parqueadero";
    private String usuario = "root";
    private String contraseña = "David%2006";

    public Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(url, usuario, contraseña);
    }

    
    public int obtenerEntradaId(String placa) {
        String sql = "SELECT e.entrada_id FROM carros c " +
                     "JOIN Entrada e ON c.placa = e.placa " +
                     "WHERE c.placa = ?";
        return obtenerId(sql, placa);        
        
    }

    public int obtenerSalidaId(int entradaId) {
        String sql = "SELECT s.salida_id FROM Salida s " +
                     "WHERE s.entrada_id = ?";
        return obtenerId(sql, entradaId);
    }

    private int obtenerId(String sql, Object parametro) {
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (parametro instanceof String) {
                statement.setString(1, (String) parametro);
            } else if (parametro instanceof Integer) {
                statement.setInt(1, (int) parametro);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
        return -1; // Retorna -1 en caso de error o si no se encuentra el ID
        
    }
   
    
 public boolean insertarFactura(int entradaId, int salidaId) {
        String sql = "INSERT INTO Factura (entrada_id, salida_id) VALUES (?, ?)";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, entradaId);
            statement.setInt(2, salidaId);

            int filasInsertadas = statement.executeUpdate();
            return filasInsertadas > 0; // Retorna true si se insertó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores
        }
        return false; // Retorna false en caso de error
    }

 
 public ResultSet obtenerFacturaPorPlaca(String placa) {
    String sql = "SELECT p.nombre AS nombre_persona, p.persona_id, c.placa AS placa_vehiculo, e.fecha_entrada, s.fecha_salida " +
                 "FROM Factura f " +
                 "JOIN Entrada e ON f.entrada_id = e.entrada_id " +
                 "JOIN Salida s ON f.salida_id = s.salida_id " +
                 "JOIN carros c ON e.placa = c.placa " +
                 "JOIN personas p ON c.persona_id = p.persona_id " +
                 "WHERE c.placa = ? " +
                 "ORDER BY f.factura_id DESC " +
                 "LIMIT 1";

    try (Connection connection = obtenerConexion();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, placa); // Establecer el valor del parámetro
        System.out.println("Ejecutando consulta para la placa: " + placa); // Mensaje de depuración
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            // Obtener los valores de las columnas
            String nombrePersona = resultSet.getString("nombre_persona");
            String cedula = resultSet.getString("persona_id");
            String placaVehiculo = resultSet.getString("placa_vehiculo");
            String fechaEntrada = resultSet.getString("fecha_entrada");
            String fechaSalida = resultSet.getString("fecha_salida");

            // Imprimir o procesar los valores según tus necesidades
            System.out.println("Nombre de la persona: " + nombrePersona);
            System.out.println("Cédula: " + cedula);
            System.out.println("Placa del vehículo: " + placaVehiculo);
            System.out.println("Fecha de entrada: " + fechaEntrada);
            System.out.println("Fecha de salida: " + fechaSalida);
        } else {
            System.out.println("No se encontraron resultados para la placa " + placa);
        }
        return resultSet;
    } catch (SQLException e) {
        e.printStackTrace();
        // Manejo de errores
    }
    return null; // En caso de no obtener resultados o error
}
    
   
 }
 

  
 
 


    
    
    /*textFieldNombrePersona.setText(nombrePersona);
textFieldPersonaId.setText(String.valueOf(personaId));
textFieldPlacaVehiculo.setText(placaVehiculo);
textFieldFechaEntrada.setText(fechaEntrada);
textFieldFechaSalida.setText(fechaSalida);*/

