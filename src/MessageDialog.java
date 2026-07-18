import java.awt.*;
import java.awt.event.*;

public class MessageDialog extends Dialog implements ActionListener {

    Label message;
    Button ok;

    public MessageDialog(Frame parent, String title, String msg) {

        super(parent, title, true);

        setLayout(new BorderLayout(20,20));

        setBackground(new Color(235,245,255));

        //================ TITLE =================//

        Panel top = new Panel();

        top.setBackground(new Color(25,118,210));

        Label heading = new Label(title, Label.CENTER);

        heading.setFont(new Font("Arial", Font.BOLD, 22));

        heading.setForeground(Color.WHITE);

        top.add(heading);

        add(top, BorderLayout.NORTH);

        //================ MESSAGE =================//

        message = new Label(msg, Label.CENTER);

        message.setFont(new Font("Arial", Font.PLAIN, 18));

        Panel center = new Panel();

        center.setBackground(Color.WHITE);

        center.add(message);

        add(center, BorderLayout.CENTER);

        //================ BUTTON =================//

        ok = new Button("OK");

        ok.setFont(new Font("Arial", Font.BOLD, 18));

        ok.setBackground(new Color(76,175,80));

        ok.setForeground(Color.WHITE);

        Panel bottom = new Panel();

        bottom.setBackground(new Color(235,245,255));

        bottom.add(ok);

        add(bottom, BorderLayout.SOUTH);

        ok.addActionListener(this);

        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                dispose();

            }

        });

        setSize(420,200);

        setLocationRelativeTo(parent);

        setVisible(false);

    }

    // Center the dialog relative to its parent
    private void setLocationRelativeTo(Frame parent) {

        if (parent != null) {

            int x = parent.getX() + (parent.getWidth() - getWidth()) / 2;

            int y = parent.getY() + (parent.getHeight() - getHeight()) / 2;

            setLocation(x, y);

        } else {

            setLocation(450, 250);

        }
    }

    public void actionPerformed(ActionEvent e) {

        dispose();

    }

}