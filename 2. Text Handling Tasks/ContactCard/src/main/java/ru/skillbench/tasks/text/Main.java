package ru.skillbench.tasks.text;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner data = new Scanner("BEGIN:VCARD\n" +
                "FN:Forrest Gump\n" +
                "ORG:Bubba Gump Shrimp Co.\n" +
                "BDAY:06-06-1944\n" +
                "TEL;TYPE=WORK,VOICE:4951234567\n" +
                "TEL;TYPE=CELL,VOICE:9150123456\n" +
                "END:VCARD");

        String datas = "BEGIN:VCARD\n" +
                "FN:Forrest Gump\n";
//        Pattern pattern = Pattern.compile("^(BEGIN:VCARD\n)+FN:([a-zA-Z| ]\n)$");
        Pattern pattern = Pattern.compile(data.nextLine());
        Matcher matcher = pattern.matcher(datas);


    }
}
