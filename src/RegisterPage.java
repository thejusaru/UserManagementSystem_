import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends Frame implements ActionListener {

    Label title;
    Label l1, l2, l3, l4, l5;

    TextField txtUsername;
    TextField txtUserId;
    TextField txtPassword;
    TextField txtGmail;
    TextField txtPhone;

    Button btnSave;
    Button btnClear;
    Button btnBack;

    FileManager fileManager;

    RegisterPage() {

        fileManager = new FileManager();

        setTitle("User Management System - Register");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(235, 245, 255));

        Panel top = new Panel();
        top.setBackground(new Color(25, 118, 210));

        title = new Label("USER REGISTRATION", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        top.add(title);
        add(top, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(5, 2, 20, 20));
        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial", Font.BOLD, 20);

        l1 = new Label("Username");
        l2 = new Label("User ID");
        l3 = new Label("Password");
        l4 = new Label("Gmail");
        l5 = new Label("Phone Number");

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);
        l4.setFont(labelFont);
        l5.setFont(labelFont);

        Font textFont = new Font("Arial", Font.PLAIN, 20);

        txtUsername = new TextField(25);
        txtUserId = new TextField(25);
        txtPassword = new TextField(25);
        txtGmail = new TextField(25);
        txtPhone = new TextField(25);

        txtUsername.setFont(textFont);
        txtUserId.setFont(textFont);
        txtPassword.setFont(textFont);
        txtGmail.setFont(textFont);
        txtPhone.setFont(textFont);

        txtPassword.setEchoChar('*');

        form.add(l1);
        form.add(txtUsername);

        form.add(l2);
        form.add(txtUserId);

        form.add(l3);
        form.add(txtPassword);

        form.add(l4);
        form.add(txtGmail);

        form.add(l5);
        form.add(txtPhone);

        Panel buttons = new Panel();
        buttons.setBackground(Color.WHITE);

        btnSave = new Button("REGISTER");
        btnClear = new Button("CLEAR");
        btnBack = new Button("BACK");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        btnSave.setFont(buttonFont);
        btnClear.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        btnSave.setBackground(new Color(76, 175, 80));
        btnSave.setForeground(Color.WHITE);

        btnClear.setBackground(new Color(255, 193, 7));
        btnClear.setForeground(Color.BLACK);

        btnBack.setBackground(new Color(33, 150, 243));
        btnBack.setForeground(Color.WHITE);

        buttons.add(btnSave);
        buttons.add(new Label("     "));
        buttons.add(btnClear);
        buttons.add(new Label("     "));
        buttons.add(btnBack);

        Panel card = new Panel(new BorderLayout(20, 20));
        card.setBackground(Color.WHITE);

        Label heading = new Label("Create Your Account", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(25, 25, 112));

        card.add(heading, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);
        card.add(buttons, BorderLayout.SOUTH);

        Panel center = new Panel(new GridBagLayout());
        center.setBackground(new Color(235, 245, 255));

        center.add(card);

        add(center, BorderLayout.CENTER);

        btnSave.addActionListener(this);
        btnClear.addActionListener(this);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        txtUsername.requestFocus();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSave) {

            String username = txtUsername.getText().trim();
            String userId = txtUserId.getText().trim();
            String password = txtPassword.getText().trim();
            String gmail = txtGmail.getText().trim();
            String phone = txtPhone.getText().trim();

            if (username.equals("") ||
                userId.equals("") ||
                password.equals("") ||
                gmail.equals("") ||
                phone.equals("")) {

                new MessageDialog(
                        this,
                        "Error",
                        "Please fill all the fields."
                ).setVisible(true);

                return;
            }

            if (fileManager.findUser(username) != null) {

                new MessageDialog(
                        this,
                        "Error",
                        "Username already exists."
                ).setVisible(true);

                return;
            }

            User user = new User(
                    username,
                    userId,
                    password,
                    gmail,
                    phone
            );

            boolean saved = fileManager.saveUser(user);

            if (saved) {

                new MessageDialog(
                        this,
                        "Success",
                        "User Registered Successfully!"
                ).setVisible(true);

                txtUsername.setText("");
                txtUserId.setText("");
                txtPassword.setText("");
                txtGmail.setText("");
                txtPhone.setText("");

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Registration Failed."
                ).setVisible(true);
            }
        }

        if (e.getSource() == btnClear) {

            txtUsername.setText("");
            txtUserId.setText("");
            txtPassword.setText("");
            txtGmail.setText("");
            txtPhone.setText("");
        }

        if (e.getSource() == btnBack) {

            new HomePage();
            dispose();
        }
    }
}