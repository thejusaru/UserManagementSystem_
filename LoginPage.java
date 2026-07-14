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

        setTitle("User Login");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());


        Panel top = new Panel();

        title = new Label("USER LOGIN", Label.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 28));

        top.add(title);

        add(top, BorderLayout.NORTH);


        Panel form = new Panel();

        form.setLayout(new GridLayout(3, 2, 15, 15));

        l1 = new Label("Username");
        l2 = new Label("Password");

        txtUsername = new TextField();

        txtPassword = new TextField();
        txtPassword.setEchoChar('*');

        form.add(l1);
        form.add(txtUsername);

        form.add(l2);
        form.add(txtPassword);


        Panel buttons = new Panel();

        btnLogin = new Button("Login");

        btnBack = new Button("Back");

        buttons.add(btnLogin);
        buttons.add(btnBack);

        Panel center = new Panel(new BorderLayout());

        center.add(form, BorderLayout.CENTER);

        center.add(buttons, BorderLayout.SOUTH);

        Panel outer = new Panel(new GridBagLayout());

        outer.add(center);

        add(outer, BorderLayout.CENTER);

        btnLogin.addActionListener(this);
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