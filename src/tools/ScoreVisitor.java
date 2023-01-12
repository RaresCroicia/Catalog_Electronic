package tools;

import university.Catalog;
import university.Grade;
import university.course.Course;
import university.user.Assistant;
import university.user.Student;
import university.user.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class ScoreVisitor implements Visitor{

    private class Tuple<K, U, V> {
        K student;
        U courseName;
        V grade;
    }

    HashMap<Teacher, Tuple<Student, String, Double>> examScores;

    HashMap<Assistant, Tuple<Student, String, Double>> partialScores;

    public ScoreVisitor() {
        examScores = new HashMap<>();
        partialScores = new HashMap<>();
    }

    public void addExamScore(Teacher teacher, Student student, String courseName, Double grade) {
        Tuple<Student, String, Double> tuple = new Tuple<>();
        tuple.courseName = courseName;
        tuple.student = student;
        tuple.grade = grade;
        examScores.put(teacher, tuple);
    }

    public void addPartialScore(Assistant assistant, Student student, String courseName, Double grade) {
        Tuple<Student, String, Double> tuple = new Tuple<>();
        tuple.courseName = courseName;
        tuple.student = student;
        tuple.grade = grade;
        partialScores.put(assistant, tuple);
    }
    @Override
    public void visit(Assistant assistant) {
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        for(Tuple<Student, String, Double> tuple : partialScores.values()) {
            for(Course course : courses)
                if(course.getName().equals(tuple.courseName)) {
                    TreeSet<Grade> grades = course.getGrades();
                    boolean found = false;
                    for(Grade grade : grades) {
                        if(grade.getStudent().equals(tuple.student)) {
                            if(grade.getPartialScore() != tuple.grade) {
                                grade.setPartialScore(tuple.grade);
                                found = true;
                                break;
                            }
                        }
                    }
                    if(!found) {
                        Grade grade = new Grade(tuple.grade, null, tuple.student, tuple.courseName);
                        grades.add(grade);
                    }
                }
        }
    }

    @Override
    public void visit(Teacher teacher) {
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        for(Tuple<Student, String, Double> tuple : examScores.values()) {
            for(Course course : courses)
                if(course.getName().equals(tuple.courseName)) {
                    TreeSet<Grade> grades = course.getGrades();
                    boolean found = false;
                    for(Grade grade : grades) {
                        if(grade.getStudent().equals(tuple.student)) {
                            if(grade.getExamScore() != tuple.grade) {
                                grade.setExamScore(tuple.grade);
                                found = true;
                                break;
                            }
                        }
                    }
                    if(!found) {
                        Grade grade = new Grade(null, tuple.grade, tuple.student, tuple.courseName);
                        grades.add(grade);
                    }
                }
        }
    }


}
