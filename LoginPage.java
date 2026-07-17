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

    setLayout(new BorderLayout(20,20));

    setBackground(new Color(235,245,255));

    //================ HEADER =================//

    Panel top = new Panel();

    top.setBackground(new Color(25,118,210));

    title = new Label("USER LOGIN", Label.CENTER);

    title.setFont(new Font("Arial", Font.BOLD, 36));

    title.setForeground(Color.WHITE);

    top.add(title);

    add(top, BorderLayout.NORTH);

    //================ LOGIN FORM =================//

    Panel form = new Panel(new GridLayout(2,2,20,20));

    form.setBackground(Color.WHITE);

    Font labelFont = new Font("Arial", Font.BOLD, 20);

    l1 = new Label("Username");
    l2 = new Label("Password");

    l1.setFont(labelFont);
    l2.setFont(labelFont);

    Font textFont = new Font("Arial", Font.PLAIN, 20);

    txtUsername = new TextField(25);
    txtPassword = new TextField(25);

    txtUsername.setFont(textFont);
    txtPassword.setFont(textFont);

    txtPassword.setEchoChar('*');

    form.add(l1);
    form.add(txtUsername);

    form.add(l2);
    form.add(txtPassword);

    //================ BUTTONS =================//

    Panel buttons = new Panel();

    buttons.setBackground(Color.WHITE);

    btnLogin = new Button("LOGIN");
    btnBack = new Button("BACK");

    Font buttonFont = new Font("Arial", Font.BOLD, 18);

    btnLogin.setFont(buttonFont);
    btnBack.setFont(buttonFont);

    btnLogin.setBackground(new Color(76,175,80));
    btnLogin.setForeground(Color.WHITE);

    btnBack.setBackground(new Color(33,150,243));
    btnBack.setForeground(Color.WHITE);

    buttons.add(btnLogin);
    buttons.add(new Label("      "));
    buttons.add(btnBack);

    //================ CARD =================//

    Panel card = new Panel(new BorderLayout(20,20));

    card.setBackground(Color.WHITE);

    Label welcome = new Label("Welcome Back!", Label.CENTER);

    welcome.setFont(new Font("Arial", Font.BOLD, 24));

    welcome.setForeground(new Color(25,25,112));

    card.add(welcome, BorderLayout.NORTH);
    card.add(form, BorderLayout.CENTER);
    card.add(buttons, BorderLayout.SOUTH);

    //================ CENTER =================//

    Panel center = new Panel(new GridBagLayout());

    center.setBackground(new Color(235,245,255));

    center.add(card);

    add(center, BorderLayout.CENTER);

    //================ EVENTS =================//

    btnLogin.addActionListener(this);
    btnBack.addActionListener(this);

    addWindowListener(new WindowAdapter() {

        public void windowClosing(WindowEvent e) {

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