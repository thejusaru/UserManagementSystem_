import java.awt.*;
import java.awt.event.*;

public class AddUser extends Frame implements ActionListener {

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

    AddUser() {

        fileManager = new FileManager();

        setTitle("Add User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("ADD USER", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title);
        add(top, BorderLayout.NORTH);


        Panel form = new Panel(new GridLayout(6,2,15,15));

        l1 = new Label("Username");
        l2 = new Label("User ID");
        l3 = new Label("Password");
        l4 = new Label("Gmail");
        l5 = new Label("Phone Number");

        txtUsername = new TextField();
        txtUserId = new TextField();
        txtPassword = new TextField();
        txtGmail = new TextField();
        txtPhone = new TextField();

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

        btnSave = new Button("Save");
        btnClear = new Button("Clear");
        btnBack = new Button("Back");

        buttons.add(btnSave);
        buttons.add(btnClear);
        buttons.add(btnBack);

        Panel center = new Panel(new BorderLayout());
        center.add(form, BorderLayout.CENTER);
        center.add(buttons, BorderLayout.SOUTH);

        Panel outer = new Panel(new GridBagLayout());
        outer.add(center);
        add(outer, BorderLayout.CENTER);

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

                new MessageDialog(this,"Error", "Username already exists.").setVisible(true);
                return;
            }

            User user = new User( username, userId, password, gmail, phone);

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

            } else {

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