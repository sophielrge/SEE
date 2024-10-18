package org.yourcompany.yourproject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.ZoneId;

public class Validator extends User{
    private String orga;

    public Validator(String n, int a, int d, String orga){
        super(n,a, d);
        this.orga = orga;
    }

    //
    public void validerR(Request r){
        r.setValidator(this);
        //A set dans le sql
        if(r.getApp().getDpt() != r.getVolunteer().getDpt()){
            r.setStatus('R');
            //A set dans le sql
            r.setMotif("Vous n'êtes pas assez proche pour réaliser cette mission");
            //A set dans le sql
        }
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        if(r.getHelpD().before(date)){
            r.setStatus('R');
            //A set dans le sql
            r.setMotif("La date de la mission est dépassée");
            //A set dans le sql
        }
        else {
            r.setStatus('A');
        }
    }
}
