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
    Color bgColor = Color.decode("#e3e3e3");
    Color logoColor = Color.decode("#9e2a2b");
    Color secondColor = Color.decode("#e09f3e"); Color secondColorDarker = Color.decode("#b88232");
    Color neutralBlue = Color.decode("#d6d9df"); Color neutralGray = Color.decode("#a89f9f");
    Color statusNeutral = Color.decode("#7a7373"); Color statusWrong = logoColor;
    Font fontBigger, fontMiddle, fontSmaller, fontButtons, fontMiddleBold;
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
            fontMiddleBold = font.deriveFont(18f);

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
    Color fillColor, hoverColor;
    int preferredWidth, preferredHeight;
    boolean squareShaped;

    public RoundedButton(String text, int preferredWidth, int preferredHeight, Color fillColor, Color hoverColor, Font font, boolean squareShaped){//, String hexBorderColor) {
        super(text);
        this.setFont(font);
        this.fillColor = fillColor;
        this.hoverColor = hoverColor;
        this.squareShaped = squareShaped;
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

        if (getModel().isArmed() || getModel().isRollover()) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(fillColor);
        }
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

class TextIconButton extends RoundedButton {
    public TextIconButton(String text, int preferredWidth, int preferredHeight, Color fillColor, Color hoverColor, Font font, boolean squareShaped, String imgPath, int imgSize) {
        super("<html><b>" + text + "</b></html>", preferredWidth, preferredHeight, fillColor, hoverColor, font, squareShaped);
        try {
            Image img = ImageIO.read(new File(getClass().getResource(imgPath).getPath()));
            img = img.getScaledInstance(imgSize, imgSize, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(img));
        } catch (Exception e) {
            ;
        }
//        setMargin(new Insets(1,1,1,1));
    }

//    @Override
//    public void doLayout()
//    {
//        super.doLayout();
//        int gap = 10;
//        gap = Math.max(gap, UIManager.getInt("Button.iconTextGap"));
//        setIconTextGap(gap);
//    }
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
    public UndoPanel(int frameWidth, int btnHeight, Color bgColor, ActionListener actionListener, String pageName, Font fontMiddle) {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBackground(bgColor);
        setPreferredSize(new Dimension(frameWidth, btnHeight));
        setMaximumSize(new Dimension(frameWidth, btnHeight));
        UndoButton undoBtn = new UndoButton(btnHeight, btnHeight, btnHeight, btnHeight);
        undoBtn.addActionListener(actionListener);
        add(Box.createRigidArea(new Dimension(btnHeight/2, 0)));
        add(undoBtn);

        JLabel pageNameLabel = new JLabel(pageName, JLabel.CENTER);
        pageNameLabel.setFont(fontMiddle);
        add(Box.createHorizontalGlue()); add(pageNameLabel); add(Box.createRigidArea(new Dimension(20, 0)));
    }
}


