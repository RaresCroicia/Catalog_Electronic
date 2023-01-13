package GUI.pages;

import university.Catalog;
import university.course.Course;
import university.user.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherPage extends JFrame implements ActionListener {
    private Teacher teacher;
    public boolean pressed;
    JList<String> tuples;
    DefaultListModel<String> tuplesModel;
    public TeacherPage(Teacher teacher) {
        super(teacher.toString() + "'s page");
        pressed = false;
        this.teacher = teacher;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setSize(300, 300);
        setMinimumSize(new Dimension(300, 300));

        JPanel p1;
        p1 = new JPanel(new GridLayout(10, 1));

        ArrayList<String> strings = Catalog.getCatalog().scoreVisitor.getTeacherTuple(teacher);
        tuplesModel = new DefaultListModel<>();
        for (String string : strings) {
            tuplesModel.addElement(string);
        }

        JScrollPane scrollPane = new JScrollPane();
        tuples = new JList<>(tuplesModel);
        scrollPane.setViewportView(tuples);
        tuples.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollPaneCursuri = new JScrollPane();
        DefaultListModel<Course> coursesModel = new DefaultListModel<>();
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        for(Course course : courses) {
            if(course.getTeacher().getFirstName().equals(teacher.getFirstName()) && course.getTeacher().getLastName().equals(teacher.getLastName()))
                coursesModel.addElement(course);
        }

        JList<Course> coursesList = new JList<>(coursesModel);
        scrollPaneCursuri.setViewportView(coursesList);
        coursesList.setLayoutOrientation(JList.VERTICAL);

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
        Catalog.getCatalog().scoreVisitor.visit(teacher);
        pressed = true;
        tuplesModel.clear();
    }
}
