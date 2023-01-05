import university.Catalog;
import university.Grade;
import university.user.User;
import university.user.UserFactory;

import static university.user.UserFactory.getUser;

public class Test {

    public void testUser() {
        User user1 = getUser(UserFactory.UserType.Student, "Rares", "Beleaua");
        System.out.println(user1);
    }

    public void testGrade() {
        Grade grade = new Grade();
        grade.setExamScore(2.5);
        grade.setPartialScore(3.5);
        System.out.println(grade.getTotal());
    }

    public void testCatalog() {
        System.out.println(Catalog.getCatalog());
        System.out.println(Catalog.getCatalog()); // testam sa fie aceeasi adresa a obiectului
    }

    public static void main(String[] args) {
        Test obj = new Test();
        obj.testUser();
        obj.testCatalog();
        obj.testGrade();
    }
}