package org.pdla.assistance_app.accounts;

import java.sql.Date;
import java.util.Scanner;

import org.pdla.assistance_app.structure.Request;

public class Applicant extends User{
    
    public Applicant(String n, int age, int dpt, String psw){
        super(n, age, dpt, psw);
    }

    public Applicant(){
        
    }

    //methodes
    public Request makeRequest(Date date){
        System.out.println("sujet de la requete ?");
        Scanner scanner = new Scanner(System.in);
        String sub = scanner.nextLine();
        Request r = new Request(sub,date);
        r.setApp(this);
        scanner.close();
        return r;
    }

    public void completeRequest(Request r){
        r.setStatus('C');
    }

}
