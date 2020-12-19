package id.absent.app.model;

import java.util.Date;

public class AbsentIn {
    private long startDate;
    private User user;

    public AbsentIn(User user) {
        this.user = user;
    }

    public String getUserByName() {
        return this.user.getName();
    }

    public String getUserByNim() {
        return this.user.getNim();
    }

    public void setStartDate() {
        this.startDate = new Date().getTime();
    }

    public long getStartDate() {
        return this.startDate;
    }

}
