package university.user;

public class UserFactory {
    public enum UserType {
        Assistant,
        Parent,
        Student,
        Teacher
    }

    public static User getUser(UserType userType, String firstName, String lastName) {
        switch(userType) {
            case Assistant:
                return new Assistant(firstName, lastName);
            case Parent:
                return new Parent(firstName, lastName);
            case Student:
                return new Student(firstName, lastName);
            case Teacher:
                return new Teacher(firstName, lastName);
        }
        throw new IllegalArgumentException("Nu exista tipul " + userType);
    }
}
