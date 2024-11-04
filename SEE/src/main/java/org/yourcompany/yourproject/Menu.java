package org.yourcompany.yourproject;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Menu {
    Scanner scanner = new Scanner(System.in);

    public int menu_depart(){
        System.out.println("-----------------------------------------");
        System.out.println("|               MENU SEE                |");
        System.out.println("|1 - Sign in                            |");
        System.out.println("|2 - Log in                             |");
        System.out.println("-----------------------------------------");
        int i = scanner.nextInt();
        scanner.nextLine();
        return i;
    }

    public int creer_compte(){
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
        Applicant applicant = new Applicant(name, age, dpt);
        base.insertApplicant(name, age, dpt);
        System.out.println("|Applicant account created with success |");
        System.out.println("-----------------------------------------");
        return applicant;
    }

    public int menu_demandeur(){
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

    public void consulter_requetes(BDD base, Applicant applicant) throws SQLException{
        System.out.println("|Your requests :                        |");
        base.printRequestApplicant(applicant);
        System.out.println("-----------------------------------------");
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
        Volunteer volunteer = new Volunteer(name2, age2, dpt2);
        base.insertVolunteer(name2, age2, dpt2);
        System.out.println("|Volunteer account created with success!|");
        System.out.println("-----------------------------------------");
        return volunteer;
    }

    public int menu_benevole(){
        System.out.println("|1 - Print requests pending             |");
        System.out.println("|2 - View my requests                   |");
        System.out.println("-----------------------------------------");
        int i4 = scanner.nextInt();
        scanner.nextLine();
        return i4;
    }

    public void devenir_volontaire(BDD base, Volunteer volunteer) throws SQLException{
        base.printRequestPending();
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
    }

    public void terminer_requete(BDD base, Volunteer volunteer) throws SQLException{
        System.out.println("|Your requests                      :   |");
        base.printRequestVolunteer(volunteer);
        System.out.println("-----------------------------------------");
        System.out.println("|To finish a request,                   |");
        System.out.println("|enter its n°                           |");
        System.out.println("-----------------------------------------");
        int i6 = scanner.nextInt();
        Request rfinished = base.getRequest(i6);
        volunteer.completeRequest(rfinished);
        base.updateRequestStatus(rfinished, "C");
    }

    public Menu(BDD base) throws SQLException, ParseException {
        int i1 = menu_depart();
        switch (i1) {
            case 1: // creer un compte
                int i2 = creer_compte();
                switch (i2) {
                    case 1: // Creation demandeur
                        Applicant applicant = crea_demandeur(base);
                        int i3 = menu_demandeur();
                        switch (i3) {
                            case 1:  // ajouter requête
                                ajouter_requete(base, applicant);
                                break;
                            case 2: // consulter mes requêtes
                                consulter_requetes(base,applicant);
                                break;
                        }
                        break;
                    case 2: // creation benevole
                        Volunteer volunteer= crea_bene(base);
                        int i4 = menu_benevole();
                        switch (i4) {
                            case 1: // afficher les reqêtes et en selectionner
                                devenir_volontaire(base, volunteer);
                                break;
                            case 2: // consulter mes requêtes
                                terminer_requete(base,volunteer);
                                break;
                        }
                        break;

                    case 3:
                        // compte valideur
                        System.out.println("|Veuillez entrer votre nom              |");
                        String name3 = scanner.nextLine();
                        System.out.println("|Veuillez entrer votre age              |");
                        int age3 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("|Veuillez entrer votre département      |");
                        int dpt3 = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("|Veuillez entrer votre organisme        |");
                        String org = scanner.nextLine();
                        Validator validator = new Validator(name3, age3, dpt3, org);
                        base.insertValidator(name3, age3, dpt3, org);
                        System.out.println("|Compte valideur créé avec succès !!    |");
                        System.out.println("-----------------------------------------");
                        System.out.println("-----------------------------------------");
                        System.out.println("|1 - Afficher les requêtes en attente   |");
                        System.out.println("|2 - Conuslter mes requêtes             |");
                        System.out.println("-----------------------------------------");
                        int i7 = scanner.nextInt();
                        scanner.nextLine();
                        switch (i7) {
                            case 1:
                                System.out.println("-----------------------------------------");
                                System.out.println("|Voici les requêtes en attente          |");
                                base.printRequestPending();
                                System.out.println("-----------------------------------------");
                                System.out.println("|Pour changer le statut d'une requête   |");
                                System.out.println("|entrez son n°                          |");
                                int i8 = scanner.nextInt();
                                scanner.nextLine();
                                Request rchange = base.getRequest(i8);
                                base.updateRequestValidator(rchange, validator);
                                System.out.println("-----------------------------------------");
                                System.out.println("|1 - Approuver le bénévole              |");
                                System.out.println("|2 - Refuser le bénévole                |");
                                int i9 = scanner.nextInt();
                                scanner.nextLine();
                                switch (i9) {
                                    case 1:
                                        validator.validerR(rchange);
                                        base.updateRequestStatus(rchange, "A");
                                        break;

                                    case 2:
                                        System.out.println("|Veuillez entrer un motif               |");
                                        System.out.println("-----------------------------------------");
                                        String motif = scanner.nextLine();
                                        validator.refuserR(rchange, motif);
                                        base.updateRequestStatus(rchange, "R");
                                        base.updateRequestMotif(rchange, motif);
                                        break;
                                }
                                break;
                            case 2:
                                System.out.println("-----------------------------------------");
                                System.out.println("|Voici vos reqêtes                  :   |");
                                base.printRequestValidator(validator);
                                System.out.println("-----------------------------------------");
                                break;
                        }

                    default:
                        break;
                }
                break;

            default:
                break;
            case 2:
                // se connecter
                System.out.println("|Veuillez entrer votre id               |");
                System.out.println("-----------------------------------------");
                int id = scanner.nextInt();
                scanner.nextLine();
                Applicant ap;
                ap = base.getApplicant(id);
                System.out.println("Bienvenue " + ap.getName());
                System.out.println("-----------------------------------------");
        }
        {

        }
    }

}
