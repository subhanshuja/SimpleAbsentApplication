package id.absent.app.model;

import java.util.Date;

public class AbsentOut {
    private long endDate;
    private User user;
    private Date date;

    public AbsentOut(User user) {
        this.user = user;
        this.date = new Date();
    }

    public String getUserByName() {
        return this.user.getName();
    }

    public String getUserByNim() {
        return this.user.getNim();
    }

    public void setEndDate(long epochDate) {
        this.endDate = epochDate;
    }

    public long getEndDate() {
        return this.endDate;
    }
}

