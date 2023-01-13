package GUI.pages;

import university.Catalog;
import university.user.Assistant;
import university.user.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AssistantPage extends JFrame implements ActionListener {
    private Assistant assistant;
    JList<String> tuples;
    DefaultListModel<String> tuplesModel;
    public AssistantPage(Assistant assistant) {
        super(assistant.toString() + "'s page");
        this.assistant = assistant;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 300);
        setMinimumSize(new Dimension(300, 300));

        JPanel p1;
        p1 = new JPanel(new GridLayout(10, 1));

        ArrayList<String> strings = Catalog.getCatalog().scoreVisitor.getAssistantTuple(assistant);
        tuplesModel = new DefaultListModel<>();
        for (String string : strings) {
            tuplesModel.addElement(string);
        }

        JScrollPane scrollPane = new JScrollPane();
        tuples = new JList<>(tuplesModel);
        scrollPane.setViewportView(tuples);
        tuples.setLayoutOrientation(JList.VERTICAL);

        add(scrollPane);


        JButton button = new JButton("Valideaza notele!");
        button.addActionListener(this);

        p1.add(button);
        add(p1);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Catalog.getCatalog().scoreVisitor.visit(assistant);
        tuplesModel.clear();
    }
}
