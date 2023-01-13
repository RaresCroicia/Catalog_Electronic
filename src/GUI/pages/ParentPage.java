package GUI.pages;

import university.user.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParentPage extends JFrame implements ActionListener{
    private Parent parent;
    JTextField textField;

    private String notificari;

    public void setNotificari(String notificare) {
        notificari = notificari + "\n" + notificare;
        actionPerformed(null);
    }

    public ParentPage(Parent parent) {
        super(parent.toString() + "'s page");
        this.parent = parent;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 300);
        setMinimumSize(new Dimension(300, 300));

        JPanel p1;
        p1 = new JPanel(new GridLayout(10, 1));
        notificari = "";

        textField = new JTextField(20);
        textField.setText(notificari);

        add(p1);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText(notificari);
    }
}
