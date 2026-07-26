import java.awt.*;
import java.awt.event.*;

public class UserDashboard extends Frame implements ActionListener {

    User user;

    Label title;
    Label heading;
    Label welcome;

    Button btnUpdate;
    Button btnView;
    Button btnChangePassword;
    Button btnDelete;
    Button btnLogout;

    UserDashboard(User user) {

        this.user = user;

        setTitle("User Management System - Dashboard");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(245, 248, 252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        title = new Label("USER DASHBOARD",Label.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,34));

        title.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(245,248,252));

        Panel card = new Panel(new BorderLayout(20,20));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(650,520));

        heading = new Label("Dashboard Menu",Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        Panel body = new Panel(new GridLayout(6,1,18,18));

        body.setBackground(Color.WHITE);

        welcome = new Label("Welcome, " + user.getUsername(),Label.CENTER);

        welcome.setFont(new Font("Arial",Font.BOLD,22));

        welcome.setForeground(new Color(60,60,60));

        btnUpdate = new Button("UPDATE PROFILE");

        btnView = new Button("VIEW PROFILE");

        btnChangePassword = new Button("CHANGE PASSWORD");

        btnDelete = new Button("DELETE ACCOUNT");

        btnLogout = new Button("LOGOUT");

        Font buttonFont = new Font("Arial",Font.BOLD,18);

        btnUpdate.setFont(buttonFont);
        btnView.setFont(buttonFont);
        btnChangePassword.setFont(buttonFont);
        btnDelete.setFont(buttonFont);
        btnLogout.setFont(buttonFont);

        btnUpdate.setBackground(new Color(33,150,243));
        btnView.setBackground(new Color(76,175,80));
        btnChangePassword.setBackground(new Color(255,193,7));
        btnDelete.setBackground(new Color(220,53,69));
        btnLogout.setBackground(new Color(97,97,97));

        btnUpdate.setForeground(Color.WHITE);
        btnView.setForeground(Color.WHITE);
        btnChangePassword.setForeground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnLogout.setForeground(Color.WHITE);

        body.add(welcome);
        body.add(btnUpdate);
        body.add(btnView);
        body.add(btnChangePassword);
        body.add(btnDelete);
        body.add(btnLogout);

        card.add(body,BorderLayout.CENTER);

        center.add(card);

        add(center,BorderLayout.CENTER);

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