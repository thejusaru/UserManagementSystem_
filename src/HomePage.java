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

        setLayout(new BorderLayout());

        setBackground(new Color(240,245,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        header.setPreferredSize(new Dimension(0,90));

        title = new Label("USER MANAGEMENT SYSTEM",Label.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,36));

        title.setForeground(Color.WHITE);

        header.add(title,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(240,245,252));

        Panel card = new Panel(new BorderLayout(0,35));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(700,500));

        Panel topSection = new Panel(new GridLayout(3,1,10,20));

        topSection.setBackground(Color.WHITE);

        welcome = new Label("WELCOME",Label.CENTER);

        welcome.setFont(new Font("Arial",Font.BOLD,34));

        welcome.setForeground(new Color(25,25,112));

        msg = new Label("A Secure and Efficient User Management Solution",Label.CENTER);

        msg.setFont(new Font("Arial",Font.PLAIN,20));

        msg.setForeground(Color.GRAY);

        Label desc = new Label("Manage Users • Register • Login • Search • Update • Delete",Label.CENTER);

        desc.setFont(new Font("Arial",Font.PLAIN,18));

        desc.setForeground(new Color(90,90,90));

        topSection.add(welcome);

        topSection.add(msg);

        topSection.add(desc);

        Panel buttonPanel = new Panel(new GridLayout(2,1,0,30));

        buttonPanel.setBackground(Color.WHITE);

        loginBtn = new Button("LOGIN");

        registerBtn = new Button("REGISTER");

        Font buttonFont = new Font("Arial",Font.BOLD,22);

        loginBtn.setFont(buttonFont);

        registerBtn.setFont(buttonFont);

        loginBtn.setPreferredSize(new Dimension(280,60));

        registerBtn.setPreferredSize(new Dimension(280,60));

        loginBtn.setBackground(new Color(46,125,50));

        loginBtn.setForeground(Color.WHITE);

        registerBtn.setBackground(new Color(25,118,210));

        registerBtn.setForeground(Color.WHITE);

        Panel p1 = new Panel();

        p1.setBackground(Color.WHITE);

        p1.add(loginBtn);

        Panel p2 = new Panel();

        p2.setBackground(Color.WHITE);

        p2.add(registerBtn);

        buttonPanel.add(p1);

        buttonPanel.add(p2);

        card.add(topSection,BorderLayout.NORTH);

        card.add(buttonPanel,BorderLayout.CENTER);

        center.add(card);

        add(center,BorderLayout.CENTER);

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

        if(e.getSource()==loginBtn){

            new LoginPage();

            dispose();

        }

        if(e.getSource()==registerBtn){

            new RegisterPage();

            dispose();

        }

    }

}