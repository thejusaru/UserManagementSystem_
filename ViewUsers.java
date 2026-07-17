import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ViewUsers extends Frame implements ActionListener {

    Label title;

    Button back;

    FileManager fileManager;

    Panel tablePanel;


    ViewUsers() {

        fileManager = new FileManager();

        setTitle("View Users");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout(20,20));

        setBackground(new Color(235,245,255));


        Panel top = new Panel();

        top.setBackground(new Color(255,193,7));

        title = new Label("REGISTERED USERS", Label.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 34));

        top.add(title);

        add(top, BorderLayout.NORTH);



        tablePanel = new Panel();

        add(tablePanel, BorderLayout.CENTER);

        loadUsers();



        Panel bottom = new Panel();

        back = new Button("BACK");

        back.setFont(new Font("Arial", Font.BOLD,18));

        back.setBackground(new Color(33,150,243));

        back.setForeground(Color.WHITE);

        bottom.add(back);

        add(bottom, BorderLayout.SOUTH);



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


        tablePanel.setLayout(new GridLayout(rows,5,5,5));

        tablePanel.setBackground(Color.WHITE);



        Font headerFont = new Font("Arial", Font.BOLD,18);

        Font rowFont = new Font("Arial", Font.PLAIN,17);



        addHeader("Username", headerFont);

        addHeader("User ID", headerFont);

        addHeader("Password", headerFont);

        addHeader("Gmail", headerFont);

        addHeader("Phone", headerFont);



        for(User u : users) {


            addRow(u.getUsername(), rowFont);

            addRow(u.getUserId(), rowFont);

            addRow("********", rowFont);

            addRow(u.getGmail(), rowFont);

            addRow(u.getPhone(), rowFont);


        }


    }



    void addHeader(String text, Font font) {


        Label lbl = new Label(text, Label.CENTER);

        lbl.setFont(font);

        lbl.setBackground(new Color(33,150,243));

        lbl.setForeground(Color.WHITE);

        tablePanel.add(lbl);


    }



    void addRow(String text, Font font) {


        Label lbl = new Label(text, Label.CENTER);

        lbl.setFont(font);

        lbl.setBackground(Color.WHITE);

        tablePanel.add(lbl);


    }



    public void actionPerformed(ActionEvent e) {


        if(e.getSource() == back) {


            new AdminDashboard();

            dispose();


        }


    }

}