import java.awt.*;
import java.awt.event.*;

public class ProfilePage extends Frame implements ActionListener {

    User user;

    Label title;

    Label lblUsername;
    Label lblUserId;
    Label lblPassword;
    Label lblGmail;
    Label lblPhone;

    Label valUsername;
    Label valUserId;
    Label valPassword;
    Label valGmail;
    Label valPhone;

    Button btnRefresh;
    Button btnDashboard;
    Button btnLogout;

    ProfilePage(User user) {

        this.user = user;

        setTitle("User Management System - My Profile");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(240,245,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        header.setPreferredSize(new Dimension(0,90));

        title = new Label("MY PROFILE",Label.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,36));

        title.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(240,245,252));

        Panel card = new Panel(new BorderLayout(20,30));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(750,500));

        Label heading = new Label("User Information",Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(5,2,25,25));

        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial",Font.BOLD,20);

        Font valueFont = new Font("Arial",Font.PLAIN,20);

        lblUsername = new Label("Username");

        lblUserId = new Label("User ID");

        lblPassword = new Label("Password");

        lblGmail = new Label("Gmail");

        lblPhone = new Label("Phone Number");

        lblUsername.setFont(labelFont);
        lblUserId.setFont(labelFont);
        lblPassword.setFont(labelFont);
        lblGmail.setFont(labelFont);
        lblPhone.setFont(labelFont);

        valUsername = new Label();
        valUserId = new Label();
        valPassword = new Label();
        valGmail = new Label();
        valPhone = new Label();

        valUsername.setFont(valueFont);
        valUserId.setFont(valueFont);
        valPassword.setFont(valueFont);
        valGmail.setFont(valueFont);
        valPhone.setFont(valueFont);

        form.add(lblUsername);
        form.add(valUsername);

        form.add(lblUserId);
        form.add(valUserId);

        form.add(lblPassword);
        form.add(valPassword);

        form.add(lblGmail);
        form.add(valGmail);

        form.add(lblPhone);
        form.add(valPhone);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,25,15));

        buttonPanel.setBackground(Color.WHITE);

        btnRefresh = new Button("REFRESH");

        btnDashboard = new Button("DASHBOARD");

        btnLogout = new Button("LOGOUT");

        Font buttonFont = new Font("Arial",Font.BOLD,18);

        btnRefresh.setFont(buttonFont);
        btnDashboard.setFont(buttonFont);
        btnLogout.setFont(buttonFont);

        btnRefresh.setPreferredSize(new Dimension(170,50));
        btnDashboard.setPreferredSize(new Dimension(170,50));
        btnLogout.setPreferredSize(new Dimension(170,50));

        btnRefresh.setBackground(new Color(255,193,7));
        btnRefresh.setForeground(Color.BLACK);

        btnDashboard.setBackground(new Color(25,118,210));
        btnDashboard.setForeground(Color.WHITE);

        btnLogout.setBackground(new Color(220,53,69));
        btnLogout.setForeground(Color.WHITE);

        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnDashboard);
        buttonPanel.add(btnLogout);

        card.add(form,BorderLayout.CENTER);
        card.add(buttonPanel,BorderLayout.SOUTH);

        center.add(card);

        add(center,BorderLayout.CENTER);

        displayProfile();

        btnRefresh.addActionListener(this);
        btnDashboard.addActionListener(this);
        btnLogout.addActionListener(this);

        addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e){

                System.exit(0);

            }

        });

        setVisible(true);

    }

    void displayProfile() {

        valUsername.setText(user.getUsername());

        valUserId.setText(user.getUserId());

        valPassword.setText("********");

        valGmail.setText(user.getGmail());

        valPhone.setText(user.getPhone());

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnRefresh){

            displayProfile();

        }

        if(e.getSource()==btnDashboard){

            new UserDashboard(user);

            dispose();

        }

        if(e.getSource()==btnLogout){

            new LoginPage();

            dispose();

        }

    }

}