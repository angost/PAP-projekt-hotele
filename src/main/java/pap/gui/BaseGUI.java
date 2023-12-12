package pap.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


public class BaseGUI {
    JFrame frame;
    JMenuBar mb;
    JMenu FileMenu, EditMenu, HelpMenu;
    JMenuItem NewMenuItem, UndoMenuItem, ContactMenuItem;
    int frameWidth = 1080; int frameHeight = 720;
    Color bgColor = Color.decode("#e3e3e3"); Color neutralColor = Color.decode("#d6d9df");
    Color helpingColor = Color.decode("#a89f9f");
    Color secondColor = Color.decode("#e09f3e"); Color secondColorDarker = Color.decode("#b88232");
    Color logoColor = Color.decode("#9e2a2b");
    Font fontBigger, fontMiddle, fontSmaller, fontButtons;
    int userId = -1; String userType = "None";

    void createFrame(){
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

    void createBaseGUI(){
        setFonts();
        createFrame();
        createMenus();
//        frame.setVisible(true);
    }

    void setFonts(){
        try {
            File fontFile = new File(getClass().getResource("/Montserrat-Regular.ttf").getPath());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontBigger = font.deriveFont(20f);
            fontMiddle = font.deriveFont(18f);
            fontSmaller = font.deriveFont(16f);
            fontFile = new File(getClass().getResource("/Montserrat-Bold.ttf").getPath());
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            fontButtons = font.deriveFont(12f);

        } catch (java.awt.FontFormatException | java.io.IOException ex) {
            fontBigger = new JLabel().getFont().deriveFont(20f);
            fontMiddle = new JLabel().getFont().deriveFont(18f);
            fontSmaller = new JLabel().getFont().deriveFont(16f);
            fontButtons = new JLabel().getFont().deriveFont(Font.BOLD, 12f);
        }
    }

    public BaseGUI(int userId, String userType){
        this.userId = userId;
        this.userType = userType;
    }

    public static void main(String[] args) {
        new BaseGUI(-1, "None").createBaseGUI();
    }
}


class RoundedButton extends JButton {
    Color fillColor, hoverColor; //, borderColor;
    int preferredWidth, preferredHeight;
    boolean squareShaped;
//    int borderSize = 3;

    public RoundedButton(String text, int preferredWidth, int preferredHeight, Color fillColor, Color hoverColor, Font font, boolean squareShaped){//, String hexBorderColor) {
        super(text);
        this.setFont(font);
        this.fillColor = fillColor;
        this.hoverColor = hoverColor;
        this.squareShaped = squareShaped;
//        this.borderColor = Color.decode(hexBorderColor);
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
        // Draw the rounded button
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2.setColor(borderColor);
//        g2.fillRoundRect(0, 0, getWidth(), getHeight(), preferredWidth/2, preferredHeight);

        if (getModel().isArmed() || getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(fillColor);
        }
//        g2.fillRoundRect(borderSize, borderSize, getWidth()-borderSize*2, getHeight()-borderSize*2, preferredWidth/2, preferredHeight);
        if (squareShaped) {
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), preferredWidth/2, preferredHeight/2);
        } else {
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), preferredWidth/2, preferredHeight);
        }

        super.paintComponent(g);
    }
}

class LogoPanel extends JPanel {
    int frameHeight, imgWidth, imgHeight;
    BufferedImage logoImage;
    Image scaledLogoImage;
    JLabel logoImageLabel;

    public LogoPanel(Color logoColor, int frameHeight, int panelWidth, int panelHeight) {
        this.frameHeight = frameHeight;
        this.imgWidth = (int)(panelHeight*5/7*3.6);
        this.imgHeight = (int)(panelHeight*5/7);
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(logoColor);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setMaximumSize(new Dimension(panelWidth, panelHeight));
        addLogoImage();
    }

    void addLogoImage() {
        try {
            logoImage = ImageIO.read(new File(getClass().getResource("/logo_name_mixed.png").getPath()));
            scaledLogoImage = logoImage.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            logoImageLabel = new JLabel(new ImageIcon(scaledLogoImage));
            this.add(Box.createRigidArea(new Dimension(20,0)));
            this.add(logoImageLabel);
            this.add(Box.createHorizontalGlue());
        } catch (Exception e) {
            ;
        }
    }
}

class TwoImgsButton extends JButton{

    Image baseImg, secondImg;
    String state;
    boolean imgUploadSuccess = false;

    public TwoImgsButton(int buttonWidth, int buttonHeight, int imgWidth, int imgHeight, String baseImgPath, String secondImgPath) {
        setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        try {
            setContentAreaFilled(false); // Make the button transparent
            Image baseImg = ImageIO.read(new File(getClass().getResource(baseImgPath).getPath()));
            baseImg = baseImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            Image secondImg = ImageIO.read(new File(getClass().getResource(secondImgPath).getPath()));
            secondImg = secondImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(baseImg));
            this.baseImg = baseImg;
            this.secondImg = secondImg;
            imgUploadSuccess = true;
            state = "base_state";
        } catch (Exception ex) {
            setContentAreaFilled(true);
        }
    }

    public void changeState() {
        if (state.equals("base_state")) {
            setIcon(new ImageIcon(secondImg));
            state = "second_state";
        } else {
            setIcon(new ImageIcon(baseImg));
            state = "base_state";
        }
    }
}

class UndoButton extends JButton{
    public UndoButton(int buttonWidth, int buttonHeight, int imgWidth, int imgHeight) {
        setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        try {
            setContentAreaFilled(false); // Make the button transparent
            Image baseImg = ImageIO.read(new File(getClass().getResource("/undo_img.png").getPath()));
            baseImg = baseImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(baseImg));
        } catch (Exception ex) {
            setContentAreaFilled(true);
        }
    }
}

class LogOutButton extends JButton{
    public LogOutButton(int buttonWidth, int buttonHeight, int imgWidth, int imgHeight) {
        setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        try {
            setContentAreaFilled(false); // Make the button transparent
            Image baseImg = ImageIO.read(new File(getClass().getResource("/logout_img_64.png").getPath()));
            baseImg = baseImg.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(baseImg));
        } catch (Exception ex) {
            setContentAreaFilled(true);
        }
    }
}


class UndoPanel extends JPanel{
    public UndoPanel(JPanel mainPanel, int frameWidth, int btnHeight, Color bgColor, ActionListener actionListener) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(bgColor);
        setPreferredSize(new Dimension(frameWidth, btnHeight));
        setMaximumSize(new Dimension(frameWidth, btnHeight));
        UndoButton undoBtn = new UndoButton(btnHeight, btnHeight, btnHeight, btnHeight);
        undoBtn.addActionListener(actionListener);
        add(Box.createRigidArea(new Dimension(btnHeight/2, 0)));
        add(undoBtn);
        mainPanel.add(this);
    }
}


