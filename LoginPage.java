import java.awt.*;
import java.awt.event.*;

class LoginPage extends Frame implements ActionListener{

    Label l1,l2;

    TextField t1,t2;

    Button login;

    LoginPage(){

        setTitle("Login Page");

        setLayout(new GridLayout(3,2,10,10));

        l1=new Label("Username");
        l2=new Label("Password");

        t1=new TextField();
        t2=new TextField();

        t2.setEchoChar('*');

        login=new Button("Login");

        add(l1);
        add(t1);

        add(l2);
        add(t2);

        add(new Label(""));
        add(login);

        login.addActionListener(this);

        addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e){

                dispose();

            }

        });

        setSize(300,180);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){

        String uname=t1.getText();
        String pass=t2.getText();

        
        if(uname.equals("admin") && pass.equals("admin")){

            //new AdminPage();

            dispose();

            return;

        }

        for(int i=0;i<RegistrationPage.count;i++){

            if(RegistrationPage.users[i].username.equals(uname) &&
               RegistrationPage.users[i].password.equals(pass)){

                new ProfilePage(RegistrationPage.users[i]);

                dispose();

                return;

            }

        }

        Dialog d=new Dialog(this,"Login Failed",true);

        d.setLayout(new FlowLayout());

        d.add(new Label("Invalid Username or Password"));

        Button ok=new Button("OK");

        ok.addActionListener(a->d.dispose());

        d.add(ok);

        d.setSize(250,120);

        d.setVisible(true);

    }

}

