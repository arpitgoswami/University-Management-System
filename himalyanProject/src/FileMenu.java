import javax.swing.*;

public class FileMenu {
    public static void create(JFrame frame) {

        /* Create the frame menu. */
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenu fontMenu = new JMenu("Font");
        JMenu optionMenu = new JMenu("Option");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(saveItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);

        JMenuItem emptyItem = new JMenuItem("Empty");
        editMenu.add(emptyItem);

        JMenuItem aboutItem = new JMenuItem("About");
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(fontMenu);
        menuBar.add(optionMenu);
        menuBar.add(helpMenu);

    }
}
