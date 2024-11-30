package org.yourcompany.yourproject;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menu {
    Scanner scanner;
    boolean global_menu = true;
    boolean choix_compteB = true;
    boolean applicant_menu = true;
    boolean volunteer_menu = true;
    boolean validator_menu = true;

    public Menu(){
        this.scanner = new Scanner(System.in);
    }

    public int menu_depart(){
        System.out.println("-----------------------------------------");
        System.out.println("|               MENU SEE                |");
        System.out.println("|0 - Quit                               |");
        System.out.println("|1 - Sign in                            |");
        System.out.println("|2 - Log in                             |");
        System.out.println("-----------------------------------------");
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public int choix_compte(){
        System.out.println("|0 - Go back                            |");
        System.out.println("|1 - I am applicant                     |");
        System.out.println("|2 - I am volunteer                     |");
        System.out.println("|3 - I am validator                     |");
        System.out.println("-----------------------------------------");
        int i2 = scanner.nextInt();
        scanner.nextLine();
        return i2;
    }

    public Applicant crea_demandeur(BDD base) throws SQLException{
        System.out.println("|Enter your name                        |");
        String name = scanner.nextLine();
        System.out.println("|Enter your age                         |");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("|Enter your dpt                         |");
        int dpt = scanner.nextInt();
        scanner.nextLine();
        System.out.println("|Enter a password                       |");
        String psw = scanner.nextLine();
        Applicant applicant = new Applicant(name, age, dpt, psw);
        base.insertApplicant(name, age, dpt, psw);
        System.out.println("|Applicant account created with success |");
        int id = base.getID_Applicant(applicant);
        System.out.println("|Your id is: " +id+" remember it           ");
        System.out.println("-----------------------------------------");
        return applicant;
    }

    public int menu_demandeur(){
        System.out.println("|0 - Go back                            |");
        System.out.println("|1 - Add a new request                  |");
        System.out.println("|2 - View my requests                   |");
        System.out.println("-----------------------------------------");
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public void ajouter_requete(BDD base, Applicant applicant) throws SQLException, ParseException{
        System.out.println("|Describe your request                  |");
        String subj = scanner.nextLine();
        System.out.println("|Indicate the date dd-MM-yyyy           |");
        String d=scanner.nextLine(); //transformer mon string en date
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = format.parse(d);
        java.sql.Date sdate = new java.sql.Date(date.getTime());
        int id = base.getID_Applicant(applicant);
        base.insertRequest(subj, id, sdate);
        System.out.println("|Request added with success !!          |");
        System.out.println("-----------------------------------------");
    }

    public boolean consulter_requetesA(BDD base, Applicant applicant) throws SQLException{
        System.out.println("|Your requests :                        |");
        boolean are_request = base.printRequestApplicant(applicant);
        if (!are_request){
            System.out.println("-----------------------------------------");
            return false;
        }
        System.out.println("-----------------------------------------");
        return true;
    }

    public Volunteer crea_bene(BDD base) throws SQLException{
        System.out.println("|Enter your name                        |");
        String name2 = scanner.nextLine();
        System.out.println("|Enter your age                         |");
        int age2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("|Enter your dpt                         |");
        int dpt2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("|Enter a password                       |");
        String psw = scanner.nextLine();
        Volunteer volunteer = new Volunteer(name2, age2, dpt2, psw);
        base.insertVolunteer(name2, age2, dpt2, psw);
        System.out.println("|Volunteer account created with success!|");
        int id = base.getID_Volunteer(volunteer);
        System.out.println("|Your id is: " +id+" remember it           ");
        System.out.println("-----------------------------------------");
        return volunteer;
    }

    public int menu_benevole(){
        System.out.println("|0 - Go back                            |");
        System.out.println("|1 - Print pending requests             |");
        System.out.println("|2 - View my requests                   |");
        System.out.println("-----------------------------------------");
        int i4 = scanner.nextInt();
        scanner.nextLine();
        return i4;
    }

    public boolean devenir_volontaire(BDD base, Volunteer volunteer) throws SQLException{
        boolean are_request = base.printRequestPending();
        if (!are_request){
            System.out.println("-----------------------------------------");
            return false;
        }
        System.out.println("-----------------------------------------");
        System.out.println("|To select a request          ,         |");
        System.out.println("|enter its n°                           |");
        System.out.println("-----------------------------------------");
        int i5 = scanner.nextInt();
        scanner.nextLine();
        Request rchosen = base.getRequest(i5);
        System.out.println("|Do you want to be the volunteer       ?|");
        System.out.println("|1- yes                                 |");
        System.out.println("|2- no                                  |");
        System.out.println("-----------------------------------------");
        int i6 = scanner.nextInt();
        scanner.nextLine();
        switch (i6){
            case 1:
                volunteer.choseRequest(rchosen);
                base.updateRequestStatus(rchosen, "P");
                base.updateRequestVolunteer(rchosen, volunteer);
                System.out.println("|You are the volunteer!                 |");
                System.out.println("-----------------------------------------");
            case 2: 
            break;
        }
        return true;
    }

    public boolean print_request_volunteer(BDD base, Volunteer volunteer) throws SQLException{
        System.out.println("|Your requests                      :   |");
        boolean are_request = base.printRequestVolunteer(volunteer);
        if (!are_request){
            System.out.println("-----------------------------------------");
            return false;
        }
        System.out.println("-----------------------------------------");
        return true;
    }

    public void terminer_requete(BDD base, Applicant applicant) throws SQLException{
        System.out.println("|Your requests                      :   |");
        base.printRequestApplicant(applicant);
        System.out.println("-----------------------------------------");
        System.out.println("|To finish a request,                   |");
        System.out.println("|enter its n°                           |");
        System.out.println("-----------------------------------------");
        int i6 = scanner.nextInt();
        Request rfinished = base.getRequest(i6);
        applicant.completeRequest(rfinished);
        base.updateRequestStatusApplicant(rfinished, "C");
        System.out.println("|Request completed                      |");
        System.out.println("|Enter a score for the volunteer        |");
        System.out.println("-----------------------------------------");
        int i7 = scanner.nextInt();
        base.updateVolunteerScore(rfinished, i7);
        System.out.println("|Thank you                              |");
        System.out.println("-----------------------------------------");
    }

    public Validator crea_valideur(BDD base) throws SQLException{
        System.out.println("|Enter your name                        |");
        String name3 = scanner.nextLine();
        System.out.println("|Enter your age                         |");
        int age3 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("|Enter your dpt                         |");
        int dpt3 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("|Enter your organization                |");
        String org = scanner.nextLine();
        System.out.println("|Enter a password                       |");
        String psw = scanner.nextLine();
        Validator validator = new Validator(name3, age3, dpt3, org, psw);
        base.insertValidator(name3, age3, dpt3, org, psw);
        System.out.println("|Applicant account created with success |");
        System.out.println("-----------------------------------------");
        return validator;
    }

    public int menu_valideur(){
        System.out.println("|1 - Print pending requests             |");
        System.out.println("|2 - View my requests                   |");
        System.out.println("-----------------------------------------");
        int i7 = scanner.nextInt();
        scanner.nextLine();
        return i7;
    }

    public boolean print_request_validator(BDD base) throws SQLException{
        System.out.println("|Pending requests:                      |");
        boolean are_request = base.printRequestPending();
        if (!are_request){
            System.out.println("-----------------------------------------");
            return false;
        }
        System.out.println("-----------------------------------------");
        return true;
    }

    public Request change_status(Validator validator, BDD base) throws SQLException{
        System.out.println("|To change a status request             |");
        System.out.println("|enter its  n°                          |");
        int i8 = scanner.nextInt();
        scanner.nextLine();
        Request rchange = base.getRequest(i8);
        base.updateRequestValidator(rchange, validator);
        System.out.println("-----------------------------------------");
        return rchange;
    }

    public int choisis_status(){
        System.out.println("|1 - Approve the volunteer              |");
        System.out.println("|2 - Refuse the volunteer                 |");
        int i9 = scanner.nextInt();
        scanner.nextLine();
        return i9;
    }

    public void valider_r(BDD base, Request rchange, Validator validator) throws SQLException{
        validator.validerR(rchange);
        base.updateRequestStatus(rchange, "A");
    }

    public void refuser_r(BDD base, Request rchange, Validator validator) throws SQLException{
        System.out.println("|Please enter a motif                   |");
        System.out.println("-----------------------------------------");
        String motif = scanner.nextLine();
        validator.refuserR(rchange, motif);
        base.updateRequestStatus(rchange, "R");
        base.updateRequestMotif(rchange, motif);
    }

    public boolean afficher_requetesV(Validator validator, BDD base) throws SQLException{
        System.out.println("|Your requests                      :   |");
        boolean are_request = base.printRequestValidator(validator);
        if (!are_request){
            System.out.println("-----------------------------------------");
            return false;
        }
        System.out.println("-----------------------------------------");
        return true;
    }

    public Applicant charge_applicant(BDD base) throws SQLException{
        boolean psw_ok = false;
        int id = -1;
        while(!psw_ok){
            System.out.println("|Enter your id                          |");
            id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("|Enter your password                    |");
            String psw = scanner.nextLine();
            if (psw.equals(base.get_psw_Applicant(id))){
                psw_ok = true;
            }else {
                System.out.println("|Wrong id or psw                    |");
            }
        }
        System.out.println("-----------------------------------------");
        Applicant ap = base.getApplicant(id);
        System.out.println("Welcome " + ap.getName());
        System.out.println("-----------------------------------------");
        return ap;
    }

    public Volunteer charge_volontaire(BDD base) throws SQLException{
        boolean psw_ok = false;
        int id = -1;
        while(!psw_ok){
            System.out.println("|Enter your id                          |");
            id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("|Enter your password                    |");
            String psw = scanner.nextLine();
            if (psw.trim().equals(base.get_psw_Volunteer(id).trim())){
                psw_ok = true;
            }else {
                System.out.println("|Wrong id or psw                    |");
            }
        }
        System.out.println("-----------------------------------------");
        Volunteer vo = base.getVolunteer(id);
        System.out.println("Welcome " + vo.getName());
        System.out.println("-----------------------------------------");
        return vo;
    }

    public Validator charge_valideur(BDD base) throws SQLException{
        boolean psw_ok = false;
        int id = -1;
        while(!psw_ok){
            System.out.println("|Enter your id                          |");
            id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("|Enter your password                    |");
            String psw = scanner.nextLine();
            if (psw == base.get_psw_Validator(id)){
                psw_ok = true;
            }else {
                System.out.println("|Wrong id or psw                    |");
            }
        }
        System.out.println("-----------------------------------------");
        Validator va = base.getValidator(id);
        System.out.println("Welcome " + va.getName());
        System.out.println("-----------------------------------------");
        return va;
    }

    public void menu(BDD base) throws SQLException, ParseException {
        while (global_menu){
            choix_compteB = true;
            int i1 = menu_depart();//menu principal
            switch (i1) {
                case 0: 
                    global_menu = false;
                    break;
                case 1: // creer un compte
                    int i2 = choix_compte();
                    switch (i2) {
                        case 0: //retour
                            break;   
                        case 1: // Creation demandeur
                            Applicant applicant = crea_demandeur(base);
                            break;
                        case 2: // creation benevole
                            Volunteer volunteer= crea_bene(base);
                            break;
                        case 3: // compte valideur
                            Validator validator = crea_valideur(base);
                            break;
                    }
                    break;
                case 2:// se connecter
                    while (choix_compteB){
                        applicant_menu = true;
                        volunteer_menu = true;
                        validator_menu = true;
                        int i10 = choix_compte();
                        switch (i10){
                            case 0:
                                choix_compteB = false;
                                break;
                            case 1: //demandeur
                                Applicant applicant = charge_applicant(base);
                                while (applicant_menu){
                                    int i3 = menu_demandeur();
                                    switch (i3) {
                                        case 0: 
                                            applicant_menu = false;
                                            break;
                                        case 1:  // ajouter requête
                                            ajouter_requete(base, applicant);
                                            break;
                                        case 2: // consulter mes requêtes
                                            terminer_requete(base, applicant);
                                            break;
                                    }
                                }
                                break; 
                            case 2: //volontaire
                                Volunteer volunteer = charge_volontaire(base);
                                while (volunteer_menu){
                                    int i4 = menu_benevole();
                                    switch (i4) {
                                        case 0:
                                            volunteer_menu = false;
                                            break;
                                        case 1: // afficher les reqêtes et en selectionner
                                            devenir_volontaire(base, volunteer);
                                            break;
                                        case 2: // consulter mes requêtes
                                            print_request_volunteer(base, volunteer);
                                            break;
                                    }
                                }
                                break;
                            case 3: //valideur
                                Validator validator = charge_valideur(base);
                                while (validator_menu){
                                    int i7 = menu_valideur();
                                    switch (i7) {
                                        case 0:
                                            validator_menu = false;
                                            break;
                                        case 1: //afficher requêtes en attente
                                            boolean are_request = print_request_validator(base);
                                            if (!are_request){
                                                break;
                                            }
                                            Request rchange = change_status(validator, base);
                                            //base.printVolunteer(rchange);
                                            int i9 = choisis_status();
                                            switch (i9) {
                                                case 1: //Valider
                                                    valider_r(base, rchange, validator);
                                                    break;
                                                case 2: //Refuser
                                                    refuser_r(base, rchange, validator);
                                                    break;
                                            }
                                            break;
                                        case 2: //afficher mes requêtes
                                            afficher_requetesV(validator, base);
                                            break;
                                    }
                                }
                                break;
                        }
                    }  
            }
        }
        System.exit(0);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
