package university.user;

import GUI.pages.ParentPage;
import tools.Notification;
import tools.Observer;

public class Parent extends User implements Observer {
    private ParentPage page;
    public void setPage(ParentPage page) {
        this.page = page;
    }

    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void update(Notification notification) {
        System.out.println(notification);
        page.setNotificari(notification.toString());
    }
}
