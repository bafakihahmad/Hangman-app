import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog {

    public CustomDialog(JFrame parent, String title) {
        super(parent, title, true);
        JPanel panel = new JPanel();
        JLabel label = new JLabel("What would you like to do?");
        JButton retryButton = new JButton("Retry");
        JButton exitButton = new JButton("Exit");

        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog
                dispose();
                // Call program with a new random word
                SwingUtilities.invokeLater(() -> new HangMan(Main.getWord()));

            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the program
                System.exit(0);
            }
        });

        panel.add(label);
        panel.add(retryButton);
        panel.add(exitButton);
        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }
}
