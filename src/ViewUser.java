import java.awt.*;
import java.awt.event.*;

public class ViewUser extends Frame implements ActionListener {

    User user;

    Label title;
    Label heading;

    Button btnBack;

    ViewUser(User user) {

        this.user = user;

        setTitle("User Management System - My Profile");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(245,248,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        title = new Label("MY PROFILE",Label.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,34));

        title.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(245,248,252));

        Panel card = new Panel(new BorderLayout(20,20));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(720,520));

        heading = new Label("Personal Information",Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        Panel table = new Panel(new GridLayout(5,2,20,20));

        table.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial",Font.BOLD,19);

        Font valueFont = new Font("Arial",Font.PLAIN,19);

        Label l1 = new Label("Username");
        Label l2 = new Label("User ID");
        Label l3 = new Label("Password");
        Label l4 = new Label("Gmail");
        Label l5 = new Label("Phone Number");

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);
        l4.setFont(labelFont);
        l5.setFont(labelFont);

        Label v1 = new Label(user.getUsername());
        Label v2 = new Label(user.getUserId());
        Label v3 = new Label("********");
        Label v4 = new Label(user.getGmail());
        Label v5 = new Label(user.getPhone());

        v1.setFont(valueFont);
        v2.setFont(valueFont);
        v3.setFont(valueFont);
        v4.setFont(valueFont);
        v5.setFont(valueFont);

        v1.setForeground(new Color(60,60,60));
        v2.setForeground(new Color(60,60,60));
        v3.setForeground(new Color(60,60,60));
        v4.setForeground(new Color(60,60,60));
        v5.setForeground(new Color(60,60,60));

        table.add(l1);
        table.add(v1);

        table.add(l2);
        table.add(v2);

        table.add(l3);
        table.add(v3);

        table.add(l4);
        table.add(v4);

        table.add(l5);
        table.add(v5);

        Panel buttonPanel = new Panel();

        buttonPanel.setBackground(Color.WHITE);

        btnBack = new Button("BACK");

        btnBack.setFont(new Font("Arial",Font.BOLD,18));

        btnBack.setBackground(new Color(33,150,243));

        btnBack.setForeground(Color.WHITE);

        buttonPanel.add(btnBack);

        card.add(table,BorderLayout.CENTER);

        card.add(buttonPanel,BorderLayout.SOUTH);

        center.add(card);

        add(center,BorderLayout.CENTER);

        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        setVisible(true);

    }
        public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnBack) {

            new UserDashboard(user);

            dispose();

        }

    }

}