import java.awt.*;
import java.awt.event.*;

public class UserDashboard extends Frame implements ActionListener {

    User user;

    Label title, welcome;

    Button btnUpdate;
    Button btnView;
    Button btnChangePassword;
    Button btnDelete;
    Button btnLogout;

    UserDashboard(User user) {

        this.user = user;
        setTitle("User Dashboard");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());


        Panel top = new Panel(new BorderLayout());
        title = new Label("USER DASHBOARD", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        btnLogout = new Button("Logout");
        top.add(title, BorderLayout.CENTER);
        top.add(btnLogout, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

       
        Panel center = new Panel();
        center.setLayout(new GridBagLayout());
        Panel menu = new Panel();
        menu.setLayout(new GridLayout(5,1,20,20));
        welcome = new Label(
                "Welcome " + user.getUsername() + " to Dashboard",
                Label.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD,18));
        btnUpdate = new Button("Update Details");
        btnView = new Button("View Details");
        btnChangePassword = new Button("Change Password");
        btnDelete = new Button("Delete Account");
        menu.add(welcome);
        menu.add(btnUpdate);
        menu.add(btnView);
        menu.add(btnChangePassword);
        menu.add(btnDelete);
        center.add(menu);
        add(center,BorderLayout.CENTER);


        btnUpdate.addActionListener(this);
        btnView.addActionListener(this);
        btnChangePassword.addActionListener(this);
        btnDelete.addActionListener(this);
        btnLogout.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        
        setBackground(Color.LIGHT_GRAY);setVisible(true);


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