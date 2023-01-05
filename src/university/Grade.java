package university;

import university.user.Student;

public class Grade implements Comparable, Cloneable {

    private Double partialScore, examScore;
    private Student student;
    private String course;

    public Double getPartialScore() {
        return partialScore;
    }

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }

    public Double getExamScore() {
        return examScore;
    }

    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getTotal() {
        return partialScore + examScore;
    }
    @Override
    public int compareTo(Object o) {
            if(o == null)
                throw new NullPointerException();
            if(!(o instanceof Grade))
                throw new ClassCastException("Nu se poate compara!");
            Grade go = (Grade) o;
            if(go.getTotal() > this.getTotal())
                return 1;
            else if(go.getTotal() == this.getTotal())
                return 0;
            return -1;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
