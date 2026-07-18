import java.awt.*;
import java.awt.event.*;

public class AddUser extends Frame implements ActionListener {

    Label title;
    Label l1, l2, l3, l4, l5;

    TextField txtUsername;
    TextField txtUserId;
    TextField txtPassword;
    TextField txtGmail;
    TextField txtPhone;

    Button btnSave;
    Button btnClear;
    Button btnBack;

    FileManager fileManager;

    AddUser() {

        fileManager = new FileManager();

        setTitle("User Management System - Add User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20,20));
        setBackground(new Color(235,245,255));

        Panel top = new Panel();
        top.setBackground(new Color(30,144,255));
        title = new Label("ADD NEW USER",Label.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,34));
        title.setForeground(Color.WHITE);
        top.add(title);
        add(top,BorderLayout.NORTH);

        Panel form = new Panel();
        form.setLayout(new GridLayout(5,2,20,20));
        form.setBackground(Color.WHITE);

        l1=new Label("Username");
        l2=new Label("User ID");
        l3=new Label("Password");
        l4=new Label("Gmail");
        l5=new Label("Phone Number");

        Font labelFont=new Font("Arial",Font.BOLD,20);

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);
        l4.setFont(labelFont);
        l5.setFont(labelFont);

        txtUsername=new TextField(25);
        txtUserId=new TextField(25);
        txtPassword=new TextField(25);
        txtGmail=new TextField(25);
        txtPhone=new TextField(25);

        Font textFont=new Font("Arial",Font.PLAIN,20);

        txtUsername.setFont(textFont);
        txtUserId.setFont(textFont);
        txtPassword.setFont(textFont);
        txtGmail.setFont(textFont);
        txtPhone.setFont(textFont);

        txtPassword.setEchoChar('*');

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

        Panel buttons=new Panel();
        buttons.setBackground(Color.WHITE);
        btnSave=new Button("SAVE");
        btnClear=new Button("CLEAR");
        btnBack=new Button("BACK");

        Font buttonFont=new Font("Arial",Font.BOLD,18);

        btnSave.setFont(buttonFont);
        btnClear.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        btnSave.setBackground(new Color(60,179,113));
        btnSave.setForeground(Color.WHITE);

        btnClear.setBackground(new Color(255,165,0));
        btnClear.setForeground(Color.WHITE);

        btnBack.setBackground(new Color(220,20,60));
        btnBack.setForeground(Color.WHITE);

        buttons.add(btnSave);
        buttons.add(new Label("      "));
        buttons.add(btnClear);
        buttons.add(new Label("      "));
        buttons.add(btnBack);

        Panel center=new Panel(new BorderLayout(20,20));
        center.setBackground(new Color(235,245,255));
        center.add(form,BorderLayout.CENTER);
        center.add(buttons,BorderLayout.SOUTH);

        Panel outer=new Panel(new GridBagLayout());
        outer.setBackground(new Color(235,245,255));
        outer.add(center);
        add(outer,BorderLayout.CENTER);

        btnSave.addActionListener(this);
        btnClear.addActionListener(this);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String username = txtUsername.getText().trim();
            String userId = txtUserId.getText().trim();
            String password = txtPassword.getText().trim();
            String gmail = txtGmail.getText().trim();
            String phone = txtPhone.getText().trim();
            if (username.equals("") ||
                userId.equals("") ||
                password.equals("") ||
                gmail.equals("") ||
                phone.equals("")) {
                new MessageDialog(
                        this,
                        "Error",
                        "Please fill all the fields."
                ).setVisible(true);
                return;
            }
            if (fileManager.findUser(username) != null) {
                new MessageDialog(this,"Error", "Username already exists.").setVisible(true);
                return;
            }

            User user = new User( username, userId, password, gmail, phone);
            boolean saved = fileManager.saveUser(user);
            if (saved) {
                new MessageDialog(
                        this,
                        "Success",
                        "User Added Successfully."
                ).setVisible(true);
                txtUsername.setText("");
                txtUserId.setText("");
                txtPassword.setText("");
                txtGmail.setText("");
                txtPhone.setText("");
            } else {
                new MessageDialog(
                        this,
                        "Error",
                        "Unable to Add User."
                ).setVisible(true);
            }
        }
        if (e.getSource() == btnClear) {
            txtUsername.setText("");
            txtUserId.setText("");
            txtPassword.setText("");
            txtGmail.setText("");
            txtPhone.setText("");
        }
        if (e.getSource() == btnBack) {
            new AdminDashboard();
            dispose();
        }
    }
}