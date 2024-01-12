package views.buttons;

import dto.CurrentUserIfor;
import views.NoticeView;
import views.ViewFunctions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static views.MainView.*;

public class NavButtons {
    JButton myProfile = new JButton("user");
    JButton chatting = new JButton("chatting");
    JButton episode = new JButton("episode");
    JButton setting = new JButton("setting");
    ArrayList<JButton> navButtons = new ArrayList<>();
    ViewFunctions VF = new ViewFunctions();
    public void setNavButton(){
        navButtons.add(myProfile);
        navButtons.add(chatting);
        navButtons.add(episode);
        navButtons.add(setting);

        //로고 세팅
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = VF.setImageIconSize("src/images/friends1.png", 110, 65);
        logoLabel.setIcon(logoIcon);
        logoLabel.setBorder(new EmptyBorder(20, 5, 40, 5));
        logoLabel.setToolTipText("홈으로");
        navPanel.add(logoLabel, "Wrap");

        //버튼 세팅
        for (JButton button:navButtons) {
            ImageIcon Icon = VF.setImageIconSize("src/images/"+button.getText()+".png");
            VF.setNavButton(button);
            button.setIcon(Icon);
            navPanel.add(button, "Wrap");
        }

        //버튼 리스너 만들기
        chatting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentUserIfor.currentId == null){
                    JOptionPane.showMessageDialog(null, "로그인해야 가능합니다.");
                }else{
                    mainCenterLayout.show(mainCenterPanel, "chattingView");
                    ChattingButtons CB = new ChattingButtons();
                    CB.startStory();
                }

            }
        });
        episode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CurrentUserIfor.currentId == null){
                    JOptionPane.showMessageDialog(null, "로그인해야 가능합니다.");
                }else{
                    mainCenterLayout.show(mainCenterPanel, "choiceEpisodeView");
                }
            }
        });
        logoLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainCenterLayout.show(mainCenterPanel, "logInView");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }
}
