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
        setTitle("Admin Dashboard");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel(new BorderLayout());
        title = new Label("ADMIN DASHBOARD", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        btnLogout = new Button("Logout");
        top.add(title, BorderLayout.CENTER);
        top.add(btnLogout, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        Panel center = new Panel();
        center.setLayout(new GridBagLayout());
        Panel menu = new Panel();
        menu.setLayout(new GridLayout(7,1,20,20));
        welcome = new Label( "Welcome Admin to Dashboard",Label.CENTER);
        welcome.setFont(new Font("Arial",Font.BOLD,18));

        ArrayList<User> users = fileManager.getAllUsers();
        totalUsers = new Label("Total Registered Users : " + users.size(),Label.CENTER);
        btnAdd = new Button("Add User");
        btnUpdate = new Button("Update User");
        btnDelete = new Button("Delete User");
        btnSearch = new Button("Search User");
        btnView = new Button("View Users");

        menu.add(welcome);
        menu.add(totalUsers);
        menu.add(btnAdd);
        menu.add(btnUpdate);
        menu.add(btnDelete);
        menu.add(btnSearch);
        menu.add(btnView);

        center.add(menu);
        add(center,BorderLayout.CENTER);

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);
        btnView.addActionListener(this);
        btnLogout.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){
            new AddUser();
            dispose();
        }
        if(e.getSource()==btnUpdate){
            new UpdateUserAdmin();
            dispose();
        }
        if(e.getSource()==btnDelete){
            new DeleteUserAdmin();
            dispose();
        }
        if(e.getSource()==btnSearch){
            new SearchUser();
            dispose();
        }
        if(e.getSource()==btnView){
            new ViewUsers();
            dispose();
        }
        if(e.getSource()==btnLogout){
            new LoginPage();
            dispose();
        }

    }

}