package tools;

import university.Grade;
import university.user.Student;

import java.util.ArrayList;
import java.util.HashMap;

public class BestExamScore implements Strategy{

    @Override
    public Student calculate(ArrayList<Student> students, HashMap<Student, Grade> grades) {
        Double bestExamScore = 0d;
        Student bestStudent = null;
        for(Student student : students) {
            if(grades.get(student).getExamScore() > bestExamScore)
                bestStudent = student;
        }
        return bestStudent;
    }
}
