package university;

import university.user.Assistant;
import university.user.Student;

import java.util.Comparator;
import java.util.TreeSet;

class ComparatorStudent implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getFirstName().equals(o2.getFirstName()))
            return o1.getLastName().compareTo(o2.getLastName());
        return o1.getFirstName().compareTo(o2.getFirstName());
    }
}

public class Group extends TreeSet<Student> {
    private String ID;
    private Assistant assistant;
    private Comparator<Student> comp;

    public Group(String ID, Assistant assistant, Comparator<Student> comp) {
        super(comp);
        this.ID = ID;
        this.assistant = assistant;
        this.comp = comp;
    }

    public Group(String ID, Assistant assistant) {
        super(new ComparatorStudent());
        this.ID = ID;
        this.assistant = assistant;
        this.comp = null;
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
