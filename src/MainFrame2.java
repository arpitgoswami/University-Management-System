import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame2 extends javax.swing.JPanel {

    Dashboard dashboard = new Dashboard();
    StudentManagement studentManagement = new StudentManagement();
    FacultyManagement facultyManagement = new FacultyManagement();
    CourseManagement courseManagement = new CourseManagement();
    AttendanceManagement attendanceManagement = new AttendanceManagement();
    FeeManagement feeManagement = new FeeManagement();

    public MainFrame2() {
        initComponents();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        File file = new File("./svg/0.svg");
        FlatSVGIcon svgIcon = new FlatSVGIcon(file);

        sideBar = new javax.swing.JPanel();
        mainLabel = new javax.swing.JLabel(svgIcon, SwingConstants.LEFT);
        dashboardBtn = new javax.swing.JButton();
        studentBtn = new javax.swing.JButton();
        facultyBtn = new javax.swing.JButton();
        courseBtn = new javax.swing.JButton();
        attendanceBtn = new javax.swing.JButton();
        feeBtn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        settingBtn = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(1200, 600));

        sideBar.setBackground(new java.awt.Color(29, 36, 43));

        mainLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        mainLabel.setForeground(new java.awt.Color(255, 255, 255));
        mainLabel.setText("University Management");

        dashboardBtn.setBackground(new java.awt.Color(29, 36, 43));
        dashboardBtn.setForeground(new java.awt.Color(255, 255, 255));
        dashboardBtn.setText("Dashboard");
        dashboardBtn.setBorder(null);
        dashboardBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardBtnActionPerformed(evt);
            }
        });

        studentBtn.setBackground(new java.awt.Color(29, 36, 43));
        studentBtn.setForeground(new java.awt.Color(255, 255, 255));
        studentBtn.setText("Student Management");
        studentBtn.setBorder(null);
        studentBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        studentBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentBtnActionPerformed(evt);
            }
        });

        facultyBtn.setBackground(new java.awt.Color(29, 36, 43));
        facultyBtn.setForeground(new java.awt.Color(255, 255, 255));
        facultyBtn.setText("Faculty Management");
        facultyBtn.setBorder(null);
        facultyBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        facultyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                facultyBtnActionPerformed(evt);
            }
        });

        courseBtn.setBackground(new java.awt.Color(29, 36, 43));
        courseBtn.setForeground(new java.awt.Color(255, 255, 255));
        courseBtn.setText("Course Management");
        courseBtn.setBorder(null);
        courseBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        courseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseBtnActionPerformed(evt);
            }
        });

        attendanceBtn.setBackground(new java.awt.Color(29, 36, 43));
        attendanceBtn.setForeground(new java.awt.Color(255, 255, 255));
        attendanceBtn.setText("Attendance Management");
        attendanceBtn.setBorder(null);
        attendanceBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        attendanceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attendanceBtnActionPerformed(evt);
            }
        });

        feeBtn.setBackground(new java.awt.Color(29, 36, 43));
        feeBtn.setForeground(new java.awt.Color(255, 255, 255));
        feeBtn.setText("Fee Management");
        feeBtn.setBorder(null);
        feeBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        feeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feeBtnActionPerformed(evt);
            }
        });

        settingBtn.setBackground(new java.awt.Color(38, 47, 56));
        settingBtn.setForeground(new java.awt.Color(255, 255, 255));
        settingBtn.setText("Settings");
        settingBtn.setBorder(null);
        settingBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        settingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sideBarLayout = new javax.swing.GroupLayout(sideBar);
        sideBar.setLayout(sideBarLayout);
        sideBarLayout.setHorizontalGroup(
                sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(sideBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(separator)
                                        .addComponent(mainLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                        .addComponent(dashboardBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(studentBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(facultyBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(courseBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(attendanceBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(feeBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(settingBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        sideBarLayout.setVerticalGroup(
                sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(sideBarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(mainLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dashboardBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(studentBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(facultyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(courseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(attendanceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(feeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 223, Short.MAX_VALUE)
                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(settingBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(new BorderLayout());
        contentPanelLayout.setHorizontalGroup(
                contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 948, Short.MAX_VALUE)
        );
        contentPanelLayout.setVerticalGroup(
                contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(sideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(sideBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }// </editor-fold>

    private void dashboardBtnActionPerformed(java.awt.event.ActionEvent evt) {
        contentPanel.removeAll();
        contentPanel.add(dashboard, BorderLayout.CENTER);
    }

    private void studentBtnActionPerformed(java.awt.event.ActionEvent evt) {
        contentPanel.removeAll();
        contentPanel.add(studentManagement, BorderLayout.CENTER);
    }

    private void facultyBtnActionPerformed(java.awt.event.ActionEvent evt) {
        contentPanel.removeAll();
        contentPanel.add(facultyManagement, BorderLayout.CENTER);
    }

    private void courseBtnActionPerformed(java.awt.event.ActionEvent evt) {
        contentPanel.removeAll();
        contentPanel.add(courseManagement, BorderLayout.CENTER);
    }

    private void attendanceBtnActionPerformed(java.awt.event.ActionEvent evt) {
        contentPanel.removeAll();
        contentPanel.add(attendanceManagement, BorderLayout.CENTER);
    }

    private void feeBtnActionPerformed(java.awt.event.ActionEvent evt) {
        contentPanel.removeAll();
        contentPanel.add(feeManagement, BorderLayout.CENTER);
    }

    private void settingBtnActionPerformed(java.awt.event.ActionEvent evt) {
        contentPanel.removeAll();
        contentPanel.add(dashboard, BorderLayout.CENTER);
        contentPanel.setSize(200,400);
    }

    private void init(){
        contentPanel.add(dashboard, BorderLayout.CENTER);
    }


    // Variables declaration - do not modify
    private javax.swing.JButton attendanceBtn;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JButton courseBtn;
    private javax.swing.JButton dashboardBtn;
    private javax.swing.JButton facultyBtn;
    private javax.swing.JButton feeBtn;
    private javax.swing.JLabel mainLabel;
    private javax.swing.JSeparator separator;
    private javax.swing.JButton settingBtn;
    private javax.swing.JPanel sideBar;
    private javax.swing.JButton studentBtn;
    // End of variables declaration
}
