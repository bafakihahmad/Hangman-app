import javax.swing.*;
import java.awt.*;

class AnimationPanel extends JPanel {
    private String labelText;
    private String joinedLabelText;
    private JLabel attemptsLeftLabel;
    private int attemptsLeft = 7;
    public JLabel hangmanImageLabel;
    public String[] hangmanImagePaths = {
            "images/hangman pic 1.png",
            "images/hangman pic 2.png",
            "images/hangman pic 3.png",
            "images/hangman pic 4.png",
            "images/hangman pic 5.png",
            "images/hangman pic 6.png",
            "images/hangman pic 7.png",
    };

    AnimationPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setLayout(new BorderLayout());
        // define Font
        Font labelFont = new Font("Arial", Font.BOLD, 22);
        // join string and int to display on label
        labelText = "Incorrect attempts left: ";
        joinedLabelText = labelText + attemptsLeft;
        attemptsLeftLabel = new JLabel();
        attemptsLeftLabel.setText(joinedLabelText);
        // set attemptsLeftLabel alignment within panel and add to panel
        attemptsLeftLabel.setHorizontalAlignment(JLabel.CENTER);
        add(attemptsLeftLabel, BorderLayout.NORTH);
        // set font and size
        attemptsLeftLabel.setFont(labelFont);
        // add image to panel
        hangmanImageLabel = new JLabel();
        // set hangmanImageLabel alignment within panel and add to panel
        hangmanImageLabel.setHorizontalAlignment(JLabel.CENTER);
        // set size of image
        add(hangmanImageLabel, BorderLayout.CENTER);
    }

    public void updateLabel() {
        // Update the label text with the new value of attemptsLeft
        joinedLabelText = labelText + attemptsLeft;
        attemptsLeftLabel.setText(joinedLabelText);
    }

    // getter and setter
    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }
}
