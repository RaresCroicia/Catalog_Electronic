import GUI.pages.AssistantPage;
import GUI.pages.ParentPage;
import GUI.pages.StudentPage;
import GUI.pages.TeacherPage;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import tools.BestExamScore;
import tools.BestPartialScore;
import tools.BestTotalScore;
import tools.Strategy;
import university.Catalog;
import university.Grade;
import university.Group;
import university.course.Course;
import university.course.FullCourse;
import university.course.PartialCourse;
import university.user.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

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

    public void parseThings() {
        File input = new File("catalog.json");
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray courses = fileObject.get("courses").getAsJsonArray();
            for (JsonElement course : courses) {
                JsonObject courseJsonObject = course.getAsJsonObject();

                // Adaugam datele despre curs

                // Tip curs (1 = partial ----- 2 = full)
                int partialSauFull = courseJsonObject.get("type").getAsString().equals("PartialCourse") ? 1 : 2;

                // Strategie curs
                String strategyString = courseJsonObject.get("strategy").getAsString();
                Strategy strategy = null;
                switch (strategyString) {
                    case "BestPartialScore":
                        strategy = new BestPartialScore();
                        break;
                    case "BestExamScore":
                        strategy = new BestExamScore();
                        break;
                    case "BestTotalScore":
                        strategy = new BestTotalScore();
                        break;
                }

                // Nume curs
                String courseName = courseJsonObject.get("name").getAsString();

                // Profesor curs
                Teacher teacher;
                {
                    JsonElement teacherJson = courseJsonObject.get("teacher");
                    JsonObject teacherJsonObject = teacherJson.getAsJsonObject();
                    String firstName = teacherJsonObject.get("firstName").getAsString();
                    String lastName = teacherJsonObject.get("lastName").getAsString();
                    teacher = new Teacher(firstName, lastName);
                }
                // Adaug asistenti
                Set<Assistant> assistants = new HashSet<>();
                {
                    JsonArray assistantsJson = courseJsonObject.get("assistants").getAsJsonArray();
                    for (JsonElement assistantJson : assistantsJson) {
                        JsonObject assistantJsonObject = assistantJson.getAsJsonObject();
                        String assistantFirstName = assistantJsonObject.get("firstName").getAsString();
                        String assistantLastName = assistantJsonObject.get("lastName").getAsString();
                        Assistant assistant = new Assistant(assistantFirstName, assistantLastName);
                        assistants.add(assistant);
                    }
                }
                //Adaug grupele
                HashMap<String, Group> groups = new HashMap<>();
                {
                    JsonArray groupsJson = courseJsonObject.get("groups").getAsJsonArray();
                    for (JsonElement groupJson : groupsJson) {
                        Group group;
                        JsonObject groupJsonObject = groupJson.getAsJsonObject();

                        String ID = groupJsonObject.get("ID").getAsString(); // <----------------------- ID
                        JsonElement assistantJson = groupJsonObject.get("assistant");
                        JsonObject assistantJsonObject = assistantJson.getAsJsonObject();
                        String assFirstName = assistantJsonObject.get("firstName").getAsString();
                        String assLastName = assistantJsonObject.get("lastName").getAsString();
                        Assistant assistant = new Assistant(assFirstName, assLastName); // <------------- Assistant
                        group = new Group(ID, assistant); // <-------------------- Empty group, will populate next

                        JsonArray studentsJson = groupJsonObject.get("students").getAsJsonArray();
                        for (JsonElement studentJson : studentsJson) {
                            JsonObject studentJsonObject = studentJson.getAsJsonObject();
                            String firstName = studentJsonObject.get("firstName").getAsString();
                            String lastName = studentJsonObject.get("lastName").getAsString();
                            Student student = new Student(firstName, lastName);

                            JsonElement motherJson = studentJsonObject.get("mother");
                            if(motherJson != null) {
                                JsonObject motherJsonObject = motherJson.getAsJsonObject();
                                String motherFirstName = motherJsonObject.get("firstName").getAsString();
                                String motherLastName = motherJsonObject.get("lastName").getAsString();
                                Parent mother = new Parent(motherFirstName, motherLastName);
                                student.setMother(mother);
                            }

                            JsonElement fatherJson = studentJsonObject.get("father");
                            if(fatherJson != null) {
                                JsonObject fatherJsonObject = fatherJson.getAsJsonObject();
                                String fatherFirstName = fatherJsonObject.get("firstName").getAsString();
                                String fatherLastName = fatherJsonObject.get("lastName").getAsString();
                                Parent father = new Parent(fatherFirstName, fatherLastName);
                                student.setFather(father);
                            }
                            group.add(student);
                        }
                        groups.put(group.getID(), group);
                    }
                }

                // Adaug cursul in catalog
                if(partialSauFull == 1) {
                    Course curs = new PartialCourse.PartialCourseBuilder(courseName, teacher, 5).grades(new TreeSet<Grade>()).groups(groups).assistants(assistants).strategy(strategy).build();
                    Catalog.getCatalog().addCourse(curs);
                }
                if(partialSauFull == 2) {
                    Course curs = new FullCourse.FullCourseBuilder(courseName, teacher, 5).grades(new TreeSet<Grade>()).groups(groups).assistants(assistants).strategy(strategy).build();
                    Catalog.getCatalog().addCourse(curs);
                }
            }

            JsonArray examScoresJson = fileObject.get("examScores").getAsJsonArray();
            for (JsonElement examScoreJson : examScoresJson) {
                JsonObject examScoreJsonObject = examScoreJson.getAsJsonObject();
                JsonObject teacherJsonObject = examScoreJsonObject.get("teacher").getAsJsonObject();
                String teacherFirstName = teacherJsonObject.get("firstName").getAsString();
                String teacherLastName = teacherJsonObject.get("lastName").getAsString();
                Teacher teacher = Catalog.getCatalog().scoreVisitor.teacherExists(teacherFirstName, teacherLastName);
                JsonObject studentJsonObject = examScoreJsonObject.get("student").getAsJsonObject();
                String studentFirstName = studentJsonObject.get("firstName").getAsString();
                String studentLastName = studentJsonObject.get("lastName").getAsString();
                Student student = new Student(studentFirstName, studentLastName);
                String courseName = examScoreJsonObject.get("course").getAsString();
                Double grade = examScoreJsonObject.get("grade").getAsDouble();
                if(teacher == null) {
                    ArrayList<Course> coursesTemp = Catalog.getCatalog().getCourses();
                    for(Course course : coursesTemp)
                        if(course.getName().equals(courseName))
                            teacher = course.getTeacher();
                }
                Catalog.getCatalog().scoreVisitor.addExamScore(teacher, student, courseName, grade);
            }

            JsonArray partialScoresJson = fileObject.get("partialScores").getAsJsonArray();
            for (JsonElement partialScoreJson : partialScoresJson) {
                JsonObject partialScoreJsonObject = partialScoreJson.getAsJsonObject();
                JsonObject assistantJsonObject = partialScoreJsonObject.get("assistant").getAsJsonObject();
                String assistantFirstName = assistantJsonObject.get("firstName").getAsString();
                String assistantLastName = assistantJsonObject.get("lastName").getAsString();
                Assistant assistant = Catalog.getCatalog().scoreVisitor.assistantExists(assistantFirstName, assistantLastName);
                JsonObject studentJsonObject = partialScoreJsonObject.get("student").getAsJsonObject();
                String studentFirstName = studentJsonObject.get("firstName").getAsString();
                String studentLastName = studentJsonObject.get("lastName").getAsString();
                Student student = new Student(studentFirstName, studentLastName);
                String courseName = partialScoreJsonObject.get("course").getAsString();
                Double grade = partialScoreJsonObject.get("grade").getAsDouble();
                if(assistant == null) {
                    ArrayList<Course> coursesTemp = Catalog.getCatalog().getCourses();
                    for(Course course : coursesTemp) {
                        if(course.getName().equals(courseName))
                            assistant = course.getAssistant(student);
                    }
                }
                Catalog.getCatalog().scoreVisitor.addPartialScore(assistant, student, courseName, grade);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Test obj = new Test();
        // Adaugam cursurile in catalog si notele in dictionare
        obj.parseThings();

        // Pagina studentei Laura Ursu
        ArrayList<Course> courses = Catalog.getCatalog().getCourses();
        Course curs = courses.get(1);
        ArrayList<Student> students = curs.getAllStudents();
        Student student = null;
        Parent parent = null;
        Teacher teacher = null;
        Assistant assistant = null;
        for(Student studentIt : students) {
            if(studentIt.getFirstName().equals("Gigel") && studentIt.getLastName().equals("Frone")) {
                student = studentIt;
                parent = student.getFather();
                teacher = curs.getTeacher();
                assistant = curs.getAssistant(student);
                break;
            }
        }


        StudentPage studentPage = new StudentPage(student);
        ParentPage parentPage = new ParentPage(parent);
        TeacherPage teacherPage = new TeacherPage(teacher);
        AssistantPage assistantPage = new AssistantPage(assistant);

    }
}