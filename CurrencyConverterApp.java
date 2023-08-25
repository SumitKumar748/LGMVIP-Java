import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

// required packages for the program were imported above

public class CurrencyConverterApp extends JFrame {
    public JTextField amountField;
    public JComboBox<String> fromCurrencyComboBox;
    public JComboBox<String> toCurrencyComboBox;
    public JButton convertButton;
    public JLabel resultLabel;

    public static final double USD_TO_EUR_RATE = 0.92;
    public static final double USD_TO_JPY_RATE = 145.56;
    public static final double EUR_TO_JPY_RATE = 145.64;
    public static final double USD_TO_INR_RATE = 82.56;
    public static final double EUR_TO_INR_RATE = 89.48;
    public static final double JPY_TO_INR_RATE = 0.57;

    public CurrencyConverterApp() {
        setTitle("CURRENCY CONVERTER");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        layoutComponents();
    }

    public void initComponents() {
        amountField = new JTextField(10);
        fromCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "INR"});
        toCurrencyComboBox = new JComboBox<>(new String[]{"USD", "EUR", "JPY", "INR"});
        convertButton = new JButton("Convert");
        resultLabel = new JLabel();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertCurrency();
            }
        });
    }

    public void layoutComponents()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(new JLabel("Amount :"));
        panel.add(amountField);
        panel.add(new JLabel("From Currency :"));
        panel.add(fromCurrencyComboBox);
        panel.add(new JLabel("To Currency :"));
        panel.add(toCurrencyComboBox);
        panel.add(new JLabel(""));
        panel.add(convertButton);

        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    public void convertCurrency() {
        double amount = Double.parseDouble(amountField.getText());
        String fromCurrency = (String) fromCurrencyComboBox.getSelectedItem();
        String toCurrency = (String) toCurrencyComboBox.getSelectedItem();

        double convertedAmount = convert(amount, fromCurrency, toCurrency);
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        resultLabel.setText(decimalFormat.format(amount) + " " + fromCurrency + " = " + decimalFormat.format(convertedAmount) + " " + toCurrency);
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals("USD")) {
            if (toCurrency.equals("EUR")) {
                return amount * USD_TO_EUR_RATE;
            } else if (toCurrency.equals("JPY")) {
                return amount * USD_TO_JPY_RATE;
            } else if (toCurrency.equals("INR")) {
                return amount * USD_TO_INR_RATE;
            }
        } else if (fromCurrency.equals("EUR")) {
            if (toCurrency.equals("USD")) {
                return amount / USD_TO_EUR_RATE;
            } else if (toCurrency.equals("JPY")) {
                return amount * EUR_TO_JPY_RATE;
            } else if (toCurrency.equals("INR")) {
                return amount * EUR_TO_INR_RATE;
            }
        } else if (fromCurrency.equals("JPY")) {
            if (toCurrency.equals("USD")) {
                return amount / USD_TO_JPY_RATE;
            } else if (toCurrency.equals("EUR")) {
                return amount / EUR_TO_JPY_RATE;
            } else if (toCurrency.equals("INR")) {
                return amount * JPY_TO_INR_RATE;
            }
        } else if (fromCurrency.equals("INR")) {
            if (toCurrency.equals("USD")) {
                return amount / USD_TO_INR_RATE;
            } else if (toCurrency.equals("EUR")) {
                return amount / EUR_TO_INR_RATE;
            } else if (toCurrency.equals("JPY")) {
                return amount / JPY_TO_INR_RATE;
            }
        }
        return amount;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CurrencyConverterApp().setVisible(true);
            }
        });
    }
}
