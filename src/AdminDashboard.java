import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AdminDashboard extends Frame implements ActionListener {

    Label title;
    Label heading;
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

        setLayout(new BorderLayout());

        Color background = new Color(240,245,252);

        setBackground(background);

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        title = new Label("ADMIN DASHBOARD", Label.CENTER);

        title.setFont(new Font("Segoe UI",Font.BOLD,34));

        title.setForeground(Color.WHITE);

        btnLogout = new Button("LOGOUT");

        btnLogout.setFont(new Font("Segoe UI",Font.BOLD,16));

        btnLogout.setBackground(new Color(220,53,69));

        btnLogout.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        header.add(btnLogout,BorderLayout.EAST);

        add(header,BorderLayout.NORTH);

        Panel card = new Panel(new BorderLayout(30,30));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(850,650));

        heading = new Label("Administrator Control Panel",Label.CENTER);

        heading.setFont(new Font("Segoe UI",Font.BOLD,30));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        ArrayList<User> users = fileManager.getAllUsers();

        Panel content = new Panel(new BorderLayout(25,25));

        content.setBackground(Color.WHITE);

        Panel infoPanel = new Panel(new GridLayout(2,1,10,10));

        infoPanel.setBackground(Color.WHITE);

        welcome = new Label("Welcome, Administrator",Label.CENTER);

        welcome.setFont(new Font("Segoe UI",Font.BOLD,26));

        welcome.setForeground(new Color(25,25,112));

        totalUsers = new Label(
                "Total Registered Users : " + users.size(),
                Label.CENTER);

        totalUsers.setFont(new Font("Segoe UI",Font.PLAIN,20));

        totalUsers.setForeground(Color.DARK_GRAY);

        infoPanel.add(welcome);

        infoPanel.add(totalUsers);

        content.add(infoPanel,BorderLayout.NORTH);

        Panel menu = new Panel(new GridLayout(5,1,20,20));

        menu.setBackground(Color.WHITE);

        Font buttonFont = new Font("Segoe UI",Font.BOLD,20);

        btnAdd = new Button("ADD USER");

        btnUpdate = new Button("UPDATE USER");

        btnDelete = new Button("DELETE USER");

        btnSearch = new Button("SEARCH USER");

        btnView = new Button("VIEW USERS");

        btnAdd.setFont(buttonFont);
        btnUpdate.setFont(buttonFont);
        btnDelete.setFont(buttonFont);
        btnSearch.setFont(buttonFont);
        btnView.setFont(buttonFont);

        btnAdd.setBackground(new Color(46,125,50));
        btnUpdate.setBackground(new Color(255,193,7));
        btnDelete.setBackground(new Color(220,53,69));
        btnSearch.setBackground(new Color(33,150,243));
        btnView.setBackground(new Color(123,31,162));

        btnAdd.setForeground(Color.WHITE);
        btnUpdate.setForeground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnSearch.setForeground(Color.WHITE);
        btnView.setForeground(Color.WHITE);

        menu.add(btnAdd);
        menu.add(btnUpdate);
        menu.add(btnDelete);
        menu.add(btnSearch);
        menu.add(btnView);

        content.add(menu,BorderLayout.CENTER);

        card.add(content,BorderLayout.CENTER);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(background);

        center.add(card);

        add(center,BorderLayout.CENTER);

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