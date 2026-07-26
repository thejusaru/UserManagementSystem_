import java.awt.*;
import java.awt.event.*;

public class DeleteUserAdmin extends Frame implements ActionListener {

    Label title;
    Label heading;
    Label label;
    Label warning;
    Label info;

    TextField txtUsername;

    Button delete;
    Button clear;
    Button back;

    FileManager fileManager;

    DeleteUserAdmin() {

        fileManager = new FileManager();

        setTitle("User Management System - Delete User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setBackground(new Color(243,247,252));

        Panel header = new Panel(new BorderLayout());
        header.setBackground(new Color(183,28,28));
        header.setPreferredSize(new Dimension(100,90));

        title = new Label("DELETE USER", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        Panel outer = new Panel(new GridBagLayout());
        outer.setBackground(new Color(243,247,252));

        Panel card = new Panel(new BorderLayout(0,30));
        card.setPreferredSize(new Dimension(760,430));
        card.setBackground(Color.WHITE);

        heading = new Label("Remove Registered User", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setForeground(new Color(40,40,40));

        card.add(heading, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(4,1,15,18));
        form.setBackground(Color.WHITE);

        label = new Label("Username");
        label.setFont(new Font("Arial", Font.BOLD,20));

        txtUsername = new TextField();
        txtUsername.setFont(new Font("Arial",Font.PLAIN,20));

        warning = new Label("Warning",Label.CENTER);
        warning.setFont(new Font("Arial",Font.BOLD,20));
        warning.setForeground(new Color(198,40,40));

        info = new Label(
                "Deleting a user account is permanent and cannot be undone.",
                Label.CENTER);
        info.setFont(new Font("Arial",Font.PLAIN,18));
        info.setForeground(Color.GRAY);

        form.add(label);
        form.add(txtUsername);
        form.add(warning);
        form.add(info);

        card.add(form,BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,25,10));
        buttonPanel.setBackground(Color.WHITE);

        delete = new Button("DELETE");
        clear = new Button("CLEAR");
        back = new Button("BACK");

        Font btnFont = new Font("Arial",Font.BOLD,18);

        delete.setFont(btnFont);
        clear.setFont(btnFont);
        back.setFont(btnFont);

        delete.setPreferredSize(new Dimension(160,45));
        clear.setPreferredSize(new Dimension(160,45));
        back.setPreferredSize(new Dimension(160,45));

        delete.setBackground(new Color(198,40,40));
        delete.setForeground(Color.WHITE);

        clear.setBackground(new Color(255,193,7));
        clear.setForeground(Color.BLACK);

        back.setBackground(new Color(25,118,210));
        back.setForeground(Color.WHITE);

        buttonPanel.add(delete);
        buttonPanel.add(clear);
        buttonPanel.add(back);

        card.add(buttonPanel,BorderLayout.SOUTH);

        outer.add(card);

        add(outer,BorderLayout.CENTER);

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
                        "Please enter a username."
                ).setVisible(true);

                return;

            }

            User user = fileManager.findUser(username);

            if (user == null) {

                new MessageDialog(
                        this,
                        "Error",
                        "User not found."
                ).setVisible(true);

                return;

            }

            boolean result = fileManager.deleteUser(username);

            if (result) {

                new MessageDialog(
                        this,
                        "Success",
                        "User deleted successfully."
                ).setVisible(true);

                txtUsername.setText("");

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to delete user."
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