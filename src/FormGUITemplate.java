import javax.swing.*;
import java.awt.*;

public class FormGUITemplate extends BaseGUI{
    JPanel mainPanel;

    void createCustomGUI() {
        // Move this part to new BaseGUI function/to createBaseGUI function
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, frameHeight/10);
        mainPanel.add(logoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/20)));


        int n_of_fields = 15;
        int field_height = Math.min(frameHeight/20, (frameHeight - frameHeight/10 - frameHeight/20 - 2*n_of_fields)/n_of_fields);
        for (int i = 0; i < n_of_fields; i++){

            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.LINE_AXIS));
            fieldPanel.setBackground(bgColor);
            fieldPanel.setPreferredSize(new Dimension(frameWidth, field_height));
            fieldPanel.setMaximumSize(new Dimension(frameWidth, field_height));
            mainPanel.add(fieldPanel);
            fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            JLabel fieldName = new JLabel("Name" + i);
            fieldName.setFont(new Font("Arial", Font.PLAIN, 20));
            fieldPanel.add(fieldName);
            fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            JTextField inputField = new JTextField();
            inputField.setFont(new Font("Arial", Font.PLAIN, 15));
            inputField.setPreferredSize(new Dimension(frameWidth/5, frameHeight/n_of_fields));
            inputField.setMaximumSize(new Dimension(frameWidth/5, frameHeight/n_of_fields));
            fieldPanel.add(inputField);

//            mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/20)));



        }
//        male = new JRadioButton("Male");
//        male.setFont(new Font("Arial", Font.PLAIN, 15));
//        male.setSelected(true);
//        male.setSize(75, 20);
//        male.setLocation(200, 200);
//        c.add(male);
//
//        female = new JRadioButton("Female");
//        female.setFont(new Font("Arial", Font.PLAIN, 15));
//        female.setSelected(false);
//        female.setSize(80, 20);
//        female.setLocation(275, 200);
//        c.add(female);
//
//        gengp = new ButtonGroup();
//        gengp.add(male);
//        gengp.add(female);


    }

    // make BaseGUI abstract, add createcustomgui as virtual method and createGUI with body as below
    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FormGUITemplate().createGUI();
    }

}
