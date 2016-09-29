package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

    public static void main(String[] args) {
        String[] langs = {"Java", "C#", "Python", "PHP"};
        //System.out.println(langs);

        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
        //languages.add("Java");
        //languages.add("C#");
        //languages.add("Python");
        //languages.add("PHP");
        System.out.println(languages.get(1));

        for ( int i = 0; i < languages.size(); i++) {
            System.out.println("Язык: " + l);
        }
    }
}
