package id.absent.app.controller;

import id.absent.app.model.AbsentIn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ValidAbsentIn implements CheckAbsentIn, ConvertDate {

    private List<AbsentIn> listAbsentIn;
    private long epochLateDay = 1608379662; // hard code is not good

    public ValidAbsentIn(List<AbsentIn> absentIn) {
        this.listAbsentIn = absentIn;
    }

    private int checkIn(AbsentIn absentIn) {
        if (epochLateDay < absentIn.getStartDate()) {
            return 1;
        } else if (epochLateDay == absentIn.getStartDate()) {
            return 2;
        }
        return 3;
    }

    @Override
    public String checkAbsentIn(AbsentIn absentIn) {
        if ( checkIn(absentIn) == 1) {
            return "on time";
        } else if (checkIn(absentIn) == 2) {
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
        for (AbsentIn absentIn : listAbsentIn) {
            System.out.println(checkAbsentIn(absentIn) + " name " + absentIn.getUserByName() + " time " + epochToDate(absentIn.getStartDate()));
        }
    }
}
