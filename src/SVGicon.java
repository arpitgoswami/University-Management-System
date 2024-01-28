import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SVGicon {

    public static void main(String[] args) {
        // Set the FlatLaf look and feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("FlatSVGIcon Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a FlatSVGIcon from an SVG file
        File file = new File("./csv/1.svg");

        // Create a FlatSVGIcon from the file
        FlatSVGIcon svgIcon = new FlatSVGIcon(file);

        // Create a JLabel and set the size of the SVG icon
        JLabel label = new JLabel(svgIcon);
        label.setPreferredSize(new Dimension(100, 100)); // Set the preferred size

        // Add the label to the frame
        frame.add(label, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
