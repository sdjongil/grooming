package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static views.MainView.*;
import static views.buttons.ChattingButtons.initHtml;

public class TeacherView {
    ViewFunctions VF = new ViewFunctions();
    JPanel teacherPanel = new JPanel(new FlowLayout());
    JPanel speechPanel = new JPanel();
    static JLabel speechLabel = new JLabel();
    String end = "</body></html>";
    String getWelcomeText = initHtml+
            "안녕하세요!<br>여러분을 도와줄 Emma라고 해요. " +
            "여러분이 힘들지 않게 항상 옆에서 " +
            "도와드릴거니 걱정마세요! 혹여나 어려운 부분 힘든 부분이 있다면 언제든 저에게 질문해주세요."
            +end;

    public void setTeacherView(){
        teacherPanel.setBackground(yellow);
        teacherPanel.setPreferredSize(new Dimension(430, 200));

        //선생님 컨테이너 생성
        JLabel teacherLabel = new JLabel();
        ImageIcon teacherIcon = VF.setImageIconSize("src/images/teacher.png", 100, 200);
        teacherLabel.setIcon(teacherIcon);
        teacherPanel.add(teacherLabel);

        //말풍선 컨테이너 생성

        setTeacherText(getWelcomeText);

        JScrollPane teacherScroll = new JScrollPane(speechLabel);
        teacherScroll.setPreferredSize(new Dimension(300,200));
        teacherScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        teacherPanel.add(teacherScroll);

        chattingViewPanel.add(teacherPanel, "wrap");
    }

    public void setTeacherText(String msg){
        speechLabel.setText(msg);

    }
}
