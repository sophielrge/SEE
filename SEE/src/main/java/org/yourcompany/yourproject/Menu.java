package org.yourcompany.yourproject;
import java.util.Scanner;
import java.sql.SQLException;


public class Menu {

    public Menu(BDD base) throws SQLException{
        System.out.println("-----------------------------------------");
        System.out.println("|               MENU SEE                |");
        System.out.println("|1 - Creer un compte                    |");
        System.out.println("|2 - Se Connecter                       |");
        System.out.println("-----------------------------------------");

        Scanner scanner = new Scanner(System.in);
        int i1 = scanner.nextInt();

        switch (i1) { 

            //creer un compte
            case 1:
                System.out.println("|1 - Je suis demandeur                  |");
                System.out.println("|2 - Je suis bénévole                   |");
                System.out.println("|3 - Je suis valideur                   |");
                System.out.println("-----------------------------------------");
                int i2 = scanner.nextInt();
                scanner.nextLine();

                switch (i2) {

                    //Creation demandeur
                    case 1:
                    System.out.println("|Veuillez entrer votre nom              |");
                    String name = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("|Veuillez entrer votre age              |");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("|Veuillez entrer votre département      |");
                    int dpt = scanner.nextInt();
                    scanner.nextLine();
                    Applicant applicant = new Applicant(name, age, dpt);
                    base.insertApplicant(name, age, dpt);
                    System.out.println("|Compte demandeur créé avec succès !!   |");
                    System.out.println("-----------------------------------------");
                    System.out.println("|1 - ajouter une requête                |");
                    System.out.println("|2 - Conuslter mes requêtes             |");
                    System.out.println("-----------------------------------------");
                    int i3 = scanner.nextInt();
                    switch (i3) {
                        //ajouter requête
                        case 1:
                        System.out.println("|Veuillez décrire votre requête         |");
                        String subj= scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("|Veuillez indiquer votre id             |");
                        int app=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("|Veuillez indiquer le jour jj/mm/aaa    |");
                        String d=scanner.nextLine(); //transformer mon string en date
                        scanner.nextLine();
                        int id=base.getID_Applicant(applicant);
                        base.insertRequest(subj,id, date);
                        break;
                        
                        //consulter mes requêtes
                        case 2:
                        System.out.println("-----------------------------------------");
                        System.out.println("|Voici vos reqêtes                  :   |");
                        base.printRequestApplicant(applicant);
                        break;
                    }
                    break;
                    
                    //creation benevole
                    case 2:
                    System.out.println("|Veuillez entrer votre nom              |");
                    String name2 = scanner.nextLine();
                    System.out.println("|Veuillez entrer votre age              |");
                    int age2 = scanner.nextInt();
                    System.out.println("|Veuillez entrer votre département      |");
                    int dpt2 = scanner.nextInt();
                    Volunteer volunteer = new Volunteer(name2, age2, dpt2);
                    base.insertVolunteer(name2, age2, dpt2);
                    System.out.println("|Compte bénévole créé avec succès !!    |");
                    System.out.println("-----------------------------------------");
                    System.out.println("-----------------------------------------");
                    System.out.println("|1 - Afficher requêtes non attribuées   |");
                    System.out.println("|2 - Conuslter mes requêtes             |");
                    System.out.println("-----------------------------------------");
                    int i4 = scanner.nextInt();
                    switch (i4) {
                        //afficher les reqêtes
                        case 1:
                        base.printRequestPending();
                        System.out.println("-----------------------------------------");
                        System.out.println("|Pour sélectionner une requête,         |");
                        System.out.println("|entrez son n°                          |");
                        System.out.println("-----------------------------------------");
                        int i5 = scanner.nextInt();
                        Request rchosen = base.getRequest(i5);
                        volunteer.choseRequest(id);
                        break;

                        //consulter mes requêtes
                        case 2:
                        System.out.println("-----------------------------------------");
                        System.out.println("|Voici vos reqêtes                  :   |");
                        base.printRequestVolunteer(volunteer);
                        System.out.println("-----------------------------------------");
                        System.out.println("|Pour terminer une requête,             |");
                        System.out.println("|entrez son n°                          |");
                        int i6 = scanner.nextInt();
                        Request rfinished = base.getRequest(i6);
                        volunteer.completeRequest(rfinished);
                        break;
                    }
                    break;

                    case 3:
                    //compte valideur
                    System.out.println("|Veuillez entrer votre nom              |");
                    String name3 = scanner.nextLine();
                    System.out.println("|Veuillez entrer votre age              |");
                    int age3 = scanner.nextInt();
                    System.out.println("|Veuillez entrer votre département      |");
                    int dpt3 = scanner.nextInt();
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
                    switch (i7) {
                        case 1:
                        break;
                        case 2:
                        break;
                    }


                    default:
                        break;
                }
                break;
        
            default:
                break;
        }{

        }
    }
    
}
