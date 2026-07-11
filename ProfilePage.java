import java.awt.*;
import java.awt.event.*;

class ProfilePage extends Frame implements ActionListener {

    User user;

    MenuBar mb;
    Menu profile;
    MenuItem refresh, logout;

    TextArea ta;

    ProfilePage(User user) {

        this.user = user;

        setTitle("Profile Page");

        mb = new MenuBar();
        profile = new Menu("Profile");

        refresh = new MenuItem("Refresh Profile");
        logout = new MenuItem("Logout");

        profile.add(refresh);
        profile.add(logout);

        mb.add(profile);
        setMenuBar(mb);

        ta = new TextArea();
        ta.setEditable(false);
        add(ta);

        displayProfile();

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

    void displayProfile() {

        ta.setText("");

        ta.append("========== USER PROFILE ==========\n\n");
        ta.append("Name      : " + user.name + "\n");
        ta.append("Username  : " + user.username + "\n");
        ta.append("Password  : " + user.password + "\n");
        ta.append("Email     : " + user.email + "\n");
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==refresh) {
            displayProfile();
        }

        if(e.getSource()==logout) {
            new LoginPage();
            dispose();
        }
    }
}

