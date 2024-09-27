package views;

import dao.UserEpisodeDao;
import dto.CurrentUserIfor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import static dto.CurrentUserIfor.currentId;
import static dto.CurrentUserIfor.userEpisodes;
import static views.MainView.*;

public class ChoiceEpisodeView {

    static JScrollPane choiceScroll = new JScrollPane(choiceViewPanel);
    static ArrayList<JButton> episodeButtons = new ArrayList<>();

    public void setChoiceEpisodeView(){
        ViewFunctions VF = new ViewFunctions();
        choiceViewPanel.setBorder(new EmptyBorder(0,15,0,0));
        choiceViewPanel.setBackground(Color.white);
        choiceScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        choiceScroll.setBackground(yellow);

        JPanel episodeBottom = new JPanel(new FlowLayout());
        JButton updateButton = new JButton("파일 올리기");
        JProgressBar updataBar = new JProgressBar();
        updataBar.setVisible(true);
        updataBar.setIndeterminate(false);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updataBar.setIndeterminate(true);
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    StoryMessages storyMessages = StoryMessages.getStoryMessages();
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    String[] fileNames = selectedFile.getName().split("\\.");
                    String fileName = fileNames[0];
                    //DB에 파일 저장 작업
                    storyMessages.getMessageInStory(CurrentUserIfor.currentId+"_"+fileName,
                            selectedFile.getPath());
                    //유저에피소드 파드 테이블에 저장
                    UserEpisodeDao userEpisodeDao = new UserEpisodeDao();
                    userEpisodeDao.insertUserEpisodes(CurrentUserIfor.currentId+"_"+fileName,
                            CurrentUserIfor.currentId);

                    setChoiceEpisodeView(currentId+"_"+fileName, 1);
                    CurrentUserIfor.userEpisodes.put(currentId+"_"+fileName,1);
                    choiceViewPanel.add(episodeButtons.get((episodeButtons.size()-1)), "wrap");
                }
                updataBar.setVisible(false);
            }

        });
        episodeBottom.add(updateButton);
        episodeBottom.add(updataBar);
        choiceEpisodePanel.add(episodeBottom, "South");
        choiceEpisodePanel.add(choiceScroll, "Center");
    }

    public void setChoiceEpisodeView(String fileName, int line) {
        ViewFunctions VF = new ViewFunctions();
        JButton episodeButton = new JButton(fileName);
        ImageIcon choiceEpisode = VF.setImageIconSize("src/images/episode1.png", 40, 25);
        VF.setNavButton(episodeButton);
        episodeButton.setIcon(choiceEpisode);
        episodeButton.setPreferredSize(new Dimension(40, 40));
        episodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = episodeButton.getText();
                CurrentUserIfor.currentEpisode = title;
                CurrentUserIfor.currentLineNumber = line;
                JOptionPane.showMessageDialog(null, title+"에피소드가 선택되었습니다. \n" +
                        +line+"부터 시작됩니다.", "에피소드", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        episodeButtons.add(episodeButton);
    }
    public void setUserEpisodes(){
        int num = 1;
        for (Map.Entry<String, Integer> entry: userEpisodes.entrySet()) {
            setChoiceEpisodeView(entry.getKey(), entry.getValue());
        }
        for (JButton jButton : episodeButtons) {
            if((num)%3==0){
                choiceViewPanel.add(jButton, "wrap");
            }else{
                choiceViewPanel.add(jButton);
            }
            num++;
        }
        choiceViewPanel.revalidate();
    }
}
