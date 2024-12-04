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
    Traitement_texte tt = new Traitement_texte();

    public Menu(){
        this.scanner = new Scanner(System.in);
    }

    public int menu_depart(){
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|               MENU SEE                |");
        tt.write_yellow("|0 - Quit                               |");   
        tt.write_yellow("|1 - Sign in                            |");   
        tt.write_yellow("|2 - Log in                             |");   
        tt.write_yellow("-----------------------------------------");   
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public int choix_compte(){
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|0 - Go back                            |");
        tt.write_yellow("|1 - I am applicant                     |");
        tt.write_yellow("|2 - I am volunteer                     |");
        tt.write_yellow("|3 - I am validator                     |");
        tt.write_yellow("-----------------------------------------");
        int i2 = scanner.nextInt();
        scanner.nextLine();
        return i2;
    }

    public Applicant crea_demandeur(BDD base) throws SQLException{
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|Enter your name                        |");
        String name = scanner.nextLine();
        tt.write_yellow("|Enter your age                         |");
        int age = scanner.nextInt();
        scanner.nextLine();
        tt.write_yellow("|Enter your dpt                         |");
        int dpt = scanner.nextInt();
        scanner.nextLine();
        tt.write_yellow("|Enter a password                       |");
        //String psw = scanner.nextLine();
        String psw = tt.ask_password_sign();
        Applicant applicant = new Applicant(name, age, dpt, psw);
        base.insertApplicant(name, age, dpt, psw);
        tt.write_green("-----------------------------------------");
        tt.write_green("|Applicant account created with success |");
        int id = base.getID_Applicant(applicant);
        tt.write_purple("|Your id is: " +id+" remember it           ");
        tt.write_green("-----------------------------------------");
        return applicant;
    }

    public int menu_demandeur(){
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|0 - Go back                            |");   
        tt.write_yellow("|1 - Add a new request                  |");   
        tt.write_yellow("|2 - View my requests                   |");
        tt.write_yellow("-----------------------------------------");
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public void ajouter_requete(BDD base, Applicant applicant) throws SQLException, ParseException{
        tt.write_yellow("|Enter the subject of your request      |");
        String subj = scanner.nextLine();
        tt.write_yellow("|Indicate the date dd-MM-yyyy           |");
        String d=scanner.nextLine(); //transformer mon string en date
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = format.parse(d);
        } catch (ParseException e) {
            tt.write_red("The date is not valid");
            tt.write_yellow("|Indicate the date dd-MM-yyyy           |");
            d=scanner.nextLine(); //transformer mon string en date
            format = new SimpleDateFormat("dd-MM-yyyy");
            date = format.parse(d);
        }
        java.sql.Date sdate = new java.sql.Date(date.getTime());
        int id = base.getID_Applicant(applicant);
        base.insertRequest(subj, id, sdate);
        Request request = new Request(subj, date);
        tt.write_green("-----------------------------------------");
        tt.write_green("|Request added with success !!          |");
        tt.write_green("-----------------------------------------");
    }

    public boolean consulter_requetesA(BDD base, Applicant applicant) throws SQLException{
        tt.write_yellow("Your requests:                        |");
        boolean are_request = base.printRequestApplicant(applicant);
        if (!are_request){
            tt.write_yellow("-----------------------------------------");
            return false;
        }
        tt.write_yellow("-----------------------------------------");
        return true;
    }

    public Volunteer crea_bene(BDD base) throws SQLException{
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("Enter your name                        |");
        String name2 = scanner.nextLine();
        tt.write_yellow("Enter your age                         |");
        int age2 = scanner.nextInt();
        scanner.nextLine();
        tt.write_yellow("Enter your dpt                         |");
        int dpt2 = scanner.nextInt();
        scanner.nextLine();
        tt.write_yellow("Enter a password                       |");
        //String psw = scanner.nextLine();
        String psw = tt.ask_password_sign();
        Volunteer volunteer = new Volunteer(name2, age2, dpt2, psw);
        base.insertVolunteer(name2, age2, dpt2, psw);
        tt.write_green("-----------------------------------------");
        tt.write_green("|Volunteer account created with success!|");
        int id = base.getID_Volunteer(volunteer);
        tt.write_purple("|Your id is: " +id+" remember it           ");
        tt.write_green("-----------------------------------------");
        return volunteer;
    }

    public int menu_benevole(){ 
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|0 - Go back                            |");
        tt.write_yellow("|1 - Print pending requests             |");
        tt.write_yellow("|2 - View my requests                   |");
        tt.write_yellow("|3 - View my score                      |");
        tt.write_yellow("-----------------------------------------");
        int i4 = scanner.nextInt();
        scanner.nextLine();
        return i4;
    }

    public boolean devenir_volontaire(BDD base, Volunteer volunteer) throws SQLException{
        boolean are_request = base.printRequestPending();
        if (!are_request){
            tt.write_yellow("-----------------------------------------");
            return false;
        }
        tt.write_yellow("|Select a request to volunteer          |");
        tt.write_yellow("|Enter its n°                           |");
        tt.write_yellow("|To quit this page, enter 0             |");
        tt.write_yellow("-----------------------------------------");
        int i5 = scanner.nextInt();
        if(i5 == 0){
            return false;
        }
        scanner.nextLine();
        Request rchosen = base.getRequest(i5);
        tt.write_yellow("|Do you want to be the volunteer       ?|");
        tt.write_yellow("|1- yes                                 |");
        tt.write_yellow("|2- no                                  |");
        tt.write_yellow("-----------------------------------------");
        int i6 = scanner.nextInt();
        scanner.nextLine();
        switch (i6){
            case 1 -> {
                volunteer.choseRequest(rchosen);
                base.updateRequestStatus(i5, "P");
                base.updateRequestVolunteer(i5, volunteer);
                tt.write_green("|You are the volunteer!                 |");
                System.out.println("-----------------------------------------");
            }
            case 2 -> tt.write_yellow("|You are not the volunteer!             |");
            default -> tt.write_red("|Wrong choice                         |");
        }
        return true;
    }

    public boolean print_request_volunteer(BDD base, Volunteer volunteer) throws SQLException{
        tt.write_yellow("|Your requests                      :   |");
        boolean are_request = base.printRequestVolunteer(volunteer);
        if (!are_request){
            tt.write_yellow("-----------------------------------------");
            return false;
        }
        tt.write_yellow("-----------------------------------------");
        return true;
    }

    public boolean terminer_requete(BDD base, Applicant applicant) throws SQLException{
        tt.write_yellow("|Your requests                      :   |");
        boolean are_request = base.printRequestApplicant(applicant);
        tt.write_yellow("-----------------------------------------");
        if (!are_request){
            return false;
        }
        tt.write_yellow("|To finish a request,                   |");
        tt.write_yellow("|enter its n°                           |");
        tt.write_yellow("|To quit this page, enter 0             |");
        tt.write_yellow("-----------------------------------------");
        int i6 = scanner.nextInt();
        if(i6 == 0){
            return false;
        }
        Request rfinished = base.getRequest(i6);
        applicant.completeRequest(rfinished);
        boolean isUpdated = base.updateRequestStatusApplicant(i6, "C");
        if(isUpdated){
            tt.write_yellow("-----------------------------------------");
            tt.write_green("|Request completed                      |");
            tt.write_yellow("|Enter a score for the volunteer        |");
            tt.write_yellow("|from 1 to 5                            |");
            tt.write_yellow("-----------------------------------------");
            int i7 = scanner.nextInt();
            base.updateVolunteerScore(rfinished, i7);
            tt.write_yellow("-----------------------------------------");
            tt.write_yellow("|Thank you                              |");
            tt.write_yellow("-----------------------------------------");
        }
        return true;
    }

    public Validator crea_valideur(BDD base) throws SQLException{
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|Enter your name                        |");
        String name3 = scanner.nextLine();
        tt.write_yellow("|Enter your age                         |");
        int age3 = scanner.nextInt();
        scanner.nextLine();
        tt.write_yellow("|Enter your dpt                         |");
        int dpt3 = scanner.nextInt();
        scanner.nextLine();
        tt.write_yellow("|Enter your organization                |");
        String org = scanner.nextLine();
        tt.write_yellow("|Enter a password                       |");   
        //String psw = scanner.nextLine();
        String psw = tt.ask_password_sign();
        Validator validator = new Validator(name3, age3, dpt3, org, psw);
        base.insertValidator(name3, age3, dpt3, org, psw);
        tt.write_green("-----------------------------------------");
        tt.write_green("|Validator account created with success  |");
        int id = base.getID_Validator(validator);
        tt.write_purple("|Your id is: " +id+" remember it             |");
        tt.write_green("-----------------------------------------");
        return validator;
    }

    public int menu_valideur(){
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|0 - Go back                            |");
        tt.write_yellow("|1 - Print pending requests             |");
        tt.write_yellow("|2 - View my requests                   |");
        tt.write_yellow("-----------------------------------------");
        int i7 = scanner.nextInt();
        scanner.nextLine();
        return i7;
    }

    public boolean print_request_validator(BDD base) throws SQLException{
        System.out.println("|Pending requests:                      |");
        boolean are_request = base.printRequestPending();
        if (!are_request){
            tt.write_yellow("-----------------------------------------");
            return false;
        }
        tt.write_yellow("-----------------------------------------");
        return true;
    }

    public int change_status(Validator validator, BDD base) throws SQLException{
        tt.write_yellow("|To change a status request             |");
        tt.write_yellow("|enter its  n°                          |");
        tt.write_yellow("|To quit this page, enter 0             |");
        tt.write_yellow("-----------------------------------------");
        int i8 = scanner.nextInt();
        if(i8 == 0){
            return 0;
        }
        scanner.nextLine();
        Request rchange = base.getRequest(i8);
        base.updateRequestValidator(i8, validator);
        return i8;
    }

    public int choisis_status(){
        tt.write_yellow("-----------------------------------------");
        tt.write_yellow("|1 - Approve the volunteer              |");
        tt.write_yellow("|2 - Refuse the volunteer               |");
        tt.write_yellow("-----------------------------------------");
        int i9 = scanner.nextInt();
        scanner.nextLine();
        return i9;
    }

    public void valider_r(BDD base, int rchange, Validator validator) throws SQLException{
        Request request = base.getRequest(rchange);
        validator.validerR(request);
        base.updateRequestStatus(rchange, "A");
    }

    public void refuser_r(BDD base, int rchange, Validator validator) throws SQLException{
        tt.write_yellow("|Please enter a motif                   |");
        tt.write_yellow("-----------------------------------------");
        String motif = scanner.nextLine();
        Request r = base.getRequest(rchange);
        validator.refuserR(r, motif);
        base.updateRequestStatus(rchange, "R");
        base.updateRequestMotif(rchange, motif);
    }

    public boolean afficher_requetesV(Validator validator, BDD base) throws SQLException{
        tt.write_yellow("|Your requests                      :   |");
        boolean are_request = base.printRequestValidator(validator);
        if (!are_request){
            tt.write_yellow("-----------------------------------------");
            return false;
        }
        tt.write_yellow("-----------------------------------------");
        return true;
    }

    public Applicant charge_applicant(BDD base) throws SQLException{
        boolean psw_ok = false;
        int id = -1;
        while(!psw_ok){
            tt.write_yellow("-----------------------------------------");
            tt.write_yellow("|Enter your id                          |");
            try{
                id = scanner.nextInt();
                scanner.nextLine();}
            catch(Exception e){
                tt.write_red("|Try again                              |");
                id = scanner.nextInt();
                scanner.nextLine();
            }
            tt.write_yellow("|Enter your password                    |");
            //String psw = scanner.nextLine();
            String psw = tt.ask_password_log();
            if (psw.equals(base.get_psw_Applicant(id))){
                psw_ok = true;
            }else {
                tt.write_red("|Wrong id or psw                    |");
            }
        }
        tt.write_yellow("-----------------------------------------");
        Applicant ap = base.getApplicant(id);
        tt.write_green("|Welcome " + ap.getName() + "                   |");
        tt.write_yellow("-----------------------------------------");
        return ap;
    }

    public Volunteer charge_volontaire(BDD base) throws SQLException{
        boolean psw_ok = false;
        int id = -1;
        while(!psw_ok){
            tt.write_yellow("-----------------------------------------");
            tt.write_yellow("|Enter your id                          |");
            try{
                id = scanner.nextInt();
                scanner.nextLine();}
            catch(Exception e){
                tt.write_red("|Try again                              |");
                id = scanner.nextInt();
                scanner.nextLine();
            }
            tt.write_yellow("|Enter your password                    |");
            //String psw = scanner.nextLine();
            String psw = tt.ask_password_log();
            if (psw.trim().equals(base.get_psw_Volunteer(id))){
                psw_ok = true;
            }else {
                tt.write_red("|Wrong id or psw                    |");
            }
        }
        tt.write_yellow("-----------------------------------------");
        Volunteer vo = base.getVolunteer(id);
        tt.write_green("|Welcome " + vo.getName() + "                   |");
        tt.write_yellow("-----------------------------------------");
        return vo;
    }

    public Validator charge_valideur(BDD base) throws SQLException{
        boolean psw_ok = false;
        int id = -1;
        while(!psw_ok){
            tt.write_yellow("-----------------------------------------");
            tt.write_yellow("|Enter your id                          |");
            try{
                id = scanner.nextInt();
                scanner.nextLine();}
            catch(Exception e){
                tt.write_red("|Try again                              |");
                id = scanner.nextInt();
                scanner.nextLine();
            }
            tt.write_yellow("|Enter your password                    |");
            //String psw = scanner.nextLine();
            String psw = tt.ask_password_log();
            if (psw.equals(base.get_psw_Validator(id))){
                psw_ok = true;
            }else {
                tt.write_red("|Wrong id or psw                    |");
            }
        }
        tt.write_yellow("-----------------------------------------");
        Validator va = base.getValidator(id);
        tt.write_green("|Welcome " + va.getName() + "                   |");
        tt.write_yellow("-----------------------------------------");
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
                                        default:
                                            tt.write_red("|Wrong choice                         |");
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
                                        case 3:
                                            base.print_score_volunteer(volunteer);
                                            break;
                                        default:
                                            tt.write_red("|Wrong choice                         |");
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
                                            int rchangeId = change_status(validator, base);
                                            //base.printVolunteer(rchange);
                                            if (rchangeId == 0){
                                                break;
                                            }
                                            int i9 = choisis_status();
                                            switch (i9) {
                                                case 1: //Valider
                                                    valider_r(base, rchangeId, validator);
                                                    break;
                                                case 2: //Refuser
                                                    refuser_r(base, rchangeId, validator);
                                                    break;
                                            }
                                            break;
                                        case 2: //afficher mes requêtes
                                            boolean are_requests = afficher_requetesV(validator, base);
                                            if (!are_requests){
                                                break;
                                            }
                                            break;
                                        default:
                                            tt.write_red("|Wrong choice                         |");
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
