import java.awt.*;
import java.awt.event.*;

class RegistrationPage extends Frame implements ActionListener {

    Label title;
    Label l1, l2, l3, l4;

    TextField t1, t2, t3, t4;

    Button register, login;

    static User users[] = new User[100];
    static int count = 0;

    RegistrationPage() {

        setTitle("Registration Page");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(235, 245, 255));

        Panel top = new Panel();
        top.setBackground(new Color(25, 118, 210));

        title = new Label("USER REGISTRATION", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        top.add(title);
        add(top, BorderLayout.NORTH);

        Panel form = new Panel(new GridLayout(4, 2, 20, 20));
        form.setBackground(Color.WHITE);

        Font labelFont = new Font("Arial", Font.BOLD, 20);

        l1 = new Label("Name");
        l2 = new Label("Username");
        l3 = new Label("Password");
        l4 = new Label("Email");

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);
        l4.setFont(labelFont);

        Font textFont = new Font("Arial", Font.PLAIN, 20);

        t1 = new TextField(25);
        t2 = new TextField(25);
        t3 = new TextField(25);
        t4 = new TextField(25);

        t1.setFont(textFont);
        t2.setFont(textFont);
        t3.setFont(textFont);
        t4.setFont(textFont);

        t3.setEchoChar('*');

        form.add(l1);
        form.add(t1);

        form.add(l2);
        form.add(t2);

        form.add(l3);
        form.add(t3);

        form.add(l4);
        form.add(t4);

        Panel buttons = new Panel();
        buttons.setBackground(Color.WHITE);

        register = new Button("REGISTER");
        login = new Button("LOGIN");

        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        register.setFont(buttonFont);
        login.setFont(buttonFont);

        register.setBackground(new Color(76, 175, 80));
        register.setForeground(Color.WHITE);

        login.setBackground(new Color(33, 150, 243));
        login.setForeground(Color.WHITE);

        buttons.add(register);
        buttons.add(new Label("      "));
        buttons.add(login);

        Panel card = new Panel(new BorderLayout(20, 20));
        card.setBackground(Color.WHITE);

        Label heading = new Label("Create Your Account", Label.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setForeground(new Color(25, 25, 112));

        card.add(heading, BorderLayout.NORTH);
        card.add(form, BorderLayout.CENTER);
        card.add(buttons, BorderLayout.SOUTH);

        Panel center = new Panel(new GridBagLayout());
        center.setBackground(new Color(235, 245, 255));

        center.add(card);

        add(center, BorderLayout.CENTER);

        register.addActionListener(this);
        login.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        t1.requestFocus();

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == register) {

            users[count] = new User(
                    t1.getText(),
                    t2.getText(),
                    t3.getText(),
                    t4.getText()
            );

            count++;

            Dialog d = new Dialog(this, "Message", true);
            d.setLayout(new FlowLayout());

            d.add(new Label("Registration Successful"));

            Button ok = new Button("OK");

            ok.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    d.dispose();
                }
            });

            d.add(ok);

            d.setSize(250, 150);
            d.setVisible(true);

            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
        }

        if (e.getSource() == login) {

            new LoginPage();
            dispose();
        }
    }
}