package GUI.pages;

import university.Catalog;
import university.course.Course;
import university.user.Assistant;
import university.user.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

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

        JScrollPane scrollPaneCursuri = new JScrollPane();
        DefaultListModel<Course> coursesModel = new DefaultListModel<>();
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        for(Course course : courses) {
            Set<Assistant> assistantSet = course.getAssistants();
            for(Assistant assistant1 : assistantSet) {
                if(assistant1.getFirstName().equals(assistant.getFirstName()) && assistant1.getLastName().equals(assistant.getLastName()))
                    coursesModel.addElement(course);
            }
        }

        JList<Course> coursesList = new JList<>(coursesModel);
        scrollPaneCursuri.setViewportView(coursesList);
        coursesList.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollPane = new JScrollPane();
        tuples = new JList<>(tuplesModel);
        scrollPane.setViewportView(tuples);
        tuples.setLayoutOrientation(JList.VERTICAL);


        add(scrollPaneCursuri);
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
