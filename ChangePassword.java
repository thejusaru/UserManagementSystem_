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
        setTitle("Change Password");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("CHANGE PASSWORD", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title);
        add(top, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(4,2,15,15));
        l1 = new Label("Current Password");
        l2 = new Label("New Password");
        l3 = new Label("Confirm Password");

        txtOldPassword = new TextField();
        txtNewPassword = new TextField();
        txtConfirmPassword = new TextField();

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
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSave) {
            String oldPass = txtOldPassword.getText();
            String newPass = txtNewPassword.getText();
            String confirmPass = txtConfirmPassword.getText();

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