package org.pdla.assistance_app.accounts;

import org.pdla.assistance_app.structure.Request;


public class Validator extends User{
    private String orga;

    public Validator(String n, int a, int d, String orga, String p){
        super(n,a, d, p);
        this.orga = orga;
    }

    public Validator(){

    }
    

    //
    public void validerR(Request r){
        r.setValidator(this);
        //A set dans le sql
        r.setStatus('A');
        //A set dans le sql
    }

    public void refuserR(Request r, String m){
        r.setValidator(this);
        //A set dans le sql
        r.setStatus('R');
        //A set dans le sql
        r.setMotif(m);
    }

    public String getOrga() {
        return orga;
    }

    public void setOrga(String orga) {
        this.orga = orga;
    }
}
