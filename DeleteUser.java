import java.awt.*;
import java.awt.event.*;

public class DeleteUser extends Frame implements ActionListener {

    User user;

    Label title;
    Label msg;
    Label warning;

    Button btnDelete;
    Button btnBack;

    FileManager fileManager;

    DeleteUser(User user) {

        this.user = user;

        fileManager = new FileManager();

        setTitle("User Management System - Delete Account");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout(20, 20));

        setBackground(new Color(235, 245, 255));

        Panel top = new Panel();
        top.setBackground(new Color(220, 53, 69));

        title = new Label("DELETE ACCOUNT", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        top.add(title);

        add(top, BorderLayout.NORTH);

        Panel card = new Panel(new BorderLayout(20, 20));
        card.setBackground(Color.WHITE);

        Panel messagePanel = new Panel(new GridLayout(2, 1, 10, 15));
        messagePanel.setBackground(Color.WHITE);

        msg = new Label(
                "Are you sure you want to permanently delete your account?",
                Label.CENTER);

        msg.setFont(new Font("Arial", Font.BOLD, 22));
        msg.setForeground(Color.RED);

        warning = new Label(
                "This action cannot be undone.",
                Label.CENTER);

        warning.setFont(new Font("Arial", Font.PLAIN, 18));
        warning.setForeground(Color.DARK_GRAY);

        messagePanel.add(msg);
        messagePanel.add(warning);

        Panel buttonPanel = new Panel();
        buttonPanel.setBackground(Color.WHITE);

        btnDelete = new Button("DELETE ACCOUNT");
        btnBack = new Button("BACK");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        btnDelete.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        btnDelete.setBackground(new Color(220, 53, 69));
        btnDelete.setForeground(Color.WHITE);

        btnBack.setBackground(new Color(33, 150, 243));
        btnBack.setForeground(Color.WHITE);

        buttonPanel.add(btnDelete);
        buttonPanel.add(new Label("     "));
        buttonPanel.add(btnBack);

        card.add(messagePanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.SOUTH);

        Panel center = new Panel(new GridBagLayout());
        center.setBackground(new Color(235, 245, 255));

        center.add(card);

        add(center, BorderLayout.CENTER);

        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDelete) {

            boolean deleted = fileManager.deleteUser(user.getUsername());

            if (deleted) {

                new MessageDialog(
                        this,
                        "Success",
                        "Account Deleted Successfully."
                ).setVisible(true);

                new HomePage();

                dispose();

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to Delete Account."
                ).setVisible(true);

            }

        }

        if (e.getSource() == btnBack) {

            new UserDashboard(user);

            dispose();

        }

    }

}