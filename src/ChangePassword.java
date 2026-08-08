import java.awt.*;
import java.awt.event.*;
import org.mindrot.jbcrypt.BCrypt;

public class ChangePassword extends Frame implements ActionListener {

    User user;

    Label title;
    Label heading;

    Label l1;
    Label l2;
    Label l3;

    TextField txtOldPassword;
    TextField txtNewPassword;
    TextField txtConfirmPassword;

    Button btnSave;
    Button btnClear;
    Button btnBack;

    FileManager fileManager;

    ChangePassword(User user) {

        this.user = user;

        fileManager = new FileManager();

        setTitle("User Management System - Change Password");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(240,245,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        header.setPreferredSize(new Dimension(100,90));

        title = new Label("CHANGE PASSWORD", Label.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD,34));

        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(3,2,30,30));

        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Segoe UI", Font.BOLD,20);

        Font textFont = new Font("Segoe UI", Font.PLAIN,20);

        l1 = new Label("Current Password");

        l2 = new Label("New Password");

        l3 = new Label("Confirm Password");

        l1.setFont(labelFont);

        l2.setFont(labelFont);

        l3.setFont(labelFont);

        txtOldPassword = new TextField(28);

        txtNewPassword = new TextField(28);

        txtConfirmPassword = new TextField(28);

        txtOldPassword.setFont(textFont);

        txtNewPassword.setFont(textFont);

        txtConfirmPassword.setFont(textFont);

        txtOldPassword.setEchoChar('*');

        txtNewPassword.setEchoChar('*');

        txtConfirmPassword.setEchoChar('*');

        form.add(l1);
        form.add(txtOldPassword);

        form.add(l2);
        form.add(txtNewPassword);

        form.add(l3);
        form.add(txtConfirmPassword);

        Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER,25,10));

        buttons.setBackground(Color.WHITE);

        btnSave = new Button("SAVE");

        btnClear = new Button("CLEAR");

        btnBack = new Button("BACK");

        Font buttonFont = new Font("Segoe UI", Font.BOLD,18);

        btnSave.setFont(buttonFont);

        btnClear.setFont(buttonFont);

        btnBack.setFont(buttonFont);

        btnSave.setPreferredSize(new Dimension(140,42));

        btnClear.setPreferredSize(new Dimension(140,42));

        btnBack.setPreferredSize(new Dimension(140,42));

        btnSave.setBackground(new Color(46,204,113));

        btnSave.setForeground(Color.WHITE);

        btnClear.setBackground(new Color(255,193,7));

        btnClear.setForeground(Color.BLACK);

        btnBack.setBackground(new Color(231,76,60));

        btnBack.setForeground(Color.WHITE);

        buttons.add(btnSave);

        buttons.add(btnClear);

        buttons.add(btnBack);

        Panel card = new Panel(new BorderLayout(30,30));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(720,470));

        heading = new Label("Update Your Account Password", Label.CENTER);

        heading.setFont(new Font("Segoe UI", Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading, BorderLayout.NORTH);

        card.add(form, BorderLayout.CENTER);

        card.add(buttons, BorderLayout.SOUTH);

        Panel wrapper = new Panel(new GridBagLayout());

        wrapper.setBackground(new Color(240,245,252));

        wrapper.add(card);

        add(wrapper, BorderLayout.CENTER);

        btnSave.addActionListener(this);

        btnClear.addActionListener(this);

        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        txtOldPassword.requestFocus();

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSave) {

            String oldPass = txtOldPassword.getText().trim();

            String newPass = txtNewPassword.getText().trim();

            String confirmPass = txtConfirmPassword.getText().trim();

            if (oldPass.equals("") ||
                newPass.equals("") ||
                confirmPass.equals("")) {

                new MessageDialog(
                        this,
                        "Error",
                        "Please fill all the fields."
                ).setVisible(true);

                return;

            }

            if (!BCrypt.checkpw(oldPass, user.getPassword())) {

                new MessageDialog(
                        this,
                        "Error",
                        "Current password is incorrect."
                ).setVisible(true);

                return;

            }
                        if (newPass.length() < 6) {

                new MessageDialog(
                        this,
                        "Error",
                        "Password must contain at least 6 characters."
                ).setVisible(true);

                return;

            }

            if (BCrypt.checkpw(newPass, user.getPassword())) {

                new MessageDialog(
                        this,
                        "Error",
                        "New password cannot be the same as the current password."
                ).setVisible(true);

                return;

            }

            if (!newPass.equals(confirmPass)) {

                new MessageDialog(
                        this,
                        "Error",
                        "New password and Confirm password do not match."
                ).setVisible(true);

                return;

            }

            String encryptedPassword = BCrypt.hashpw(newPass, BCrypt.gensalt());

            user.setPassword(encryptedPassword);

            boolean updated = fileManager.updateUser(user);

            if (updated) {

                new MessageDialog(
                        this,
                        "Success",
                        "Password changed successfully."
                ).setVisible(true);

                new UserDashboard(user);

                dispose();

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to change password."
                ).setVisible(true);

            }

        }

        if (e.getSource() == btnClear) {

            txtOldPassword.setText("");

            txtNewPassword.setText("");

            txtConfirmPassword.setText("");

            txtOldPassword.requestFocus();

        }

        if (e.getSource() == btnBack) {

            new UserDashboard(user);

            dispose();

        }

    }

}
            
