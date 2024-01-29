package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendanceManagement extends JPanel {

    private JTextArea notificationArea;
    private JButton markAttendanceButton;
    private JButton showCalendarButton;

    public AttendanceManagement() {
        setLayout(new BorderLayout());

        // Create components
        notificationArea = new JTextArea();
        markAttendanceButton = new JButton("Mark Attendance");
        showCalendarButton = new JButton("Show Calendar");

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(new JScrollPane(notificationArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(markAttendanceButton);
        buttonPanel.add(showCalendarButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        markAttendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markAttendance();
            }
        });

        showCalendarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCalendar();
            }
        });

        // Simulate initial notifications
        displayNotification("Welcome to the Attendance Management System!");
        displayNotification("Reminder: Attendance report due tomorrow.");
    }

    private void markAttendance() {
        String studentName = JOptionPane.showInputDialog(this, "Enter student name for attendance:");
        if (studentName != null) {
            displayNotification("Attendance marked for student: " + studentName);
        }
    }

    private void showCalendar() {
        // Simulate opening a calendar
        JOptionPane.showMessageDialog(this, "Calendar functionality is under development.", "Calendar", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayNotification(String message) {
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        notificationArea.append("[" + timeStamp + "] " + message + "\n");
    }
}
