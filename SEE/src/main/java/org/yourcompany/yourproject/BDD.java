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

        String sql = "SELECT id, nom, age, dpt, FROM TValidator "
        + "WHERE id = ";
        
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            val = new Validator(rs.getString("nom"),rs.getInt("age"), rs.getInt("dpt"), rs.getString("orga"));
            val.setName(rs.getString("nom"));
            val.setAge(rs.getInt("age"));
            val.setDpt(rs.getInt("dpt"));
            val.setOrga(rs.getString("orga"));
        }

        return val; 
    }

    public Volunteer getVolunteer(int id) throws SQLException{
        Volunteer vol = null;

        String sql = "SELECT id, nom, age, dpt FROM TVolunteer "
        + "WHERE id = ";
        
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            vol = new Volunteer(rs.getString("nom"),rs.getInt("age"), rs.getInt("dpt"));
            vol.setName(rs.getString("nom"));
            vol.setAge(rs.getInt("age"));
            vol.setDpt(rs.getInt("dpt"));
        }

        return vol; 
    }

    public Applicant getApplicant(int id) throws SQLException{
        Applicant app = null;

        String sql = "SELECT id, nom, age, dpt FROM TApplicant "
        + "WHERE id = ?";
        
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            app = new Applicant(rs.getString("nom"),rs.getInt("age"), rs.getInt("dpt"));
            app.setName(rs.getString("nom"));
            app.setAge(rs.getInt("age"));
            app.setDpt(rs.getInt("dpt"));
        }

        return app; 
    }

    //Getter
    public int getID_Applicant(Applicant app) throws SQLException{
        int res = -1;
        String sql = "SELECT id FROM TApplicant WHERE nom = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, app.getName());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
        }
        return res;
    }

    public int getID_Volunteer(Volunteer vol) throws SQLException{
        int res = -1;
        String sql = "SELECT id FROM TVolunteer WHERE nom = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vol.getName());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
        }
        return res;
    }

    public int getID_Validator(Validator val) throws SQLException{
        int res = -1;
        String sql = "SELECT id FROM TValidator WHERE nom = ?";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, val.getName());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            res = rs.getInt("id");
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
        String sql = "INSERT INTO TRequest(id, subj, id_applicant, helpday) VALUES (?,?,?,?)";
        this.pstmt = conn.prepareStatement(sql);

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
        String sql = "INSERT INTO TValidator (id, nom, age, dpt, orga) VALUES (?,?,?,?,?)";
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
        String sql = "SELECT * FROM TRequest" + "ORDER BY TRequest.helpD DESC";
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
    }

    public void printRequestApplicant(Applicant app) throws SQLException{
        String sql = "SELECT TRequest.*, TAppliacnt FROM TRequest" + "JOIN TApplicant ON TApplicant.id = TRequest.id_applicant" + "WHERE TApplicant.nom =";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, app.getName());
        
        ResultSet rs = pstmt.executeQuery();
        
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
    }

    public void printRequestVolunteer(Volunteer vol) throws SQLException{
        String sql = "SELECT TRequest.*, TVolunteer FROM TRequest" + "JOIN TVolunteer ON TVolunteer.id = TRequest.id_volunteer" + "WHERE TVolunteer.nom =";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vol.getName());
        
        ResultSet rs = pstmt.executeQuery();
        
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
    }

    public void printRequestValidator(Validator val) throws SQLException{
        String sql = "SELECT TRequest.*, TValidator FROM TRequest" + "JOIN TValidator ON TValidator.id = TValidator.id_validator" + "WHERE TValidator.nom =";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, val.getName());
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("status");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int validator = rs.getInt("id");
            

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
    }

    public void printRequestByDateCreation() throws SQLException{
        String sql = "SELECT TRequest.*, TValidator FROM TRequest" + "ORDER BY TRequest.date_creation DESC";
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
            int validator = rs.getInt("id");
            

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
    }

    public void printRequestPending() throws SQLException{
        String sql = "SELECT * FROM TRequest " + "WHERE TRequest.statut = 'P'";
        this.pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            System.out.println("-----------------------------------------");
            int id = rs.getInt("id");
            Date date = rs.getDate("date_creation");
            String subject= rs.getString("subj");
            String status = rs.getString("statut");
            Date helpD = rs.getDate("helpday");
            String motif = rs.getString("motif");
            int validator = rs.getInt("id");
            

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
    }


    public void printRequestOrderByApplicant() throws SQLException{
        String sql = "SELECT TRequest.*, TApplicant FROM TRequest" + "JOIN TApplicant.id = TRequest.id_applicant" + "ORDER BY TApplicant.nom ASC";
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
    }

    public void printRequestOrderByVolunteer(Volunteer vol) throws SQLException{
        String sql = "SELECT TRequest.*, TVolunteer FROM TRequest" + "JOIN TVolunteer.id = TRequest.id_volunteer" + "ORDER BY TVolunteer.nom ASC";
        this.pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, vol.getName());
        
        ResultSet rs = pstmt.executeQuery();
        
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
    }

    //Changement 
    private boolean isValidStatus(String status) {
        return status.equals("P") || status.equals("A") || status.equals("R") || status.equals("C");
    }

    public void updateRequestStatus(Request req, String newStatus) throws SQLException {

        int requestId = getID_Request(req);

        if (!isValidStatus(newStatus)) {
            throw new IllegalArgumentException("Statut invalide : " + newStatus);
        }
    
        String sql = "UPDATE TRequest SET status = ? WHERE id = ?";
    
        this.pstmt = conn.prepareStatement(sql);
        
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, requestId);
        
        int rowsAffected = pstmt.executeUpdate();
    
        if (rowsAffected > 0) {
            System.out.println("Statut mis à jour avec succès pour la requête n°" + requestId);
        } else {
            System.out.println("Aucune requête trouvée avec l'ID : " + requestId);
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
            System.out.println("Volontaire mis à jour avec succès pour la requête n°" + requestId);
        } else {
            System.out.println("Aucune requête trouvée avec l'ID : " + requestId);
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
            System.out.println("Validateur mis à jour avec succès pour la requête n°" + requestId);
        } else {
            System.out.println("No request with the ID : " + requestId);
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
            System.out.println("Motif mis à jour avec succès pour la requête n°" + requestId);
        } else {
            System.out.println("Aucune requête trouvée avec l'ID : " + requestId);
        }
    }

    //Getter et Setter attributs
    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


}
