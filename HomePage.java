import java.awt.*;
import java.awt.event.*;

public class HomePage extends Frame implements ActionListener {

    Label title;
    Button loginBtn, registerBtn;

    HomePage() {

        setTitle("User Management System");

        setExtendedState(Frame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        Panel top = new Panel();

        title = new Label("USER MANAGEMENT SYSTEM", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        top.add(title);

        add(top, BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        Panel p = new Panel(new GridLayout(2, 1, 20, 20));

        loginBtn = new Button("LOGIN");
        registerBtn = new Button("REGISTER");

        loginBtn.setPreferredSize(new Dimension(200, 45));
        registerBtn.setPreferredSize(new Dimension(200, 45));

        p.add(loginBtn);
        p.add(registerBtn);

        center.add(p);

        add(center, BorderLayout.CENTER);

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);


        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });

        setBackground(Color.LIGHT_GRAY);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginBtn) {

            new LoginPage();
            dispose();

        }

        if (e.getSource() == registerBtn) {

            new RegisterPage();
            dispose();

        }

    }

}