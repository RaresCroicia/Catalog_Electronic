package GUI.pages;

import university.Catalog;
import university.course.Course;
import university.user.Student;
import university.user.Teacher;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class StudentPage extends JFrame implements ListSelectionListener {
    private Student student;
    JList<Course> courseList;
    DefaultListModel<Course> courseListModel;

    JTextField teacher, assistants, theAssistant, partialGrade, finalGrade, courseGrade;
    private ArrayList<Course> getCoursesForStudent(Student student) {
        ArrayList<Course> studentCourses = new ArrayList<>();
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        for(Course course : courses) {
            if(course.studentExists(student))
                studentCourses.add(course);
        }
        return studentCourses;
    }
    public StudentPage(Student student) {
        super(student + "'s page");
        this.student = student;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        ArrayList<Course> courses = getCoursesForStudent(student);
        courseListModel = new DefaultListModel<>();
        for(Course course : courses) {
            courseListModel.addElement(course);
        }
        JScrollPane scrollPane = new JScrollPane();
        courseList = new JList<>(courseListModel);
        scrollPane.setViewportView(courseList);
        courseList.setLayoutOrientation(JList.VERTICAL);
        courseList.addListSelectionListener(this);
        JPanel p1 = new JPanel(new GridLayout(2, 1));
        JPanel p2 = new JPanel(new GridLayout(5, 1));
        JPanel p3 = new JPanel(new GridLayout(5, 1));

        p1.add(p2);
        add(scrollPane);
        add(p1);

        teacher = new JTextField();
        teacher.setColumns(50);
        assistants = new JTextField();
        assistants.setColumns(50);
        theAssistant = new JTextField();
        theAssistant.setColumns(50);
        partialGrade = new JTextField();
        partialGrade.setColumns(50);
        finalGrade = new JTextField();
        finalGrade.setColumns(50);
        courseGrade = new JTextField();
        courseGrade.setColumns(50);

        p3.add(teacher);
        p3.add(assistants);
        p3.add(theAssistant);
        p3.add(partialGrade);
        p3.add(finalGrade);
        p3.add(courseGrade);

        p1.add(p3);

        pack();
        setVisible(true);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(courseList.isSelectionEmpty()) {
            teacher.setText("");
            assistants.setText("");
            theAssistant.setText("");
            partialGrade.setText("");
            finalGrade.setText("");
            courseGrade.setText("");
            return;
        }
        Course course = courseList.getSelectedValue();
        teacher.setText("Profesorul de curs: " + course.getTeacher().toString());
        assistants.setText("Echipa de asistenti: " + course.getAssistants().toString());
        theAssistant.setText("Asistenul tau: " + course.getAssistant(student).toString());
        if(course.getPartialScore(student) == null)
            partialGrade.setText("N-ai nota pe parcurs");
        else
            partialGrade.setText("Nota pe parcurs: " + course.getPartialScore(student).toString());
        if(course.getExamScore(student) == null)
            finalGrade.setText("N-ai nota in examen");
        else
            finalGrade.setText("Nota in examen: " + course.getExamScore(student).toString());
        if(course.getTotalScore(student) == null)
            courseGrade.setText("N-ai nicio nota momentan!");
        else
            courseGrade.setText("Nota totala: " + course.getTotalScore(student).toString());

    }
}
