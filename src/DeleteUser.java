import java.awt.*;
import java.awt.event.*;

public class DeleteUser extends Frame implements ActionListener {

    User user;

    Label title;
    Label heading;
    Label msg;
    Label warning;

    Button btnDelete;
    Button btnBack;

    FileManager fileManager;

    DeleteUser(User user) {

        this.user = user;

        fileManager = new FileManager();

        setTitle("User Management System - Delete Account");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(240,245,252));

        //================ HEADER =================//

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(220,53,69));

        header.setPreferredSize(new Dimension(100,90));

        title = new Label("DELETE ACCOUNT", Label.CENTER);

        title.setFont(new Font("Segoe UI", Font.BOLD,34));

        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        //================ MESSAGE PANEL =================//

        Panel messagePanel = new Panel(new GridLayout(3,1,10,15));

        messagePanel.setBackground(Color.WHITE);

        heading = new Label("⚠ WARNING", Label.CENTER);

        heading.setFont(new Font("Segoe UI", Font.BOLD,28));

        heading.setForeground(new Color(220,53,69));

        msg = new Label(
                "Are you sure you want to permanently delete your account?",
                Label.CENTER);

        msg.setFont(new Font("Segoe UI", Font.BOLD,21));

        msg.setForeground(new Color(60,60,60));

        warning = new Label(
                "This action cannot be undone and all your data will be removed.",
                Label.CENTER);

        warning.setFont(new Font("Segoe UI", Font.PLAIN,18));

        warning.setForeground(Color.GRAY);

        messagePanel.add(heading);

        messagePanel.add(msg);

        messagePanel.add(warning);

        //================ BUTTON PANEL =================//

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,30,15));

        buttonPanel.setBackground(Color.WHITE);

        btnDelete = new Button("DELETE ACCOUNT");

        btnBack = new Button("CANCEL");

        Font buttonFont = new Font("Segoe UI", Font.BOLD,18);

        btnDelete.setFont(buttonFont);

        btnBack.setFont(buttonFont);

        btnDelete.setPreferredSize(new Dimension(180,45));

        btnBack.setPreferredSize(new Dimension(150,45));

        btnDelete.setBackground(new Color(220,53,69));

        btnDelete.setForeground(Color.WHITE);

        btnBack.setBackground(new Color(33,150,243));

        btnBack.setForeground(Color.WHITE);

        buttonPanel.add(btnDelete);

        buttonPanel.add(btnBack);

        //================ CARD =================//

        Panel card = new Panel(new BorderLayout(30,30));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(760,380));

        card.add(messagePanel, BorderLayout.CENTER);

        card.add(buttonPanel, BorderLayout.SOUTH);

        //================ CENTER =================//

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(240,245,252));

        center.add(card);

        add(center, BorderLayout.CENTER);

        //================ EVENTS =================//

        btnDelete.addActionListener(this);

        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        setVisible(true);

    }
        public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnDelete) {

            boolean deleted = fileManager.deleteUser(user.getUsername());

            if (deleted) {

                new MessageDialog(
                        this,
                        "Success",
                        "Your account has been deleted successfully."
                ).setVisible(true);

                new HomePage();

                dispose();

            } else {

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to delete your account."
                ).setVisible(true);

            }

        }

        if (e.getSource() == btnBack) {

            new UserDashboard(user);

            dispose();

        }

    }

}