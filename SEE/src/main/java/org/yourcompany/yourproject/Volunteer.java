package org.yourcompany.yourproject;


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
