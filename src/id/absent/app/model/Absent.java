package id.absent.app.model;

import java.util.Date;

public class Absent {
    private long startDate;
    private long endDate;
    private User user;

    public Absent(User user) {
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

    public void setEndDate() {
        this.endDate = new Date().getTime();
    }

    public long getEndDate() {
        return this.endDate;
    }

}
