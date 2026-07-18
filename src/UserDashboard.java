import java.awt.*;
import java.awt.event.*;

public class UserDashboard extends Frame implements ActionListener {

    User user;

    Label title;
    Label welcome;

    Button btnUpdate;
    Button btnView;
    Button btnChangePassword;
    Button btnDelete;
    Button btnLogout;

    UserDashboard(User user) {

        this.user = user;

        setTitle("User Management System - User Dashboard");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout(20, 20));

        setBackground(new Color(235, 245, 255));

        Panel top = new Panel(new BorderLayout());

        top.setBackground(new Color(255, 193, 7));

        title = new Label("USER DASHBOARD", Label.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 34));

        title.setForeground(Color.BLACK);

        btnLogout = new Button("LOGOUT");

        btnLogout.setFont(new Font("Arial", Font.BOLD, 16));

        btnLogout.setBackground(new Color(220, 53, 69));

        btnLogout.setForeground(Color.WHITE);

        top.add(title, BorderLayout.CENTER);

        top.add(btnLogout, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        Panel menu = new Panel(new GridLayout(5, 1, 20, 20));

        menu.setBackground(Color.WHITE);

        welcome = new Label(
                "Welcome, " + user.getUsername(),
                Label.CENTER
        );

        welcome.setFont(new Font("Arial", Font.BOLD, 24));

        welcome.setForeground(new Color(25, 25, 112));

        btnUpdate = new Button("UPDATE DETAILS");

        btnView = new Button("VIEW PROFILE");

        btnChangePassword = new Button("CHANGE PASSWORD");

        btnDelete = new Button("DELETE ACCOUNT");

        Font buttonFont = new Font("Arial", Font.BOLD, 20);

        btnUpdate.setFont(buttonFont);
        btnView.setFont(buttonFont);
        btnChangePassword.setFont(buttonFont);
        btnDelete.setFont(buttonFont);

        btnUpdate.setBackground(new Color(33, 150, 243));
        btnView.setBackground(new Color(76, 175, 80));
        btnChangePassword.setBackground(new Color(255, 193, 7));
        btnDelete.setBackground(new Color(220, 53, 69));

        btnUpdate.setForeground(Color.WHITE);
        btnView.setForeground(Color.WHITE);
        btnChangePassword.setForeground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);

        menu.add(welcome);
        menu.add(btnUpdate);
        menu.add(btnView);
        menu.add(btnChangePassword);
        menu.add(btnDelete);

        Panel card = new Panel(new BorderLayout(20, 20));

        card.setBackground(Color.WHITE);

        Label heading = new Label("Dashboard Menu", Label.CENTER);

        heading.setFont(new Font("Arial", Font.BOLD, 28));

        heading.setForeground(new Color(25, 25, 112));

        card.add(heading, BorderLayout.NORTH);

        card.add(menu, BorderLayout.CENTER);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(235, 245, 255));

        center.add(card);

        add(center, BorderLayout.CENTER);

        btnUpdate.addActionListener(this);
        btnView.addActionListener(this);
        btnChangePassword.addActionListener(this);
        btnDelete.addActionListener(this);
        btnLogout.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnUpdate) {

            new UpdateUser(user);

            dispose();

        }

        if (e.getSource() == btnView) {

            new ViewUser(user);

            dispose();

        }

        if (e.getSource() == btnChangePassword) {

            new ChangePassword(user);

            dispose();

        }

        if (e.getSource() == btnDelete) {

            new DeleteUser(user);

            dispose();

        }

        if (e.getSource() == btnLogout) {

            new LoginPage();

            dispose();

        }

    }

}