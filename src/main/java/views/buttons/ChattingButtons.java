package views.buttons;

import apiService.FreeTtsApi;
import apiService.GptApiService;
import apiService.NaverApiService;
import apiService.GoogleTtsApi;
import dao.StoryDao;
import dto.CurrentUserIfor;
import views.NoticeView;
import views.TeacherView;
import views.ViewFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import static dto.CurrentUserIfor.orginalEn;
import static views.ChattingView.chatPanel;
import static views.ChattingView.chatScroll;
import static views.MainView.yellow;

public class ChattingButtons {
    public static final String initHtml = "<html><body style='padding-left: 10px; " +
            "padding-right:10px; width: 210px; word-wrap: break-word;'>";
    TeacherView teacherView = new TeacherView();
    NoticeView noticeView = new NoticeView();
    ViewFunctions VF = new ViewFunctions();
    FreeTtsApi freeTtsApi = new FreeTtsApi();
    JButton sendButton = new JButton("보내기");
    JButton receiveButton = new JButton("받기");
    JTextField messageField = new JTextField();
    public JPanel myMessagePanel = new JPanel(new FlowLayout());


    public void setChattingButton(){
        sendButton.setPreferredSize(new Dimension(70, 30));
        receiveButton.setPreferredSize(new Dimension(70, 30));
        VF.setTextFeilds(messageField, 230, 30);
        myMessagePanel.setBackground(yellow);
        myMessagePanel.add(messageField);
        myMessagePanel.add(sendButton);
        myMessagePanel.add(receiveButton);
        messageField.setEnabled(true);

        //------------------버튼 리스너들----------
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendButton.doClick();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewFunctions VF = new ViewFunctions();
                String message = messageField.getText();
                if (!message.isEmpty()) {
                    JPanel panel = VF.setMessageBalloon("character1", message);
                    chatPanel.add(panel,"wrap, span , gapleft 140");
                    messageField.setText("");
                    chatPanel.revalidate();
                    chatScroll.revalidate();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            JScrollBar verticalScrollBar = chatScroll.getVerticalScrollBar();
                            verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                        }
                    });
                }
            }
        });

        receiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storyProcess();
            }
        });

    }
    public String getGptAdvice(int line) throws IOException {
        StoryDao storyDao = new StoryDao();
        GptApiService gptApiService = GptApiService.getApiService();

        ArrayList<String> lines = storyDao.getStories(line);
        String question = gptApiService.AITranslatePrompt(lines);

        return gptApiService.connectChatGPT(question);
    }
    public void talkFriends(String whoSpeeking,String msg, int line){
        ViewFunctions VF = new ViewFunctions();
        JPanel panel = VF.setMessageBalloon(whoSpeeking, msg);
        JButton transNormal = new JButton("번역");
        JButton AITrans = new JButton("AI번역");
        JButton soundButton = new JButton("듣기");
        JButton AiSoundButton = new JButton("AI듣기");
        transNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = translationApi(msg);
                teacherView.setTeacherText(initHtml+message+"</body></html>");
            }
        });
        AITrans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int buttonline = line;
                try {
                    String answer = getGptAdvice(buttonline);
                    teacherView.setTeacherText(initHtml+answer+"</body></html>");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = msg;
                freeTtsApi.ttsService(message);
            }
        });
        AiSoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = msg;
                try {
                    GoogleTtsApi.synthesizeText(msg);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                GoogleTtsApi.playSound("output.wav");
            }
        });
        chatPanel.add(panel, "wrap, span");
        chatPanel.add(transNormal);
        chatPanel.add(AITrans);
        chatPanel.add(soundButton);
        chatPanel.add(AiSoundButton, "wrap");
        chatPanel.revalidate();
        chatScroll.revalidate();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JScrollBar verticalScrollBar = chatScroll.getVerticalScrollBar();
                verticalScrollBar.setValue(verticalScrollBar.getMaximum());
            }
        });
    }
    public void startStory(){
        noticeView.setNoticeText(CurrentUserIfor.currentEpisode+" / " + CurrentUserIfor.currentLineNumber +
                "라인부터 시작합니다.");
    }
    public void storyProcess() {
        StoryDao storyDao = new StoryDao();
        int lineNumber = CurrentUserIfor.currentLineNumber;
        String character = CurrentUserIfor.character;
        String Episode = CurrentUserIfor.currentEpisode;
        String[] result = storyDao.selectScene(Episode,lineNumber);
        if(result[0].equalsIgnoreCase(character)){
            orginalEn = result[1];
            String message = translationApi(result[1]);
            teacherView.setTeacherText(initHtml +
                    CurrentUserIfor.currentId+"님 차례에요! 밑에 말을 영어로 해보해요!</body></html>");
            noticeView.setNoticeText("<html><body style=width: 230px;'>"+message+"</body></html>");
        }else if(lineNumber==1){
            String message = translationApi(result[1]);
            noticeView.setNoticeText("<html><body style=width: 230px;'>"+message+"</body></html>");
        }
        else if(result[0].equals("narration")){
            noticeView.setNoticeText("<html><body style=width: 230px;'>"+result[1]+"</body></html>");
        }
        else{
            talkFriends(result[0],result[1], CurrentUserIfor.currentLineNumber);
        }
        CurrentUserIfor.currentLineNumber++;
    }
    public String translationApi(String msg){
        NaverApiService naverApiService = NaverApiService.getNaverApiService();
        return naverApiService.getTranslate(msg);
    }



}
