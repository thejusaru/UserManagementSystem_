import java.awt.*;
import java.awt.event.*;

public class UpdateUserAdmin extends Frame implements ActionListener {

    Label title;

    Label searchLabel;
    TextField searchUsername;

    Button searchButton;

    Label l1, l2, l3, l4, l5;

    TextField txtUsername;
    TextField txtUserId;
    TextField txtPassword;
    TextField txtGmail;
    TextField txtPhone;

    Button save;
    Button clear;
    Button back;

    User user;

    FileManager fileManager;


    UpdateUserAdmin() {
        fileManager = new FileManager();
        setTitle("Admin Update User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("UPDATE USER DETAILS", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title);
        add(top, BorderLayout.NORTH);

        Panel searchPanel = new Panel();
        searchLabel = new Label("Enter Username:");
        searchUsername = new TextField(20);
        searchButton = new Button("Search");

        searchPanel.add(searchLabel);
        searchPanel.add(searchUsername);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.SOUTH);

        Panel form = new Panel(new GridLayout(5,2,15,15));

        l1 = new Label("Username");
        l2 = new Label("User ID");
        l3 = new Label("Password");
        l4 = new Label("Gmail");
        l5 = new Label("Phone Number");

        txtUsername = new TextField();
        txtUserId = new TextField();
        txtPassword = new TextField();
        txtGmail = new TextField();
        txtPhone = new TextField();
        txtPassword.setEchoChar('*');
        txtUsername.setEditable(false);

        form.add(l1);
        form.add(txtUsername);

        form.add(l2);
        form.add(txtUserId);

        form.add(l3);
        form.add(txtPassword);

        form.add(l4);
        form.add(txtGmail);

        form.add(l5);
        form.add(txtPhone);

        Panel buttons = new Panel();
        save = new Button("Save");
        clear = new Button("Clear");
        back = new Button("Back");
        buttons.add(save);
        buttons.add(clear);
        buttons.add(back);

        Panel center = new Panel(new BorderLayout());
        center.add(form,BorderLayout.CENTER);
        center.add(buttons,BorderLayout.SOUTH);

        add(center,BorderLayout.CENTER);
        searchButton.addActionListener(this);
        save.addActionListener(this);
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
        if(e.getSource()==searchButton){
            String username = searchUsername.getText();
            user = fileManager.findUser(username);
            if(user != null){
                txtUsername.setText(user.getUsername());
                txtUserId.setText(user.getUserId());
                txtPassword.setText(user.getPassword());
                txtGmail.setText(user.getGmail());
                txtPhone.setText(user.getPhone());
            }
            else{
                new MessageDialog(this,"Error","User Not Found").setVisible(true);
            }
        }
        if(e.getSource()==save){
            if(user==null){
                new MessageDialog(this,"Error","Search user first").setVisible(true);
                return;
            }
            User updatedUser = new User(txtUsername.getText(),txtUserId.getText(),txtPassword.getText(),txtGmail.getText(),txtPhone.getText());
            boolean result = fileManager.updateUser(updatedUser);
            
            if(result){
                new MessageDialog(this, "Success", "User Updated Successfully").setVisible(true);
            }
            else{
                new MessageDialog(this,"Error","Update Failed").setVisible(true);
            }
        }
        if(e.getSource()==clear){
            txtUsername.setText("");
            txtUserId.setText("");
            txtPassword.setText("");
            txtGmail.setText("");
            txtPhone.setText("");
            searchUsername.setText("");
            user=null;
        }
        if(e.getSource()==back){
            new AdminDashboard();
            dispose();
        }
    }
}