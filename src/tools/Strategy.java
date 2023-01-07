package tools;

import university.Grade;
import university.user.Student;

import java.util.ArrayList;
import java.util.HashMap;

public interface Strategy {
    public Student calculate(ArrayList<Student> students, HashMap<Student, Grade> grades);
}
