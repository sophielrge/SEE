package org.pdla.assistance_app.structure;
import java.time.LocalDate;
import java.util.Date;

import org.pdla.assistance_app.accounts.Applicant;
import org.pdla.assistance_app.accounts.Validator;
import org.pdla.assistance_app.accounts.Volunteer;


public class Request {
    //attributs
    private Applicant applicant;
    private Volunteer volunteer;
    private LocalDate date;
    private String subject;
    private Character status;
    private Date helpD;
    private String motif;
    private Validator validator;

    //Constructeur
    public Request(String subject, Date helpD){
        this.date = LocalDate.now();
        this.subject = subject;
        this.status = 'P';   // Pending, Approved, Rejected, Completed
        this.volunteer=null;
        this.helpD=helpD;
    }
    
    public Request(){
        
    }

    //SetGet
    public void setApp(Applicant a){
        this.applicant = a;
    }

    public Applicant getApp(){
        return this.applicant;
    }

    public void setVolunteer(Volunteer b){
        this.volunteer=b;
    }

    public Volunteer getVolunteer(){
        return this.volunteer;
    }

    public void setStatus(Character s){
        this.status=s;
    }

    public Character getStatus(){
        return this.status;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String getSubject(){
        return this.subject;
    }

    public Date getHelpD() {
        return helpD;
    }

    public void setHelpD(Date helpD) {
        this.helpD = helpD;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //methodes

    
}
