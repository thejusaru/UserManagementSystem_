import java.awt.*;
import java.awt.event.*;

public class DeleteUserAdmin extends Frame implements ActionListener {

    Label title;
    Label label;

    TextField txtUsername;

    Button delete;
    Button clear;
    Button back;

    FileManager fileManager;


    DeleteUserAdmin() {

        fileManager = new FileManager();

        setTitle("Admin Delete User");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("DELETE USER", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title);
        add(top, BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());
        Panel form = new Panel(new GridLayout(2,2,15,15));

        label = new Label("Enter Username");
        txtUsername = new TextField();

        form.add(label);
        form.add(txtUsername);

        center.add(form);
        add(center, BorderLayout.CENTER);

        Panel bottom = new Panel();
        delete = new Button("Delete");
        clear = new Button("Clear");
        back = new Button("Back");

        bottom.add(delete);
        bottom.add(clear);
        bottom.add(back);
        add(bottom, BorderLayout.SOUTH);

        delete.addActionListener(this);
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
        if(e.getSource()==delete){
            String username = txtUsername.getText().trim();
            if(username.equals("")){
                new MessageDialog(
                        this,
                        "Error",
                        "Enter Username"
                ).setVisible(true);
                return;
            }
            User user = fileManager.findUser(username);
            if(user == null){
                new MessageDialog(
                        this,
                        "Error",
                        "User Not Found"
                ).setVisible(true);
                return;
            }
            boolean result =
                    fileManager.deleteUser(username);
            if(result){
                new MessageDialog(this, "Success", "User Deleted Successfully").setVisible(true);
                txtUsername.setText("");
            }
            else{
                new MessageDialog(this, "Error", "Delete Failed").setVisible(true);
            }
        }
        if(e.getSource()==clear){
            txtUsername.setText("");
        }
        if(e.getSource()==back){
            new AdminDashboard();
            dispose();
        }
   }
}
