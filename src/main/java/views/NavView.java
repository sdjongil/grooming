package views;

import views.buttons.NavButtons;

import java.awt.*;

import static views.MainView.*;

public class NavView {
    NavButtons navButtons = new NavButtons();
    ViewFunctions VF = new ViewFunctions();
    public void setNavView(){

        navPanel.setBackground(orange);
        navPanel.setPreferredSize(new Dimension(120, 900));

        navButtons.setNavButton();

    }

}
