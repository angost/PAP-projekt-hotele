package pap.gui;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.stream.Collectors;

import pap.db.dao.ClientDAO;
import pap.db.entities.Client;
import pap.logic.ErrorCodes;
import pap.logic.validators.*;

public abstract class FormGUITemplate extends BaseGUI{
    JPanel mainPanel;
    List<JTextField> textFields = new ArrayList<JTextField>();
    List<String> textFieldLabels = new ArrayList<String>();
    JLabel statusLabel;

    public FormGUITemplate(int userId, String userType) {
        super(userId, userType);
    }

    //przeniesc tutaj niektore componenty

    abstract String[] getFieldLabels();
    abstract String[] getFieldTypes();
    abstract Object[] getFieldParameters();

    void createCustomGUI() {
        // Move this part to new main.java.pap.gui.BaseGUI function/to createBaseGUI function
        // podzielic te funkcje na czesci
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(bgColor);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

        LogoPanel logoPanel = new LogoPanel(logoColor, frameHeight, frameWidth, frameHeight/10);
        mainPanel.add(logoPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/20)));

        int contentPanelSize = frameHeight - frameHeight/10 - frameHeight/20 - frameHeight/20;
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.LINE_AXIS));
        contentPanel.setBackground(bgColor);
        contentPanel.setPreferredSize(new Dimension(frameWidth, contentPanelSize));
        contentPanel.setMaximumSize(new Dimension(frameWidth, contentPanelSize));
        mainPanel.add(contentPanel);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.PAGE_AXIS));
        fieldsPanel.setBackground(bgColor);
        fieldsPanel.setPreferredSize(new Dimension(frameWidth*2/3, contentPanelSize));
        fieldsPanel.setMaximumSize(new Dimension(frameWidth*2/3, contentPanelSize));
        contentPanel.add(fieldsPanel);

        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.PAGE_AXIS));
        registerPanel.setBackground(bgColor);
        registerPanel.setPreferredSize(new Dimension(frameWidth/3, contentPanelSize));
        registerPanel.setMaximumSize(new Dimension(frameWidth/3, contentPanelSize));
        contentPanel.add(registerPanel);

        int nrOfFields;
        String[] fieldLabels = getFieldLabels();
        String[] fieldTypes = getFieldTypes();
        Object[] fieldParameters = getFieldParameters();

        if ((fieldLabels.length == fieldTypes.length) && (fieldLabels.length == fieldParameters.length) && (fieldTypes.length == fieldParameters.length)) {
            nrOfFields = fieldLabels.length;
        } else {
            nrOfFields = 0; // Throw exception?
            JOptionPane.showMessageDialog(frame, "Nr of fields inconsistent", "Error!", JOptionPane.ERROR_MESSAGE);
        }

        int fieldHeight = frameHeight/22;
        JLabel longestLabel = new JLabel("");
        longestLabel.setFont(fontBigger);
        int longestLabelWidth = 0;
        for (String labelText : fieldLabels) {
            int labelWidth = longestLabel.getFontMetrics(fontBigger).stringWidth(String.valueOf(labelText));
            if (labelWidth > longestLabelWidth) {
                longestLabelWidth = labelWidth;
            }
        }
        int minLabelInputGap = 20;
        int characterWidth = longestLabel.getFontMetrics(fontBigger).stringWidth(String.valueOf("m"));

        // Creating all the form fields according to types
        for (int i = 0; i < nrOfFields; i++){

            JPanel fieldPanel = new JPanel();
            fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.LINE_AXIS));
            fieldPanel.setBackground(bgColor);
            fieldPanel.setPreferredSize(new Dimension(frameWidth, fieldHeight));
            fieldPanel.setMaximumSize(new Dimension(frameWidth, fieldHeight));
            fieldsPanel.add(fieldPanel);
            fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/20,0)));

            JLabel fieldLabel = new JLabel(fieldLabels[i]);
            fieldLabel.setFont(fontBigger);
            fieldPanel.add(fieldLabel);
            int labelWidth = fieldLabel.getFontMetrics(fontBigger).stringWidth(String.valueOf(fieldLabel.getText()));
            int labelInputGap = longestLabelWidth - labelWidth + minLabelInputGap;
            fieldPanel.add(Box.createRigidArea(new Dimension(labelInputGap,0)));

            if (fieldTypes[i].equals("text")){
                JTextField inputField = new JTextField();
                inputField.setFont(fontMiddle);
                inputField.setPreferredSize(new Dimension((Integer) fieldParameters[i]*characterWidth, fieldHeight));
                inputField.setMaximumSize(new Dimension((Integer) fieldParameters[i]*characterWidth, fieldHeight));
                fieldPanel.add(inputField);
                textFields.add(inputField);
                textFieldLabels.add(fieldLabels[i]);

            } else if (fieldTypes[i].startsWith("radioButton")) {
                Object[] radioBtnOptions;
                if (fieldTypes[i].equals("radioButtonInteger")) {
                    radioBtnOptions = (Integer[]) fieldParameters[i];
                } else {
                    radioBtnOptions = (String[]) fieldParameters[i];
                }
                ButtonGroup radioButtonGroup = new ButtonGroup();
                for (var option : radioBtnOptions) {
                    JRadioButton optionBtn = new JRadioButton(String.valueOf(option));
                    optionBtn.setFont(fontMiddle);
                    optionBtn.setSelected(false);
                    int optionWidth = optionBtn.getFontMetrics(fontMiddle).stringWidth(String.valueOf(option));
                    optionBtn.setPreferredSize(new Dimension(optionWidth + 25, fieldHeight));
                    optionBtn.setMaximumSize(new Dimension(optionWidth + 25, fieldHeight));
                    fieldPanel.add(optionBtn);
                    fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/40,0)));
                    radioButtonGroup.add(optionBtn);
                }

            } else if (fieldTypes[i].startsWith("comboBox")) {
                // comboBoxesData may all be Integer or all String
                Object[][] comboBoxesData;
                if (fieldTypes[i].equals("comboBoxInteger")) {
                    comboBoxesData = (Integer[][]) fieldParameters[i];
                } else {
                    comboBoxesData = (String[][]) fieldParameters[i];
                }
                // Create comboBox from data
                for ( var data: comboBoxesData) {
                    JComboBox comboBox = new JComboBox(data);
                    comboBox.setFont(fontMiddle);

                    String longestEl = "";
                    for (var el : data) {
                        String elToString = String.valueOf(el);
                        if (elToString.length() > longestEl.length()) {
                            longestEl = elToString;
                        }
                    }
                    int longestElWidth = comboBox.getFontMetrics(fontMiddle).stringWidth(longestEl);
                    comboBox.setPreferredSize(new Dimension(longestElWidth+50, fieldHeight));
                    comboBox.setMaximumSize(new Dimension(longestElWidth+50, fieldHeight));
                    fieldPanel.add(comboBox);
                    fieldPanel.add(Box.createRigidArea(new Dimension(frameWidth/40,0)));
                }
            }
            fieldsPanel.add(Box.createVerticalGlue());
        }

        registerPanel.add(Box.createVerticalGlue());
        statusLabel = new JLabel("<html>Insert your data<br/></html>", JLabel.LEFT);
        statusLabel.setFont(fontSmaller);
        statusLabel.setForeground(Color.decode("#7a7373"));
//        statusLabel.setPreferredSize(new Dimension(frameWidth/4, contentPanelSize/5));
//        statusLabel.setMaximumSize(new Dimension(frameWidth/4, contentPanelSize/5));
        registerPanel.add(statusLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/40)));

        RoundedButton registerButton = new RoundedButton("Register", frameWidth*3/20, frameHeight/10, secondColor, secondColorDarker, fontButtons, false);
        registerButton.addActionListener(e->registerBtnClickedAction());
        registerPanel.add(registerButton);
        registerPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/20)));

        UndoPanel undoPanel = new UndoPanel(mainPanel, frameWidth, frameHeight/20, bgColor, e->undoBtnClickedAction());
        mainPanel.add(Box.createRigidArea(new Dimension(0,frameHeight/40)));
    }

    // make main.java.pap.gui.BaseGUI abstract, add createcustomgui as virtual method and createGUI with body as below
    void createGUI(){
        super.createBaseGUI();
        createCustomGUI();
        frame.setVisible(true);
    }

    void undoBtnClickedAction(){
        new ChooseAccountTypeGUI(-1, "None").createGUI();
        frame.setVisible(false);
    }

    void registerBtnClickedAction(){
        // Get values
        HashMap<String, String> textFieldsValues = getTextFieldValues();
        // Validate values
        List <Integer> errorCodes = validateCredentials(textFieldsValues);
        // Create user and open Home Page
        if (errorCodes.isEmpty()) {
            createUser(textFieldsValues);
            JOptionPane.showMessageDialog(frame, "Success! Created user!");
            new LogInGUI(-1, "None").createGUI();
            frame.setVisible(false);
        }
        // Errors occured, display them on screen
        else {
            String statusLabelText = "<html>"; String spacingCharacter = "<br/>";
            if (errorCodes.size() > 10) spacingCharacter = " | ";
            for (Integer code : errorCodes) {
                statusLabelText = statusLabelText + ErrorCodes.getErrorDescription(code) + spacingCharacter;
            }
            statusLabelText = statusLabelText + "</html>";
            statusLabel.setText(statusLabelText);
            statusLabel.setForeground(logoColor);
        }
    }

    HashMap<String, String> getTextFieldValues(){
        int nrOfFields = textFields.size();
        HashMap<String, String> textFieldsValues = new HashMap<String, String>();

        for (int i = 0; i < nrOfFields; i++){
            textFieldsValues.put(textFieldLabels.get(i), textFields.get(i).getText());
        }
        return textFieldsValues;
    }

    abstract List <Integer> validateCredentials(HashMap<String, String> textFieldsValues);

    abstract void createUser(HashMap<String, String> textFieldsValues);
}
