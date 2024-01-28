import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGUniverse;
import com.kitfox.svg.app.beans.SVGIcon;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;

public class SVGicon extends JFrame {

    public SVGicon() throws MalformedURLException {
        super("SVG Salamander Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);

        setLayout(new BorderLayout());

        // Create an SVG universe
        SVGUniverse svgUniverse = new SVGUniverse();

        // Load an SVG file (replace with your SVG file path)
        File svgFile = new File("./csv/file.svg");

        URI svgUri = svgUniverse.loadSVG(svgFile.toURI().toURL());
        SVGDiagram svgDiagram = svgUniverse.getDiagram(svgUri);

        // Create an SVGIcon from the loaded SVG diagram
        SVGIcon svgIcon = new SVGIcon();
        svgIcon.setSvgURI(svgUri);

        // Create a JLabel with the SVGIcon
        JLabel iconLabel = new JLabel(svgIcon);

        // Add the label to the frame
        add(iconLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new SVGicon();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
