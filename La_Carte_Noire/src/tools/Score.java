package tools;

import java.util.HashMap;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import model.JoueurScore;

public class Score {
    
    private Score() {}
    
    public static ArrayList<JoueurScore> getScores() throws IOException{
        HashMap<String,Integer> mapScores = readScores();
        ArrayList<JoueurScore> joueurs = new ArrayList<JoueurScore>();
        
        for(Map.Entry<String,Integer> tmp : mapScores.entrySet()){
            joueurs.add(new JoueurScore(tmp.getKey(),tmp.getValue()));
        }
        Collections.sort(joueurs);
        Collections.reverse(joueurs);
        return joueurs;
    }
    
    public static HashMap<String,Integer> readScores() throws IOException{
        HashMap<String,Integer> mapScores = new HashMap<String,Integer>();
        try{
            File fichier = new File("scores.json");
            if(fichier.canRead()){
                ObjectMapper obj = new ObjectMapper();
                mapScores = obj.readValue(fichier,new TypeReference<Map<String,Integer>>(){});
            }
            else{
                fichier.createNewFile();
            }
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return mapScores;
    }
                  
    public static void writeScores(HashMap<String,Integer> map){
        try {
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String,Integer> mapFinale = readScores();
            
            for(Map.Entry<String,Integer> tmp : map.entrySet()){
                //si le joueur existe déjà dans le fichier, on récupère son score et on l'ajoute avec le nouveau
                if(mapFinale.containsKey(tmp.getKey())){
                    mapFinale.replace(tmp.getKey(),tmp.getValue() + mapFinale.get(tmp.getKey()));
                }
                //sinon on rajoute le joueur dans le fichier avec son nouveau score
                else{
                    mapFinale.put(tmp.getKey(),tmp.getValue());
                }
            }
            mapper.writeValue(new File("scores.json"), mapFinale);
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }
}
    