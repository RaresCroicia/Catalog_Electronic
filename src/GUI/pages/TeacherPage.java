package GUI.pages;

import university.Catalog;
import university.user.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherPage extends JFrame implements ActionListener {
    private Teacher teacher;
    public TeacherPage(Teacher teacher) {
        super(teacher.toString() + "'s page");
        this.teacher = teacher;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 300);
        setMinimumSize(new Dimension(300, 300));

        JPanel p1;
        p1 = new JPanel(new GridLayout(10, 1));

        JButton button = new JButton("Valideaza notele!");
        button.addActionListener(this);

        p1.add(button);
        add(p1);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Catalog.getCatalog().scoreVisitor.visit(teacher);
    }
}
