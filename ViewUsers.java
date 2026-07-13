import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ViewUsers extends Frame implements ActionListener {

    Label title;

    TextArea display;

    Button back;

    FileManager fileManager;

    ViewUsers() {

        fileManager = new FileManager();
        setTitle("View All Users");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        Panel top = new Panel();
        title = new Label("REGISTERED USERS", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        top.add(title);
        add(top, BorderLayout.NORTH);

        display = new TextArea();
        display.setFont(new Font("Monospaced",Font.PLAIN,16));
        display.setEditable(false);
        add(display, BorderLayout.CENTER);

        showUsers();

        Panel bottom = new Panel();
        back = new Button("Back");
        bottom.add(back);
        add(bottom, BorderLayout.SOUTH);
        back.addActionListener(this);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
        setVisible(true);
    }
    void showUsers(){
        display.setText("");
        ArrayList<User> users = fileManager.getAllUsers();
        if(users.size()==0){
            display.append("No Users Registered");
            return;
        }
        display.append("============================================================\n");
        display.append("USERNAME\tUSER ID\tGMAIL\tPHONE\n");
        display.append("============================================================\n\n");
        for(User u : users){
            display.append(u.getUsername()+ "\t"+ u.getUserId()+ "\t"+ u.getGmail()+ "\t"+ u.getPhone()+ "\n\n");
      }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==back){
            new AdminDashboard();
            dispose();
        }
    }
}