package id.absent.app.controller;

import id.absent.app.model.Absent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ValidAbsent implements CheckAbsent, ConvertDate {

    private List<Absent> listAbsent;
    private long epochLateDay = 1608379662; // hard code is not good

    public ValidAbsent(List<Absent> absent) {
        this.listAbsent = absent;
    }

    private int checkIn(Absent absent) {
        if (epochLateDay < absent.getStartDate()) {
            return 1;
        } else if (epochLateDay == absent.getStartDate()) {
            return 2;
        }
        return 3;
    }

    @Override
    public String checkAbsent(Absent absent) {
        if ( checkIn(absent) == 1) {
            return "on time";
        } else if (checkIn(absent) == 2) {
            return "in time";
        }
        return "late";
    }

    @Override
    public String epochToDate(long epoch) {
        Date date = new Date(epoch);
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        format.setTimeZone(TimeZone.getTimeZone("Indonesia/Surabaya"));
        String formatted = format.format(date);
        return formatted;
    }

    public void showAbsentIn() {
        for (Absent absent : listAbsent) {
            System.out.println(checkAbsent(absent) + " name " + absent.getUserByName() + " time " + epochToDate(absent.getStartDate()));
        }
    }
}
