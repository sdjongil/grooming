package dto;

import java.util.HashMap;

public class CurrentUserIfor {
    public static String orginalEn = "";
    public static String currentId = null;
    public static int currentLineNumber = 1;
    public static String currentEpisode = "s01_e02";
    public static HashMap<String, Integer> userEpisodes = new HashMap<>();
    public static String character = "phoebe";
    public static int currentPoint = 0;
    public static String eMail = null;
    public static String name = null;


    public static String getHistory(){
        return currentEpisode +":"+currentLineNumber;
    }
    public void splitHistory(String history){
        String[] historys = history.split(":");
        currentLineNumber = Integer.parseInt(historys[1]);
        currentEpisode = historys[0];
    }

}
