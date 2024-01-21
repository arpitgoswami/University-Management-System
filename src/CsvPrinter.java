import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class CsvPrinter extends JFrame {

    public void printCSV(String csvFilePath) {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new CSVPrintable(csvFilePath));

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class CSVPrintable implements Printable {
        private String csvFilePath;
        private List<String[]> data;
        private String header;

        public CSVPrintable(String csvFilePath) {
            this.csvFilePath = csvFilePath;
            this.data = readDataFromCSV();
            this.header = generateHeader();
        }

        private List<String[]> readDataFromCSV() {
            List<String[]> dataList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    dataList.add(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dataList;
        }

        private String generateHeader() {
            return "Student Management System"; // Custom header
        }

        @Override
        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
            int linesPerPage = (int) (pageFormat.getImageableHeight() / graphics.getFontMetrics().getHeight());
            int numPages = (int) Math.ceil((double) data.size() / linesPerPage);

            if (pageIndex >= numPages) {
                return NO_SUCH_PAGE;
            }

            int startRow = pageIndex * linesPerPage;
            int endRow = Math.min((pageIndex + 1) * linesPerPage, data.size());

            Graphics2D g2d = (Graphics2D) graphics;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

            // Print header at the beginning of each page
            if (startRow == 0) {
                g2d.drawString(header, 0, g2d.getFontMetrics().getHeight());
            }

            int y = 2 * g2d.getFontMetrics().getHeight();
            for (int i = startRow; i < endRow; i++) {
                String[] rowData = data.get(i);
                String line = String.join(", ", rowData);
                g2d.drawString(line, 0, y);
                y += g2d.getFontMetrics().getHeight();
            }

            return PAGE_EXISTS;
        }
    }
}
