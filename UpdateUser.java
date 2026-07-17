import java.awt.*;
import java.awt.event.*;

public class UpdateUser extends Frame implements ActionListener {

    User user;

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

    UpdateUser(User user) {

        this.user = user;

        fileManager = new FileManager();

        setTitle("User Management System - Update Profile");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout(20, 20));

        setBackground(new Color(235, 245, 255));

        Panel top = new Panel();

        top.setBackground(new Color(255, 193, 7));

        title = new Label("UPDATE USER DETAILS", Label.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 34));

        title.setForeground(Color.BLACK);

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

        txtUsername = new TextField(user.getUsername(), 25);
        txtUserId = new TextField(user.getUserId(), 25);
        txtPassword = new TextField(user.getPassword(), 25);
        txtGmail = new TextField(user.getGmail(), 25);
        txtPhone = new TextField(user.getPhone(), 25);

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

        btnSave = new Button("UPDATE");
        btnClear = new Button("CLEAR");
        btnBack = new Button("BACK");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        btnSave.setFont(buttonFont);
        btnClear.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        btnSave.setBackground(new Color(255, 193, 7));
        btnSave.setForeground(Color.BLACK);

        btnClear.setBackground(new Color(244, 244, 244));
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

        Label heading = new Label("Update Your Information", Label.CENTER);

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

            if (!gmail.endsWith("@gmail.com")) {

                new MessageDialog(
                        this,
                        "Error",
                        "Enter a valid Gmail address."
                ).setVisible(true);

                return;

            }

            if (phone.length() != 10) {

                new MessageDialog(
                        this,
                        "Error",
                        "Phone number must contain 10 digits."
                ).setVisible(true);

                return;

            }

            if (password.length() < 6) {

                new MessageDialog(
                        this,
                        "Error",
                        "Password must contain at least 6 characters."
                ).setVisible(true);

                return;

            }

            User updatedUser = new User(
                    username,
                    userId,
                    password,
                    gmail,
                    phone
            );

            boolean updated = fileManager.updateUser(updatedUser);

            if (updated) {

                new MessageDialog(
                        this,
                        "Success",
                        "User Details Updated Successfully."
                ).setVisible(true);

                new UserDashboard(updatedUser);

                dispose();

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to Update User."
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

            new UserDashboard(user);

            dispose();

        }

    }

}