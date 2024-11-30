package org.yourcompany.yourproject;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

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

    public static String ask_password_log() {
        LineReader reader = LineReaderBuilder.builder().build();
        return reader.readLine("", '*');
    }

    public static String ask_password_sign() {
        LineReader reader = LineReaderBuilder.builder().build();
        return reader.readLine("", '*');
    }
    
}
