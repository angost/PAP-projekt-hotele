

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


class GUI {

    JFrame frame;
    JMenuBar mb;
    JMenu FileMenu, EditMenu, HelpMenu;
    JMenuItem NewMenuItem, UndoMenuItem, ContactMenuItem;
    RoundedButton userButton;
    RoundedButton ownerButton;
    JPanel mainPanel, buttonsPanel, logoPanel, textPanel;
    JLabel chooseUserLabel;
    BufferedImage logoImage;

    int frameWidth = 1080; int frameHeight = 720;

    void createBaseGUI(){
        frame = new JFrame("Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            ;
        }
    }

    void createMenus(){
        mb = new JMenuBar();
        FileMenu = new JMenu("File");
        EditMenu = new JMenu("Edit");
        HelpMenu = new JMenu("Help");
        mb.add(FileMenu); mb.add(EditMenu); mb.add(HelpMenu);
        NewMenuItem = new JMenuItem("New");
        UndoMenuItem = new JMenuItem("Undo");
        ContactMenuItem = new JMenuItem("Contact us");
        FileMenu.add(NewMenuItem); EditMenu.add(UndoMenuItem); HelpMenu.add(ContactMenuItem);
        frame.getContentPane().add(BorderLayout.PAGE_START, mb);
    }

    void createCustomGUI(){
        Color bgColor = Color.decode("#fff3b0");
        Color logoColor = Color.decode("#9e2a2b");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        logoPanel = new JPanel();
        logoPanel.setLayout(new BoxLayout(logoPanel, BoxLayout.LINE_AXIS));
        logoPanel.setBackground(logoColor);
        logoPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, frameHeight/5));
        logoPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, frameHeight/5));
        try {
            logoImage = ImageIO.read(new File("res/logo_name_mixed.png"));
            Image scaledLogoImage = logoImage.getScaledInstance((int)(frameHeight/7*3.6), frameHeight/7, Image.SCALE_SMOOTH);
            JLabel logoImageLabel = new JLabel(new ImageIcon(scaledLogoImage));
            logoPanel.add(Box.createRigidArea(new Dimension(20,0)));
            logoPanel.add(logoImageLabel);
            logoPanel.add(Box.createHorizontalGlue());
        } catch (Exception e) {
            ;
        }
        mainPanel.add(logoPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/20)));
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.LINE_AXIS));
        textPanel.setBackground(bgColor);
        chooseUserLabel = new JLabel("Choose user type:", JLabel.CENTER);
        chooseUserLabel.setFont(chooseUserLabel.getFont().deriveFont(Font.BOLD, 16));
        textPanel.add(Box.createHorizontalGlue()); textPanel.add(chooseUserLabel); textPanel.add(Box.createHorizontalGlue());
        mainPanel.add(textPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,40)));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setBackground(bgColor);
        userButton = new RoundedButton("User", "#e09f3e", frameWidth*3/20, frameHeight/10);
        ownerButton = new RoundedButton("Owner", "#e09f3e", frameWidth*3/20, frameHeight/10);
        userButton.addActionListener(e->createUserAccountAction());
        ownerButton.addActionListener(e->createOwnerAccountAction());
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(userButton); buttonsPanel.add(Box.createRigidArea(new Dimension(userButton.preferredWidth/5,0))); buttonsPanel.add(ownerButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight*3/10)));

    }

    void createGUI(){
        createBaseGUI();
        createMenus();
        createCustomGUI();
        frame.setVisible(true);
    }

    void createUserAccountAction() {
        JOptionPane.showMessageDialog(frame, "User account created");
    }

    void createOwnerAccountAction() {
        JOptionPane.showMessageDialog(frame, "Owner account created");
    }

    public static void main(String[] args) {
        new GUI().createGUI();
    }
}



class RoundedButton extends JButton {
    Color bgColor;
    int preferredWidth, preferredHeight;

    public RoundedButton(String text, String hexBgColor, int preferredWidth, int preferredHeight) {
        super(text);
        this.setFont(this.getFont().deriveFont(Font.BOLD));
        this.bgColor = Color.decode(hexBgColor);
        setContentAreaFilled(false); // Make the button transparent
        setFocusPainted(false); // Remove the focus border
        setBorderPainted(false); // Make border transparent
        setPreferredSize(new Dimension(preferredWidth, preferredHeight));
        setMaximumSize(new Dimension(preferredWidth, preferredHeight));
        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray); // Change the background color when the button is pressed
        } else {
            g.setColor(this.bgColor);
        }

        // Draw the rounded button
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        super.paintComponent(g);
    }
}