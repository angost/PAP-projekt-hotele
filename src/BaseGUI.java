import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


public class BaseGUI {
    JFrame frame;
    JMenuBar mb;
    JMenu FileMenu, EditMenu, HelpMenu;
    JMenuItem NewMenuItem, UndoMenuItem, ContactMenuItem;
    int frameWidth = 1080; int frameHeight = 720;

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
        createFrame();
        createMenus();
//        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BaseGUI().createBaseGUI();
    }
}


class RoundedButton extends JButton {
    Color fillColor, hoverColor; //, borderColor;
    int preferredWidth, preferredHeight;
//    int borderSize = 3;

    public RoundedButton(String text, int preferredWidth, int preferredHeight, String hexFillColor, String hexHoverColor){//, String hexBorderColor) {
        super(text);
        this.setFont(this.getFont().deriveFont(Font.BOLD));
        this.fillColor = Color.decode(hexFillColor);
        this.hoverColor = Color.decode(hexHoverColor);
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
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), preferredWidth/2, preferredHeight);

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
            logoImage = ImageIO.read(new File("res/logo_name_mixed.png"));
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
