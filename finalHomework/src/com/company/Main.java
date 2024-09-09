package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main{
    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        JPanel redPanel = new JPanel();
//
//        frame.setSize(600,600);
//        frame.setTitle("our JFrame");
//        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//        frame.getContentPane().setBackground(Color.green);
//        frame.setResizable(false);
//        frame.setVisible(true);
//        JLabel label = new JLabel();
//        label.setText("Penisi");
//        label.setForeground(Color.blue);
//        label.setOpaque(true);
//        label.setBackground(Color.pink);
//        label.setBounds(0,0,100,100);
//        label.setVerticalAlignment(JLabel.CENTER);
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setFont(new Font("MV Boli", Font.PLAIN, 20));
//        frame.add(label);
//        frame.setLayout(null);





























        AtomicInteger count = new AtomicInteger();
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setBackground(Color.GREEN);
        JButton buttonone = new JButton("1");
        buttonone.addActionListener(e -> count.set(count.get() + 1));
        buttonone.setBounds(0, 300, 100, 100);
        JButton buttontwo = new JButton("2");
        buttontwo.addActionListener(e -> count.set(count.get() + 2));
        buttontwo.setBounds(100, 300, 100, 100);
        JButton buttonfour = new JButton("4");
        buttonfour.addActionListener(e -> count.set(count.get() + 4));
        buttonfour.setBounds(200, 300, 100, 100);
        JButton buttonadd = new JButton("calculate");
        buttonadd.addActionListener(e -> System.out.println(count));
        buttonadd.setBounds(300, 300, 100, 100);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(buttonone);
        frame.add(buttontwo);
        frame.add(buttonfour);
        frame.add(buttonadd);
        JLabel labelone = new JLabel();

    }
}
//package com.company;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Main extends JFrame {
//
//    private JTextField inputField;
//    private JTextField resultField;
//
//    public Main() {
//        setTitle("Simple Calculator"); // Set the title of the window
//        setSize(800, 500); // Set the size of the window
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the program exits when the window is closed
//        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall frame layout
//
//        // Initialize text fields
//        inputField = new JTextField(); // Input field
//        inputField.setEditable(false); // Set the input field to be non-editable
//        resultField = new JTextField(); // Result field
//        resultField.setEditable(false); // Set the result field to be non-editable
//
//        // Create a panel for text fields and set their dimensions
//        JPanel textPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // One row, two columns, and gaps
//        inputField.setPreferredSize(new Dimension(250, 250)); // Square shape
//        resultField.setPreferredSize(new Dimension(250, 250)); // Square shape
//        textPanel.add(inputField);
//        textPanel.add(resultField);
//
//        // Adding the text panel to the center of the frame
//        add(textPanel, BorderLayout.NORTH);
//
//        // Panel for buttons
//        JPanel buttonPanel = new JPanel(); // Create a new panel for buttons
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Use FlowLayout for even spacing
//        JButton button1 = new JButton("1");
//        JButton button2 = new JButton("2");
//        JButton button4 = new JButton("4");
//        JButton plusButton = new JButton("+");
//        JButton sumButton = new JButton("Sum");
//
//        // Ensure buttons are well-sized
//        button1.setPreferredSize(new Dimension(90, 90));
//        button2.setPreferredSize(new Dimension(90, 90));
//        button4.setPreferredSize(new Dimension(90, 90));
//        plusButton.setPreferredSize(new Dimension(90, 90));
//        sumButton.setPreferredSize(new Dimension(90, 90));
//
//        // Add buttons to the panel
//        buttonPanel.add(button1);
//        buttonPanel.add(button2);
//        buttonPanel.add(button4);
//        buttonPanel.add(plusButton);
//        buttonPanel.add(sumButton);
//
//        // Adding the button panel to the bottom of the frame
//        add(buttonPanel, BorderLayout.SOUTH);
//
//        // Add action listeners to the buttons
//        ActionListener insert = new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String actionCommand = e.getActionCommand(); // Get the text from the button
//                inputField.setText(inputField.getText() + actionCommand); // Append it to the input text field
//            }
//        };
//
//        button1.addActionListener(insert);
//        button2.addActionListener(insert);
//        button4.addActionListener(insert);
//        plusButton.addActionListener(insert);
//
//        // Add functionality to the sum button
//        sumButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    // Calculate the sum of the inputs
//                    int sum = evaluate(inputField.getText());
//                    resultField.setText(String.valueOf(sum)); // Set the result field to show the result
//                } catch (Exception ex) {
//                    resultField.setText("Error");
//                }
//            }
//        });
//    }
//
//    // Method to evaluate simple expressions with '+'
//    private int evaluate(String expression) {
//        String[] parts = expression.split("\\+"); // Split the input by '+'
//        int sum = 0;
//        for (String part : parts) {
//            sum += Integer.parseInt(part.trim()); // Convert each part to an integer and add to sum
//        }
//        return sum;
//    }
//
//    public static void main(String[] args) {
//        Main frame = new Main(); // Create the calculator frame
//        frame.setVisible(true); // Make the frame visible
//    }
//}
