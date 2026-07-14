import java.awt.*;
import java.awt.event.*;

public class ViewUser extends Frame implements ActionListener {

    User user;

    Label title;

    TextArea ta;

    Button btnBack;

    ViewUser(User user) {

        this.user = user;

        setTitle("View User Details");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("VIEW USER DETAILS", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title);

        add(top, BorderLayout.NORTH);
        ta = new TextArea();
        ta.setEditable(false);
        ta.setFont(new Font("Monospaced", Font.PLAIN, 16));
        ta.append("\n");
        ta.append("========================================\n");
        ta.append("            USER DETAILS\n");
        ta.append("========================================\n\n");

        ta.append("Username     : " + user.getUsername() + "\n\n");
        ta.append("User ID      : " + user.getUserId() + "\n\n");
        ta.append("Password     : " + user.getPassword() + "\n\n");
        ta.append("Gmail        : " + user.getGmail() + "\n\n");
        ta.append("Phone Number : " + user.getPhone() + "\n");

        add(ta, BorderLayout.CENTER);

        Panel bottom = new Panel();
        btnBack = new Button("Back");
        bottom.add(btnBack);

        add(bottom, BorderLayout.SOUTH);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            new UserDashboard(user);
            dispose();
        }
    }
}