package org.yourcompany.yourproject;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class BDD {

    private String url = null;
    private String user = null;
    private String password = null;
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private Traitement_texte tt = new Traitement_texte();

    int valid, appl, vol, req =0;

    public BDD(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }



    public void connect() throws SQLException{
        this.conn = DriverManager.getConnection(this.url, this.user, this.password);
    }

    //Créateur
    public Request getRequest(int id) throws SQLException{
        Request req = new Request(); 
        Applicant app = null;
        Volunteer vol = null;
        Validator val = null;

        String sql = "SELECT TRequest.*, TVolunteer.id AS ID_Vol, TValidator.id AS ID_Val, TApplicant.id AS ID_App FROM TRequest " + 
        "JOIN TVolunteer ON TVolunteer.id = TRequest.id_volunteer " 
        + "JOIN TValidator ON TValidator.id = TRequest.id_validator " 
        + "JOIN TApplicant ON TApplicant.id = TRequest.id_applicant " 
        + "WHERE TRequest.id = ? ";
        
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            app = getApplicant(rs.getInt("ID_App"));
            vol = getVolunteer(rs.getInt("ID_Vol"));
            val = getValidator(rs.getInt("ID_Val"));

            req.setApp(app);
            req.setHelpD(rs.getDate("helpD"));
            req.setMotif(rs.getString("motif"));
            req.setStatus(rs.getString("status").charAt(0));
            req.setValidator(val);
            req.setVolunteer(vol);
            req.setDate(rs.getDate("date_creation").toInstant()            
            .atZone(ZoneId.systemDefault()) 
            .toLocalDate());
            req.setSubject(rs.getString("subj"));
        }

        return req; 
    }

    public Validator getValidator(int id) throws SQLException{
        Validator val = null;

        String sql = "SELECT id, nom, age, dpt, orga, psw FROM TValidator "
        + "WHERE id = ?";
        
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            val = new Validator(rs.getString("nom"),rs.getInt("age"), rs.getInt("dpt"), rs.getString("orga"), rs.getString("psw"));
            val.setName(rs.getString("nom"));
            val.setAge(rs.getInt("age"));
            val.setDpt(rs.getInt("dpt"));
            val.setPsw(rs.getString("psw"));
            val.setOrga(rs.getString("orga"));
        }

        return val; 
    }

    public Volunteer getVolunteer(int id) throws SQLException{
        Volunteer vol = null;

        String sql = "SELECT id, nom, age, dpt, psw FROM TVolunteer "
        + "WHERE id = ?";
        
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            vol = new Volunteer(rs.getString("nom"),rs.getInt("age"), rs.getInt("dpt"),rs.getString("psw"));
            vol.setName(rs.getString("nom"));
            vol.setAge(rs.getInt("age"));
            vol.setDpt(rs.getInt("dpt"));
        }

        return vol; 
    }

    public Applicant getApplicant(int id) throws SQLException{
        Applicant app = null;

        String sql = "SELECT id, nom, age, dpt, psw FROM TApplicant "
        + "WHERE id = ?";
        
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            app = new Applicant(rs.getString("nom"),rs.getInt("age"), rs.getInt("dpt"),rs.getString("psw"));
            app.setName(rs.getString("nom"));
            app.setAge(rs.getInt("age"));
            app.setDpt(rs.getInt("dpt"));
        }

        return app; 
    }

    //Getter
    public int getID_Applicant(Applicant app) throws SQLException{
        int res = -1;
        String sql = "SELECT id FROM TApplicant WHERE nom = ? AND age = ? AND dpt = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, app.getName());
        pstmt.setInt(2, app.getAge());
        pstmt.setInt(3, app.getDpt());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
        }
        return res;
    }

    public String get_psw_Applicant(int id) throws SQLException{
        String res = "";
        String sql = "SELECT psw FROM TApplicant WHERE id = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getString("psw");
        }
        return res;
    }

    public int getID_Volunteer(Volunteer vol) throws SQLException{
        int res = -1;
        String sql = "SELECT id FROM TVolunteer WHERE nom = ? AND age = ? AND dpt = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vol.getName());
        pstmt.setInt(2, vol.getAge());
        pstmt.setInt(3, vol.getDpt());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
        }
        return res;
    }

    public String get_psw_Volunteer(int id) throws SQLException{
        String res = "";
        String sql = "SELECT psw FROM TVolunteer WHERE id = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getString("psw");
        }
        return res;
    }
    

    public int getID_Validator(Validator val) throws SQLException{
        int res = -1;
        String sql = "SELECT id FROM TValidator WHERE nom = ? AND age = ? AND dpt = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, val.getName());
        pstmt.setInt(2, val.getAge());
        pstmt.setInt(3, val.getDpt());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
        }
        return res;
    }

    public String get_psw_Validator(int id) throws SQLException{
        String res = "";
        String sql = "SELECT psw FROM TValidator WHERE id = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getString("psw");
        }
        return res;
    }

    public int getID_Request(Request req) throws SQLException{
        int res = -1;
        String sql = "SELECT id FROM TRequest WHERE subj = ? AND date_creation = ? AND helpday = ?";
        this.pstmt = conn.prepareStatement(sql);

        Date d = (Date) Date.from(req.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());

        pstmt.setString(1, req.getMotif());
        pstmt.setDate(2, d);
        pstmt.setDate(3, (Date) req.getHelpD());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
        }
        return res;
    }


    //Ajout et Suppression dans les tables
    public void insertRequest(String subj, int app, Date date) throws SQLException{
        String sql = "INSERT INTO TRequest(subj, id_applicant, helpday) VALUES (?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, subj);  
        pstmt.setInt(2, app);
        pstmt.setDate(3, date);

        pstmt.executeUpdate();
    }

    public void insertVolunteer(String nom, int age, int dpt, String psw) throws SQLException{
        String sql = "INSERT INTO TVolunteer (nom, age, dpt, psw) VALUES (?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, nom);  
        pstmt.setInt(2, age);
        pstmt.setInt(3, dpt);
        pstmt.setString(4, psw);


        pstmt.executeUpdate();
    }

    public void deleteVolunteerByName(String nom) throws SQLException{
        String sql = "DELETE FROM TVolunteer WHERE nom=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nom);  

       pstmt.executeUpdate();
    }

    public void deleteVolunteerById(int id) throws SQLException{
        String sql = "DELETE FROM TVolunteer WHERE id=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);  

        pstmt.executeUpdate();
    }

    public void insertApplicant(String nom, int age, int dpt, String psw) throws SQLException{
        String sql = "INSERT INTO TApplicant (nom, age, dpt, psw) VALUES (?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, nom);  
        pstmt.setInt(2, age);
        pstmt.setInt(3, dpt);
        pstmt.setString(4, psw);

        pstmt.executeUpdate();
    }

    public void deleteApplicantByName(String nom) throws SQLException{
        String sql = "DELETE FROM TApplicant WHERE nom=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nom);  

        pstmt.executeUpdate();
    }

    public void deleteApplicantById(int id) throws SQLException{
        String sql = "DELETE FROM TApplicant WHERE id=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);  

        pstmt.executeUpdate();
    }

    public void insertValidator(String nom, int age, int dpt, String orga, String psw) throws SQLException{
        String sql = "INSERT INTO TValidator (nom, age, dpt, orga, psw) VALUES (?,?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

        // Paramètres à insérer
        pstmt.setString(1, nom);  
        pstmt.setInt(2, age);
        pstmt.setInt(3, dpt);
        pstmt.setString(4, orga); 
        pstmt.setString(5, psw);

        pstmt.executeUpdate();
    }

    public void deleteValidatorByName(String nom) throws SQLException{
        String sql = "DELETE FROM TApplicant WHERE nom=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, nom);  

        pstmt.executeUpdate();
    }

    public void deleteValidatorById(int id) throws SQLException{
        String sql = "DELETE FROM TValidator WHERE id=?" ;
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);  

       pstmt.executeUpdate();
    }


    //Affichage du contenus des tables

    public Boolean printRequest() throws SQLException{
        String sql = "SELECT * FROM TRequest";
        this.pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()){
            tt.write_red("No request");
            return false;
        }
        
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

        return true;
    }

    public Boolean printRequestApplicant(Applicant app) throws SQLException {
        String sql = "SELECT * FROM TRequest "
                   + "JOIN TApplicant ON TApplicant.id = TRequest.id_applicant "
                   + "WHERE TApplicant.nom = ?";
        this.pstmt = conn.prepareStatement(sql);
    
        pstmt.setString(1, app.getName());
        ResultSet rs = pstmt.executeQuery();
        
        System.out.println("-----------------------------------------");

        if(!rs.next()){
            tt.write_red("No request found for applicant: " + app.getName());
            return false;
        }
    
        // Vérifie si le ResultSet contient des lignes avant de tenter de lire
        while(rs.next()) {
            // Si une ligne existe, on peut lire les données
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            int age = rs.getInt("age");
            int dpt = rs.getInt("dpt");
            int score = rs.getInt("score");
            int nb_avis = rs.getInt("nb_avis");
    
            System.out.println("Volunteer n°" + id);
            System.out.println("Name: " + nom);
            System.out.println("Age: " + age);
            System.out.println("Department: " + dpt);
            System.out.println("Score: " + score);
            
        }
    
        System.out.println("-----------------------------------------");
        return true;
    }

    public Boolean printRequestVolunteer(Volunteer vol) throws SQLException{
        String sql = "SELECT TRequest.*, TVolunteer FROM TRequest " + "JOIN TVolunteer ON TVolunteer.id = TRequest.id_volunteer " + "WHERE TVolunteer.nom = ? AND TVolunteer.age = ? AND TVolunteer.dpt = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vol.getName());
        pstmt.setInt(2, vol.getAge());
        pstmt.setInt(3, vol.getDpt());
        
        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()){
           tt.write_red("No request");
            return false;
        }
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int val = rs.getInt("id_validator");

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + helpD);
            System.out.println("Status: " + status);
            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
                System.out.println("Validator: " + val);
            }
        }

        System.out.println("-----------------------------------------");
        return true;
    }

    public Boolean printRequestValidator(Validator val) throws SQLException{
        String sql = "SELECT TRequest.*, TValidator.* FROM TRequest " + "JOIN TValidator ON TValidator.id = TRequest.id_validator " + "WHERE TValidator.nom = ? AND TValidator.age = ? AND TValidator.dpt = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, val.getName());
        pstmt.setInt(2, val.getAge());
        pstmt.setInt(3, val.getDpt());
        
        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()){
            tt.write_red("No request");
            return false;
        }
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int validator = rs.getInt("id_validator");
            int volunteer = rs.getInt("id_volunteer");
            

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + helpD);
            System.out.println("Status: " + status);

            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
                System.out.println("Validator: " + validator);
            }
        }

        System.out.println("-----------------------------------------");

        return true;
    }

    public Boolean printRequestByDateCreation() throws SQLException{
        String sql = "SELECT TRequest.*, TValidator FROM TRequest" + "ORDER BY TRequest.date_creation DESC";
        this.pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()){
            tt.write_red("No request");
            return false;
        }
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int validator = rs.getInt("id_validator");
            

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + helpD);
            System.out.println("Status: " + status);
            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
                System.out.println("Validator: " + validator);
            }
        }

        System.out.println("-----------------------------------------");
        return true;
    }

    public Boolean printRequestPending() throws SQLException{
        String sql = "SELECT * FROM TRequest " + "WHERE TRequest.statut = 'P'";
        this.pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()){
            tt.write_red("No pending request");
            return false;
        }
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("statut");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int validator = rs.getInt("id_validator");
            

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + helpD);
            System.out.println("Status: " + status);
            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
                System.out.println("Validator: " + validator);
            }
        }

        System.out.println("-----------------------------------------");
        return true;
    }


    public Boolean printRequestOrderByApplicant() throws SQLException{
        String sql = "SELECT TRequest.*, TApplicant FROM TRequest" + "JOIN TApplicant.id = TRequest.id_applicant" + "ORDER BY TApplicant.nom ASC";
        this.pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
        
        if(!rs.next()){
            tt.write_red("No request");
            return false;
        }

        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int val = rs.getInt("id");

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + helpD);
            System.out.println("Status: " + status);
            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
                System.out.println("Validator: " + val);
            }
        }

        System.out.println("-----------------------------------------");
        return true;
    }

    public Boolean printRequestOrderByVolunteer(Volunteer vol) throws SQLException{
        String sql = "SELECT TRequest.*, TVolunteer FROM TRequest" + "JOIN TVolunteer.id = TRequest.id_volunteer" + "ORDER BY TVolunteer.nom ASC";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vol.getName());
        
        ResultSet rs = pstmt.executeQuery();

        if(!rs.next()){
            tt.write_red("No request");
            return false;
        }   
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int val = rs.getInt("id");

            switch(status){
                case "P" -> status = "Pending";
                case "A" -> status = "Approved";
                case "R" -> status = "Rejected";
                case "C" -> status = "Completed";
            }
            
            
            System.out.println("Request n°" + id);
            System.out.println("Subject: " + subject);
            System.out.println("Date: " + helpD);
            System.out.println("Status: " + status);
            if("Rejected".equals(status)){
                System.out.println("Reason: " + motif);
                System.out.println("Validator: " + val);
            }
        }

        System.out.println("-----------------------------------------");

        return true;
    }

    public void printVolunteer(Request req) throws SQLException{
        Volunteer vol = req.getVolunteer();
        int id_vol = getID_Volunteer(vol);

        String sql = "SELECT * FROM TVolunteer" + "Where id = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id_vol);
        ResultSet rs = pstmt.executeQuery();
        
        System.out.println("-----------------------------------------");
        int id = rs.getInt("id");
        String nom = rs.getString("nom");
        int age = rs.getInt("age");
        int dpt = rs.getInt("dpt");
        int note = rs.getInt("note");
        int nb_avis = rs.getInt("nb_avis");
        
        System.out.println("Volunteer n°" + id);
        System.out.println("Name: " + nom);
        System.out.println("Age: " + age);
        System.out.println("Department: " + dpt);
        System.out.println("Score: " + note);
        System.out.println("Number of comments: " + nb_avis);

        System.out.println("-----------------------------------------");

    }


    //Changement 
    public boolean isValidStatus(String status) {
        return status.equals("P") || status.equals("A") || status.equals("R") || status.equals("C");
    }

    public void updateRequestStatus(Request req, String newStatus) throws SQLException {

        int requestId = getID_Request(req);

        if (!isValidStatus(newStatus)) {
            tt.write_red(newStatus + " is not a valid status.");
            throw new IllegalArgumentException();
        }
    
        String sql = "UPDATE TRequest SET status = ? WHERE id = ?";
    
        this.pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, requestId);
        
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            tt.write_green("Status updated successfully for query n°" + requestId);
        } else {
            tt.write_red(sql + "No request with the ID: " + requestId);
        }
    }

    public void updateRequestStatusApplicant(Request req, String newStatus) throws SQLException {
        int requestId = getID_Request(req);
    
        // Vérifie que le statut actuel est "A"
        String currentStatus = getCurrentStatus(requestId); // Méthode à définir pour obtenir le statut actuel de la requête
    
        if (!"A".equals(currentStatus)) {
            tt.write_red("Le statut actuel n'est pas 'Aprouvé', aucune mise à jour n'a été effectuée.");
            return; // Si le statut actuel n'est pas "A", ne rien faire
        }
    
        if (!isValidStatus(newStatus)) {
            tt.write_red(newStatus + " n'est pas un statut valide.");
            throw new IllegalArgumentException();
        }
    
        String sql = "UPDATE TRequest SET status = ? WHERE id = ?";
    
        this.pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, requestId);
        
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            tt.write_green("Status updated successfully for query n°" + requestId);
        } else {
            tt.write_red("No request with the ID: " + requestId);
        }
    }
    
    // Méthode pour obtenir le statut actuel de la requête
    private String getCurrentStatus(int requestId) throws SQLException {
        String sql = "SELECT status FROM TRequest WHERE id = ?";
        this.pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, requestId);
    
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            return rs.getString("status"); // Retourne le statut actuel
        } else {
            throw new SQLException("Request with ID " + requestId + " not found.");
        }
    }

    public void updateRequestVolunteer(Request req, Volunteer newVolunteer) throws SQLException {
        int idVol= getID_Volunteer(newVolunteer);
        int requestId = getID_Request(req);
    
        String sql = "UPDATE TRequest SET id_volunteer = ? WHERE id = ?";
    
        this.pstmt = conn.prepareStatement(sql);
        
        pstmt.setInt(1, idVol);
        pstmt.setInt(2, requestId);
        
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            tt.write_green("Volunteer updated successfully for query n°" + requestId);
        } else {
            tt.write_red("No request with the ID: " + requestId);
        }
    }

    public void updateRequestValidator(Request req, Validator newValidator) throws SQLException {
        int idVal= getID_Validator(newValidator);
        int requestId = getID_Request(req);
    
        String sql = "UPDATE TRequest SET id_validator = ? WHERE id = ?";
    
        this.pstmt = conn.prepareStatement(sql);
        
        pstmt.setInt(1, idVal);
        pstmt.setInt(2, requestId);
        
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            tt.write_green("Validator updated successfully for query n°" + requestId);
        } else {
            tt.write_red("No request with the ID: " + requestId);
        }
    }

    public void updateRequestMotif(Request req, String newMotif) throws SQLException {
        int requestId = getID_Request(req);
    
        String sql = "UPDATE TRequest SET motif = ? WHERE id = ?";
    
        this.pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, newMotif);
        pstmt.setInt(2, requestId);
        
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            tt.write_green("Motif updated successfully for query n°" + requestId);
        } else {
            tt.write_red("No request with the ID: " + requestId);
        }
    }

    public void updateVolunteerScore(Request req, int score) throws SQLException {
        int id_req= getID_Request(req);
        Volunteer vol = req.getVolunteer();
        int id_vol = getID_Volunteer(vol);

        String sql = "SELECT score, nb_avis FROM TVolunteer WHERE id = ?";
        this.pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id_vol);

        ResultSet rs = pstmt.executeQuery();

        int nowScore = rs.getInt("score");
        int nb_avis = rs.getInt("nb_avis");
        int newNb = nb_avis + 1;
        int newScore = (nowScore + score)/newNb;

        String sql2 = "UPDATE TVolunteer SET score = ? AND nb_avis = ? WHERE id = ?";
    
        this.pstmt = conn.prepareStatement(sql2);
        
        pstmt.setInt(1, newScore);
        pstmt.setInt(2, newNb);
        pstmt.setInt(3, id_vol);
        
        pstmt.executeUpdate();
    }

    public int get_score_Volunteer(Volunteer vol) throws SQLException{
        int id_vol = getID_Volunteer(vol);

        int res = -1;
        String sql = "SELECT score FROM TVolunteer WHERE id = ?";

        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id_vol);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
        }

        return res;

    }

    public void print_score_volunteer(Volunteer vol) throws SQLException{
        int id_vol = getID_Volunteer(vol);

        String sql = "SELECT score FROM TVolunteer WHERE id = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id_vol);
        ResultSet rs = pstmt.executeQuery();

        System.out.println("-----------------------------------------");
        int score = rs.getInt("score");
        System.out.println("|Score:                                    |" + score);
        int nb_avis = rs.getInt("nb_avis");
        System.out.println("|Number of scores:                       |" + nb_avis);
        System.out.println("-----------------------------------------");

    }



    //Getter et Setter attributs
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


}
