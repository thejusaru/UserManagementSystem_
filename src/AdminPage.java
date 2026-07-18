import java.awt.*;
import java.awt.event.*;

class AdminPage extends Frame implements ActionListener {

    MenuBar mb;
    Menu admin;
    MenuItem refresh, logout;

    TextArea ta;

    AdminPage() {

        setTitle("Admin Page");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        mb = new MenuBar();

        admin = new Menu("Admin");

        refresh = new MenuItem("Refresh Users");
        logout = new MenuItem("Logout");

        admin.add(refresh);
        admin.add(logout);

        mb.add(admin);

        setMenuBar(mb);

        Panel top = new Panel();
        top.setBackground(new Color(25, 118, 210));

        Label heading = new Label("REGISTERED USERS", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(Color.WHITE);

        top.add(heading);

        add(top, BorderLayout.NORTH);

        ta = new TextArea();
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 18));
        ta.setBackground(Color.WHITE);
        ta.setForeground(Color.BLACK);

        add(ta, BorderLayout.CENTER);

        displayUsers();

        refresh.addActionListener(this);
        logout.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        setVisible(true);
    }

    void displayUsers() {

        ta.setText("");

        ta.append("=====================================================\n");
        ta.append("              REGISTERED USERS\n");
        ta.append("=====================================================\n\n");

        if (RegistrationPage.count == 0) {

            ta.append("No users registered.");

            return;

        }

        for (int i = 0; i < RegistrationPage.count; i++) {

            User user = RegistrationPage.users[i];

            ta.append("User : " + (i + 1) + "\n\n");

            ta.append("Username  : " + user.getUsername() + "\n");
            ta.append("User ID   : " + user.getUserId() + "\n");
            ta.append("Password  : " + user.getPassword() + "\n");
            ta.append("Gmail     : " + user.getGmail() + "\n");
            ta.append("Phone     : " + user.getPhone() + "\n");

            ta.append("\n-----------------------------------------------------\n\n");

        }

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == refresh) {

            displayUsers();

        }

        if (e.getSource() == logout) {

            new LoginPage();

            dispose();

        }

    }

}