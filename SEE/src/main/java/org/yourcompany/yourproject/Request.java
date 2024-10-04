package org.yourcompany.yourproject;
import java.time.LocalDate;
import org.yourcompany.yourproject.SEE.Stat;

public class Request {
    //attributs
    private Applicant applicant;
    private Volunteer volunteer;
    private LocalDate date;
    private String subject;
    private Stat status;

    //Constructeur
    Request(String subject){
        this.date = LocalDate.now();
        this.subject = subject;
        this.status = Stat.PENDING; 
        this.volunteer=null;
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

    public void setStatus(Stat s){
        this.status=s;
    }

    public Stat getStatus(){
        return this.status;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public String getSubject(){
        return this.subject;
    }

    //methodes

    
}
