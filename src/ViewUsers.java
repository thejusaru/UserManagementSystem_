import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ViewUsers extends Frame implements ActionListener {

    Label title;
    Label heading;

    Button back;

    FileManager fileManager;

    Panel tablePanel;

    ViewUsers() {

        fileManager = new FileManager();

        setTitle("User Management System - View Users");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        setBackground(new Color(245,248,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        title = new Label("REGISTERED USERS",Label.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,34));

        title.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(245,248,252));

        Panel card = new Panel(new BorderLayout(20,20));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(1100,600));

        heading = new Label("All Registered Users",Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        tablePanel = new Panel();

        tablePanel.setBackground(Color.WHITE);

        loadUsers();

        card.add(tablePanel,BorderLayout.CENTER);

        Panel buttonPanel = new Panel();

        buttonPanel.setBackground(Color.WHITE);

        back = new Button("BACK");

        back.setFont(new Font("Arial",Font.BOLD,18));

        back.setBackground(new Color(33,150,243));

        back.setForeground(Color.WHITE);

        buttonPanel.add(back);

        card.add(buttonPanel,BorderLayout.SOUTH);

        center.add(card);

        add(center,BorderLayout.CENTER);

        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        setVisible(true);

    }

    void loadUsers() {

        ArrayList<User> users = fileManager.getAllUsers();

        int rows = users.size() + 1;

        tablePanel.setLayout(new GridLayout(rows,5,8,8));

        tablePanel.removeAll();

        Font headerFont = new Font("Arial",Font.BOLD,18);

        Font rowFont = new Font("Arial",Font.PLAIN,17);

        addHeader("Username",headerFont);
        addHeader("User ID",headerFont);
        addHeader("Password",headerFont);
        addHeader("Gmail",headerFont);
        addHeader("Phone Number",headerFont);

        for(User u : users) {

            addRow(u.getUsername(),rowFont);
            addRow(u.getUserId(),rowFont);
            addRow("********",rowFont);
            addRow(u.getGmail(),rowFont);
            addRow(u.getPhone(),rowFont);

        }

    }

    void addHeader(String text, Font font) {

        Label lbl = new Label(text,Label.CENTER);

        lbl.setFont(font);

        lbl.setBackground(new Color(25,118,210));

        lbl.setForeground(Color.WHITE);

        tablePanel.add(lbl);

    }

    void addRow(String text, Font font) {

        Label lbl = new Label(text,Label.CENTER);

        lbl.setFont(font);

        lbl.setBackground(new Color(245,248,252));

        lbl.setForeground(new Color(60,60,60));

        tablePanel.add(lbl);

    }
        public void actionPerformed(ActionEvent e) {

        if(e.getSource() == back) {

            new AdminDashboard();

            dispose();

        }

    }

}