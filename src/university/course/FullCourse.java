package university.course;

import university.Grade;
import university.user.Student;
import university.user.Teacher;

import java.util.ArrayList;

public class FullCourse extends Course{

    public FullCourse(FullCourseBuilder builder) {
        super(builder);
    }
    public static class FullCourseBuilder extends CourseBuilder {
        public FullCourseBuilder(String courseName, Teacher teacher, int creditsPoints) {
            super(courseName, teacher, creditsPoints);
        }

        @Override
        public Course build() {
            return new FullCourse(this);
        }
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduatedStudents = new ArrayList<>();
        ArrayList<Student> students = getAllStudents();
        for(Student student : students) {
            Grade grade = getGrade(student);
            if(grade == null)
                continue;
            if(grade.getPartialScore() >= 3 && grade.getExamScore() >= 2)
                graduatedStudents.add(student);
        }
        return graduatedStudents;
    }
}