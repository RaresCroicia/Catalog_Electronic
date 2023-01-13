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
        K first;
        U second;
        V third;
        public String toString() {
            return first.toString() + " " + second.toString() + " " + third.toString();
        }
    }

    public HashMap<Teacher, ArrayList<Tuple<Student, String, Double>>> examScores;

    public HashMap<Assistant, ArrayList<Tuple<Student, String, Double>>> partialScores;

    public ScoreVisitor() {
        examScores = new HashMap<>();
        partialScores = new HashMap<>();
    }

    public Teacher teacherExists(String firstName, String lastName) {
        for(Teacher teacher : examScores.keySet()) {
            if(teacher.getFirstName().equals(firstName) && teacher.getLastName().equals(lastName))
                return teacher;
        }
        return null;
    }

    public Assistant assistantExists(String firstName, String lastName) {
        for(Assistant assistant : partialScores.keySet()) {
            if(assistant.getFirstName().equals(firstName) && assistant.getLastName().equals(lastName))
                return assistant;
        }
        return null;
    }

    public void addExamScore(Teacher teacher, Student student, String courseName, Double grade) {
        Tuple<Student, String, Double> tuple = new Tuple<>();
        tuple.first = student;
        tuple.second = courseName;
        tuple.third = grade;
        ArrayList<Tuple<Student, String, Double>> actual = examScores.get(teacher);
        if(actual == null)
            actual = new ArrayList<>();
        actual.add(tuple);
        examScores.put(teacher, actual);
    }

    public void addPartialScore(Assistant assistant, Student student, String courseName, Double grade) {
        Tuple<Student, String, Double> tuple = new Tuple<>();
        tuple.second = courseName;
        tuple.first = student;
        tuple.third = grade;
        ArrayList<Tuple<Student, String, Double>> actual = partialScores.get(assistant);
        if(actual == null)
            actual = new ArrayList<>();
        actual.add(tuple);
        partialScores.put(assistant, actual);
    }
    @Override
    public void visit(Assistant assistant) {
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        ArrayList<Tuple<Student, String, Double>> tuples = partialScores.get(assistant);
            for(Tuple<Student, String, Double> tuple : tuples) {
                for(Course course : courses)
                    if(course.getName().equals(tuple.second)) {
                        TreeSet<Grade> grades = course.getGrades();
                        boolean found = false;
                        for(Grade grade : grades) {
                            if(grade.getStudent().getFirstName().equals(tuple.first.getFirstName()) && grade.getStudent().getLastName().equals(tuple.first.getLastName())) {
                                if(grade.getPartialScore() != tuple.third) {
                                    grade.setPartialScore(tuple.third);
                                    found = true;
                                    grade.getStudent().notifyObservers(grade);
                                    break;
                                }
                            }
                        }
                        if(!found) {
                            Grade grade = new Grade(tuple.third, 0.0d, tuple.first, tuple.second);
                            grades.add(grade);
                            grade.getStudent().notifyObservers(grade);
                        }
                    }
        }
    }

    @Override
    public void visit(Teacher teacher) {
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        ArrayList<Tuple<Student, String, Double>> tuples = examScores.get(teacher);
            for(Tuple<Student, String, Double> tuple : tuples) {
                for (Course course : courses)
                    if (course.getName().equals(tuple.second)) {
                        TreeSet<Grade> grades = course.getGrades();
                        boolean found = false;
                        for (Grade grade : grades) {
                            if (grade.getStudent().getFirstName().equals(tuple.first.getFirstName()) && grade.getStudent().getLastName().equals(tuple.first.getLastName())) {
                                if (grade.getExamScore() != tuple.third) {
                                    grade.setExamScore(tuple.third);
                                    grade.getStudent().notifyObservers(grade);
                                    System.out.println(grade.getStudent());
                                    System.out.println(grade.getStudent().getFather());
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (!found) {
                            Grade grade = new Grade(0.0d, tuple.third, tuple.first, tuple.second);
                            grades.add(grade);
                            grade.getStudent().notifyObservers(grade);
                        }
                    }
            }
    }


}
