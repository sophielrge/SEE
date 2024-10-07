/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sophie
 */
public class SEE {

    public enum Stat {
        PENDING, 
        VALIDATED, 
        COMPLETED 
    }

    public static void main(String[] args) throws SQLException {

         // URL de connexion au serveur MySQL
        String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/"; 
        String user = "projet_gei_007"; 
        String password = "deiD1ou2";
        Connection conn = null;
        PreparedStatement pstmt = null;

        conn = DriverManager.getConnection(url, user, password);
        String sql = "INSERT INTO User (nom, email) VALUES (?,?)";
        pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, "Elise");  
        pstmt.setString(2, "elise.gimond");

        // Exécuter la requête
        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");

        Applicant a = new Applicant("Sophax", 20);
        Request r = a.makeRequest();
        System.out.println("requete :" + r.getSubject() + " date: " + r.getDate());

    }
}
