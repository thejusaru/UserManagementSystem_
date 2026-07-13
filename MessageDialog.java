import java.awt.*;
import java.awt.event.*;

public class MessageDialog extends Dialog implements ActionListener {

    Label message;
    Button ok;

    public MessageDialog(Frame parent, String title, String msg) {
        super(parent, title, true);
        setLayout(new BorderLayout(10,10));
        message = new Label(msg, Label.CENTER);
        ok = new Button("OK");

        Panel p = new Panel();
        p.add(ok);

        add(message, BorderLayout.CENTER);
        add(p, BorderLayout.SOUTH);

        ok.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(350,150);
        setLocation(500,250);
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
    }

}