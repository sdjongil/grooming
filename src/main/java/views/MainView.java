package views;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private static MainView mainView = null;
    public static MainView getMainView(){
        if(mainView==null){
            mainView = new MainView();
        }
        return mainView;
    }
    public static Color yellow = new Color(238, 181, 116);
    public static Color orange = new Color(255, 199, 95);
    public static Color namsek = new Color(180, 180, 180);
    public static Color dark = new Color(250, 230, 210);
    public static Color pink = new Color(235, 120, 106);




    //메인 프레임 판넬 객체들
    public static JPanel choiceViewPanel = new JPanel(new MigLayout("", "2[]4[]4[]","[]4[]4[]4[]4[]4[]4[]"));
    public static JPanel choiceEpisodePanel = new JPanel(new BorderLayout());

    public static JPanel SignUpPanel = new JPanel(new MigLayout("","70[][]",
            "160[]5[]5[]20[]5[]20[]5[]20[]5[]20[]5[]"));
    public static JPanel navPanel = new JPanel(new MigLayout());
    public static CardLayout mainCenterLayout = new CardLayout();
    public static JPanel mainCenterPanel = new JPanel();
    public static JPanel chattingViewPanel = new JPanel(new MigLayout());
    public static JPanel logInViewPanel = new JPanel(new MigLayout( "","120[]50[]", "300[]10[]20[]10[]20[]"));
    public static  JPanel helperPanel = new JPanel(new FlowLayout());


    //객체 생성
    ChattingView chattingView = new ChattingView();
    NavView navView = new NavView();
    TeacherView teacherView = new TeacherView();
    NoticeView noticeView = new NoticeView();
    LogInView logInView = new LogInView();
    HelperView helperView = new HelperView();
    ChoiceEpisodeView choiceEpisodeView = new ChoiceEpisodeView();
    SignUpView signUpView = new SignUpView();

    private MainView(){
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Speak in English");
        this.setSize(600, 900);
        mainCenterPanel.setLayout(mainCenterLayout);
        chattingViewPanel.setBorder(BorderFactory.createLineBorder(dark, 5));
        chattingViewPanel.setBackground(yellow);

        navView.setNavView();
        teacherView.setTeacherView();
        noticeView.setNoticeView();
        chattingView.setChattingView();
        logInView.setLogInView();
        helperView.setHelperView();
        choiceEpisodeView.setChoiceEpisodeView();
        signUpView.setSignUpView();

        finishSet();
    }

    void finishSet(){

        mainCenterPanel.add(logInViewPanel, "logInView");
        mainCenterPanel.add(chattingViewPanel, "chattingView");
        mainCenterPanel.add(choiceEpisodePanel, "choiceEpisodeView");
        mainCenterPanel.add(SignUpPanel, "signUpView");

        this.add(navPanel, "West");
        this.add(mainCenterPanel, "Center");
        this.setVisible(true);
    }
}
