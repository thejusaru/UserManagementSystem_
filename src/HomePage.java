import java.awt.*;
import java.awt.event.*;

public class HomePage extends Frame implements ActionListener {

    Label title;
    Label welcome;
    Label msg;

    Button loginBtn;
    Button registerBtn;

    HomePage() {

        setTitle("User Management System");
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout(20,20));
        setBackground(new Color(235,245,255));

        Panel top = new Panel();
        top.setBackground(new Color(25,118,210));

        title = new Label("USER MANAGEMENT SYSTEM", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);

        top.add(title);
        add(top, BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());
        center.setBackground(new Color(235,245,255));

        Panel card = new Panel(new GridLayout(4,1,20,20));
        card.setBackground(Color.WHITE);

        welcome = new Label("WELCOME", Label.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD,28));
        welcome.setForeground(new Color(25,25,112));

        msg = new Label("Manage Users Easily and Securely", Label.CENTER);
        msg.setFont(new Font("Arial", Font.PLAIN,18));

        loginBtn = new Button("LOGIN");
        registerBtn = new Button("REGISTER");

        Font buttonFont = new Font("Arial", Font.BOLD,18);

        loginBtn.setFont(buttonFont);
        registerBtn.setFont(buttonFont);

        loginBtn.setBackground(new Color(76,175,80));
        loginBtn.setForeground(Color.WHITE);

        registerBtn.setBackground(new Color(33,150,243));
        registerBtn.setForeground(Color.WHITE);

        card.add(welcome);
        card.add(msg);
        card.add(loginBtn);
        card.add(registerBtn);

        center.add(card);

        add(center, BorderLayout.CENTER);

        loginBtn.addActionListener(this);
        registerBtn.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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