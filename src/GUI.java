
import javax.swing.*;
import java.awt.*;

class GUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080,720);

        JMenuBar mb = new JMenuBar();
        JMenu FileMenu = new JMenu("File");
        JMenu EditMenu = new JMenu("Edit");
        JMenu HelpMenu = new JMenu("Help");
        mb.add(FileMenu); mb.add(EditMenu); mb.add(HelpMenu);
        JMenuItem NewMenuItem = new JMenuItem("New");
        JMenuItem UndoMenuItem = new JMenuItem("Undo");
        JMenuItem ContactMenuItem = new JMenuItem("Contact us");
        FileMenu.add(NewMenuItem); EditMenu.add(UndoMenuItem); HelpMenu.add(ContactMenuItem);
        frame.getContentPane().add(BorderLayout.PAGE_START, mb);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JLabel chooseUserLabel = new JLabel("Choose user type:", JLabel.CENTER);
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(chooseUserLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,30)));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        RoundedButton userButton = new RoundedButton("User", "#54e865");
        RoundedButton ownerButton = new RoundedButton("Owner", "#5390ed");

        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(userButton); buttonsPanel.add(Box.createRigidArea(new Dimension(40,0))); buttonsPanel.add(ownerButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        mainPanel.add(buttonsPanel);
        mainPanel.add(Box.createVerticalGlue());
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            SwingUtilities.updateComponentTreeUI(frame);
        } catch (Exception e) {
            ;
        }
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setVisible(true);
    }
}

class RoundedButton extends JButton {
    Color bgColor;
    public RoundedButton(String text, String hexBgColor) {
        super(text);
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