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
                    base.insertApplicant(name, age, dpt);
                    System.out.println("|Compte demandeur créé avec succès !!   |");
                    System.out.println("-----------------------------------------");
                    System.out.println("|1 - ajouter une requête                |");
                    System.out.println("|2 - Conuslter mes requêtes             |");
                    System.out.println("-----------------------------------------");
                    int i3 = scanner.nextInt();
                    switch (i3) {
                        case 1:
                        System.out.println("|Veuillez décrire votre requête         |");
                        String subj= scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("|Veuillez indiquer votre id             |");
                        int app=scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("|Veuillez indiquer le jour jj/mm/aaa    |");
                        String d=scanner.nextLine();
                        scanner.nextLine();
                        //base.insertRequest(subj, int vol, app, date);

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
                    base.insertVolunteer(name2, age2, dpt2);
                    System.out.println("|Compte bénévole créé avec succès !!    |");
                    System.out.println("-----------------------------------------");
                    break;

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
