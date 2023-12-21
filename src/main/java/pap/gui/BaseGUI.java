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
    Font fontBigger, fontMiddle, fontSmaller, fontButtons, fontBiggerBold, fontMiddleBold, fontSmallerBold;
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
            fontBiggerBold = font.deriveFont(20f);
            fontMiddleBold = font.deriveFont(18f);
            fontSmallerBold = font.deriveFont(16f);

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
