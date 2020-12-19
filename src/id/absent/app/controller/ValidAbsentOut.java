package id.absent.app.controller;

import id.absent.app.model.AbsentOut;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ValidAbsentOut implements CheckAbsentOut, ConvertDate {

    private List<AbsentOut> listAbsentOut;
    private long epochLateDay = 1608408045; // hard code is not good

    public ValidAbsentOut(List<AbsentOut> absentOut) {
        this.listAbsentOut = absentOut;
    }

    private int checkOut(AbsentOut absentOut) {
        if (epochLateDay > absentOut.getEndDate()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String checkAbsentOut(AbsentOut absentOut) {
        if ( checkOut(absentOut) > 1) {
            return "good student";
        }
        return "flee";

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

    public void showAbsentOut() {
        System.out.println("after absent");
        for (AbsentOut absentOut : listAbsentOut) {
            System.out.println(checkAbsentOut(absentOut) + " name " + absentOut.getUserByName() + " time " +
                    epochToDate(absentOut.getEndDate()));
        }
    }
}