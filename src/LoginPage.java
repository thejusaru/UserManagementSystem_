import java.awt.*;
import java.awt.event.*;

public class LoginPage extends Frame implements ActionListener {

    Label title;
    Label l1, l2;

    TextField txtUsername;
    TextField txtPassword;

    Button btnLogin;
    Button btnBack;

    FileManager fileManager;

    LoginPage() {

    fileManager = new FileManager();

    setTitle("User Management System - Login");

    setExtendedState(Frame.MAXIMIZED_BOTH);

    setLayout(new BorderLayout());

    setBackground(new Color(240,245,252));

    Panel header = new Panel(new BorderLayout());

    header.setBackground(new Color(25,118,210));

    header.setPreferredSize(new Dimension(0,90));

    title = new Label("USER LOGIN",Label.CENTER);

    title.setFont(new Font("Arial",Font.BOLD,36));

    title.setForeground(Color.WHITE);

    header.add(title,BorderLayout.CENTER);

    add(header,BorderLayout.NORTH);

    Panel center = new Panel(new GridBagLayout());

    center.setBackground(new Color(240,245,252));

    Panel card = new Panel(new BorderLayout(20,35));

    card.setBackground(Color.WHITE);

    card.setPreferredSize(new Dimension(700,500));

    Panel topSection = new Panel(new GridLayout(2,1,10,15));

    topSection.setBackground(Color.WHITE);

    Label welcome = new Label("Welcome Back",Label.CENTER);

    welcome.setFont(new Font("Arial",Font.BOLD,30));

    welcome.setForeground(new Color(25,25,112));

    Label subtitle = new Label("Sign in to continue to your account",Label.CENTER);

    subtitle.setFont(new Font("Arial",Font.PLAIN,18));

    subtitle.setForeground(Color.GRAY);

    topSection.add(welcome);

    topSection.add(subtitle);

    Panel form = new Panel(new GridLayout(2,2,25,30));

    form.setBackground(Color.WHITE);

    Font labelFont = new Font("Arial",Font.BOLD,20);

    Font textFont = new Font("Arial",Font.PLAIN,20);

    l1 = new Label("Username");

    l2 = new Label("Password");

    l1.setFont(labelFont);

    l2.setFont(labelFont);

    txtUsername = new TextField(30);

    txtPassword = new TextField(30);

    txtUsername.setFont(textFont);

    txtPassword.setFont(textFont);

    txtPassword.setEchoChar('*');

    form.add(l1);

    form.add(txtUsername);

    form.add(l2);

    form.add(txtPassword);

    Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,30,15));

    buttonPanel.setBackground(Color.WHITE);

    btnLogin = new Button("LOGIN");

    btnBack = new Button("BACK");

    Font buttonFont = new Font("Arial",Font.BOLD,20);

    btnLogin.setFont(buttonFont);

    btnBack.setFont(buttonFont);

    btnLogin.setPreferredSize(new Dimension(180,55));

    btnBack.setPreferredSize(new Dimension(180,55));

    btnLogin.setBackground(new Color(46,125,50));

    btnLogin.setForeground(Color.WHITE);

    btnBack.setBackground(new Color(25,118,210));

    btnBack.setForeground(Color.WHITE);

    buttonPanel.add(btnLogin);

    buttonPanel.add(btnBack);

    card.add(topSection,BorderLayout.NORTH);

    card.add(form,BorderLayout.CENTER);

    card.add(buttonPanel,BorderLayout.SOUTH);

    center.add(card);

    add(center,BorderLayout.CENTER);

    btnLogin.addActionListener(this);

    btnBack.addActionListener(this);

    addWindowListener(new WindowAdapter(){

        public void windowClosing(WindowEvent e){

            System.exit(0);

        }

    });

    setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogin) {

            String username = txtUsername.getText().trim();
            String password = txtPassword.getText().trim();

            // Empty validation
            if (username.equals("") || password.equals("")) {

                new MessageDialog(
                        this,
                        "Error",
                        "Please enter Username and Password."
                ).setVisible(true);

                return;
            }

            if (username.equals("admin") && password.equals("admin")) {

                new AdminDashboard();

                dispose();

                return;
            }

            User user = fileManager.findUser(username);

            if (user == null) {

                Dialog d = new Dialog(this, "User Not Registered", true);

                d.setLayout(new FlowLayout());

                Label msg = new Label(
                        "User not registered. Do you want to register?"
                );

                Button yes = new Button("Yes");
                Button no = new Button("No");

                d.add(msg);
                d.add(yes);
                d.add(no);

                yes.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        d.dispose();

                        new RegisterPage();

                        dispose();

                    }

                });

                no.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        d.dispose();

                    }

                });

                d.setSize(420,150);
                
                d.setVisible(true);

                return;

            }

            if (!user.getPassword().equals(password)) {

                Dialog d = new Dialog(this, "Login Failed", true);

                d.setLayout(new FlowLayout());

                Label msg = new Label(
                        "Invalid Username or Password"
                );

                Button retry = new Button("Try Again");

                d.add(msg);
                d.add(retry);

                retry.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        d.dispose();

                        txtPassword.setText("");

                        txtPassword.requestFocus();

                    }

                });

                d.setSize(300,150);

                d.setVisible(true);

                return;

            }


            new UserDashboard(user);

            dispose();

        }

        if (e.getSource() == btnBack) {

            new HomePage();

            dispose();

        }

    }

}