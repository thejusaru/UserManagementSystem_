import java.awt.*;
import java.awt.event.*;

public class ChangePassword extends Frame implements ActionListener {

    User user;

    Label title;
    Label l1, l2, l3;

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

        setLayout(new BorderLayout(20, 20));

        setBackground(new Color(235, 245, 255));

        Panel top = new Panel();
        top.setBackground(new Color(25, 118, 210));

        title = new Label("CHANGE PASSWORD", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        top.add(title);

        add(top, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(3, 2, 20, 20));
        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font textFont = new Font("Arial", Font.PLAIN, 20);

        l1 = new Label("Current Password");
        l2 = new Label("New Password");
        l3 = new Label("Confirm Password");

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);

        txtOldPassword = new TextField(25);
        txtNewPassword = new TextField(25);
        txtConfirmPassword = new TextField(25);

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

        Panel buttons = new Panel();
        buttons.setBackground(Color.WHITE);

        btnSave = new Button("SAVE");
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

        btnBack.setBackground(new Color(244, 67, 54));
        btnBack.setForeground(Color.WHITE);

        buttons.add(btnSave);
        buttons.add(btnClear);
        buttons.add(btnBack);

        Panel card = new Panel(new BorderLayout(20, 20));
        card.setBackground(Color.WHITE);

        Label heading = new Label("Update Your Password", Label.CENTER);
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

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSave) {

            String oldPass = txtOldPassword.getText().trim();
            String newPass = txtNewPassword.getText().trim();
            String confirmPass = txtConfirmPassword.getText().trim();

            if (oldPass.equals("") || newPass.equals("") || confirmPass.equals("")) {

                new MessageDialog(this, "Error", "Please fill all the fields.").setVisible(true);
                return;

            }

            if (!oldPass.equals(user.getPassword())) {

                new MessageDialog(this, "Error", "Current Password is Incorrect.").setVisible(true);
                return;

            }

            if (!newPass.equals(confirmPass)) {

                new MessageDialog(this, "Error", "New Password and Confirm Password do not match.").setVisible(true);
                return;

            }

            user.setPassword(newPass);

            fileManager.updateUser(user);

            new MessageDialog(this, "Success", "Password Changed Successfully.").setVisible(true);

            new UserDashboard(user);

            dispose();

        }

        if (e.getSource() == btnClear) {

            txtOldPassword.setText("");
            txtNewPassword.setText("");
            txtConfirmPassword.setText("");

        }

        if (e.getSource() == btnBack) {

            new UserDashboard(user);

            dispose();

        }

    }

}