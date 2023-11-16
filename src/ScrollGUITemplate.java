import javax.swing.*;
import java.awt.*;

public class ScrollGUITemplate extends BaseGUI{
    JPanel mainPanel, scrollPanel;
    // move some new components here

    void createCustomGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, frameHeight/10);
        mainPanel.add(logoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/20)));

        scrollPanel = new JPanel();
        scrollPanel.setBackground(bgColor);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPanelEnabler = new JScrollPane(scrollPanel);
        scrollPanelEnabler.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelEnabler.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPanelEnabler.getViewport().setBackground(Color.black);
        mainPanel.add(scrollPanelEnabler);


        for (int i = 0; i<9; i++) {

            int offerHeight = frameHeight/4;

            JPanel offerPanel = new JPanel();
            offerPanel.setBackground(neutralColor);
            offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.LINE_AXIS));
            offerPanel.setPreferredSize(new Dimension(frameWidth, offerHeight));
            offerPanel.setMaximumSize(new Dimension(frameWidth, offerHeight));
            offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            JPanel offerInfo = new JPanel();
            offerInfo.setBackground(helpingColor);
            offerInfo.setPreferredSize(new Dimension(frameWidth/3, offerHeight));
            offerInfo.setMaximumSize(new Dimension(frameWidth/3, offerHeight));
            offerPanel.add(offerInfo);
            offerPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            RoundedButton seeOfferBtn = new RoundedButton("See offer "+ i, frameHeight/7, frameHeight/7, secondColor, secondColorDarker, true);
            offerPanel.add(seeOfferBtn);

            scrollPanel.add(offerPanel);
            scrollPanel.add(Box.createRigidArea(new Dimension(0,30)));

        }

    }

    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ScrollGUITemplate().createGUI();
    }

}
