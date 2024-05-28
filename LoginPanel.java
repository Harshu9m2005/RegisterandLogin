import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class LoginPanel extends JPanel {
    private JFrame parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPanel(JFrame frame) {
        this.parentFrame = frame;
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        add(loginButton);

        JButton registerButton = new JButton("Go to Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) parentFrame.getContentPane().getLayout();
                cl.show(parentFrame.getContentPane(), "Register");
            }
        });
        add(registerButton);
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_db", "root", "Harshu@955")) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String bookName = rs.getString("book_name");
                String bookId = rs.getString("book_id");
                String issueDate = rs.getString("issue_date");
                String returnDate = rs.getString("return_date");
                new UserDetailsFrame(bookName, bookId, issueDate, returnDate);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Login failed!");
        }
    }
}
