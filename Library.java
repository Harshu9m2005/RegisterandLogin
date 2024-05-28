import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Library{
    public static void main(String[] args) {
        JFrame frame = new JFrame("User Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new CardLayout());

        JPanel registerPanel = new RegisterPanel(frame);
        JPanel loginPanel = new LoginPanel(frame);

        frame.add(registerPanel, "Register");
        frame.add(loginPanel, "Login");

        frame.setVisible(true);
    }
}
