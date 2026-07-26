import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminPage extends Frame implements ActionListener {

    Label title;
    Label totalUsers;

    TextArea ta;

    Button btnRefresh;
    Button btnLogout;

    FileManager fileManager;

    AdminPage() {

        fileManager = new FileManager();

        setTitle("User Management System - Admin Page");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        Color background = new Color(240,245,252);

        setBackground(background);

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        title = new Label("REGISTERED USERS", Label.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD, 34));

        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        Panel center = new Panel(new BorderLayout(20,20));

        center.setBackground(background);

        center.setPreferredSize(new Dimension(900,650));

        totalUsers = new Label("", Label.CENTER);

        totalUsers.setFont(new Font("Segoe UI", Font.BOLD, 20));

        totalUsers.setForeground(new Color(25,25,112));

        center.add(totalUsers, BorderLayout.NORTH);

        ta = new TextArea();

        ta.setEditable(false);

        ta.setFont(new Font("Consolas", Font.PLAIN,18));

        ta.setBackground(Color.WHITE);

        ta.setForeground(Color.BLACK);

        center.add(ta, BorderLayout.CENTER);

        Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER,25,15));

        buttons.setBackground(background);

        btnRefresh = new Button("REFRESH");

        btnLogout = new Button("LOGOUT");

        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD,18));

        btnLogout.setFont(new Font("Segoe UI", Font.BOLD,18));

        btnRefresh.setBackground(new Color(33,150,243));

        btnRefresh.setForeground(Color.WHITE);

        btnLogout.setBackground(new Color(220,53,69));

        btnLogout.setForeground(Color.WHITE);

        buttons.add(btnRefresh);

        buttons.add(btnLogout);

        center.add(buttons, BorderLayout.SOUTH);

        Panel wrapper = new Panel(new GridBagLayout());

        wrapper.setBackground(background);

        wrapper.add(center);

        add(wrapper, BorderLayout.CENTER);

        displayUsers();

        btnRefresh.addActionListener(this);

        btnLogout.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        setVisible(true);

    }

    void displayUsers() {

        ta.setText("");

        ArrayList<User> users = fileManager.getAllUsers();

        totalUsers.setText("Total Registered Users : " + users.size());

        ta.append("==============================================================\n");
        ta.append("                 USER MANAGEMENT SYSTEM\n");
        ta.append("==============================================================\n\n");

        if(users.size()==0){

            ta.append("No users found.");

            return;

        }

        for(int i=0;i<users.size();i++){

            User user = users.get(i);

            ta.append("User " + (i+1) + "\n");

            ta.append("---------------------------------------------\n");

            ta.append("Username : " + user.getUsername() + "\n");

            ta.append("User ID  : " + user.getUserId() + "\n");

            ta.append("Password : ********\n");

            ta.append("Gmail    : " + user.getGmail() + "\n");

            ta.append("Phone    : " + user.getPhone() + "\n");

            ta.append("\n");

        }

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnRefresh){

            displayUsers();

        }

        if(e.getSource()==btnLogout){

            new LoginPage();

            dispose();

        }

    }

}