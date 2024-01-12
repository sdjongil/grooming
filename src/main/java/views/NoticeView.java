package views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;

import static views.MainView.*;

public class NoticeView {
    ViewFunctions VF = new ViewFunctions();
    JPanel noticePanel = new JPanel(new BorderLayout());
    JPanel messagePanel = new JPanel(new BorderLayout());
    static String messageHtml = "<html><body style='width: 230px;'>"+"알람창"+"</body></html>";
    static JLabel messageLabel = new JLabel(messageHtml);

    public void setNoticeView(){
        ImageIcon balloonIcon = VF.setImageIconSize("src/images/narrative.png", 30, 30);
        JLabel iconLabel = new JLabel(balloonIcon);
        messagePanel.add(messageLabel, "Center");
        messagePanel.add(iconLabel,"West");
        messagePanel.setBorder(new EmptyBorder(5,10,5,10));

        Border border =  BorderFactory.createLineBorder(namsek, 4);
        noticePanel.setBorder(border);
        noticePanel.add(messagePanel, "Center");
        chattingViewPanel.setMaximumSize(new Dimension(300, 120));
        chattingViewPanel.setPreferredSize(new Dimension(300, 120));
        chattingViewPanel.add(noticePanel, "wrap, gapleft 50");
    }
    public void setNoticeText(String msg){
        messageLabel.setText(msg);
    }

}
