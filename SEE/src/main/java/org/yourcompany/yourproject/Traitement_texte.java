package org.yourcompany.yourproject;

import java.io.Console;

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
        Console console = System.console();
        
        if (console == null) {
            write_red("La console n'est pas disponible.");
            throw new UnsupportedOperationException();
        }

        // Affiche le message et masque les entrées de l'utilisateur
        char[] passwordArray = console.readPassword();
        return new String(passwordArray);
    }
    
}
