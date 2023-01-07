package university.course;

import tools.Strategy;
import university.Grade;
import university.Group;
import university.user.Assistant;
import university.user.Student;
import university.user.Teacher;

import java.util.*;

public abstract class Course {
    private String name; // required
    private Teacher teacher; // required
    Set<Assistant> assistants;
    TreeSet<Grade> grades;
    HashMap<String, Group> groups;
    private int creditPoints; // required
    private Strategy strategy;

    protected Course(CourseBuilder builder) {
        this.name = builder.name;
        this.teacher = builder.teacher;
        this.creditPoints = builder.creditPoints;
        this.assistants = builder.assistants;
        this.grades = builder.grades;
        this.groups = builder.groups;
    }

    public abstract static class CourseBuilder {
        private final String name;
        private final Teacher teacher;
        private final int creditPoints;
        private Set<Assistant> assistants;
        private TreeSet<Grade> grades;
        private HashMap<String, Group> groups;

        public CourseBuilder(String name, Teacher teacher, int creditPoints) {
            this.name = name;
            this.teacher = teacher;
            this.creditPoints = creditPoints;
        }

        public CourseBuilder assistants(Set<Assistant> assistants){
            this.assistants = assistants;
            return this;
        }

        public CourseBuilder grades(TreeSet<Grade> grades) {
            this.grades = grades;
            return this;
        }

        public CourseBuilder groups(HashMap<String, Group> groups) {
            this.groups = groups;
            return this;
        }

        abstract Course build();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(Set<Assistant> assistants) {
        this.assistants = assistants;
    }



    public TreeSet<Grade> getGrades() {
        return grades;
    }

    public void setGrades(TreeSet<Grade> grades) {
        this.grades = grades;
    }

    public HashMap<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(HashMap<String, Group> groups) {
        this.groups = groups;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    public void addAssistant(String ID, Assistant assistant) {
        Group group = groups.get(ID);
        group.setAssistant(assistant);
        if(!assistants.contains(assistant))
            assistants.add(assistant);
        groups.put(ID, group);
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void addStudent(String ID, Student student) {
        Group group = groups.get(ID);
        group.add(student);
        groups.put(ID, group);
    }

    public void addGroup(Group group) {
        if(groups == null)
            groups = new HashMap<>();
        groups.put(group.getID(), group);
    }

    public void addGroup(String ID, Assistant assistant) {
        Group group = new Group(ID, assistant);
        groups.put(ID, group);
    }

    public void addGroup(String ID, Assistant assistant, Comparator<Student> comp) {
        Group group = new Group(ID, assistant, comp);
        groups.put(ID, group);
    }

    public Grade getGrade(Student student) {
        for(Grade grade : grades) {
            if(grade.getStudent().equals(student))
                return grade;
        }
        return null;
    }

    public void addGrade(Grade grade) {
        if(grades == null)
            grades = new TreeSet<>();
        grades.add(grade);
        grade.getStudent().notifyObservers(grade);
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        for(Group group : groups.values()) {
            for(Student student : group)
                students.add(student);
        }
        return students;
    }
    public HashMap<Student, Grade> getAllStudentGrades() {
        HashMap<Student, Grade> studentGrades = new HashMap<Student, Grade>();
        ArrayList<Student> students = getAllStudents();
        for(Student student : students) {
            studentGrades.put(student, getGrade(student));
        }
        return studentGrades;
    }
    public abstract ArrayList<Student> getGraduatedStudents();
    public String toString() {
        return name + " " + teacher + " " + creditPoints;
    }

    public Student getBestStudent() {
        return strategy.calculate(getAllStudents(), getAllStudentGrades());
    }

}
