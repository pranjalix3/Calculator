package pj.calculator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;


public class Calculator {
    int calcHeight = 540;  // Define the height of the calculator window
    int calcWidth = 360;   // Define the width of the calculator window

    Color lightGray = new Color(212, 212, 210);
    Color darkGray = new Color(80, 80, 80);
    Color black = new Color(28, 28, 28);
    Color yellow = new Color(255,149,0);

    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("Calculator");  // Create a new JFrame (window) with title "Calculator"
    JPanel displayPanel = new JPanel();
    JLabel displayLabel = new JLabel();
    JPanel buttonsPanel = new JPanel();
    JButton buttons = new JButton();

    //num1-num2,num1+num2..
    String num1 = null;
    String operator = null;
    String num2 = null;


    // Constructor for the Calculator class
    Calculator() {
        frame.setSize(calcWidth, calcHeight); // Set the size of the window (width x height)
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false); // Prevent the user from resizing the window
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the program when the window is closed
        frame.setLayout(new BorderLayout()); // Set the layout of the frame to BorderLayout

        displayLabel.setBackground(black);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel,BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(5,4));
        buttonsPanel.setBackground(black);
        frame.add(buttonsPanel);

        for(int i=0;i<buttonValues.length;i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(black));
            if(Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(lightGray);
                button.setForeground(black);
            }
            else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                button.setBackground(yellow);
                button.setForeground(Color.white);
            }
            else{
                button.setBackground(darkGray);
                button.setForeground(Color.white);
            }
            buttonsPanel.add(button);
            calculationAction(button);
        }
        frame.setVisible(true); // Make the window visible
    }

    private void calculationAction(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton buttonClicked = (JButton) e.getSource();
                String buttonVal = buttonClicked.getText();
                if(Arrays.asList(topSymbols).contains(buttonVal)){
                    if(Objects.equals(buttonVal, "AC")){
                        clearAllValues();
                        displayLabel.setText("0");
                    }
                    else if (Objects.equals(buttonVal, "+/-")) {
                        double numDisplay = Double.parseDouble(displayLabel.getText());
                        numDisplay *= -1;
                        displayLabel.setText(removeZeroDecimal(numDisplay));
                    }
                    else{
                        double numDisplay = Double.parseDouble(displayLabel.getText());
                        numDisplay /= 100;
                        displayLabel.setText(removeZeroDecimal(numDisplay));
                    }
                }
                else if (Arrays.asList(rightSymbols).contains(buttonVal)){ // operations
                    if(Objects.equals(buttonVal, "=")){
                       if(num1!=null){
                           num2 = displayLabel.getText();
                           double numA = Double.parseDouble(num1);
                           double numB = Double.parseDouble(num2);

                           if(Objects.equals(operator, "+")){
                               displayLabel.setText(removeZeroDecimal(numA+numB));
                           }
                           else if(Objects.equals(operator, "-")){
                               displayLabel.setText(removeZeroDecimal(numA-numB));
                           }
                           else if(Objects.equals(operator, "×")){
                               displayLabel.setText(removeZeroDecimal(numA*numB));
                           }
                           else{
                               displayLabel.setText(removeZeroDecimal(numA/numB));
                           }
                           clearAllValues();
                       }
                    }
                    else if ("+-×÷".contains(buttonVal)){
                        if(operator == null){
                            num1 = displayLabel.getText();
                            displayLabel.setText("0");
                        }
                        operator = buttonVal;
                    }
                }
                else{ //digits or .
                    if(Objects.equals(buttonVal, ".")){
                        if(!displayLabel.getText().contains(buttonVal)){
                            displayLabel.setText(displayLabel.getText()+buttonVal);
                        }
                    }
                    else if(Objects.equals(buttonVal, "√")){
                        //to-do
                    }
                    else{
                        if(Objects.equals(displayLabel.getText(), "0")){
                            displayLabel.setText(buttonVal);
                        }
                        else{
                            displayLabel.setText(displayLabel.getText()+buttonVal);
                        }
                    }
                }
            }
        });

    }

    private String removeZeroDecimal(double numDisplay) {
        if(numDisplay%1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }

    private void clearAllValues() {
        num1 = null;
        operator = null;
        num2 = null;
    }

}
