package pap.gui.components;

import lombok.Getter;
import pap.db.entities.PaymentMethod;
import pap.gui.FormGUITemplate;

import pap.db.dao.PaymentMethodDAO;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.io.File;
import java.util.HashMap;
import java.util.Calendar;


public class MakeReservationPanel extends JPanel {
    int panelWidth, panelHeight, userId;
    private JComboBox<Integer> startYearComboBox;
    private JComboBox<Integer> startMonthComboBox;
    private JComboBox<Integer> startDayComboBox;

    private JComboBox<Integer> endYearComboBox;
    private JComboBox<Integer> endMonthComboBox;
    private JComboBox<Integer> endDayComboBox;
    Color bgColor; Font fontBigger, fontMiddle, fontMiddleBold, fontSmaller, fontSmallerBold;
    LocalDate startDate, endDate;

    public MakeReservationPanel(Color bgColor, Font fontBigger, Font fontBiggerBold, Font fontMiddle, Font fontMiddleBold, Font fontSmaller, Font fontSmallerBold, int panelWidth, int panelHeight,
                             HashMap<String, String> offerInfo, HashMap<String, String> reservationInfo, Integer userId) {

        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.bgColor = bgColor;
        this.fontBigger = fontBigger;
        this.fontMiddle = fontMiddle;
        this.fontMiddleBold = fontMiddleBold;
        this.fontSmaller = fontSmaller;
        this.fontSmallerBold = fontSmallerBold;
        int topPanelHeight = panelHeight / 6;
        this.userId = userId;

        setBackground(bgColor);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setMaximumSize(new Dimension(panelWidth, panelHeight));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(bgColor);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
        topPanel.setPreferredSize(new Dimension(panelWidth, topPanelHeight));
        topPanel.setMaximumSize(new Dimension(panelWidth, topPanelHeight));
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        JLabel offerTitle1 = new JLabel(offerInfo.get("name"), JLabel.LEFT);
        offerTitle1.setFont(fontBiggerBold);
        JLabel offerTitle2 = new JLabel(" by ", JLabel.LEFT);
        offerTitle2.setFont(fontBigger);
        JLabel offerTitle3 = new JLabel(offerInfo.get("hotel"), JLabel.LEFT);
        offerTitle3.setFont(fontBiggerBold);

        topPanel.add(offerTitle1);
        topPanel.add(offerTitle2);
        topPanel.add(offerTitle3);
        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        add(topPanel);

        int contentPanelsHeight = panelHeight - topPanelHeight;
        JPanel groupPanel1 = new JPanel();
        groupPanel1.setBackground(bgColor);
        groupPanel1.setLayout(new BoxLayout(groupPanel1, BoxLayout.LINE_AXIS));
        groupPanel1.setPreferredSize(new Dimension(panelWidth, contentPanelsHeight));
        groupPanel1.setMaximumSize(new Dimension(panelWidth, contentPanelsHeight));
        groupPanel1.add(Box.createRigidArea(new Dimension(10, 0)));

        int leftPanelWidth = panelWidth * 4 / 10;
        JPanel contentPanelLeft = new JPanel();
        contentPanelLeft.setBackground(bgColor);
        contentPanelLeft.setLayout(new BoxLayout(contentPanelLeft, BoxLayout.PAGE_AXIS));
        contentPanelLeft.setPreferredSize(new Dimension(leftPanelWidth, contentPanelsHeight));
        contentPanelLeft.setMaximumSize(new Dimension(leftPanelWidth, contentPanelsHeight));
        groupPanel1.add(contentPanelLeft);
        groupPanel1.add(createSeparator(50, 600), BorderLayout.CENTER);
        JPanel contentPanelRight = new JPanel();
        contentPanelRight.setBackground(bgColor);
        contentPanelRight.setLayout(new BoxLayout(contentPanelRight, BoxLayout.PAGE_AXIS));
        contentPanelRight.setPreferredSize(new Dimension(panelWidth - leftPanelWidth, contentPanelsHeight));
        contentPanelRight.setMaximumSize(new Dimension(panelWidth - leftPanelWidth, contentPanelsHeight));
        groupPanel1.add(contentPanelRight);
        add(groupPanel1);
        groupPanel1.add(Box.createRigidArea(new Dimension(10, 0)));

        // LEFT PANEL
        int offerImgWidth = leftPanelWidth;
        int offerImgHeight = contentPanelsHeight / 2;
        try {
            Image offerImg = ImageIO.read(new File(getClass().getResource(offerInfo.get("img_path")).getPath()));
            offerImg = offerImg.getScaledInstance(offerImgWidth, offerImgHeight, Image.SCALE_SMOOTH);
            ImageIcon offerImgIcon = new ImageIcon(offerImg);
            JLabel offerImgLabel = new JLabel();
            offerImgLabel.setIcon(offerImgIcon);
            contentPanelLeft.add(offerImgLabel);
        } catch (Exception e) {
            JPanel imgPanel = new JPanel();
            imgPanel.setBackground(Color.RED);
            imgPanel.setPreferredSize(new Dimension(offerImgWidth, offerImgHeight));
            imgPanel.setMaximumSize(new Dimension(offerImgWidth, offerImgHeight));
            contentPanelLeft.add(imgPanel);
        }
        contentPanelLeft.add(Box.createRigidArea(new Dimension(0, contentPanelsHeight / 30)));

        // Start Date
        JPanel startDatePanel = new JPanel();
        startDatePanel.setBackground(bgColor);
        startDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel startDateLabel = new JLabel("Reservation Start Date:");
        startDateLabel.setFont(new Font(startDateLabel.getFont().getName(), Font.BOLD, 16));
        startYearComboBox = createComboBox(getCurrentYear(), getCurrentYear()+1);
        startMonthComboBox = createComboBox(1, 12);
        startDayComboBox = new JComboBox<>();

        startDatePanel.add(startDateLabel);
        startDatePanel.add(startDayComboBox);
        startDatePanel.add(startMonthComboBox);
        startDatePanel.add(startYearComboBox);
        contentPanelLeft.add(startDatePanel);

        // End Date
        JPanel endDatePanel = new JPanel();
        endDatePanel.setBackground(bgColor);
        endDatePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel endDateLabel = new JLabel("Reservation End Date:  ");
        endDateLabel.setFont(new Font(endDateLabel.getFont().getName(), Font.BOLD, 16));
        endYearComboBox = createComboBox(getCurrentYear(), getCurrentYear()+1);
        endMonthComboBox = createComboBox(1, 12);
        endDayComboBox = new JComboBox<>();

        endDatePanel.add(endDateLabel);
        endDatePanel.add(endDayComboBox);
        endDatePanel.add(endMonthComboBox);
        endDatePanel.add(endYearComboBox);
        contentPanelLeft.add(endDatePanel);

        startMonthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDays(startYearComboBox, startMonthComboBox, startDayComboBox);
            }
        });
        startYearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDays(startYearComboBox, startMonthComboBox, startDayComboBox);
            }
        });

        endMonthComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDays(endYearComboBox, endMonthComboBox, endDayComboBox);
            }
        });
        endYearComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDays(endYearComboBox, endMonthComboBox, endDayComboBox);
            }
        });

        updateDays(startYearComboBox, startMonthComboBox, startDayComboBox);
        updateDays(endYearComboBox, endMonthComboBox, endDayComboBox);

        // RIGHT PANEL
        contentPanelRight.setLayout(new BoxLayout(contentPanelRight, BoxLayout.Y_AXIS));
        contentPanelRight.revalidate();
        JLabel paymentMethodLabel = new JLabel("Choose Payment Method:");
        paymentMethodLabel.setFont(new Font(endDateLabel.getFont().getName(), Font.BOLD, 24));
        contentPanelRight.add(paymentMethodLabel);

        addCreditCardPanel(contentPanelRight);

    }

    void addJLabel(String text, Color color, Font font, JPanel panel, int panelWidth, int panelHeight) {
        JLabel textLabel = new JLabel(text, JLabel.LEFT);
        textLabel.setFont(font);
        textLabel.setForeground(color);
        if (panelWidth != -1 && panelHeight != -1){
            textLabel.setPreferredSize(new Dimension(panelWidth, panelHeight));
            textLabel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        }
        panel.add(textLabel);
    }

    private JComboBox<Integer> createComboBox(int start, int end) {
        Integer[] items = new Integer[end - start + 1];
        for (int i = 0; i < items.length; i++) {
            items[i] = start + i;
        }
        return new JComboBox<>(items);
    }

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private void updateDays(JComboBox<Integer> yearComboBox, JComboBox<Integer> monthComboBox, JComboBox<Integer> dayComboBox) {
        int year = (int) yearComboBox.getSelectedItem();
        int month = (int) monthComboBox.getSelectedItem();
        int daysInMonth = getDaysInMonth(year, month);

        dayComboBox.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(i);
        }
    }

    private int getDaysInMonth(int year, int month) {
        int daysInMonth;
        if (month == 2) {
            daysInMonth = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            daysInMonth = 30;
        } else {
            daysInMonth = 31;
        }
        return daysInMonth;
    }

    void addCreditCardPanel(JPanel panel) {
        List<PaymentMethod> creditCards = getCreditCards();

        if (creditCards.isEmpty()) {
            JLabel addCreditCardLabel = new JLabel("You have no credit cards. Add a new one.");
            addCreditCardLabel.setFont(new Font(addCreditCardLabel.getFont().getName(), Font.BOLD, 16));
            RoundedButton addCreditCardButton = new RoundedButton("Go to your credit cards.",
                    panelWidth*5/21, panelHeight/10, Color.ORANGE, Color.YELLOW, fontMiddle, false );

            panel.add(addCreditCardLabel);
            panel.add(addCreditCardButton);

            addCreditCardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO
                    System.out.println("User: " + userId);
                }
            });
        } else {
            JLabel selectCreditCardLabel = new JLabel("Select a credit card:");
            selectCreditCardLabel.setFont(new Font(selectCreditCardLabel.getFont().getName(), Font.BOLD, 16));
            panel.add(selectCreditCardLabel);

            ButtonGroup cardButtonGroup = new ButtonGroup();
            Integer counter = 1;
            for (PaymentMethod creditCard : creditCards) {
                JLabel cardCounter = new JLabel("Credit Card" + counter + ": ");
                cardCounter.setFont(new Font(selectCreditCardLabel.getFont().getName(), Font.PLAIN, 16));
                counter += 1;
                String cardNumber = creditCard.getCardNumber();
                cardNumber = cardNumber.substring(0, 4) + "-" + "#".repeat(cardNumber.length() - 8) + "-" + cardNumber.substring(cardNumber.length() - 4);
                JRadioButton creditCardRadioButton = new JRadioButton(cardNumber);
                creditCardRadioButton.setFont(new Font(selectCreditCardLabel.getFont().getName(), Font.BOLD, 16));

                creditCardRadioButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // TODO: Handle the selected credit card, e.g., store it in a variable
                        System.out.println("Selected Credit Card: " + creditCard.getCardNumber());
                    }
                });
                panel.add(cardCounter);
                cardButtonGroup.add(creditCardRadioButton);
                panel.add(creditCardRadioButton);
            }
        }
    }

    List<PaymentMethod> getCreditCards(){
        return new PaymentMethodDAO().findByClientId(userId);
    }

    private JPanel createSeparator(int w, int h) {
        JPanel separatorPanel = new JPanel();
        separatorPanel.setBackground(bgColor);
        separatorPanel.setPreferredSize(new Dimension(w, h)); // Dostosuj szerokość separatora do swoich potrzeb
        return separatorPanel;
    }

    private void returnSelectedDates() {
        if (endDayComboBox.getSelectedItem() != null) {
            int startYear = (int) startYearComboBox.getSelectedItem();
            int startMonth = (int) startMonthComboBox.getSelectedItem();
            int startDay = (int) startDayComboBox.getSelectedItem();

            int endYear = (int) endYearComboBox.getSelectedItem();
            int endMonth = (int) endMonthComboBox.getSelectedItem();
            int endDay = (int) endDayComboBox.getSelectedItem();

            this.startDate = LocalDate.of(startYear, startMonth, startDay);
            this.endDate = LocalDate.of(endYear, endMonth, endDay);
        }
    }

    public LocalDate getStartDate(){
        returnSelectedDates();
        return startDate;
    }

    public LocalDate getEndDate(){
        returnSelectedDates();
        return endDate;
    }

}
