package views;

import dao.MemberDao;
import dao.StoryDao;
import dto.CurrentUserIfor;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.Selector;
import java.util.ArrayList;

import static views.MainView.*;

public class HelperView extends JFrame{
    JButton scriptBnt = new JButton("대본 보기");
    JButton saveBnt = new JButton("저장 하기");
    JButton changeRole = new JButton("역할 교체");
    public void setHelperView(){
        helperPanel.setBackground(yellow);
        helperPanel.add(scriptBnt);
        helperPanel.add(saveBnt);
        helperPanel.add(changeRole);
        changeRole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                ButtonGroup group = new ButtonGroup();

                JRadioButton option1 = new JRadioButton("ross");
                JRadioButton option2 = new JRadioButton("chandler");
                JRadioButton option3 = new JRadioButton("joey");
                JRadioButton option4 = new JRadioButton("rachel");
                JRadioButton option5 = new JRadioButton("monica");
                JRadioButton option6 = new JRadioButton("phoebe");
                // 라디오 버튼을 그룹화하여 하나만 선택되도록 함
                group.add(option1);
                group.add(option2);
                group.add(option3);
                group.add(option4);
                group.add(option5);
                group.add(option6);

                panel.add(option1);
                panel.add(option2);
                panel.add(option3);
                panel.add(option4);
                panel.add(option5);
                panel.add(option6);

                // JOptionPane을 사용하여 패널을 포함하는 대화상자 표시
                int result = JOptionPane.showConfirmDialog(helperPanel,panel, "옵션 선택", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    if (option1.isSelected()) {
                        JOptionPane.showMessageDialog(helperPanel, "ross를 선택했습니다.");
                        CurrentUserIfor.character = "ross";
                    } else if (option2.isSelected()) {
                        JOptionPane.showMessageDialog(helperPanel, "chandler를 선택했습니다.");
                        CurrentUserIfor.character = "chandler";
                    } else if (option3.isSelected()) {
                        JOptionPane.showMessageDialog(helperPanel, "joey를 선택했습니다.");
                        CurrentUserIfor.character = "joey";
                    }else if (option4.isSelected()) {
                        JOptionPane.showMessageDialog(helperPanel, "rachel를 선택했습니다.");
                        CurrentUserIfor.character = "rachel";
                    }else if (option5.isSelected()) {
                        JOptionPane.showMessageDialog(helperPanel, "monica를 선택했습니다.");
                        CurrentUserIfor.character = "monica";
                    }else if (option6.isSelected()) {
                        JOptionPane.showMessageDialog(helperPanel, "phoebe를 선택했습니다.");
                        CurrentUserIfor.character = "phoebe";
                    }
                }
            }
        });


        scriptBnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoryDao storyDao = new StoryDao();
                setScriptView(storyDao.getStories(CurrentUserIfor.currentLineNumber,5));

            }
        });
        saveBnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StoryDao storyDao = new StoryDao();
                MemberDao memberDao = new MemberDao();
                int num =storyDao.updateHistory(CurrentUserIfor.getHistory());
                int number = memberDao.updateHistory();
                if(num==0||number==0){
                    JOptionPane.showMessageDialog(null, "오류 : 저장에 실패하였습니다.");
                }else {
                    JOptionPane.showMessageDialog(null, "저장되었습니다.");
                }
            }
        });

        chattingViewPanel.add(helperPanel);

    }
    public void setScriptView(ArrayList<String> lines){
        this.setSize(540, 700);
        this.setTitle("English script");
        this.setLayout(new MigLayout());
        this.setBackground(namsek);
        for (String s: lines) {
            JLabel label = new JLabel();
            label.setBackground(Color.white);
            label.setText("<html><body style='width: 350px;'>"+s+"</body></html>");
            label.setFont(new Font("Arial", Font.BOLD, 16));
            label.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 4));
            this.add(label, "wrap, span ");
        }
        this.setVisible(true);
    }
}
