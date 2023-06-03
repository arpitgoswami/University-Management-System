import javax.swing.*;
import java.net.URL;

public class FirstTab {

    private JPanel contentPane;
    private JTextField nameField;
    private JTextField fatherNameField;
    private JTextField motherNameField;
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField dobField;
    private JTextField corrAddrField;
    private JTextField emailField;
    private JTextField fatherOccupationField;
    private JTextField motherOccupationField;

    public int widthBig = 320;
    public int leftPadding = 20;
    public int firstField = 200;
    public int diffField = 30;
    public int diffInput = 4;

    public void create(JPanel firstTab) {

        int width = Main.width;

        URL imageUrl = getClass().getResource("./image.jpg");
        ImageIcon imageIcon = new ImageIcon(imageUrl);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(20,1,500, 200);
        firstTab.add(imageLabel);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(leftPadding, firstField, 46, 14);
        firstTab.add(lblName);

        nameField = new JTextField();
        nameField.setBounds(width, (firstField+(diffField*0))-diffInput, width, 24);
        nameField.setColumns(leftPadding);
        firstTab.add(nameField);
        
        JLabel lblFathersName = new JLabel("Father's Name");
        lblFathersName.setBounds(leftPadding, firstField+(diffField*1), 89, 14);
        firstTab.add(lblFathersName);

        fatherNameField = new JTextField();
        fatherNameField.setBounds(width, (firstField+(diffField*1))-diffInput, width, 24);
        firstTab.add(fatherNameField);
        fatherNameField.setColumns(leftPadding);

        JLabel fatherOccupation = new JLabel("Occupation");
        fatherOccupation.setBounds(leftPadding+widthBig, firstField+(diffField*1), 89, 14);
        firstTab.add(fatherOccupation);

        fatherOccupationField = new JTextField();
        fatherOccupationField.setBounds(leftPadding+widthBig+width, (firstField+(diffField*1))-diffInput, width, 24);
        firstTab.add(fatherOccupationField);
        fatherOccupationField.setColumns(leftPadding);

        JLabel lblMothersName = new JLabel("Mother's Name");
        lblMothersName.setBounds(leftPadding, firstField+(diffField*2),89, 14);
        firstTab.add(lblMothersName);

        motherNameField = new JTextField();
        motherNameField.setBounds(width, (firstField+(diffField*2))-diffInput, width, 23);
        firstTab.add(motherNameField);
        motherNameField.setColumns(leftPadding);

        JLabel motherOccupation = new JLabel("Occupation");
        motherOccupation.setBounds(leftPadding+widthBig, firstField+(diffField*2), 89, 14);
        firstTab.add(motherOccupation);

        motherOccupationField = new JTextField();
        motherOccupationField.setBounds(leftPadding+widthBig+width, (firstField+(diffField*2))-diffInput, width, 24);
        firstTab.add(motherOccupationField);
        motherOccupationField.setColumns(leftPadding);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(leftPadding, firstField+(diffField*3), 46, 14);
        firstTab.add(lblGender);

        JRadioButton rdbtnMale = new JRadioButton("Male");
        buttonGroup.add(rdbtnMale);
        rdbtnMale.setBounds(width, (firstField+(diffField*3))-diffInput, 109, 23);
        firstTab.add(rdbtnMale);

        JRadioButton rdbtnFemale = new JRadioButton("Female");
        buttonGroup.add(rdbtnFemale);
        rdbtnFemale.setBounds(leftPadding+widthBig, (firstField+(diffField*3))-diffInput, 109, 23);
        firstTab.add(rdbtnFemale);

        JLabel lblMaritalStatus = new JLabel("Marital Status");
        lblMaritalStatus.setBounds(leftPadding, firstField+(diffField*4), 89, 14);
        firstTab.add(lblMaritalStatus);

        JComboBox maritalStatusComboBox = new JComboBox();
        maritalStatusComboBox.setModel(new DefaultComboBoxModel(new String[] {"Single", "Married", "Divorced", "Widowed"}));
        maritalStatusComboBox.setBounds(width, (firstField+(diffField*4))-diffInput, width, 23);
        firstTab.add(maritalStatusComboBox);

        JLabel lblDateOfBirth = new JLabel("Date of Birth");
        lblDateOfBirth.setBounds(leftPadding, firstField+(diffField*5), 89, 14);
        firstTab.add(lblDateOfBirth);

        dobField = new JTextField();
        dobField.setBounds(width, (firstField+(diffField*5))-diffInput, width, 23);
        firstTab.add(dobField);
        dobField.setColumns(leftPadding);

        JLabel lblAddressOfCorrespondance = new JLabel("Correspondance Address");
        lblAddressOfCorrespondance.setBounds(leftPadding, firstField+(diffField*6), 159, 14);
        firstTab.add(lblAddressOfCorrespondance);

        corrAddrField = new JTextField();
        corrAddrField.setBounds(width, (firstField+(diffField*6))-diffInput, widthBig+width+leftPadding, 23);
        firstTab.add(corrAddrField);
        corrAddrField.setColumns(leftPadding);

        JLabel lblEmailId = new JLabel("Email ID");
        lblEmailId.setBounds(leftPadding, firstField+(diffField*7), 46, 14);
        firstTab.add(lblEmailId);

        emailField = new JTextField();
        emailField.setBounds(width, (firstField+(diffField*7))-diffInput, widthBig+width+leftPadding, 23);
        firstTab.add(emailField);
        emailField.setColumns(leftPadding);

        JLabel lblCategory = new JLabel("Category");
        lblCategory.setBounds(leftPadding, firstField+(diffField*8), 50, 14);
        firstTab.add(lblCategory);

        JComboBox categoryComboBox = new JComboBox();
        categoryComboBox.setModel(new DefaultComboBoxModel(new String[] {"General", "OBC", "SC", "ST"}));
        categoryComboBox.setBounds(width, (firstField+(diffField*8))-diffInput, width, 23);
        firstTab.add(categoryComboBox);

        JLabel lblNationality = new JLabel("Nationality");
        lblNationality.setBounds(leftPadding, firstField+(diffField*9), 68, 14);
        firstTab.add(lblNationality);

        JTextField nationalityField = new JTextField();
        nationalityField.setBounds(width, (firstField+(diffField*9))-diffInput, width, 23);
        firstTab.add(nationalityField);
        nationalityField.setColumns(leftPadding);

        JLabel lblBloodGroup = new JLabel("Blood Group");
        lblBloodGroup.setBounds(leftPadding, firstField+(diffField*10), 68, 14);
        firstTab.add(lblBloodGroup);

        JComboBox bloodGroupComboBox = new JComboBox();
        bloodGroupComboBox.setModel(new DefaultComboBoxModel(new String[] {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));
        bloodGroupComboBox.setBounds(width, (firstField+(diffField*10))-diffInput, width, 23);
        firstTab.add(bloodGroupComboBox);

        JLabel lblMobileNumber = new JLabel("Mobile Number");
        lblMobileNumber.setBounds(leftPadding, firstField+(diffField*11), width, 14);
        firstTab.add(lblMobileNumber);

        JTextField mobileNumField = new JTextField();
        mobileNumField.setBounds(width, (firstField+(diffField*11))-diffInput, width, 23);
        firstTab.add(mobileNumField);
        mobileNumField.setColumns(leftPadding);

        JLabel lblalterNumber = new JLabel("Alternate Number");
        lblalterNumber.setBounds(leftPadding, firstField+(diffField*12), width, 14);
        firstTab.add(lblalterNumber);

        JTextField phoneNumField = new JTextField();
        phoneNumField.setBounds(width, (firstField+(diffField*12))-diffInput, width, 23);
        firstTab.add(phoneNumField);
        phoneNumField.setColumns(leftPadding);

        JLabel lblCourse = new JLabel("Course");
        lblCourse.setBounds(leftPadding, firstField+(diffField*13), 89, 14);
        firstTab.add(lblCourse);

        JComboBox CourseComboBox = new JComboBox();
        CourseComboBox.setModel(new DefaultComboBoxModel(new String[] {"B.Tech", "BCA", "BBA", "MBA", "B.Pharmacy", "M.Pharmacy", "LLB", "B.LLB", "GNM", "B.Sc. Nursing", "JBT", "B.Ed."}));
        CourseComboBox.setBounds(width, (firstField+(diffField*13))-diffInput, width, 23);
        firstTab.add(CourseComboBox);

        JLabel lbledu = new JLabel("Education");
        lbledu.setBounds(leftPadding+widthBig, firstField+(diffField*13), 89, 14);
        firstTab.add(lbledu);

        JComboBox PrevEducationComboBox = new JComboBox();
        PrevEducationComboBox.setModel(new DefaultComboBoxModel(new String[] {"Inter School", "Diploma", "Graduate"}));
        PrevEducationComboBox.setBounds(leftPadding+widthBig+width, (firstField+(diffField*13))-diffInput, width, 23);
        firstTab.add(PrevEducationComboBox);

        JLabel lblfee = new JLabel("Fee Method");
        lblfee.setBounds(leftPadding+widthBig+leftPadding+widthBig, firstField+(diffField*13), 89, 14);
        firstTab.add(lblfee);

        JComboBox feeComboBox = new JComboBox();
        feeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Self", "Scholarship"}));
        feeComboBox.setBounds(leftPadding+widthBig+leftPadding+widthBig+width, (firstField+(diffField*13))-diffInput, width, 23);
        firstTab.add(feeComboBox);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(width, (firstField+(diffField*14))-diffInput, width, 23);
        firstTab.add(btnSubmit);

        JLabel empty = new JLabel("");
        firstTab.add(empty);

    }
}