package views;

import net.miginfocom.swing.MigLayout;
import views.buttons.ChattingButtons;

import javax.swing.*;
import java.awt.*;

import static views.MainView.*;


public class ChattingView {
    ViewFunctions VF = new ViewFunctions();
    ChattingButtons CB = new ChattingButtons();
    public  static JPanel chatPanel = new JPanel(new MigLayout("","[][][][][]"));
    public static JScrollPane chatScroll = new JScrollPane(chatPanel);
    JPanel chatWindow = new JPanel(new BorderLayout());


    public void setChattingView(){
        CB.setChattingButton();
//        chatPanel.setPreferredSize(new Dimension(410, 400));
        chatPanel.setBackground(namsek);
        chatScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        chatScroll.setPreferredSize(new Dimension(430,450));
        chatWindow.add(chatScroll, "Center");
        chatWindow.add(CB.myMessagePanel, "South");


        chattingViewPanel.add(chatWindow, "wrap");
    }
}
