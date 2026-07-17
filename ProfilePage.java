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

    ta.setFont(new Font("Monospaced", Font.PLAIN, 18));

    ta.setText("");

    ta.append("==============================================================\n");
    ta.append("                     USER PROFILE\n");
    ta.append("==============================================================\n\n");

    ta.append("+----------------------+--------------------------------------+\n");
    ta.append("| Field                | Value                                |\n");
    ta.append("+----------------------+--------------------------------------+\n");

    ta.append(String.format("| %-20s | %-36s |\n",
            "Username", user.getUsername()));

    ta.append(String.format("| %-20s | %-36s |\n",
            "User ID", user.getUserId()));

    ta.append(String.format("| %-20s | %-36s |\n",
            "Password", user.getPassword()));

    ta.append(String.format("| %-20s | %-36s |\n",
            "Gmail", user.getGmail()));

    ta.append(String.format("| %-20s | %-36s |\n",
            "Phone", user.getPhone()));

    ta.append("+----------------------+--------------------------------------+\n");

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

