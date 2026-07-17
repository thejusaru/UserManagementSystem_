import java.awt.*;
import java.awt.event.*;

public class DeleteUserAdmin extends Frame implements ActionListener {

    Label title;
    Label label;
    Label warning;

    TextField txtUsername;

    Button delete;
    Button clear;
    Button back;

    FileManager fileManager;

    DeleteUserAdmin() {

        fileManager = new FileManager();

        setTitle("User Management System - Delete User");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout(20, 20));

        setBackground(new Color(235, 245, 255));

        Panel top = new Panel();
        top.setBackground(new Color(220, 53, 69));

        title = new Label("DELETE USER", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        top.add(title);

        add(top, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(2, 2, 20, 20));
        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial", Font.BOLD, 20);
        Font textFont = new Font("Arial", Font.PLAIN, 20);

        label = new Label("Enter Username");
        label.setFont(labelFont);

        txtUsername = new TextField(25);
        txtUsername.setFont(textFont);

        warning = new Label("Warning");
        warning.setFont(labelFont);
        warning.setForeground(Color.RED);

        Label info = new Label("Deleting a user is permanent.");
        info.setFont(new Font("Arial", Font.PLAIN, 18));
        info.setForeground(Color.DARK_GRAY);

        form.add(label);
        form.add(txtUsername);
        form.add(warning);
        form.add(info);

        Panel buttons = new Panel();
        buttons.setBackground(Color.WHITE);

        delete = new Button("DELETE");
        clear = new Button("CLEAR");
        back = new Button("BACK");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        delete.setFont(buttonFont);
        clear.setFont(buttonFont);
        back.setFont(buttonFont);

        delete.setBackground(new Color(220, 53, 69));
        delete.setForeground(Color.WHITE);

        clear.setBackground(new Color(255, 193, 7));
        clear.setForeground(Color.BLACK);

        back.setBackground(new Color(33, 150, 243));
        back.setForeground(Color.WHITE);

        buttons.add(delete);
        buttons.add(new Label("     "));
        buttons.add(clear);
        buttons.add(new Label("     "));
        buttons.add(back);

        Panel card = new Panel(new BorderLayout(20, 20));
        card.setBackground(Color.WHITE);

        Label heading = new Label("Delete Registered User", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(25, 25, 112));

        card.add(heading, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);
        card.add(buttons, BorderLayout.SOUTH);

        Panel center = new Panel(new GridBagLayout());
        center.setBackground(new Color(235, 245, 255));

        center.add(card);

        add(center, BorderLayout.CENTER);

        delete.addActionListener(this);
        clear.addActionListener(this);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == delete) {

            String username = txtUsername.getText().trim();

            if (username.equals("")) {

                new MessageDialog(
                        this,
                        "Error",
                        "Enter Username"
                ).setVisible(true);

                return;

            }

            User user = fileManager.findUser(username);

            if (user == null) {

                new MessageDialog(
                        this,
                        "Error",
                        "User Not Found"
                ).setVisible(true);

                return;

            }

            boolean result = fileManager.deleteUser(username);

            if (result) {

                new MessageDialog(
                        this,
                        "Success",
                        "User Deleted Successfully"
                ).setVisible(true);

                txtUsername.setText("");

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Delete Failed"
                ).setVisible(true);

            }

        }

        if (e.getSource() == clear) {

            txtUsername.setText("");

        }

        if (e.getSource() == back) {

            new AdminDashboard();

            dispose();

        }

    }

}