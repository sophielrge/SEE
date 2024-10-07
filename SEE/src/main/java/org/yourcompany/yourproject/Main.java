/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

import java.sql.SQLException;

/**
 *
 * @author sophie
 */
public class Main {

    public static void main(String[] args) throws SQLException {

         // URL de connexion au serveur MySQL
        String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_007"; 
        String user = "projet_gei_007"; 
        String password = "deiD1ou2";
       
        BDD base = new BDD(url, user, password);
        base.connect();

        //base.insertApplicant("Elise", 21, 31);
        //base.insertApplicant("Adèle", 21, 12);
        //base.insertVolunteer("Anna", 21, 31);
        Menu menu = new Menu(base);

    }
}
