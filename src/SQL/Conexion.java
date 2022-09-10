/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author davpa
 */
public class Conexion {
    
    private static String ciudad="Quito";
    private static int idC=1;
    
    public static Connection getConexion() {

        
        String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                +"databaseName=PryDb;"
                +"user=sa;"
                +"password=1234;";
        
        try {
           Connection con = DriverManager.getConnection (conexionUrl);
           return con;
        } catch ( SQLException e ) {
            System.out.println ( e.toString ( ) ) ;
            return null ;
        }
    }
    
    
    public static int llenarID(String tabla, String ide){
        int id=0;
        
        int columnas;
        try{
            ResultSet rs;
            PreparedStatement ps;
            ResultSetMetaData rsmd;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT "+ ide +" FROM "+ tabla);
            //ps.setString(1, tabla);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();
            
            
            while(rs.next()){

                for(int indice =0; indice < columnas; indice++){
                    if(id<rs.getInt(indice +1)){
                        id = rs.getInt(indice +1);
                    }
                    
                }
            }
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return id+1;
    }
    
    
    public static String getCiudad(){
        return ciudad;
    }
    public static int getID(){
        return idC;
    }
    
}
