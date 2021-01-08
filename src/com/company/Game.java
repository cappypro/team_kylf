package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private static Table table;

    /**
     * nombre de round et affichage du game over et du score
     */

    public static int askMove (String players, boolean direction, Color colorText){
        if(direction) {
            ColorConsole.print("              8: ", Color.RED);
            ColorConsole.println("↑", Color.GREEN);
            ColorConsole.print("\n   4: ", Color.RED);
            ColorConsole.print("←  ", Color.GREEN);
            ColorConsole.print("     2:", Color.RED);
            ColorConsole.print(" ↓  ", Color.GREEN);
            ColorConsole.print("    6: ", Color.RED);
            ColorConsole.println("->", Color.GREEN);
            System.out.println();
        }
        ColorConsole.print(players.toUpperCase().substring(0,1) + players.substring(1) + ", à toi de jouer : ", colorText);
        Scanner scan=new Scanner(System.in);
        // je stock la valeur de l'utilisateur ici
        try { // on essaie
            int nextInt = scan.nextInt(); // nextint = saisie user
            if(nextInt != 2 && nextInt != 4 && nextInt != 6 && nextInt != 8){ // si erreur throw new :2 4 6 8
                throw new Exception();
            } //on avance
            return nextInt;
        }
        catch (Exception e){ // on attrape erreur
            ColorConsole.println("Entrée incorrecte",Color.RED); // on affaiche l'erreur
            ColorConsole.print("Entrez votre choix : ", Color.GREEN); //on demande le choix
            return askMove(players, false, colorText); // on rappelle la fonction pour scanné le choix saisie
        }
    }

    /*
    *
    * grid = [
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    [Case, Case,Case,Case,Case,Case,Case, Case, Case, Case, Case,],
    *
    *
    * */
    public static void move(int idPlayers, Boolean askMoveDirection) {
        Player player = table.players[idPlayers];
        int choice = Game.askMove(player.pseudo, askMoveDirection, player.color); // On demande ou veux allez l'utilisateurs
        table.grille[player.cordy][player.cordx].display = "o";
        table.grille[player.cordy][player.cordx].color = Color.BLUE;
        int x = player.cordx;
        int y = player.cordy;
        switch (choice) {
            case 2 -> y++;
            case 4 -> x--;
            case 6 -> x++;
            case 8 -> y--;
        }
        if (y>=0 && y<=9 && x>=0 && x<=10) {
            if (table.grille[y][x].available) {
                table.grille[player.cordy][player.cordx].available = true;
                player.cordx = x;
                player.cordy = y;
                table.grille[player.cordy][player.cordx].available = false;
                table.grille[player.cordy][player.cordx].color = player.color;
                table.grille[player.cordy][player.cordx].display = player.pseudo.substring(0,1).toUpperCase();;
                table.displayGrid();
            } else {
                ColorConsole.println("Cette case est déjà occupée", Color.RED);
                move(idPlayers, false);
            }
        }
        else {
            ColorConsole.println("On fait pas de hors map sa****", Color.RED);
            move(idPlayers, false);
        }


    }

    


    public static int[] askDestroy() {

        ColorConsole.print("Veuillez choisir la case à détruire : ",Color.GREEN);
        Scanner scan=new Scanner(System.in);
        // je stock la valeur de l'utilisateur ici
        try { // on essaie
            String nextString = scan.nextLine(); // nextint = saisie user
            String[] Coord = nextString.split(" ");
            int[]result = new int[2];
            // b 6 = ["b","6"]
            String[] checkX = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"};
            String[] checkY = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            boolean errorX = true;
            boolean errorY = true;
            for (int i = 0; i < checkX.length; i++) {
                if (Coord[0].toUpperCase().equals(checkX[i])) {
                    errorX = false;
                    result[0] = i;
                    break;
                }
            }

            for (int i = 0; i < checkY.length; i++) {
                if(Coord[1].equals(checkY[i])){
                    errorY = false;
                    result[1] = i;
                    break;
                }
            }

            if(errorX || errorY){ // si erreur throw new :2 4 6 8
                throw new Exception();
            } //on avance

            return result; // a 6 k 5
        }
        catch (Exception e){ // on attrape erreur
            ColorConsole.println("Entrée incorrecte : ex -> b 6 ",Color.RED); // on affaiche l'erreur
            return askDestroy(); // on rappelle la fonction pour scanné le choix saisie
        }

    }

    public static void destroy() {
        int[] destroyCase = askDestroy(); // Cette function retourne un tableau exemple : [3, 4]

            if (table.grille[destroyCase[1]][destroyCase[0]].available){
                table.grille[destroyCase[1]][destroyCase[0]].available = false;
                table.grille[destroyCase[1]][destroyCase[0]].display = "x";
                table.grille[destroyCase[1]][destroyCase[0]].color = Color.RED;
                table.displayGrid();
            }
            else {
                ColorConsole.println("Cette case est déjà occupée", Color.RED);
                destroy();
            }

    }

    public static boolean replay(int turn,int nbplayer, HashMap<String, Integer> score){

        ArrayList<Player> playerIG = new ArrayList<>();
        ArrayList<Player> playerLoose = new ArrayList<>();
        for (int i = 0; i < nbplayer; i++) {
            if (!table.players[i].defeat){
                playerIG.add(table.players[i]);
            } else {
                playerLoose.add(table.players[i]);
            }
        }



        if (playerIG.size()>1) {
            if (!table.players[turn].isDefeat(table.grille)) {
                Game.move(turn, true); // Peut allez hors MAP !!!!!!!!!!!!!
                Game.destroy();
            }
            if (turn < nbplayer - 1) {
               return replay(turn + 1, nbplayer, score);
            } else {
               return replay(0, nbplayer, score);
            }
        } else {
            return endgame(playerIG.get(0), playerLoose, score);

        }
    }

    public static boolean play(int nbPlayer, String[] playerName, HashMap<String, Integer> score) {
            table = new Table(nbPlayer, playerName);
            table.displayGrid(); // On affiche la grille
            int random = (int) (Math.random() * (nbPlayer));
            return replay(random,nbPlayer, score);
    }
    public static boolean endgame(Player player, ArrayList<Player> playerLoose, HashMap<String, Integer > score ) {
        if (score.containsKey(player.pseudo)) {
            int count = score.get(player.pseudo);
            score.put(player.pseudo, count + 5);
        } else {
            score.put(player.pseudo, 5);
        }
        System.out.print("Bien jouer ");
        ColorConsole.print(player.pseudo.toUpperCase().charAt(0) + player.pseudo.substring(1), player.color);
        System.out.println(" Ton score est : " + score.get(player.pseudo));

        for (int i = 0; i < playerLoose.size(); i++) {
            if (score.containsKey(playerLoose.get(i).pseudo)) {
                int count = score.get(playerLoose.get(i).pseudo);
                if (count > 1) {
                    score.put(playerLoose.get(i).pseudo, count - 2);
                } else {
                    score.put(playerLoose.get(i).pseudo, 0);
                }
            } else {
                score.put(playerLoose.get(i).pseudo, 0);
            }
        }
        Files.create(score);
        return true;
    }
}

// implémentation Pseudo
// implémentation Score grace a Pseudo