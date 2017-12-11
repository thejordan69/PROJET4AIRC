package controler;

import java.io.IOException;
import java.util.HashMap;
import tools.Score;

public class ScoreControler {
    
    private ScoreControler(){}
    
    public static HashMap<String,Integer> getScores() throws IOException {
        return Score.getScores();
    }
}