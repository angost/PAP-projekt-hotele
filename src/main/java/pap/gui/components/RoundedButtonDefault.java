package pap.gui.components;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RoundedButtonDefault extends RoundedButton{

    public RoundedButtonDefault(String text, int preferredWidth, int preferredHeight, boolean squareShaped) {
        super(text, preferredWidth, preferredHeight, Color.YELLOW, Color.YELLOW, new JLabel().getFont().deriveFont(Font.BOLD, 12f), squareShaped);
        Font fontButtons;
        try {
            File fontFile = new File(getClass().getResource("/Montserrat-Bold.ttf").getPath());
            fontButtons = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(12f);
        } catch (java.awt.FontFormatException | java.io.IOException ex) {
            fontButtons = new JLabel().getFont().deriveFont(Font.BOLD, 12f);
        }
        Color secondColor = Color.decode("#e09f3e"); Color secondColorDarker = Color.decode("#b88232");
        this.setFont(fontButtons);
        fillColor = secondColor;
        hoverColor = secondColorDarker;
    }

}
