import java.awt.*;
import java.awt.event.*;

class AdminPage extends Frame implements ActionListener {

    MenuBar mb;
    Menu admin;
    MenuItem refresh, logout;

    TextArea ta;

    AdminPage() {

        setTitle("Admin Page");

        mb = new MenuBar();

        admin = new Menu("Admin");

        refresh = new MenuItem("Refresh Users");
        logout = new MenuItem("Logout");

        admin.add(refresh);
        admin.add(logout);

        mb.add(admin);

        setMenuBar(mb);

        ta = new TextArea();
        ta.setEditable(false);
        add(ta);

        displayUsers();

        refresh.addActionListener(this);
        logout.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    void displayUsers() {

        ta.setText("");

        ta.append("========== REGISTERED USERS ==========\n\n");

        if(RegistrationPage.count == 0) {
            ta.append("No users registered.");
            return;
        }

        for(int i=0; i<RegistrationPage.count; i++) {

            ta.append("User " + (i+1) + "\n");
            ta.append("Name      : " + RegistrationPage.users[i].name + "\n");
            ta.append("Username  : " + RegistrationPage.users[i].username + "\n");
            ta.append("Password  : " + RegistrationPage.users[i].password + "\n");
            ta.append("Email     : " + RegistrationPage.users[i].email + "\n");
            ta.append("--------------------------------------\n");
        }
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==refresh) {
            displayUsers();
        }

        if(e.getSource()==logout) {
            new LoginPage();
            dispose();
        }
    }
}