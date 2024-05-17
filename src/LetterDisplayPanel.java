import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

class LetterDisplayPanel extends JPanel {
    private String publicWord;
    private int correctGuesses;
    private String[] lettersPlaceholders;
    private JLabel placeHolder;

    public LetterDisplayPanel(HangMan hangMan, String word) {
        // global copy of word for event handling
        publicWord = word;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.black));
        // create placeholders for letters
        lettersPlaceholders = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            lettersPlaceholders[i] = " _ ";
        }
        // Join array elements into a single string
        String labelText = String.join("", lettersPlaceholders);
        // Create JLabel with the concatenated string
        placeHolder = new JLabel(labelText);
        // Center placeHolder horizontally and vertically
        placeHolder.setHorizontalAlignment(JLabel.CENTER);
        placeHolder.setVerticalAlignment(JLabel.CENTER);
        add(placeHolder, BorderLayout.CENTER);
        // Add a component listener to the frame to dynamically adjust the font size
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeFontToFitLabel(placeHolder, hangMan);
            }
        });

    }

    public void insertLetter(String e) {
        // TODO: fix so that a correct letter is not counted more than it appears
        String[] arr = publicWord.split("");
        for (int i = 0; i < publicWord.length(); i++) {
            if (arr[i].equalsIgnoreCase(e)) {
                lettersPlaceholders[i] = e;
            }
        }
        // Reconstruct the text based on the updated lettersPlaceholders array
        String labelText = String.join("", lettersPlaceholders);
        // Set the reconstructed text to the JLabel
        placeHolder.setText(labelText);

        // Increment correctGuesses only if the guessed letter matches at least one
        // occurrence in the word
        if (publicWord.toLowerCase().contains(e.toLowerCase())) {
            correctGuesses++;
        }
    }

    private static void resizeFontToFitLabel(JLabel label, JFrame frame) {
        // Calculate the font size based on the frame size & Adjust the scaling factor
        // as needed
        int fontSize = (int) (Math.min(frame.getWidth(), frame.getHeight()) * 0.1);

        // Create a new font with the calculated font size
        Font labelFont = label.getFont();
        label.setFont(labelFont.deriveFont(Font.PLAIN, fontSize));
    }

    // correctGuesses getter
    public int getCorrectGuesses() {
        return correctGuesses;
    }

}
