import java.awt.*;
import java.awt.print.*;
import javax.swing.JTable;

public class Main implements Printable {

    private JTable table;

    public Main(JTable table) {
        this.table = table;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // Calculate the total width and height of the table
        int tableWidth = table.getColumnModel().getTotalColumnWidth();
        int tableHeight = table.getRowHeight() * table.getRowCount();

        // Calculate the scale factor to fit the table within the page bounds
        double scaleX = pageFormat.getImageableWidth() / tableWidth;
        double scaleY = pageFormat.getImageableHeight() / tableHeight;
        double scale = Math.min(scaleX, scaleY);

        // Apply the scaling transformation
        g2d.scale(scale, scale);

        // Print the table
        table.print(g2d);

        return Printable.PAGE_EXISTS;
    }

    public void printTable() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Assuming you have a JTable named "table"
        JTable table = new JTable();

        Main jTablePrinter = new Main(table);
        jTablePrinter.printTable();
    }
}
