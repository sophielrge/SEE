/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.yourcompany.yourproject;

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

    public static void main(String[] args) {
        Applicant a = new Applicant("Sophax", 20);
        Request r = a.makeRequest();
        System.out.println("requete :" + r.getSubject() + " date: " + r.getDate());

    }
}
