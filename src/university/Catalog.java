package university;

import tools.ScoreVisitor;
import university.course.Course;

import java.util.ArrayList;

public class Catalog {
    private static Catalog obj = null;
    public ScoreVisitor scoreVisitor;
    private ArrayList<Course> courses;
    private Catalog() {
        courses = new ArrayList<>();
        scoreVisitor = new ScoreVisitor();
    }
    public static Catalog getCatalog() {
        if(obj == null)
            obj = new Catalog();
        return obj;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

}
