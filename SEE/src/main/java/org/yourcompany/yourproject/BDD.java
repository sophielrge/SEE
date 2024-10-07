package org.yourcompany.yourproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDD {

    private String url ;
    private String user;
    private String password;
    private Connection conn;
    private PreparedStatement pstmt;

    public BDD(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
        this.conn = null;
        this.pstmt = null;
    }

    public void connect(BDD bdd) throws SQLException{
        this.conn = DriverManager.getConnection(this.url, this.user, this.password);
    }

    //A FINIR
    public void insertRequest() throws SQLException{
        String sql = "INSERT INTO TRequest(nom, email) VALUES (?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, "Elise");  
        pstmt.setString(2, "elise.gimond");

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void insertVolunteer() throws SQLException{
        String sql = "INSERT INTO TVolunteer (nom, email) VALUES (?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, "Elise");  
        pstmt.setString(2, "elise.gimond");

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void insertApplicant() throws SQLException{
        String sql = "INSERT INTO TVolunteer (nom, email) VALUES (?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, "Elise");  
        pstmt.setString(2, "elise.gimond");

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }
}
