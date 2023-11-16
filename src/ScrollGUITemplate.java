import javax.swing.*;
import java.awt.*;

public class ScrollGUITemplate extends BaseGUI{
    JPanel mainPanel, scrollPanel;

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

            JPanel offerPanel = new JPanel();
            offerPanel.setPreferredSize(new Dimension(frameWidth, frameHeight/5));
            offerPanel.setMaximumSize(new Dimension(frameWidth, frameHeight/5));

            JPanel offerInfo = new JPanel();
            offerInfo.setBackground(neutralColor);
            offerInfo.setPreferredSize(new Dimension(frameWidth/3, frameHeight/5));
            offerInfo.setMaximumSize(new Dimension(frameWidth/3, frameHeight/5));
            offerPanel.add(offerInfo);

            RoundedButton seeOfferBtn = new RoundedButton("See offer "+ i, frameHeight/6, frameHeight/6, secondColor, secondColorDarker);
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
