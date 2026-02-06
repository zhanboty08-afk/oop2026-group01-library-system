package org.example.app.notifications;

public final class NotificationCenter {

    private static final NotificationCenter INSTANCE = new NotificationCenter();

    private NotificationCenter() {}

    public static NotificationCenter getInstance() {
        return INSTANCE;
    }

    public void notifyUser(long userId, String message) {
        System.out.println("[NOTIFY userId=" + userId + "]: " + message);
    }
}

