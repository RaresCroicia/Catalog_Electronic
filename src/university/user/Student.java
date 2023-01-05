package university.user;

public class Student extends User{
    Parent father, mother;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public void setFather(Parent father) {
        this.father = father;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
    }
}
