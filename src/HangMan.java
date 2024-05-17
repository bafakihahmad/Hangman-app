import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangMan extends JFrame implements ActionListener {
    // initialize game state
    private State currentState;
    // copy of random word for event handling
    private String publicWord;
    private LetterDisplayPanel letterDisplayPanel;
    private AlphabetPanel alphabetPanel;
    private AnimationPanel animationPanel;
    private JButton play;
    private int incorrectGuesses = 0;
    private MenuScreen menuScreen;
    Font labelFont = new Font("Arial", Font.BOLD, 26);

    public HangMan(String word) {
        // initial game state
        currentState = State.MENU;
        // global copy of word for event handling
        publicWord = word;
        initializeUI(word);
    }

    private void initializeUI(String word) {
        // default basic setup
        this.setTitle("HangMan");
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        // set background color
        getContentPane().setBackground(new java.awt.Color(0, 139, 139));
        // Handle user actions
        if (currentState == State.MENU) {
            // Logic to transition to the GAME state
            menuScreen = new MenuScreen(this);

            // draw the GUI
            this.setVisible(true);
        } else if (currentState == State.GAME) {
            // Logic to handle actions during the game state
            GridBagConstraints c = new GridBagConstraints();
            // Letters Panel
            letterDisplayPanel = new LetterDisplayPanel(this, word);
            // background color is not overlapped
            letterDisplayPanel.setOpaque(false);
            c.fill = GridBagConstraints.BOTH;
            // set grid dimensions
            c.gridwidth = 1;
            c.gridheight = 1;
            // grid universal weight
            c.weightx = 1.0;
            c.weighty = 1.0;
            // panel location in grid
            c.gridx = 0;
            c.gridy = 0;
            this.add(letterDisplayPanel, c);

            // Alphabet Panel
            alphabetPanel = new AlphabetPanel(this);
            // background color is not overlapped
            alphabetPanel.setOpaque(false);
            c.gridx = 0;
            c.gridy = 1;
            this.add(alphabetPanel, c);

            // Animation Panel
            animationPanel = new AnimationPanel();
            // background color is not overlapped
            animationPanel.setOpaque(false);
            c.gridx = 1;
            c.gridy = 0;
            c.gridheight = 2;
            this.add(animationPanel, c);

            // draw the GUI
            this.setVisible(true);
        }
    }

    // Method to update UI components based on current state
    private void updateUI(String word) {
        // Clear existing components
        this.getContentPane().removeAll();

        // Initialize UI components based on the current state
        initializeUI(word);

        // Revalidate and repaint the frame to reflect the changes
        this.revalidate();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the source of the event
        Object source = e.getSource();

        if (source instanceof JButton) {
            JButton clickedButton = (JButton) source;
            String buttonText = clickedButton.getText();
            // check state
            if (buttonText.equalsIgnoreCase("Play")) {
                currentState = State.GAME;
                updateUI(publicWord);
            } else {
                // Convert publicWord to an array of characters
                char[] letters = publicWord.toCharArray();

                // Flag to track if the guess was correct
                boolean correctGuess = false;

                for (int i = 0; i < letters.length; i++) {
                    // Convert the current character to a string
                    String letterString = String.valueOf(letters[i]);
                    // make background color of button clicked changeable
                    clickedButton.setOpaque(true);
                    // Check if the buttonText equals the current letter
                    if (buttonText.equalsIgnoreCase(letterString)) {
                        // edit current word array to show correct letter
                        letterDisplayPanel.insertLetter(letterString);
                        // set button color green
                        alphabetPanel.getAlphabetButton(letterString).setBackground(Color.GREEN);
                        // remove actionListener - so it is not counted multiple times
                        alphabetPanel.getAlphabetButton(letterString).removeActionListener(this);
                        // Update flag to indicate correct guess
                        correctGuess = true;
                    }
                }
                // Decrement the incorrect attempts counter if the guess was incorrect
                if (!correctGuess && currentState == State.GAME) {
                    // set button color to red
                    alphabetPanel.getAlphabetButton(buttonText).setBackground(Color.RED);
                    // remove actionListener
                    alphabetPanel.getAlphabetButton(buttonText).removeActionListener(this);
                    // decrement attempts counter
                    int attempts = animationPanel.getAttemptsLeft();
                    attempts--;
                    // set updated value
                    animationPanel.setAttemptsLeft(attempts);
                    animationPanel.updateLabel();
                    // update hangman image state
                    ImageIcon icon = new ImageIcon(
                            getClass().getResource(animationPanel.hangmanImagePaths[incorrectGuesses]));
                    // set size
                    Image originalImage = icon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_DEFAULT); // Adjust width
                                                                                                        // and height as
                                                                                                        // needed
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    // set icon to label
                    animationPanel.hangmanImageLabel.setIcon(scaledIcon);
                    incorrectGuesses++;
                }
                // Check for winning condition
                if (correctGuess && letterDisplayPanel.getCorrectGuesses() >= publicWord.length()) {
                    currentState = State.WIN;
                    // show message
                    CustomDialog winScreen = new CustomDialog(this, "YOU WIN!");
                    winScreen.setVisible(true);
                    // Dispose previous attempt (frame)
                    dispose();
                }
                // Check for losing condition
                if (!correctGuess && letterDisplayPanel.getCorrectGuesses() < publicWord.length()
                        && animationPanel.getAttemptsLeft() == 0) {
                    currentState = State.GAMEOVER;
                    // show message
                    CustomDialog endScreen = new CustomDialog(this, "The word was: " + publicWord);
                    endScreen.setVisible(true);
                    // Dispose previous attempt (frame)
                    dispose();
                }
            }
        }
    }
}
