package sem1;

import javax.swing. *;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualCalculator {

	
	
	/*
	 * 1. Create private variables to hold
	 *  a. Current input from the user
	 *  b. Operators
	 *  c. intermediate results
	 *  d. JextField
	 * 
	 * 2. Create frame
	 * 3. Set layout for the frame(going with FlowLayout)
	 * 4. Make JTextField Visible and set size
	 * 5. Create buttons
	 * 6. Add buttons to frame
	 * 7. Create actionListener for the buttons
	 * 	a. Include a JOptionPane to inform the user of the limitation of the calculator
	 * 
	 * 8. Add the actionListeners to the buttons using 'for loop'
	 * 9. Create a method for the computations
	 * 
	 */
	
    private static String Input = ""; // Holds the current input string
    private static String operator = ""; // Stores the current operator
    private static double intermediateResult = 0; // Stores the intermediate result
    private static JTextField display; // Display field for the calculator (Initializing it here to make it more accessible later on.) 

    public static void main(String[] args) {
    	
    	
    	
        JFrame calculator = new JFrame("Visual Calculator"); // Creating the frame or window
        calculator.setLayout(new FlowLayout()); // Using FlowLayout for simplicity

        display = new JTextField(30); // Setting size for the display
        display.setEditable(false); // Avoiding text from the keyboard. Limiting it to just the buttons on the calculator

        // Creating buttons for the calculator
        JButton one = new JButton("1");
        JButton two = new JButton("2");
        JButton three = new JButton("3");
        JButton four = new JButton("4");
        JButton five = new JButton("5");
        JButton six = new JButton("6");
        JButton seven = new JButton("7");
        JButton eight = new JButton("8");
        JButton nine = new JButton("9");
        JButton zero = new JButton("0");
        JButton clear = new JButton("C");
        JButton info = new JButton("Click me!");

        
        // Adding buttons and TextField to the frame
        calculator.add(info);
        calculator.add(display);
        

        
        // ActionListener for the buttons
        ActionListener buttonListener = new ActionListener() {
            @Override // Defines what happens each time a button is clicked by overriding the actionPerformed method.
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();

                // Appending numeric input directly to the Input variable
                if (command.length() == 1 && command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                    Input += command;
                    display.setText(Input);
                } 
                
                // Handling the operators
                else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
                    if (!Input.isEmpty()) {
                        double currentNumber = Double.parseDouble(Input); // Converting the string from the currentInput into a double and storing it in the currentNumber variable

                        // Compute if an operator was previously selected
                        if (!operator.isEmpty()) {
                        	intermediateResult = performCalculation(intermediateResult, currentNumber, operator);
                        } else {
                        	intermediateResult = currentNumber; // Set previousNumber initially
                        }

                        operator = command; // Updating the operator
                        Input = ""; // Clear input for next number
                        display.setText(String.valueOf(intermediateResult)); // Display intermediate result
                    }
                }
                

                // Handling the equals button for final result
                else if (command.equals("=")) {
                    if (!Input.isEmpty() && !operator.isEmpty()) {
                        double currentNumber = Double.parseDouble(Input);
                        double result = performCalculation(intermediateResult, currentNumber, operator);

                        display.setText(String.valueOf(result)); //Display final result
                        Input = ""; // Clear input after result
                        operator = ""; // Clear operator for next calculation
                    }
                } 
                
                
                // Handling the clear button
                else if (command.equals("C")) {
                	
                	//Resetting to default
                    Input = "";
                    operator = "";
                    intermediateResult = 0;
                    display.setText(""); // Clear display
                }
                
                
             // A simple message about the calculator to the user
            if(command.equals("Click me!")) {
            	JOptionPane.showMessageDialog(calculator, "This is a simple calculator.\nIt doesn't compute multiple\n streams of calculations.\nIt does it one at a time\n"
            			+ "so kindly take it easy :)\n      Have Fun!");
            	}
            }
        };
        
        

        // Storing the buttons into a  list to make adding their actionListeners easy
        JButton[] numButtons = {clear,one, two, three, four, five, six, seven, eight, nine, zero};
        String[] opts = {"+", "-", "*", "/", "="};

        // Adding ActionListener to each button using a loop for clarity
        for (JButton numButton : numButtons) {
            numButton.addActionListener(buttonListener);
            calculator.add(numButton);
        }
        
        
        // Adding actionListeners to the buttons and adding the buttons to the frame
        for (String ops : opts) {
            JButton opsButton = new JButton(ops);
            opsButton.addActionListener(buttonListener);
            calculator.add(opsButton);  // Add operator buttons to the calculator frame here
        }
        
        

        // Positioned here, independent of the others because of its position to catch user's attention
        info.addActionListener(buttonListener);

        
        
        
        calculator.setSize(400, 500); // Setting calculator window size 
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Making sure the program exits when closed
        calculator.setVisible(true); // Making the entire calculator visible
        
        System.out.println("Go Hatters!\n\nProgram by:[Benjamin N. Davis]");
    }

    
    
    // Method for calculation based on the selected operator
    private static double performCalculation(double num1, double num2, String operator) {
        if (operator.equals("+")) {
            return num1 + num2;
        } else if (operator.equals("-")) {
            return num1 - num2;
        } else if (operator.equals("*")) {
            return num1 * num2;
        } else if (operator.equals("/") && num2 != 0) {
            return num1 / num2; // Handle division
        } else {
            display.setText("Error"); // 
            return 0;
        }
    }
}

