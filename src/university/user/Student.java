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

    public void setFather(Parent father) {
        this.father = father;
    }

    public void setMother(Parent mother) {
        this.mother = mother;
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
        for(Observer observer : observers) {
            observer.update(new Notification("Copilul dumneavoastra a primit nota " + grade.getTotal() + " la cursul de " + grade.getCourse()));
        }
    }
}
