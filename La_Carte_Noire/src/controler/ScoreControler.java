package controler;

import java.io.IOException;
import java.util.ArrayList;
import model.JoueurScore;
import tools.Score;

public class ScoreControler {
    
    private ScoreControler(){}
    
    public static ArrayList<JoueurScore> getScores() throws IOException {
        return Score.getScores();
    }
}