package it.pn.frontend.e2e.model.singleton;

import it.pn.frontend.e2e.rest.RestContact;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationSingleton {

    private String iun;
    private String notificationRequestId;
    private static NotificationSingleton instance;

    private void NotificationSingleton(){
    }

    public static synchronized NotificationSingleton getInstance() {
        if (instance == null) {
            instance = new NotificationSingleton();
        }
        return instance;
    }



}
