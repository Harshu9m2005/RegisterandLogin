import javax.swing.*;
import java.awt.*;

public class UserDetailsFrame extends JFrame {
    public UserDetailsFrame(String bookName, String bookId, String issueDate, String returnDate) {
        setTitle("User Details");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Book Name:"));
        add(new JLabel(bookName));

        add(new JLabel("Book ID:"));
        add(new JLabel(bookId));

        add(new JLabel("Issue Date:"));
        add(new JLabel(issueDate));

        add(new JLabel("Return Date:"));
        add(new JLabel(returnDate));

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
