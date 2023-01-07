import tools.BestTotalScore;
import university.Catalog;
import university.Grade;
import university.Group;
import university.course.Course;
import university.course.PartialCourse;
import university.user.*;

import java.util.HashMap;
import java.util.Map;

import static university.user.UserFactory.getUser;

public class Test {

    public void testUser() {
        User user1 = getUser(UserFactory.UserType.Student, "Rares", "Beleaua");
        System.out.println(user1);
    }

    public void testGrade() {
        Grade grade = new Grade(2.5, 3.5, new Student("KYL", "DAS"), "Sport");
        grade.setExamScore(2.5);
        grade.setPartialScore(3.5);
        System.out.println(grade.getTotal());
    }

    public void testCatalog() {
        System.out.println(Catalog.getCatalog());
        System.out.println(Catalog.getCatalog()); // testam sa fie aceeasi adresa a obiectului
    }

    public void testCourse() {
        Course cursUso = new PartialCourse.PartialCourseBuilder("Uso", new Teacher("Razvan", "Deaconescu"), 6).build();
        System.out.println(cursUso);
        Student student = new Student("Rares", "Croicia");
        cursUso.addGroup(new Group("313CC", new Assistant("Andreia", "Ocanoaia")));
        cursUso.addStudent("313CC", student);
        Grade grade = new Grade(4d, 5d, student, "USO");
        student.addObserver(new Parent("Lidia", "Stroe"));
        cursUso.addGrade(grade);
        cursUso.addStudent("313CC", new Student("Codrut", "Ciulacu"));
        cursUso.addStudent("313CC", new Student("Adi", "Pana"));
        cursUso.addStudent("313CC", new Student("Ramona", "Mitran"));
        System.out.println(cursUso.getAllStudents());
        HashMap<Student, Grade> grades = cursUso.getAllStudentGrades();
        for(Map.Entry<Student, Grade> entry : grades.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println(cursUso.getGraduatedStudents());
        cursUso.setStrategy(new BestTotalScore());
        System.out.println(cursUso.getBestStudent());
    }

    public static void main(String[] args) {
        Test obj = new Test();
        obj.testUser();
        obj.testCatalog();
        obj.testGrade();
        obj.testCourse();
    }
}