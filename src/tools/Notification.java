package tools;

public class Notification {
    private String message;
    public Notification(String message) {
        this.message = message;
    }
    public String toString() {
        return "Ati primit urmatoarea notificare:\n" + message;
    }
}
