package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    /**
     * affiche le menu démarrer
     *
     * @return le choix de l'utilisateur sous forme d'index
     */
    static int choiceStart() {
        ColorConsole.println("\n*********************************************************", Color.GREEN);
        ColorConsole.println("*                                                       *", Color.GREEN);
        ColorConsole.print("*", Color.GREEN);
        ColorConsole.print("             Bienvenue sur CASSE LA TETE               ", Color.RED);
        ColorConsole.println("*                                                       *", Color.GREEN);
        ColorConsole.print("", Color.GREEN);
        ColorConsole.println("*                                                       *", Color.GREEN);
        ColorConsole.println("*********************************************************", Color.GREEN);
        ColorConsole.println("\n\t1 : Nouvelle Partie ", Color.BLUE);
        ColorConsole.println("\t2 : Affichez les scores", Color.BLUE);
        ColorConsole.println("\t3 : Affichez les règles", Color.BLUE);
        ColorConsole.println("\t4 : Quittez la partie", Color.BLUE);
        ColorConsole.print("\nEntrez votre choix: ", Color.GREEN);
        return askInput(3, 0);
    }

    /**
     * affiche le menu de choix de difficulté
     *
     * @return le niveau de difficulté sous forme d'index
     */
    static int choiceNumberPlayer() {
        ColorConsole.print("Combien de joueurs 2 à 4 : ", Color.BLUE);
        return askInput(4, 1);


    }

    public static void rulesOfTheGame() { // Affichage dans la console des règles
        System.out.println(("Bonjour et Bienvenue dans Casse La Tete ! Voici les règles du jeu : \n\n") +
                "Au début de la partie, votre pion sera placé à côté de celui de votre adversaire, le but est simple, gagner la partie en bloquant au maximum votre ennemi.\n" +
                "Vous jouerez tour à tour et vous disposerez de 15 secondes pour jouer le vôtre.\n" +
                "A chaque tours vous devrez déplacer soit horizontalement soit verticalement votre pion, puis, vous devrez détruire une case sur le plateau\n" +
                "Le premier pion étant bloqué par une case au dessus, une case en dessous, une case à droite et une case à gauche aura perdu !\n" +
                "Choisissez bien vos déplacements ! Vous êtes prêt ? A vos marques, Prêt ? Jouez ! \n"
        );
        System.out.print("Appuyez sur la touche entrer : ");


    }

    /**
     * sécurité pour ne pas faire crash le programme
     *
     * @param max désigne le max d'input possible
     * @return une valeur valable
     */
    static int askInput(int max, int choice) {
        Scanner scan = new Scanner(System.in);
        try {
            int nextInt = scan.nextInt();
            if (nextInt > max || nextInt <= choice) {
                throw new Exception();
            }
            return nextInt;
        } catch (Exception e) {
            ColorConsole.println("Entrée incorrecte", Color.RED);
            ColorConsole.print("Entrez votre choix : ", Color.GREEN);
            return askInput(max, choice);
        }
    }

    public static int displayInterface(HashMap<String, Integer> score) {
        int number;
        number = Menu.choiceStart(); //Nouvelle partie, règles, quitter
        if (number == 1) { // si le choix = 1 = nouvelle partie
            int input = Menu.choiceNumberPlayer(); // Nombre de joueur
            return input;
        } else if (number == 2) {
            displayScore(score);
            Menu.returnRules();
            return Menu.displayInterface(score);
        } else if (number == 3) {
            Menu.rulesOfTheGame();
            Menu.returnRules();
            return Menu.displayInterface(score);
        }

        return 0;
    }

    public static void displayScore(HashMap<String, Integer> score) {
        if (score.isEmpty()) {
            System.out.println("Aucun sauvegarde n'existe");
        } else {
            for (Map.Entry<String, Integer> entry : score.entrySet()) {
                ColorConsole.println(entry.getKey() + " : " + entry.getValue(), Color.GREEN);
            }
        }
    }

    /**
     * Création du ficher de sauvegarde
     * Pseudo de l'users et du score
     * <p>
     * Après sauvegarder Users et score !
     *
     * @return
     */

    public static String[] choiceName(int nbPlayer) {
        String[] pseudo = new String[nbPlayer];
        for (int i = 0; i < nbPlayer; i++) {
            System.out.print("Pseudo du joueur " + String.valueOf(i + 1) + " : ");
            Scanner scanner = new Scanner(System.in);
            pseudo[i] = scanner.nextLine();

        }
        return pseudo;
    }

    public static void returnRules() {

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}
