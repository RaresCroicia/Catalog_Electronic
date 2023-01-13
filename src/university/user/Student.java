package university.user;

import tools.Notification;
import tools.Observer;
import tools.Subject;
import university.Grade;

import java.util.ArrayList;

public class Student extends User implements Subject {
    Parent father, mother;
    private ArrayList<Observer> observers;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Parent getMother() {
        return mother;
    }

    public Parent getFather() {
        return father;
    }
    public void setFather(Parent father) {
        this.father = father;
        addObserver(father);
    }

    public void setMother(Parent mother) {
        this.mother = mother;
        addObserver(mother);
    }

    @Override
    public void addObserver(Observer observer) {
        if(observers == null)
            observers = new ArrayList<>();
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Grade grade) {
        if(observers == null)
            return;
        for(Observer observer : observers) {
            observer.update(new Notification("Notele actualizate ale copilului dumneavoastra la cursul de " + grade.getCourse() + ":\nNota pe parcurs:  " + grade.getPartialScore() + "\nNota in examen: " + grade.getExamScore()));
        }
    }
}
