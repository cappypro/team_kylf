package com.company;



public class Player {
    int cordx;
    int cordy;
    String nbPlayer;
    Color color = Color.WHITE;
    boolean defeat = false;
    String pseudo;

    public Player(int cordx, int cordy, String nbPlayer, String pseudo) {
        this.cordx = cordx;
        this.cordy = cordy;
        this.nbPlayer = nbPlayer;
        this.pseudo = pseudo;


        switch (nbPlayer){
            case "1" -> this.color = Color.GREEN;
            case "2" -> this.color = Color.YELLOW;
            case "3" -> this.color = Color.CYAN;
            case "4" -> this.color = Color.PURPLE;
        }
    }

    public boolean isDefeat(Case[][] grille){
        if (!this.defeat){
            boolean result = true;
            if (grille[this.cordy+1][this.cordx].available) {
                result = false;
            }

            else if (grille[this.cordy][this.cordx+1].available) {
                result = false;
            }

            else if (grille[this.cordy-1][this.cordx].available) {
                result = false;
            }

            else if (grille[this.cordy][this.cordx-1].available) {
                result = false;
            }

            if (result){
                this.defeat=true;
            }
            return this.defeat;
        }
        else {
            return this.defeat;
        }
    }
}
