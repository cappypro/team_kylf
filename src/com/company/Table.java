package com.company;



public class Table {
    private static int nbCol = 11;
    private static int nbLig = 10;
    public Case[][] grille;
    // Attribut de table
    public Player[] players;

    // Fonction pour Initialisé la grille
    public Table(int nbPlayer, String[] namePlayer) {
        grille = new Case[nbLig][nbCol];
        players = new Player[nbPlayer];
        for (int i = 0; i < nbPlayer; i++){
            if (i == 3){
                this.players[i] = new Player(5, 3, String.valueOf(i + 1), namePlayer[i]);
            } else {
                this.players[i] = new Player(5,4+i, String.valueOf(i + 1), namePlayer[i]);
            }

        }


        // For pour parcourir toute les lignes
        for (int l = 0; l < nbLig; l++) {
            // For pour parcourir toute les colonnes
            for (int c = 0; c < nbCol; c++) {

                //------------------------------------------------------

                grille[l][c] = new Case();
            }
        }

        for (int i = 0; i < this.players.length; i++) {
            int l = this.players[i].cordy;
            int c = this.players[i].cordx;
            grille[l][c].display = this.players[i].pseudo.substring(0,1).toUpperCase();
            grille[l][c].available = false;
            grille[l][c].color = this.players[i].color;
        }
        /**
         * Position de la personne
         *
         * Nombre de participant
         * Initial
         *
         * Players[0]
         * Players = [
         * [6, 5]
         * [6, 6]
         * [6, 7]
         * [6, 4]
         * ]
         */

    }


    public  void displayGrid() {
        ColorConsole.println("\n    A  B  C  D  E  F  G  H  I  J  K", Color.CYAN);


        // For pour parcourir toute les colognes
        for (int l = 0; l < nbLig; l++) {
            System.out.println();
            ColorConsole.print(String.valueOf(l+1), Color.CYAN);
            if (l < 9){
                System.out.print(" ");
            }
            // For pour parcourir toute les lignes
            for (int c = 0; c < nbCol; c++) {
                ColorConsole.print("  " + grille[l][c].display, grille[l][c].color); // Joueur pas color parceque color case = bleu,
            }

        }
        System.out.println("\n");
    }
}
