import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminDashboard extends Frame implements ActionListener {

    Label title;
    Label welcome;
    Label totalUsers;

    Button btnAdd;
    Button btnUpdate;
    Button btnDelete;
    Button btnSearch;
    Button btnView;
    Button btnLogout;

    FileManager fileManager;

    AdminDashboard() {

        fileManager = new FileManager();

        setTitle("User Management System - Admin Dashboard");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(235, 245, 255));

        Panel top = new Panel(new BorderLayout());
        top.setBackground(new Color(25, 118, 210));

        title = new Label("ADMIN DASHBOARD", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        btnLogout = new Button("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogout.setBackground(new Color(220, 53, 69));
        btnLogout.setForeground(Color.WHITE);

        top.add(title, BorderLayout.CENTER);
        top.add(btnLogout, BorderLayout.EAST);

        add(top, BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());
        center.setBackground(new Color(235, 245, 255));

        Panel menu = new Panel();
        menu.setLayout(new GridLayout(7, 1, 20, 20));
        menu.setBackground(Color.WHITE);

        ArrayList<User> users = fileManager.getAllUsers();

        welcome = new Label("Welcome Admin to Dashboard", Label.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 24));
        welcome.setForeground(new Color(25, 25, 112));

        totalUsers = new Label(
                "Total Registered Users : " + users.size(),
                Label.CENTER
        );
        totalUsers.setFont(new Font("Arial", Font.BOLD, 18));

        btnAdd = new Button("Add User");
        btnUpdate = new Button("Update User");
        btnDelete = new Button("Delete User");
        btnSearch = new Button("Search User");
        btnView = new Button("View Users");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        btnAdd.setFont(buttonFont);
        btnUpdate.setFont(buttonFont);
        btnDelete.setFont(buttonFont);
        btnSearch.setFont(buttonFont);
        btnView.setFont(buttonFont);

        btnAdd.setBackground(new Color(76, 175, 80));
        btnUpdate.setBackground(new Color(255, 193, 7));
        btnDelete.setBackground(new Color(244, 67, 54));
        btnSearch.setBackground(new Color(33, 150, 243));
        btnView.setBackground(new Color(156, 39, 176));

        btnAdd.setForeground(Color.WHITE);
        btnUpdate.setForeground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnSearch.setForeground(Color.WHITE);
        btnView.setForeground(Color.WHITE);

        menu.add(welcome);
        menu.add(totalUsers);
        menu.add(btnAdd);
        menu.add(btnUpdate);
        menu.add(btnDelete);
        menu.add(btnSearch);
        menu.add(btnView);

        center.add(menu);

        add(center, BorderLayout.CENTER);

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnLogout.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

            new AddUser();
            dispose();

        }

        if (e.getSource() == btnUpdate) {

            new UpdateUserAdmin();
            dispose();

        }

        if (e.getSource() == btnDelete) {

            new DeleteUserAdmin();
            dispose();

        }

        if (e.getSource() == btnSearch) {

            new SearchUser();
            dispose();

        }

        if (e.getSource() == btnView) {

            new ViewUsers();
            dispose();

        }

        if (e.getSource() == btnLogout) {

            new LoginPage();
            dispose();

        }

    }

}