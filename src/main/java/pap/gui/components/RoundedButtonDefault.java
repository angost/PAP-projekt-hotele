package pap.gui.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RoundedButtonDefault extends RoundedButton{

    public RoundedButtonDefault(String text, int preferredWidth, int preferredHeight, boolean squareShaped, boolean faded) {
        super(text, preferredWidth, preferredHeight, Color.YELLOW, Color.YELLOW, new JLabel().getFont().deriveFont(Font.BOLD, 12f), squareShaped);
        Font fontButtons;
        try {
            File fontFile = new File(getClass().getResource("/Montserrat-Bold.ttf").getPath());
            fontButtons = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12f);
        } catch (java.awt.FontFormatException | java.io.IOException ex) {
            fontButtons = new JLabel().getFont().deriveFont(Font.BOLD, 12f);
        }
        this.setFont(fontButtons);
        if (!faded) {
            fillColor = Color.decode("#e09f3e");
            hoverColor = Color.decode("#b88232");
        } else {
            fillColor = Color.decode("#e1b87b");;
            hoverColor = Color.decode("#e09f3e");
        }
    }
}
