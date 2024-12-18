package org.pdla.assistance_app.accounts;

import org.pdla.assistance_app.structure.Request;

public class Volunteer extends User{
    public Volunteer(String n, int age, int dpt, String psw){
        super(n, age, dpt, psw);
    }

    public Volunteer(){
        
    }

    //méthodes
    public void choseRequest(Request r){
        r.setVolunteer(this);
    }

    public void completeRequest(Request r){
        r.setStatus('C');
    }
}
