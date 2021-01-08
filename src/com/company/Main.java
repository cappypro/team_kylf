package com.company;


import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        launchApp();
    }

    public static void launchApp() {
        HashMap<String, Integer> score = new HashMap<String, Integer>();
        Files.read(score);
        int nbPlayer = Menu.displayInterface(score); // On affiche l'interface du jeux
        String[] name = Menu.choiceName(nbPlayer);
        boolean nameWinner = Game.play(nbPlayer, name, score); // On lance la partie avec le nombre de joueur donnée
        launchApp();
    }

}


/*
* Story 1 :
* Story 2 :
* Story 3 :
* Story 4 :
* Story 5 :
* Story 6 :
* Story 7 :
* Story 8 :
* Story 9 :
* Story 10 :
*
* */