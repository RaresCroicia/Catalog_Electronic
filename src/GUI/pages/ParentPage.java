package GUI.pages;

import university.user.Parent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParentPage extends JFrame{
    private Parent parent;
    JTextArea textArea;

    private String notificari;

    public void setNotificari(String notificare) {
        notificari = notificari + "\n" + notificare + "\n";
        textArea.setText(notificari);
    }

    public ParentPage(Parent parent) {
        super(parent.toString() + "'s page");
        this.parent = parent;
        parent.setPage(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 300);
        setMinimumSize(new Dimension(600, 600));

        JPanel p1;
        p1 = new JPanel(new GridLayout(10, 1));
        notificari = "";

        textArea = new JTextArea();
        textArea.setText(notificari);
        textArea.setEditable(false);

        p1.add(textArea);
        add(p1);
        pack();

        setVisible(true);
    }

}
