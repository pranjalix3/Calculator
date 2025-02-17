package pj.calculator;

import javax.swing.*;
import java.awt.*;

public class Calculator {
    int calcHeight = 540;  // Define the height of the calculator window
    int calcWidth = 360;   // Define the width of the calculator window

    Color lightGrey = new Color(212, 212, 210);
    Color darkGrey = new Color(80, 80, 80);
    Color black = new Color(28, 28, 28);
    Color yellow = new Color(255,149,0);

    //String buttonValues = {}

    JFrame frame = new JFrame("Calculator");  // Create a new JFrame (window) with title "Calculator"
    JPanel displayPanel = new JPanel();
    JLabel displayLabel = new JLabel();
    JPanel buttonsPanel = new JPanel();
    JButton buttons = new JButton();


    // Constructor for the Calculator class
    Calculator() {
        frame.setVisible(true); // Make the window visible
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


    }

}
