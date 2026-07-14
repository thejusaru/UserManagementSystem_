import java.awt.*;
import java.awt.event.*;

public class DeleteUser extends Frame implements ActionListener {

    User user;

    Label title;
    Label msg;

    Button btnDelete;
    Button btnBack;

    FileManager fileManager;

    DeleteUser(User user) {

        this.user = user;

        fileManager = new FileManager();
        setTitle("Delete User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("DELETE ACCOUNT", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title);

        add(top, BorderLayout.NORTH);

        Panel center = new Panel(new GridLayout(2,1));
        msg = new Label("Are you sure you want to delete your account?",Label.CENTER);
        msg.setFont(new Font("Arial", Font.BOLD,18));
        center.add(msg);
        add(center,BorderLayout.CENTER);

        Panel bottom = new Panel();
        btnDelete = new Button("Delete");
        btnBack = new Button("Back");
        bottom.add(btnDelete);
        bottom.add(btnBack);
        add(bottom,BorderLayout.SOUTH);

        btnDelete.addActionListener(this);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnDelete){
            boolean deleted =
                    fileManager.deleteUser(user.getUsername());
            if(deleted){
                new MessageDialog(this,"Success","Account Deleted Successfully.").setVisible(true);
                new HomePage();
                dispose();
            }
            else{
                new MessageDialog(this, "Error","Unable to Delete Account.").setVisible(true);
            }
        }
        if(e.getSource()==btnBack){
            new UserDashboard(user);
            dispose();
        }
    }
}