import javax.swing.*;
import java.awt.*;
import java.time.Year;

public class FormGUITemplate extends BaseGUI{
    JPanel mainPanel;
    Font fontBigger = new Font("Malgun Gothic", Font.PLAIN, 20);
    Font fontSmaller = new Font("Malgun Gothic", Font.PLAIN, 18);

    void createCustomGUI() {
        // Move this part to new BaseGUI function/to createBaseGUI function
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, frameHeight/10);
        mainPanel.add(logoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/20)));


        Integer[] days = new Integer[31];
        for (int i=0; i < days.length; i++) {
            days[i] = i+1;
        }
        Integer[] months = new Integer[12];
        for (int i=0; i < months.length; i++) {
            months[i] = i+1;
        }

        int baseYear = Year.now().getValue();
        Integer[] years = new Integer[110];
        for (int i=0; i < years.length; i++) {
            years[i] = baseYear-i;
        }

        int nrOfFields = 11;
        String[] fieldLabels = {"Name", "Surname", "Date of birth", "Gender", "Country", "City", "Street", "Street number", "Postal Code", "Phone number", "Email address"};
        String[] fieldTypes = {"text", "text", "comboBox", "radioButton", "text", "text", "text", "text", "text", "text", "text"};
        Object[] fieldParameters = {20, 30, new Integer[][]{days, months, years}, new String[]{"Male", "Female"}, 20, 20, 30, 10, 10, 15, 30};
        assert (fieldLabels.length == nrOfFields) && (fieldTypes.length == nrOfFields) && (fieldParameters.length == nrOfFields);


        int fieldHeight = frameHeight/22;

        for (int i = 0; i < nrOfFields; i++){

            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.LINE_AXIS));
            fieldPanel.setBackground(bgColor);
            fieldPanel.setPreferredSize(new Dimension(frameWidth, fieldHeight));
            fieldPanel.setMaximumSize(new Dimension(frameWidth, fieldHeight));
            mainPanel.add(fieldPanel);
            fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            JLabel fieldLabel = new JLabel(fieldLabels[i]);
            fieldLabel.setFont(fontBigger);
            fieldPanel.add(fieldLabel);
            fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            if (fieldTypes[i].equals("text")){
                JTextField inputField = new JTextField();
                inputField.setFont(fontSmaller);
                inputField.setPreferredSize(new Dimension(frameWidth/5, fieldHeight));
                inputField.setMaximumSize(new Dimension(frameWidth/5, fieldHeight));
                fieldPanel.add(inputField);

            } else if (fieldTypes[i].equals("radioButton")) {
                ButtonGroup radioButtonGroup = new ButtonGroup();
                String[] radioBtnOptions = (String[]) fieldParameters[i];
                for (String option : radioBtnOptions) {
                    JRadioButton optionBtn = new JRadioButton(option);
                    optionBtn.setFont(fontSmaller);
                    optionBtn.setSelected(false);
                    optionBtn.setPreferredSize(new Dimension(100, fieldHeight));
                    optionBtn.setMaximumSize(new Dimension(100, fieldHeight));
                    fieldPanel.add(optionBtn);
                    fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/40,0)));
                    radioButtonGroup.add(optionBtn);
                }
            } else if (fieldTypes[i].equals("comboBox")) {
                Integer[][] comboBoxesData = (Integer[][])fieldParameters[i];
                for ( Integer[] data: comboBoxesData) {

                    JComboBox comboBox = new JComboBox(data);
                    comboBox.setFont(fontSmaller);

                    String longestEl = "";
                    for (Integer el : data){
                        String elToString = String.valueOf(el);
                        if (elToString.length() > longestEl.length()) {
                            longestEl = elToString;
                        }
                    }
                    int longestElWidth = comboBox.getFontMetrics(fontSmaller).stringWidth(longestEl);
                    comboBox.setPreferredSize(new Dimension(longestElWidth+50, fieldHeight));
                    comboBox.setMaximumSize(new Dimension(longestElWidth+50, fieldHeight));

                    fieldPanel.add(comboBox);
                    fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/40,0)));
                }
            }

            mainPanel.add(Box.createVerticalGlue());

        }

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


class FormTextField extends JTextField {

    public FormTextField(int frameWidth, int fieldHeight) {
        setFont(new Font("Malgun Gothic", Font.PLAIN, 18));
        setPreferredSize(new Dimension(frameWidth/5, fieldHeight));
        setMaximumSize(new Dimension(frameWidth/5, fieldHeight));
    }

}