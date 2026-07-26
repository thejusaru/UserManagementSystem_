import java.awt.*;
import java.awt.event.*;

public class AddUser extends Frame implements ActionListener {

    Label title;
    Label heading;
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

    AddUser() {

        fileManager = new FileManager();

        setTitle("User Management System - Add User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Color background = new Color(240,245,252);
        setBackground(background);

        Panel header = new Panel();

        header.setBackground(new Color(25,118,210));

        title = new Label("ADD NEW USER",Label.CENTER);

        title.setFont(new Font("Segoe UI",Font.BOLD,34));

        title.setForeground(Color.WHITE);

        header.add(title);

        add(header,BorderLayout.NORTH);

        Panel card = new Panel();

        card.setLayout(new BorderLayout(20,20));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(650,560));

        heading = new Label("Create New User Account",Label.CENTER);

        heading.setFont(new Font("Segoe UI",Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        Panel form = new Panel();

        form.setLayout(new GridLayout(5,2,25,25));

        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI",Font.BOLD,20);

        Font textFont = new Font("Segoe UI",Font.PLAIN,20);

        l1 = new Label("Username");
        l2 = new Label("User ID");
        l3 = new Label("Password");
        l4 = new Label("Email");
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

        card.add(form,BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,25,15));

        buttonPanel.setBackground(Color.WHITE);

        Font buttonFont = new Font("Segoe UI",Font.BOLD,18);

        btnSave = new Button("SAVE USER");

        btnClear = new Button("CLEAR");

        btnBack = new Button("BACK");

        btnSave.setFont(buttonFont);
        btnClear.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        btnSave.setPreferredSize(new Dimension(170,45));
        btnClear.setPreferredSize(new Dimension(150,45));
        btnBack.setPreferredSize(new Dimension(150,45));

        btnSave.setBackground(new Color(46,125,50));
        btnSave.setForeground(Color.WHITE);

        btnClear.setBackground(new Color(255,193,7));
        btnClear.setForeground(Color.BLACK);

        btnBack.setBackground(new Color(33,150,243));
        btnBack.setForeground(Color.WHITE);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnBack);

        card.add(buttonPanel,BorderLayout.SOUTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(background);

        center.add(card);

        add(center,BorderLayout.CENTER);

        btnSave.addActionListener(this);
        btnClear.addActionListener(this);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

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
                        "User Added Successfully."
                ).setVisible(true);

                txtUsername.setText("");
                txtUserId.setText("");
                txtPassword.setText("");
                txtGmail.setText("");
                txtPhone.setText("");

            }

            else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to Add User."
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

            new AdminDashboard();

            dispose();

        }

    }

}