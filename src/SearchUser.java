import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class SearchUser extends Frame implements ActionListener {

    Label title;
    Label heading;
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

        setLayout(new BorderLayout());

        setBackground(new Color(240,245,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        header.setPreferredSize(new Dimension(0,90));

        title = new Label("SEARCH USER",Label.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,36));

        title.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(240,245,252));

        Panel card = new Panel(new BorderLayout(25,25));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(900,620));

        heading = new Label("Search Registered Users",Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,28));

        heading.setForeground(new Color(25,25,112));

        card.add(heading,BorderLayout.NORTH);

        Panel searchPanel = new Panel(new FlowLayout(FlowLayout.CENTER,20,15));

        searchPanel.setBackground(Color.WHITE);

        searchLabel = new Label("Username / User ID");

        searchLabel.setFont(new Font("Arial",Font.BOLD,20));

        searchText = new TextField(28);

        searchText.setFont(new Font("Arial",Font.PLAIN,20));

        search = new Button("SEARCH");

        Font buttonFont = new Font("Arial",Font.BOLD,18);

        search.setFont(buttonFont);

        search.setPreferredSize(new Dimension(160,45));

        search.setBackground(new Color(46,125,50));

        search.setForeground(Color.WHITE);

        searchPanel.add(searchLabel);

        searchPanel.add(searchText);

        searchPanel.add(search);

        result = new TextArea();

        result.setEditable(false);

        result.setFont(new Font("Monospaced",Font.PLAIN,18));

        result.setBackground(new Color(250,250,250));

        result.setForeground(Color.BLACK);

        Panel body = new Panel(new BorderLayout(15,15));

        body.setBackground(Color.WHITE);

        body.add(searchPanel,BorderLayout.NORTH);

        body.add(result,BorderLayout.CENTER);

        card.add(body,BorderLayout.CENTER);

        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER,25,10));

        buttonPanel.setBackground(Color.WHITE);

        clear = new Button("CLEAR");

        back = new Button("BACK");

        clear.setFont(buttonFont);

        back.setFont(buttonFont);

        clear.setPreferredSize(new Dimension(170,50));

        back.setPreferredSize(new Dimension(170,50));

        clear.setBackground(new Color(251,140,0));

        clear.setForeground(Color.WHITE);

        back.setBackground(new Color(25,118,210));

        back.setForeground(Color.WHITE);

        buttonPanel.add(clear);

        buttonPanel.add(back);

        card.add(buttonPanel,BorderLayout.SOUTH);

        center.add(card);

        add(center,BorderLayout.CENTER);

        search.addActionListener(this);

        clear.addActionListener(this);

        back.addActionListener(this);

        addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e){

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

                new MessageDialog(
                        this,
                        "Error",
                        "Please enter a Username or User ID."
                ).setVisible(true);

                searchText.requestFocus();

                return;

            }

            ArrayList<User> users = fileManager.searchUser(keyword);

            result.setText("");

            if (users.size() == 0) {

                result.append("\n\n");
                result.append("                 No Matching User Found.");
                result.append("\n\n");
                result.append("Please verify the Username or User ID.");

                return;

            }

            result.append("==============================================================\n");
            result.append("                    SEARCH RESULTS\n");
            result.append("==============================================================\n\n");

            int i = 1;

            for (User u : users) {

                result.append("User " + i++ + "\n");
                result.append("--------------------------------------------------------------\n");
                result.append("Username      : " + u.getUsername() + "\n");
                result.append("User ID       : " + u.getUserId() + "\n");
                result.append("Gmail         : " + u.getGmail() + "\n");
                result.append("Phone Number  : " + u.getPhone() + "\n");
                result.append("--------------------------------------------------------------\n\n");

            }

        }

        if (e.getSource() == clear) {

            searchText.setText("");

            result.setText("");

            searchText.requestFocus();

        }

        if (e.getSource() == back) {

            new AdminDashboard();

            dispose();

        }

    }

}