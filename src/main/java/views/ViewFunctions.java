package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static views.MainView.*;


public class ViewFunctions {
    //이미지 아이콘 사이즈 조절 및 생성 메서드 (기본값 50, 50)
    public ImageIcon setImageIconSize(String filePath, int width, int height){
        ImageIcon imageIcon = new ImageIcon(filePath);
        if (imageIcon.getIconWidth() == -1) {
            imageIcon = new ImageIcon("src/images/anonymous.png");
        }
        return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
    public ImageIcon setImageIconSize(String filePath){
        ImageIcon imageIcon = new ImageIcon(filePath);
        return new ImageIcon(imageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
    }
    //튼 디자인 설정 메서드
    public void setNavButton(JButton button){
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setMargin(new Insets(38,30,15,15));

    }

    public void setTextFeilds(JTextField field){
        field.setBackground(Color.white);
        field.setPreferredSize(new Dimension(220, 30));
    }
    public void setTextFeilds(JTextField field, int width, int height){
        field.setBackground(Color.white);
        field.setPreferredSize(new Dimension(width, height));
    }
    public void setTextFeilds(JPasswordField field){
        field.setBackground(Color.white);
        field.setPreferredSize(new Dimension(220, 30));
    }
    public JPanel setMessageBalloon(String user, String text){
        String mesaageHtml = "<html><body style='width: 170px;'>"+text+"</body></html>";
        JPanel messagePanel = new JPanel(new BorderLayout());

        JLabel messageLabel = new JLabel(mesaageHtml);

        ImageIcon balloonIcon = setImageIconSize("src/images/"+user+".png", 30, 30);
        JLabel iconLabel = new JLabel(balloonIcon);

        messageLabel.setPreferredSize(new Dimension(240,30));
        messagePanel.setPreferredSize(new Dimension(250, 30));
        messagePanel.setBorder(new EmptyBorder(0,2,0,2));
        messagePanel.add(messageLabel, "West");
        messagePanel.add(iconLabel,"East");
        return messagePanel;
    }

    public JPanel setNoticeBalloon(String user, String text){
        String messageHtml = "<html><body style='width: 230px;'>"+text+"</body></html>";
        JPanel messagePanel = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel(messageHtml);
        ImageIcon balloonIcon = setImageIconSize("src/images/"+user+".png", 30, 30);
        JLabel iconLabel = new JLabel(balloonIcon);

        messagePanel.add(messageLabel, "Center");
        messagePanel.add(iconLabel,"West");
        return messagePanel;
    }
    public void setMessageDialog(String text){
        JOptionPane.showMessageDialog(mainCenterPanel,text, "mesaage",JOptionPane.INFORMATION_MESSAGE);
        if(text.equals("회원가입에 성공했습니다.")){
            mainCenterLayout.show(mainCenterPanel, "logInView");
        }
    }




}
