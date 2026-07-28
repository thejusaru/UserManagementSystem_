import java.awt.*;
import java.awt.event.*;
import org.mindrot.jbcrypt.BCrypt;

public class UpdateUser extends Frame implements ActionListener {

    User user;

    Label title;
    Label heading;
    Label l1, l2, l3, l4, l5;

    TextField txtUsername;
    TextField txtUserId;
    TextField txtPassword;
    TextField txtGmail;
    TextField txtPhone;

    Button btnSave;
    Button btnBack;

    FileManager fileManager;

    UpdateUser(User user) {

        this.user = user;

        fileManager = new FileManager();

        setTitle("User Management System - Update Profile");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setBackground(new Color(238,243,250));

        Panel header = new Panel(new BorderLayout());
        header.setBackground(new Color(33,150,243));
        header.setPreferredSize(new Dimension(100,90));

        title = new Label("UPDATE PROFILE", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 34));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        Panel outer = new Panel(new GridBagLayout());
        outer.setBackground(new Color(238,243,250));

        Panel card = new Panel(new BorderLayout(25,25));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(760,520));

        heading = new Label("Update Your Information", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setForeground(new Color(33,70,140));

        card.add(heading, BorderLayout.NORTH);

        Panel form = new Panel(new GridBagLayout());
        form.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,20,15,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD,20);
        Font textFont = new Font("Arial", Font.PLAIN,19);

        l1 = new Label("Username");
        l2 = new Label("User ID");
        l3 = new Label("Password");
        l4 = new Label("Gmail");
        l5 = new Label("Phone Number");

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);
        l4.setFont(labelFont);
        l5.setFont(labelFont);

        txtUsername = new TextField(user.getUsername(),25);
        txtUserId = new TextField(user.getUserId(),25);
        txtPassword = new TextField(25);
        txtGmail = new TextField(user.getGmail(),25);
        txtPhone = new TextField(user.getPhone(),25);

        txtUsername.setFont(textFont);
        txtUserId.setFont(textFont);
        txtPassword.setFont(textFont);
        txtGmail.setFont(textFont);
        txtPhone.setFont(textFont);

        txtPassword.setEchoChar('*');

        txtUsername.setEditable(false);
        txtUsername.setBackground(new Color(245,245,245));

        gbc.gridx=0;
        gbc.gridy=0;
        form.add(l1,gbc);

        gbc.gridx=1;
        form.add(txtUsername,gbc);

        gbc.gridx=0;
        gbc.gridy=1;
        form.add(l2,gbc);

        gbc.gridx=1;
        form.add(txtUserId,gbc);

        gbc.gridx=0;
        gbc.gridy=2;
        form.add(l3,gbc);

        gbc.gridx=1;
        form.add(txtPassword,gbc);

        gbc.gridx=0;
        gbc.gridy=3;
        form.add(l4,gbc);

        gbc.gridx=1;
        form.add(txtGmail,gbc);

        gbc.gridx=0;
        gbc.gridy=4;
        form.add(l5,gbc);

        gbc.gridx=1;
        form.add(txtPhone,gbc);

        card.add(form,BorderLayout.CENTER);

        Panel buttons = new Panel(new FlowLayout(FlowLayout.CENTER,30,15));
        buttons.setBackground(Color.WHITE);

        btnSave = new Button("UPDATE PROFILE");
        btnBack = new Button("BACK");

        Font buttonFont = new Font("Arial",Font.BOLD,18);

        btnSave.setFont(buttonFont);
        btnBack.setFont(buttonFont);

        btnSave.setPreferredSize(new Dimension(180,45));
        btnBack.setPreferredSize(new Dimension(130,45));

        btnSave.setBackground(new Color(255,152,0));
        btnSave.setForeground(Color.WHITE);

        btnBack.setBackground(new Color(96,125,139));
        btnBack.setForeground(Color.WHITE);

        buttons.add(btnSave);
        buttons.add(btnBack);

        card.add(buttons,BorderLayout.SOUTH);

        outer.add(card);

        add(outer,BorderLayout.CENTER);

        btnSave.addActionListener(this);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                System.exit(0);

            }

        });

        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnSave){

            String username = txtUsername.getText().trim();
            String userId = txtUserId.getText().trim();
            String password = txtPassword.getText().trim();
            String gmail = txtGmail.getText().trim();
            String phone = txtPhone.getText().trim();

            if(username.equals("") ||
               userId.equals("") ||
               password.equals("") ||
               gmail.equals("") ||
               phone.equals("")){

                new MessageDialog(this,"Error","Please fill all the fields.").setVisible(true);
                return;
            }

            if(!gmail.endsWith("@gmail.com")){

                new MessageDialog(this,"Error","Enter a valid Gmail address.").setVisible(true);
                return;
            }

            if(phone.length()!=10){

                new MessageDialog(this,"Error","Phone number must contain 10 digits.").setVisible(true);
                return;
            }

            if(password.length()<6){

                new MessageDialog(this,"Error","Password must contain at least 6 characters.").setVisible(true);
                return;
            }
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User updatedUser = new User(username,userId,encryptedPassword,gmail,phone);

            boolean updated = fileManager.updateUser(updatedUser);

            if(updated){

                new MessageDialog(
                        this,
                        "Success",
                        "Profile Updated Successfully."
                ).setVisible(true);

                new UserDashboard(updatedUser);

                dispose();

            }else{

                new MessageDialog(
                        this,
                        "Error",
                        "Unable to Update Profile."
                ).setVisible(true);

            }

        }

        if(e.getSource()==btnBack){

            new UserDashboard(user);

            dispose();

        }

    }

}