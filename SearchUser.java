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

    SearchUser(){

        fileManager = new FileManager();

        setTitle("Search User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("SEARCH USER",Label.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,28));
        top.add(title);

        add(top,BorderLayout.NORTH);

        Panel searchPanel = new Panel();
        searchLabel = new Label("Enter Username / User ID");
        searchText = new TextField(25);
        search = new Button("Search");

        searchPanel.add(searchLabel);
        searchPanel.add(searchText);
        searchPanel.add(search);
        add(searchPanel,BorderLayout.CENTER);

        result = new TextArea();
        result.setFont(new Font("Monospaced",Font.PLAIN,16));
        result.setEditable(false);
        add(result,BorderLayout.SOUTH);

        Panel bottom = new Panel();
        clear = new Button("Clear");
        back = new Button("Back");

        bottom.add(clear);
        bottom.add(back);
        add(bottom,BorderLayout.EAST);

        search.addActionListener(this);
        clear.addActionListener(this);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==search){
            String keyword =
                    searchText.getText().trim();
            if(keyword.equals("")){
                new MessageDialog(this,"Error","Enter Search Value").setVisible(true);
                return;
            }
            ArrayList<User> users = fileManager.searchUser(keyword);
            result.setText("");

            if(users.size()==0){
                result.append("User Not Found");
                return;
            }
            result.append("==============================\n");
            result.append("SEARCH RESULT\n");
            result.append("==============================\n\n");
            for(User u : users){
                result.append("Username : "+u.getUsername()+"\n");
                result.append("User ID : "+u.getUserId()+"\n");
                result.append("Gmail : "+u.getGmail()+"\n");
                result.append("Phone : "+u.getPhone()+"\n");
                result.append("------------------------------\n");
            }
        }

        if(e.getSource()==clear){
           searchText.setText("");
           result.setText("");
        }
        if(e.getSource()==back){
            new AdminDashboard();
            dispose();
        }
    }
}