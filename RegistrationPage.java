import java.awt.*;
import java.awt.event.*;

class RegistrationPage extends Frame implements ActionListener {

    Label l1, l2, l3, l4;
    TextField t1, t2, t3, t4;
    Button register, login;

    static User users[] = new User[100];
    static int count = 0;

    RegistrationPage() {

        setTitle("Registration Page");
        setLayout(new GridLayout(5,2,10,10));

        l1 = new Label("Name");
        l2 = new Label("Username");
        l3 = new Label("Password");
        l4 = new Label("Email");

        t1 = new TextField();
        t2 = new TextField();
        t3 = new TextField();
        t4 = new TextField();

        t3.setEchoChar('*');

        register = new Button("Register");
        login = new Button("Login");

        add(l1); add(t1);
        add(l2); add(t2);
        add(l3); add(t3);
        add(l4); add(t4);

        add(register);
        add(login);

        register.addActionListener(this);
        login.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(350,250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==register){

            users[count]=new User(
                    t1.getText(),
                    t2.getText(),
                    t3.getText(),
                    t4.getText());

            count++;

            Dialog d=new Dialog(this,"Message",true);
            d.setLayout(new FlowLayout());
            d.add(new Label("Registration Successful"));

            Button ok=new Button("OK");
            ok.addActionListener(a->d.dispose());
            d.add(ok);
            
            d.setSize(220,120);
            d.setVisible(true);

            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");

        }

        if(e.getSource()==login){

            new LoginPage();
            dispose();

        }

    }

}

