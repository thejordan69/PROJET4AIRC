package model;

public class JoueurScore implements Comparable {
    private String pseudo;
    private int score;
    
    public JoueurScore(String pseudo, int score){
        this.pseudo = pseudo;
        this.score = score;
    }
    
    public int getScore(){
        return this.score;
    }
    
    public String getPseudo(){
        return this.pseudo;
    }

    @Override
    public int compareTo(Object o) {
        JoueurScore joueur = (JoueurScore) o;
        return Integer.compare(this.score, joueur.getScore());
    }
}
