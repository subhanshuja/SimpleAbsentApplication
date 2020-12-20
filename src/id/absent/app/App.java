package id.absent.app;

import id.absent.app.model.Absent;
import id.absent.app.view.AbsentFrame;

import javax.swing.*;

public class App {

    public static void main(String[] args) {

        // comment line interface
        /** List<Absent> listUserAbsent = new ArrayList<Absent>();

        String[] listName = new String[] {"rikza", "ayu", "agus"};
        String[] listNim = new String[] {"H011", "H013", "H010"};

        int[] listEndDate = new int[] {1608408105, 1608408075, 1608408065};

        for (int index = 0; index < listName.length; index++) {

            User user = new User();
            user.setName(listName[index]);
            user.setNim(listNim[index]);

            AbsentIn userAbsent = new AbsentIn(user);
            userAbsent.setStartDate();

            listUserAbsent.add(userAbsent);
        }

        listUserAbsent.forEach((userIn) -> System.out.println(userIn.getUserByName()+" "+userIn.getStartDate()));


        ValidAbsentIn validAbsentIn = new ValidAbsentIn(listUserAbsent);
        validAbsentIn.showAbsentIn();

        for (int index = 0; index < listEndDate.length; index++) {

            User user = new User();
            user.setName(listName[index]);
            user.setNim(listNim[index]);

            AbsentOut absentOut = new AbsentOut(user);
            absentOut.setEndDate(listEndDate[index]);

            listUserAbsent.add(absentOut);
        }

        listUserAbsentOut.forEach((userOut) -> System.out.println(userOut.getUserByName()+" "+userOut.getEndDate()));

        ValidAbsentOut validAbsentOut = new ValidAbsentOut(listUserAbsentOut);
        validAbsentOut.showAbsentOut(); **/

        // GUI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AbsentFrame().setVisible(true);
            }
        });
    }
}
