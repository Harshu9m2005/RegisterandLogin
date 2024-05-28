import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class RegisterPanel extends JPanel {
    private JFrame parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField bookNameField;
    private JTextField bookIdField;
    private JTextField issueDateField;
    private JTextField returnDateField;

    public RegisterPanel(JFrame frame) {
        this.parentFrame = frame;
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);

        add(new JLabel("Book Name:"));
        bookNameField = new JTextField();
        add(bookNameField);

        add(new JLabel("Book ID:"));
        bookIdField = new JTextField();
        add(bookIdField);

        add(new JLabel("Issue Date:"));
        issueDateField = new JTextField();
        add(issueDateField);

        add(new JLabel("Return Date:"));
        returnDateField = new JTextField();
        add(returnDateField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        add(registerButton);

        JButton loginButton = new JButton("Go to Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) parentFrame.getContentPane().getLayout();
                cl.show(parentFrame.getContentPane(), "Login");
            }
        });
        add(loginButton);
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String bookName = bookNameField.getText();
        String bookId = bookIdField.getText();
        String issueDate = issueDateField.getText();
        String returnDate = returnDateField.getText();

        // Validate password match
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "your_password")) {
            String sql = "INSERT INTO users (username, password, book_name, book_id, issue_date, return_date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, bookName);
            pstmt.setString(4, bookId);
            pstmt.setString(5, issueDate);
            pstmt.setString(6, returnDate);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Registration failed!");
        }
    }
}
