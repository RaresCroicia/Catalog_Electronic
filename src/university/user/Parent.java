package university.user;

import GUI.pages.ParentPage;
import tools.Notification;
import tools.Observer;

public class Parent extends User implements Observer {
    public ParentPage page;
    public void setPage(ParentPage page) {
        this.page = page;
    }

    public Parent(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void update(Notification notification) {
        if(page != null)
            page.setNotificari(notification.toString());
    }
}
