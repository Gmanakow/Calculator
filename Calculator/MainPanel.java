import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class MainPanel extends JPanel {
    private JTextField inputField, resultfield;

    public MainPanel() {
        this.setLayout(new FlowLayout());
        Font font = this.getFont().deriveFont(Font.PLAIN, 20f);

        inputField = new JTextField("", 20);
        inputField.setHorizontalAlignment(SwingConstants.CENTER);
        inputField.setFont(font);
        inputField.setVisible(true);
        add(inputField);

        JButton clearButton = new JButton("Equals");
        clearButton.setFont(font);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        clearButton.setVisible(true);
        add(clearButton);

        JButton goButton = new JButton("Clear");
        goButton.setFont(font);
        goButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        goButton.setVisible(true);
        add(goButton);

        resultfield = new JTextField("", 5);
        resultfield.setHorizontalAlignment(SwingConstants.CENTER);
        resultfield.setFont(font);
        resultfield.setVisible(true);
        add(resultfield);
    }

    public void calculate(){
        try {
            Parser parser = new Parser(inputField.getText());
            String res = new DecimalFormat("#0.00").format(parser.parseExpression());
            if (parser.checkOC()) {
                resultfield.setText(res);
            } else throw new ParserException();
        } catch (LexerException e){
            JOptionPane.showMessageDialog(null,
                    "Incorrect symbols input",
                    "Error",JOptionPane.ERROR_MESSAGE);
        } catch (ParserException e){
            JOptionPane.showMessageDialog(null,
                    "Incorrect string construction",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void clear(){
        inputField.setText("");
        resultfield.setText("");
    }

}
