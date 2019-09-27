/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.folder;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Koneksi {

    /**
     * @param args the command line arguments
     */
    
    public static Connection getKoneksi(String host, String port, String username, String password, String db) {
        String konString = "jdbc:mysql://"+ host + ":" + port + "/" + db ;
        Connection koneksi = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = DriverManager.getConnection(konString, username, password);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Koneksi Error");
            koneksi = null;
        }
        
        return koneksi;
    }
    
    public static void main(String[] args) {
        Login l = new Login("Pelanggan");
        l.setVisible(true);
    }
    
}
