package org.yourcompany.yourproject;

import java.util.Scanner;

public class Applicant extends User{
    
    public Applicant(String n, int age){
        super(n, age);
    }

    //methodes
    public Request makeRequest(){
        System.out.println("sujet de la requete ?");
        Scanner scanner = new Scanner(System.in);
        String sub = scanner.nextLine();
        Request r = new Request(sub);
        r.setApp(this);
        scanner.close();
        return r;
    }

}
