import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

class AlphabetPanel extends JPanel {
    // num. of alphabets
    private static final int NUM_BUTTONS = 26;
    JButton[] alphabetButtons;

    AlphabetPanel(HangMan hangman) {
        setLayout(new GridLayout(5, 6));
        setBorder(BorderFactory.createLineBorder(Color.black));
        // Initialize array to hold alphabet buttons
        alphabetButtons = new JButton[NUM_BUTTONS];
        // define Font
        Font buttonFont = new Font("Arial", Font.BOLD, 20);
        // Create and add alphabet buttons dynamically
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            JButton button = new JButton(String.valueOf(ch));
            button.addActionListener(hangman);
            // Store button reference in array
            alphabetButtons[ch - 'A'] = button;
            // Set button border
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // avoid overlapping background color
            button.setOpaque(true);
            // Set background color
            button.setBackground(new java.awt.Color(99, 43, 108));
            // Set text color
            button.setForeground(new java.awt.Color(252, 195, 163));
            // Set font
            button.setFont(buttonFont);
            // Add button to panel
            add(button);
        }
    }

    // modified getter
    public JButton getAlphabetButton(String letter) {
        letter = letter.trim();
        for (int i = 0; i < alphabetButtons.length; i++) {
            if (alphabetButtons[i].getText().trim().equalsIgnoreCase(letter)) {
                return alphabetButtons[i];
            }
        }
        return null;
    }
}