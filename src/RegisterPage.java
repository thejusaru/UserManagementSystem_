import java.awt.*;
import java.awt.event.*;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterPage extends Frame implements ActionListener {

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

    RegisterPage() {

        fileManager = new FileManager();

        setTitle("User Management System - Register");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(240,245,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        header.setPreferredSize(new Dimension(0,90));

        title = new Label("USER REGISTRATION",Label.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,36));

        title.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(240,245,252));

        Panel card = new Panel(new BorderLayout(30,30));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(820,620));

        heading = new Label("Create Your Account",Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(5,2,30,25));

        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial",Font.BOLD,20);

        Font textFont = new Font("Arial",Font.PLAIN,20);

        l1 = new Label("Username");
        l2 = new Label("User ID");
        l3 = new Label("Password");
        l4 = new Label("Gmail Address");
        l5 = new Label("Phone Number");

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);
        l4.setFont(labelFont);
        l5.setFont(labelFont);

        txtUsername = new TextField(30);
        txtUserId = new TextField(30);
        txtPassword = new TextField(30);
        txtGmail = new TextField(30);
        txtPhone = new TextField(30);

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

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,25,10));

        buttonPanel.setBackground(Color.WHITE);

        btnSave = new Button("REGISTER");

        btnClear = new Button("CLEAR");

        btnBack = new Button("BACK");

        Font buttonFont = new Font("Arial",Font.BOLD,18);

        btnSave.setFont(buttonFont);
        btnClear.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        btnSave.setPreferredSize(new Dimension(170,50));
        btnClear.setPreferredSize(new Dimension(170,50));
        btnBack.setPreferredSize(new Dimension(170,50));

        btnSave.setBackground(new Color(46,125,50));
        btnSave.setForeground(Color.WHITE);

        btnClear.setBackground(new Color(251,140,0));
        btnClear.setForeground(Color.WHITE);

        btnBack.setBackground(new Color(25,118,210));
        btnBack.setForeground(Color.WHITE);

        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnBack);

        card.add(form,BorderLayout.CENTER);

        card.add(buttonPanel,BorderLayout.SOUTH);

        center.add(card);

        add(center,BorderLayout.CENTER);

        btnSave.addActionListener(this);

        btnClear.addActionListener(this);

        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e){

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
                        "Please fill all the required fields."
                ).setVisible(true);

                return;

            }

            if (fileManager.usernameExists(username)) {

                new MessageDialog(
                        this,
                        "Error",
                        "Username already exists."
                ).setVisible(true);

                return;

            }

            if (fileManager.userIdExists(userId)) {

                new MessageDialog(
                        this,
                        "Error",
                        "User ID already exists."
                ).setVisible(true);

                return;

            }

            if (fileManager.gmailExists(gmail)) {

                new MessageDialog(
                        this,
                        "Error",
                        "Gmail already registered."
                ).setVisible(true);

                return;

            }

            if (fileManager.phoneExists(phone)) {

                new MessageDialog(
                        this,
                        "Error",
                        "Phone number already registered."
                ).setVisible(true);

                return;

            }

            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User user = new User(username,userId,encryptedPassword,gmail,phone);

            boolean saved = fileManager.saveUser(user);

            if (saved) {

                new MessageDialog(
                        this,
                        "Success",
                        "Registration completed successfully!"
                ).setVisible(true);

                txtUsername.setText("");
                txtUserId.setText("");
                txtPassword.setText("");
                txtGmail.setText("");
                txtPhone.setText("");

                txtUsername.requestFocus();

            }

            else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to register user."
                ).setVisible(true);

            }

        }

        if (e.getSource() == btnClear) {

            txtUsername.setText("");
            txtUserId.setText("");
            txtPassword.setText("");
            txtGmail.setText("");
            txtPhone.setText("");

            txtUsername.requestFocus();

        }

        if (e.getSource() == btnBack) {

            new HomePage();

            dispose();

        }

    }

}