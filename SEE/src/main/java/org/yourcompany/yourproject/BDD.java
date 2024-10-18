package org.yourcompany.yourproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BDD {

    private String url = null;
    private String user = null;
    private String password = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;

    int valid, appl, vol, req =0;

    public BDD(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }



    public void connect() throws SQLException{
        this.conn = DriverManager.getConnection(this.url, this.user, this.password);
    }

    //Ajout et Suppression dans les tables
    public void insertRequest(String subj, int app, Date date) throws SQLException{
        String sql = "INSERT INTO TRequest(id, subj, id_applicant, helpday) VALUES (?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setInt(1, this.req);
        this.req ++;
        pstmt.setString(2, subj);  
        pstmt.setInt(3, app);
        pstmt.setDate(4, date);

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void insertVolunteer(String nom, int age, int dpt) throws SQLException{
        String sql = "INSERT INTO TVolunteer (id, nom, age, dpt) VALUES (?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setInt(1, this.vol);
        this.vol ++;
        pstmt.setString(2, nom);  
        pstmt.setInt(3, age);
        pstmt.setInt(4, dpt);


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

    public void insertApplicant(String nom, int age, int dpt) throws SQLException{
        String sql = "INSERT INTO TApplicant (id, nom, age, dpt) VALUES (?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setInt(1, this.appl);
        this.appl++;
        pstmt.setString(2, nom);  
        pstmt.setInt(3, age);
        pstmt.setInt(4, dpt);

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void deleteApplicantByName(String nom) throws SQLException{
        String sql = "DELETE FROM TApplicant WHERE nom=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nom);  

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) supprimée(s)");
    }

    public void deleteApplicantById(int id) throws SQLException{
        String sql = "DELETE FROM TApplicant WHERE id=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);  

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) supprimée(s)");
    }

    public void insertValidator(String nom, int age, int dpt, String orga) throws SQLException{
        String sql = "INSERT INTO TValidator (id, nom, age, dpt) VALUES (?,?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setInt(1, this.appl);
        this.appl++;
        pstmt.setString(2, nom);  
        pstmt.setInt(3, age);
        pstmt.setInt(4, dpt);
        pstmt.setString(5, orga); 

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) insérée(s)");
    }

    public void deleteValidatorByName(String nom) throws SQLException{
        String sql = "DELETE FROM TApplicant WHERE nom=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nom);  

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) supprimée(s)");
    }

    public void deleteValidatorById(int id) throws SQLException{
        String sql = "DELETE FROM TValidator WHERE id=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);  

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " ligne(s) supprimée(s)");
    }

    //Affichage du contenus des tables

    public void printRequest() throws SQLException{
        String sql = "SELECT * FROM TRequest";
        this.pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + date);
            System.out.println("Status: " + status);
            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
            }
        }

        System.out.println("-----------------------------------------");
    }

    public void printRequestUser(Applicant app) throws SQLException{
        String sql = "SELECT TRequest.*, TAppliacnt FROM TRequest" + "JOIN TApplicant.id = TRequest.id_applicant" + "WHERE TApplicant.nom =";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, app.getNom());
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + date);
            System.out.println("Status: " + status);
            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
            }
        }

        System.out.println("-----------------------------------------");
    }
}
