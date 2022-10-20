package ru.skillbench.tasks.text;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner data = new Scanner("BEGIN:VCARD\nFN:Kate Austen\n" +
                "ORG:Hollywood\n" +
                "BDAY:10-04-1940\n" +
                "TEL;TYPE=WORK:1234567890\nTEL;TYPE=CELL," +
                "VOICE:9150123456\nEND:VCARD");

        ContactCardImpl card = new ContactCardImpl();
        System.out.println(card.getInstance(data));
    }
}