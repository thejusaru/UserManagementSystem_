import java.awt.*;
import java.awt.event.*;

class LoginPage extends Frame implements ActionListener{

    Label l1,l2;

    TextField t1,t2;

    Button login;

    LoginPage(){

        setTitle("Login Page");

        l1=new Label("Username");
        l2=new Label("Password");

        t1=new TextField();
        t2=new TextField();

        t2.setEchoChar('*');

        login=new Button("Login");
        
        setLayout(new BorderLayout());

        Panel p = new Panel();

        p.setLayout(new GridLayout(3,2,10,10));

        p.add(l1);
        p.add(t1);

        p.add(l2);
        p.add(t2);

        p.add(new Label(""));
        p.add(login);

        Panel center = new Panel(new GridBagLayout());

        center.add(p);

        add(center, BorderLayout.CENTER);

        login.addActionListener(this);

        addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e){

                dispose();

            }

        });

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){

        String uname=t1.getText();
        String pass=t2.getText();

        if(uname.equals("admin") && pass.equals("admin")){

            new AdminPage();

            dispose();

            return;

        }

        for(int i=0;i<RegistrationPage.count;i++){

            if(RegistrationPage.users[i].username.equals(uname)
                    &&
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

        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);

    }

}