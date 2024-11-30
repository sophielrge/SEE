package org.yourcompany.yourproject;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public class Traitement_texte {

    public Traitement_texte() {
    }

    public void write_red(String text) {
        System.out.println("\u001B[31m" + text + "\u001B[0m");
    }

    public void write_green(String text) {
        System.out.println("\u001B[32m" + text + "\u001B[0m");
    }

    public void write_yellow(String text) {
        System.out.println("\u001B[33m" + text + "\u001B[0m");
    }   

    public void write_blue(String text) {
        System.out.println("\u001B[34m" + text + "\u001B[0m");
    }

    public void write_purple(String text) {
        System.out.println("\u001B[35m" + text + "\u001B[0m");
    }

    public void write_cyan(String text) {
        System.out.println("\u001B[36m" + text + "\u001B[0m");
    }

    public String ask_password() {
         StringBuilder password = new StringBuilder();

        while (true) {
            try {
                // Lire un caractère
                char inputChar = (char) System.in.read();

                switch (inputChar) {
                    case '\n':
                        // Fin de la saisie (l'utilisateur appuie sur Entrée)
                        return password.toString();
                    case '\b':
                        // Gestion du retour arrière
                        if (password.length() > 0) {
                            password.deleteCharAt(password.length() - 1);
                            System.out.print("\b \b"); // Supprime une étoile affichée
                        }
                        break;
                    default:
                        // Ajouter le caractère au mot de passe
                        password.append(inputChar);
                        System.out.print("*"); // Affiche une étoile pour chaque caractère
                        break;
                }
            } catch (IOException e) {
                System.out.println("Erreur lors de la lecture : " + e.getMessage());
            }
        }
    }
    
}
