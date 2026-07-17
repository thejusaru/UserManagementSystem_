import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchUser extends Frame implements ActionListener {

    Label title;
    Label searchLabel;

    TextField searchText;

    Button search;
    Button clear;
    Button back;

    TextArea result;

    FileManager fileManager;

    SearchUser() {

        fileManager = new FileManager();

        setTitle("User Management System - Search User");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout(20, 20));

        setBackground(new Color(235, 245, 255));

        Panel top = new Panel();

        top.setBackground(new Color(25, 118, 210));

        title = new Label("SEARCH USER", Label.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 34));

        title.setForeground(Color.WHITE);

        top.add(title);

        add(top, BorderLayout.NORTH);

        Panel searchPanel = new Panel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        searchPanel.setBackground(Color.WHITE);

        searchLabel = new Label("Enter Username / User ID");

        searchLabel.setFont(new Font("Arial", Font.BOLD, 20));

        searchText = new TextField(25);

        searchText.setFont(new Font("Arial", Font.PLAIN, 18));

        search = new Button("SEARCH");

        search.setFont(new Font("Arial", Font.BOLD, 18));

        search.setBackground(new Color(76, 175, 80));

        search.setForeground(Color.WHITE);

        searchPanel.add(searchLabel);

        searchPanel.add(searchText);

        searchPanel.add(search);

        result = new TextArea();

        result.setEditable(false);

        result.setFont(new Font("Monospaced", Font.PLAIN, 18));

        result.setBackground(Color.WHITE);

        result.setForeground(Color.BLACK);

        Panel center = new Panel(new BorderLayout(20, 20));

        center.setBackground(new Color(235, 245, 255));

        center.add(searchPanel, BorderLayout.NORTH);

        center.add(result, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        Panel bottom = new Panel();

        bottom.setBackground(new Color(235, 245, 255));

        clear = new Button("CLEAR");

        back = new Button("BACK");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        clear.setFont(buttonFont);

        back.setFont(buttonFont);

        clear.setBackground(new Color(255, 193, 7));

        clear.setForeground(Color.BLACK);

        back.setBackground(new Color(33, 150, 243));

        back.setForeground(Color.WHITE);

        bottom.add(clear);

        bottom.add(new Label("     "));

        bottom.add(back);

        add(bottom, BorderLayout.SOUTH);

        search.addActionListener(this);

        clear.addActionListener(this);

        back.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        searchText.requestFocus();

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == search) {

            String keyword = searchText.getText().trim();

            if (keyword.equals("")) {

                new MessageDialog(this, "Error", "Enter Search Value").setVisible(true);

                return;

            }

            ArrayList<User> users = fileManager.searchUser(keyword);

            result.setText("");

            if (users.size() == 0) {

                result.append("User Not Found");

                return;

            }

            result.append("=====================================================================\n");
            result.append("                           SEARCH RESULT\n");
            result.append("=====================================================================\n\n");

            for (User u : users) {

                result.append("+----------------------+--------------------------------------+\n");
                result.append("| Field                | Value                                |\n");
                result.append("+----------------------+--------------------------------------+\n");

                result.append(String.format("| %-20s | %-36s |\n", "Username", u.getUsername()));
                result.append(String.format("| %-20s | %-36s |\n", "User ID", u.getUserId()));
                result.append(String.format("| %-20s | %-36s |\n", "Gmail", u.getGmail()));
                result.append(String.format("| %-20s | %-36s |\n", "Phone", u.getPhone()));

                result.append("+----------------------+--------------------------------------+\n\n");

            }

        }

        if (e.getSource() == clear) {

            searchText.setText("");

            result.setText("");

        }

        if (e.getSource() == back) {

            new AdminDashboard();

            dispose();

        }

    }

}