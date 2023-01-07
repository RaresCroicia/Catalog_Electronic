package tools;

import university.Grade;
import university.user.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class BestTotalScore implements Strategy{

    @Override
    public Student calculate(ArrayList<Student> students, HashMap<Student, Grade> grades) {
        Double bestTotalScore = 0d;
        Student bestStudent = null;
        for(Student student : students) {
            if(grades.get(student) == null)
                continue;
            if(grades.get(student).getTotal() > bestTotalScore)
                bestStudent = student;
        }
        return bestStudent;
    }
}
