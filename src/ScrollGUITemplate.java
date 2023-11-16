import javax.swing.*;
import java.awt.*;

public class ScrollGUITemplate extends BaseGUI{
    JPanel mainPanel, scrollPanel;

    void createCustomGUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
//        mainPanel.setBackground(Color.green);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        scrollPanel = new JPanel();
        scrollPanel.setBackground(Color.green);
        scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.PAGE_AXIS));

        JScrollPane scrollPanelEnabler = new JScrollPane(scrollPanel);
        scrollPanelEnabler.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPanelEnabler.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        scrollPanelEnabler.getViewport().setBackground(Color.black);

        mainPanel.add(scrollPanelEnabler);
        mainPanel.add(new LogoPanel(Color.BLUE, frameHeight, frameWidth,frameHeight/5));

        for (int i = 0; i<9; i++) {
            RoundedButton btn = new RoundedButton("User "+ i, frameWidth*3/20, frameHeight/6, secondColor, secondColorDarker);
            LogoPanel logoPanel = new LogoPanel(Color.BLUE, frameHeight, frameWidth, frameHeight/5);
            scrollPanel.add(logoPanel);
            scrollPanel.add(Box.createRigidArea(new Dimension(0,30)));
//            scrollPanel.add(btn);
//            scrollPanel.add(Box.createRigidArea(new Dimension(0,30)));
//            JPanel panel1 = new JPanel();
//            panel1.setBackground(Color.BLUE);
//            panel1.setPreferredSize(new Dimension(frameWidth, frameHeight/5));
//            panel1.setMaximumSize(new Dimension(frameWidth, frameHeight/5));
//            scrollPanel.add(panel1);

        }

//        JTextArea textArea = new JTextArea(5, 5);

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
