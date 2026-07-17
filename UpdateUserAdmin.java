import java.awt.*;
import java.awt.event.*;

public class UpdateUserAdmin extends Frame implements ActionListener {

    Label title;

    Label searchLabel;
    TextField searchUsername;

    Button searchButton;

    Label l1, l2, l3, l4, l5;

    TextField txtUsername;
    TextField txtUserId;
    TextField txtPassword;
    TextField txtGmail;
    TextField txtPhone;

    Button save;
    Button clear;
    Button back;

    User user;

    FileManager fileManager;

    UpdateUserAdmin() {

        fileManager = new FileManager();

        setTitle("Admin - Update User");

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

        Panel searchPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        searchPanel.setBackground(Color.WHITE);

        searchLabel = new Label("Enter Username");

        searchLabel.setFont(new Font("Arial", Font.BOLD, 20));

        searchUsername = new TextField(25);

        searchUsername.setFont(new Font("Arial", Font.PLAIN, 18));

        searchButton = new Button("SEARCH");

        searchButton.setFont(new Font("Arial", Font.BOLD, 18));

        searchButton.setBackground(new Color(33, 150, 243));

        searchButton.setForeground(Color.WHITE);

        searchPanel.add(searchLabel);

        searchPanel.add(searchUsername);

        searchPanel.add(searchButton);

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

        txtUsername = new TextField();
        txtUserId = new TextField();
        txtPassword = new TextField();
        txtGmail = new TextField();
        txtPhone = new TextField();

        txtUsername.setFont(textFont);
        txtUserId.setFont(textFont);
        txtPassword.setFont(textFont);
        txtGmail.setFont(textFont);
        txtPhone.setFont(textFont);

        txtPassword.setEchoChar('*');

        txtUsername.setEditable(false);

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

        save = new Button("UPDATE");
        clear = new Button("CLEAR");
        back = new Button("BACK");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        save.setFont(buttonFont);
        clear.setFont(buttonFont);
        back.setFont(buttonFont);

        save.setBackground(new Color(255, 193, 7));
        clear.setBackground(new Color(244, 244, 244));
        back.setBackground(new Color(33, 150, 243));

        save.setForeground(Color.BLACK);
        clear.setForeground(Color.BLACK);
        back.setForeground(Color.WHITE);

        buttons.add(save);
        buttons.add(new Label("     "));
        buttons.add(clear);
        buttons.add(new Label("     "));
        buttons.add(back);

        Panel card = new Panel(new BorderLayout(20, 20));

        card.setBackground(Color.WHITE);

        card.add(searchPanel, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);
        card.add(buttons, BorderLayout.SOUTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(235, 245, 255));

        center.add(card);

        add(center, BorderLayout.CENTER);

        searchButton.addActionListener(this);
        save.addActionListener(this);
        clear.addActionListener(this);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        searchUsername.requestFocus();

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == searchButton) {

            String username = searchUsername.getText().trim();

            if (username.equals("")) {

                new MessageDialog(
                        this,
                        "Error",
                        "Enter Username."
                ).setVisible(true);

                return;

            }

            user = fileManager.findUser(username);

            if (user != null) {

                txtUsername.setText(user.getUsername());
                txtUserId.setText(user.getUserId());
                txtPassword.setText(user.getPassword());
                txtGmail.setText(user.getGmail());
                txtPhone.setText(user.getPhone());

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "User Not Found."
                ).setVisible(true);

            }

        }

        if (e.getSource() == save) {

            if (user == null) {

                new MessageDialog(
                        this,
                        "Error",
                        "Search a user first."
                ).setVisible(true);

                return;

            }

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

            User updatedUser = new User(
                    username,
                    userId,
                    password,
                    gmail,
                    phone
            );

            boolean result = fileManager.updateUser(updatedUser);

            if (result) {

                new MessageDialog(
                        this,
                        "Success",
                        "User Updated Successfully."
                ).setVisible(true);

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to Update User."
                ).setVisible(true);

            }

        }

        if (e.getSource() == clear) {

            txtUsername.setText("");
            txtUserId.setText("");
            txtPassword.setText("");
            txtGmail.setText("");
            txtPhone.setText("");
            searchUsername.setText("");

            user = null;

        }

        if (e.getSource() == back) {

            new AdminDashboard();

            dispose();

        }

    }

}