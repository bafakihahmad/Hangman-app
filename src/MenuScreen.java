import javax.swing.*;
import java.awt.*;

public class MenuScreen {
    JLabel gameTitle;
    JButton play;
    // define Font
    Font labelFont = new Font("Arial", Font.BOLD, 26);

    MenuScreen(HangMan hangMan) {
        // game title customization
        gameTitle = new JLabel("The Hangman!!!");
        gameTitle.setFont(labelFont);
        hangMan.add(gameTitle);

        // play button customization
        play = new JButton("Play");
        play.addActionListener(hangMan);
        play.setFont(labelFont);
        // background color
        play.setOpaque(true);
        play.setBackground(Color.PINK);
        // Set button border
        play.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        hangMan.add(play);
    }
}
