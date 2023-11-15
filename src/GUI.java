
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// jedna klasa - mainGUI, ze startowaniem okienka, custom komponentami (rounded button) itd
// klasa z typem okienka - wpisywanie danych - przy tworzeniu uzytkownika/dodawaniu oferty; scrollowania - przegladanie ofert, przegladanie wlasnych hoteli, przegladanie historii

class GUI {

    JFrame frame;
    JMenuBar mb;
    JMenu FileMenu, EditMenu, HelpMenu;
    JMenuItem NewMenuItem, UndoMenuItem, ContactMenuItem;
    RoundedButton userButton;
    RoundedButton ownerButton;
    JPanel mainPanel, buttonsPanel;
    JLabel chooseUserLabel;

    void createBaseGUI(){
        frame = new JFrame("Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080,720);

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

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        chooseUserLabel = new JLabel("Choose user type:", JLabel.CENTER);
        chooseUserLabel.setFont(chooseUserLabel.getFont().deriveFont(Font.BOLD));
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(chooseUserLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,30)));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        userButton = new RoundedButton("User", "#54e865");
        ownerButton = new RoundedButton("Owner", "#5390ed");
        userButton.addActionListener(e->createUserAccountAction());
        ownerButton.addActionListener(e->createOwnerAccountAction());
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(userButton); buttonsPanel.add(Box.createRigidArea(new Dimension(40,0))); buttonsPanel.add(ownerButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createVerticalGlue());

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
    public RoundedButton(String text, String hexBgColor) {
        super(text);
        this.setFont(this.getFont().deriveFont(Font.BOLD));
        this.bgColor = Color.decode(hexBgColor);
        setContentAreaFilled(false); // Make the button transparent
        setFocusPainted(false); // Remove the focus border
        setBorderPainted(false); // Make border transparent
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