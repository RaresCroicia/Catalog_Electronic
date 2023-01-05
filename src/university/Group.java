package university;

import university.user.Assistant;
import university.user.Student;

import java.util.Comparator;
import java.util.TreeSet;

public class Group extends TreeSet<Student> {
    private String ID;
    private Assistant assistant;
    private Comparator<Student> comparator;

    public Group(String ID, Assistant assistant, Comparator<Student> comparator) {
        this.ID = ID;
        this.assistant = assistant;
        this.comparator = comparator;
    }

    public Group(String ID, Assistant assistant) {
        this.ID = ID;
        this.assistant = assistant;
        this.comparator = null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
}
