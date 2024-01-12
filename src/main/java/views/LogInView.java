package views;

import dao.MemberDao;
import dao.UserEpisodeDao;
import dto.CurrentUserIfor;
import dto.MemberDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static views.MainView.*;

public class LogInView {
    ViewFunctions VF = new ViewFunctions();
    JButton logInBnt = new JButton("로그인");
    JButton signUpBnt = new JButton("회원가입");
    JTextField IDfield = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JLabel IDLabel = new JLabel("ID");
    JLabel password = new JLabel("PASSWORD");

    public void setLogInView(){
        IDLabel.setBackground(Color.white);
        IDLabel.setHorizontalAlignment(SwingConstants.CENTER);
        IDLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        password.setBackground(Color.white);
        password.setHorizontalAlignment(SwingConstants.CENTER);
        password.setFont(new Font("Arial", Font.PLAIN, 16));
        VF.setTextFeilds(IDfield);
        VF.setTextFeilds(passwordField);
        logInBnt.setBackground(Color.white);
        signUpBnt.setBackground(Color.white);
        logInBnt.setPreferredSize(new Dimension(85, 30));
        signUpBnt.setPreferredSize(new Dimension(85, 30));


        signUpBnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainCenterLayout.show(mainCenterPanel, "signUpView");

            }
        });
        logInBnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MemberDao memberDao = new MemberDao();
                MemberDto memberDto = new MemberDto();
                UserEpisodeDao userEpisodeDao = new UserEpisodeDao();
                char[] passChar = passwordField.getPassword();
                String passString = new String(passChar);
                memberDto =memberDao.logIn(IDfield.getText(), passString);
                if(memberDto==null){
                    JOptionPane.showMessageDialog(null, "회원 정보가 올바르지 않습니다.");
                }else{
                    JOptionPane.showMessageDialog(null, "로그인 되었습니다.");
                    logInUser(memberDto);
                    //유저 에피소드 세팅
                    userEpisodeDao.getUserEpisodes(memberDto.getId());
                    ChoiceEpisodeView choiceEpisodeView = new ChoiceEpisodeView();
                    choiceEpisodeView.setUserEpisodes();
                    IDfield.setText("");
                    passwordField.setText("");
                    mainCenterLayout.show(mainCenterPanel, "chattingView");
                }

            }
        });


        logInViewPanel.setBackground(yellow);
        logInViewPanel.add(IDLabel,"wrap, span") ;
        logInViewPanel.add(IDfield, "wrap, span");
        logInViewPanel.add(password, "wrap, span");
        logInViewPanel.add(passwordField,"wrap, span");
        logInViewPanel.add(logInBnt);
        logInViewPanel.add(signUpBnt);

    }
    public void logInUser(MemberDto mdto){
        CurrentUserIfor.currentId = mdto.getId();
        CurrentUserIfor.currentPoint = mdto.getPoint();
        String[] historys = mdto.getHistory().split(":");
        int line = Integer.parseInt(historys[1]);
        if(line>4){
            line -= 3;
        }
        CurrentUserIfor.currentLineNumber = line;
        CurrentUserIfor.currentEpisode = historys[0];
        CurrentUserIfor.eMail = mdto.geteMail();
        CurrentUserIfor.name = mdto.getName();

    }
}
