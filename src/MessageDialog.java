import java.awt.*;
import java.awt.event.*;

public class MessageDialog extends Dialog implements ActionListener {

    Label message;

    Button ok;

    public MessageDialog(Frame parent, String title, String msg) {

        super(parent, title, true);

        setLayout(new BorderLayout());

        setBackground(new Color(240,245,252));

        Panel header = new Panel(new BorderLayout());

        header.setBackground(new Color(25,118,210));

        header.setPreferredSize(new Dimension(0,60));

        Label heading = new Label(title,Label.CENTER);

        heading.setFont(new Font("Arial",Font.BOLD,24));

        heading.setForeground(Color.WHITE);

        header.add(heading,BorderLayout.CENTER);

        add(header,BorderLayout.NORTH);

        Panel center = new Panel(new GridBagLayout());

        center.setBackground(new Color(240,245,252));

        Panel card = new Panel(new BorderLayout(20,25));

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(460,180));

        message = new Label(msg,Label.CENTER);

        message.setFont(new Font("Arial",Font.PLAIN,20));

        message.setForeground(new Color(60,60,60));

        Panel messagePanel = new Panel(new GridBagLayout());

        messagePanel.setBackground(Color.WHITE);

        messagePanel.add(message);

        Panel buttonPanel = new Panel();

        buttonPanel.setBackground(Color.WHITE);

        ok = new Button("OK");

        ok.setFont(new Font("Arial",Font.BOLD,18));

        ok.setPreferredSize(new Dimension(140,45));

        ok.setBackground(new Color(46,125,50));

        ok.setForeground(Color.WHITE);

        buttonPanel.add(ok);

        card.add(messagePanel,BorderLayout.CENTER);

        card.add(buttonPanel,BorderLayout.SOUTH);

        center.add(card);

        add(center,BorderLayout.CENTER);

        ok.addActionListener(this);

        addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e){

                dispose();

            }

        });

        setSize(550,280);

        setLocationRelativeTo(parent);

        setResizable(false);

        setVisible(false);

    }

    private void setLocationRelativeTo(Frame parent) {

        if(parent!=null){

            int x = parent.getX() + (parent.getWidth()-getWidth())/2;

            int y = parent.getY() + (parent.getHeight()-getHeight())/2;

            setLocation(x,y);

        }

        else{

            setLocation(450,250);

        }

    }

    public void actionPerformed(ActionEvent e){

        dispose();

    }

}