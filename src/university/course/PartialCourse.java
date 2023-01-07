package university.course;

import university.Grade;
import university.user.Student;
import university.user.Teacher;

import java.util.ArrayList;

public class PartialCourse extends Course{
    public PartialCourse(PartialCourseBuilder builder) {
        super(builder);
    }
    public static class PartialCourseBuilder extends CourseBuilder {
        public PartialCourseBuilder(String courseName, Teacher teacher, int creditsPoints) {
            super(courseName, teacher, creditsPoints);
        }

        @Override
        public Course build() {
            return new PartialCourse(this);
        }
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduatedStudents = new ArrayList<Student>();
        ArrayList<Student> students = getAllStudents();
        for(Student student : students) {
            Grade grade = getGrade(student);
            if(grade == null)
                continue;
            if(grade.getTotal() >= 5)
                graduatedStudents.add(student);
        }
        return graduatedStudents;
    }
}
