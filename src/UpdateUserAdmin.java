import java.awt.*;
import java.awt.event.*;
import org.mindrot.jbcrypt.BCrypt;

public class UpdateUserAdmin extends Frame implements ActionListener {

    Label title;
    Label heading;

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
    Button back;

    User user;

    FileManager fileManager;

    UpdateUserAdmin() {

        fileManager = new FileManager();

        setTitle("User Management System - Update User");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(245,248,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        title = new Label("UPDATE USER DETAILS", Label.CENTER);

        title.setFont(new Font("Arial", Font.BOLD,34));

        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(245,248,252));

        Panel card = new Panel(new BorderLayout(20,20));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(780,600));

        heading = new Label("Search User and Update Information", Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,26));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        Panel body = new Panel(new BorderLayout(20,25));

        body.setBackground(Color.WHITE);

        Panel searchPanel = new Panel(new FlowLayout(FlowLayout.CENTER,15,15));

        searchPanel.setBackground(Color.WHITE);

        searchLabel = new Label("Username");

        searchLabel.setFont(new Font("Arial",Font.BOLD,18));

        searchUsername = new TextField(25);

        searchUsername.setFont(new Font("Arial",Font.PLAIN,18));

        searchButton = new Button("SEARCH");

        searchButton.setFont(new Font("Arial",Font.BOLD,17));

        searchButton.setBackground(new Color(33,150,243));

        searchButton.setForeground(Color.WHITE);

        searchPanel.add(searchLabel);

        searchPanel.add(searchUsername);

        searchPanel.add(searchButton);

        Panel form = new Panel(new GridLayout(5,2,18,18));

        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial",Font.BOLD,18);

        Font textFont = new Font("Arial",Font.PLAIN,18);

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

        txtUsername.setEditable(false);

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

        save = new Button("UPDATE");

        back = new Button("BACK");

        Font buttonFont = new Font("Arial",Font.BOLD,18);

        save.setFont(buttonFont);

        back.setFont(buttonFont);

        save.setBackground(new Color(255,193,7));

        save.setForeground(Color.BLACK);

        back.setBackground(new Color(33,150,243));

        back.setForeground(Color.WHITE);

        buttons.add(save);

        buttons.add(new Label("        "));

        buttons.add(back);

        body.add(searchPanel,BorderLayout.NORTH);

        body.add(form,BorderLayout.CENTER);

        body.add(buttons,BorderLayout.SOUTH);

        card.add(body,BorderLayout.CENTER);

        center.add(card);

        add(center,BorderLayout.CENTER);

        searchButton.addActionListener(this);

        save.addActionListener(this);

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
                    "Please enter a username."
            ).setVisible(true);

            return;

        }

        user = fileManager.findUser(username);

        if (user == null) {

            new MessageDialog(
                    this,
                    "Not Found",
                    "No user found with the given username."
            ).setVisible(true);

            return;

        }

        txtUsername.setText(user.getUsername());
        txtUserId.setText(user.getUserId());
        txtPassword.setText("");
        txtGmail.setText(user.getGmail());
        txtPhone.setText(user.getPhone());

    }

    if (e.getSource() == save) {

        if (user == null) {

            new MessageDialog(
                    this,
                    "Error",
                    "Search a user before updating."
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

        if (!gmail.endsWith("@gmail.com")) {

            new MessageDialog(
                    this,
                    "Invalid Gmail",
                    "Please enter a valid Gmail address."
            ).setVisible(true);

            return;

        }

        if (phone.length() != 10) {

            new MessageDialog(
                    this,
                    "Invalid Phone",
                    "Phone number must contain exactly 10 digits."
            ).setVisible(true);

            return;

        }

        if (password.length() < 6) {

            new MessageDialog(
                    this,
                    "Weak Password",
                    "Password must contain at least 6 characters."
            ).setVisible(true);

            return;

        }

        User idUser = fileManager.findUserId(userId);

        if (idUser != null &&
            !idUser.getUsername().equals(user.getUsername())) {

            new MessageDialog(
                    this,
                    "Duplicate User ID",
                    "User ID already exists."
            ).setVisible(true);

            return;

        }

        User gmailUser = fileManager.findGmail(gmail);

        if (gmailUser != null &&
            !gmailUser.getUsername().equals(user.getUsername())) {

            new MessageDialog(
                    this,
                    "Duplicate Gmail",
                    "Gmail already exists."
            ).setVisible(true);

            return;

        }

        User phoneUser = fileManager.findPhone(phone);

        if (phoneUser != null &&
            !phoneUser.getUsername().equals(user.getUsername())) {

            new MessageDialog(
                    this,
                    "Duplicate Phone",
                    "Phone number already exists."
            ).setVisible(true);

            return;

        }

        String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        User updatedUser = new User(username,userId,encryptedPassword,gmail,phone);

        boolean updated = fileManager.updateUser(updatedUser);

        if (updated) {

            user = updatedUser;

            new MessageDialog(
                    this,
                    "Success",
                    "User details updated successfully."
            ).setVisible(true);

        } else {

            new MessageDialog(
                    this,
                    "Error",
                    "Unable to update user details."
            ).setVisible(true);

        }

    }

    if (e.getSource() == back) {

        new AdminDashboard();

        dispose();

    }

}
}