package org.yourcompany.yourproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BDD {

    private String url = null;
    private String user = null;
    private String password = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    public BDD(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void connect() throws SQLException{
        this.conn = DriverManager.getConnection(this.url, this.user, this.password);
    }

    //A FINIR
    public void insertRequest(String subj, int vol, int app, Date date) throws SQLException{
        String sql = "INSERT INTO TRequest(subj, id_volunteer, id_applicant, helpday) VALUES (?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, subj);  
        pstmt.setInt(2, vol);
        pstmt.setInt(3, app);
        pstmt.setDate(4, date);

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void insertVolunteer(String nom, int age, int dpt) throws SQLException{
        String sql = "INSERT INTO TVolunteer (nom, age, dpt) VALUES (?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, nom);  
        pstmt.setInt(2, age);
        pstmt.setInt(3, dpt);


        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void insertApplicant(String nom, int age, int dpt) throws SQLException{
        String sql = "INSERT INTO TVolunteer (nom, age, dpt) VALUES (?,?, ?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, nom);  
        pstmt.setInt(2, age);
        pstmt.setInt(3, dpt);

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void deleteVolunteerByName(String nom) throws SQLException{
        String sql = "DELETE FROM TVolunteer WHERE nom=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nom);  

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) supprimée(s)");
    }

    public void deleteVolunteerById(int id) throws SQLException{
        String sql = "DELETE FROM TVolunteer WHERE id=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);  

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) supprimée(s)");
    }
}
