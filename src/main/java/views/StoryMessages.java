package views;

import dao.StoryDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StoryMessages {
    static StoryMessages storyMessages = null;
    private StoryMessages(){};
    public static StoryMessages getStoryMessages(){
        if (storyMessages == null){
            storyMessages = new StoryMessages();
        }
        return storyMessages;
    }

    public void getMessageInStory(String tableTitle, String storyFilePath){
        String message = "";
        StoryDao storyDao = new StoryDao();
        try (BufferedReader reader = new BufferedReader(new FileReader(storyFilePath))) {
            String line;
            storyDao.createEpisodeTable(tableTitle);
            storyDao.createEpisodeSequence(tableTitle);
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("The one")) {
                    storyDao.insertStory("title", line, tableTitle);
                } else if(line.startsWith("[")) {
                    storyDao.insertStory("narration", line,tableTitle);
                }else if(line.startsWith("(")) {
                    storyDao.insertStory("narration", line,tableTitle);
                }else if(line.startsWith("Written by")) {

                }else if(line.startsWith("End")) {
                    storyDao.insertStory("End", "End",tableTitle);
                }else{
                    try{
                        String[] lines = line.split(":",2);
                        storyDao.insertStory(lines[0], lines[1],tableTitle);
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
