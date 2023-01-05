package university;

import university.course.Course;

import java.util.ArrayList;

public class Catalog {
    private static Catalog obj = null;
    private ArrayList<Course> courses;
    private Catalog() {
        courses = new ArrayList<>();
    }
    public static Catalog getCatalog() {
        if(obj == null)
            obj = new Catalog();
        return obj;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

}
